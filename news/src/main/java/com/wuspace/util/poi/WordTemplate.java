package com.wuspace.util.poi;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对docx文件中的文本及表格中的内容进行替换 --模板仅支持对 {key} 标签的替换
 * <br>(1)word模板注意页边距的问题，存在问题：比如页边距默认为3cm，画表格时，仍然可以通过
 * 拖拽，把表格边框拖动到看起来就像页边距只有1cm的样子，但是实际上此时页边距还是3cm，生成的
 * word报表的页边距还是会按照3cm来生成。解决办法，在word文件里，设置好页边距，如果需要表格
 * 两边页边距很窄，需要在word里设置页边距窄一点，而不是直接拖动表格边框来实现。
 */

public class WordTemplate {

    private CustomXWPFDocument document;

    public CustomXWPFDocument getDocument() {
        return document;
    }

    public void setDocument(CustomXWPFDocument document) {
        this.document = document;
    }

    /**
     * 初始化模板内容
     *
     * @param inputStream 模板的读取流(docx文件)
     * @throws IOException
     */
    public WordTemplate(InputStream inputStream) throws IOException {
        document = new CustomXWPFDocument(inputStream);
    }

    /**
     * 将处理后的内容写入到输出流中
     *
     * @param outputStream
     * @throws IOException
     */
    public void write(OutputStream outputStream) throws IOException {
        document.write(outputStream);
    }


    /**
     * 根据dataMap对word文件中的标签进行替换; <br><br>
     * ！！！！***需要注意dataMap的数据格式***！！！！ <br><br>
     * 对于需要替换的普通标签数据标签（不需要循环）-----必须在dataMap中存储一个key为parametersMap的map，
     * 来存储这些不需要循环生成的数据，比如：表头信息，日期，制表人等。 <br><br>
     * 对于需要循环生成的表格数据------key自定义，value为 --ArrayList&lt;Map&lt;String, String>>
     *
     * @param dataMap
     */
    public void replaceDocument(Map<String, Object> dataMap) {

        if (!dataMap.containsKey("parametersMap")) {
            System.out.println("数据源错误--数据源(parametersMap)缺失");
            return;
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> parametersMap = (Map<String, Object>) dataMap
                .get("parametersMap");

        // 所有对象（段落+表格）
        List<IBodyElement> bodyElements = document.getBodyElements();
        // 标记模板文件（段落+表格）总个数
        int templateBodySize = bodyElements.size();

        // 当前操作表格对象的索引
        int curT = 0;
        // 当前操作段落对象的索引
        int curP = 0;

        for (int a = 0; a < templateBodySize; a++) {
            IBodyElement body = bodyElements.get(a);
            // 处理表格
            if (BodyElementType.TABLE.equals(body.getElementType())) {
                XWPFTable table;

                List<XWPFTable> tables = body.getBody().getTables();
                table = tables.get(curT);
                if (table != null) {

                    // 处理表格
                    // 获取到模板表格第一行，用来判断表格类型
                    List<XWPFTableCell> tableCells = table.getRows().get(0).getTableCells();
                    // 表格中的所有文本
                    String tableText = table.getText();

                    if (tableText.indexOf("##{foreach") > -1) {
                        // 查找到##{foreach标签，该表格需要处理循环
                        if (tableCells.size() != 2
                                || tableCells.get(0).getText().indexOf("##{foreach") < 0
                                || tableCells.get(0).getText().trim().length() == 0) {
                            System.out
                                    .println("文档中第"
                                            + (curT + 1)
                                            + "个表格模板错误,模板表格第一行需要设置2个单元格，"
                                            + "第一个单元格存储表格类型(##{foreachTable}## 或者 ##{foreachTableRow}##)，第二个单元格定义数据源。");
                            return;
                        }

                        String tableType = tableCells.get(0).getText();
                        String dataSource = tableCells.get(1).getText();
                        System.out.println("读取到数据源：" + dataSource);
                        if (!dataMap.containsKey(dataSource)) {
                            System.out.println("文档中第" + (curT + 1) + "个表格模板数据源缺失");
                            return;
                        }

                        @SuppressWarnings("unchecked")
                        List<Map<String, Object>> tableDataList = (List<Map<String, Object>>) dataMap
                                .get(dataSource);
                        if ("##{foreachTable}##".equals(tableType)) {
                            // 循环生成表格
                            addTableInDocFooter(table, tableDataList, parametersMap, 1);

                        } else if ("##{foreachTableRow}##".equals(tableType)) {
                            // 循环生成表格内部的行
                            addTableInDocFooter(table, tableDataList, parametersMap, 2);
                        }

                    } else if (tableText.indexOf("{") > -1) {
                        // 没有查找到##{foreach标签，查找到了普通替换数据的{}标签，该表格只需要简单替换
                        addTableInDocFooter(table, null, parametersMap, 3);
                    } else {
                        // 没有查找到任何标签，该表格是一个静态表格，仅需要复制一个即可。
                        addTableInDocFooter(table, null, null, 0);
                    }
                    curT++;

                }
            }
            // 处理段落
            else if (BodyElementType.PARAGRAPH.equals(body.getElementType())) {
                // 获取到段落
                XWPFParagraph ph = body.getBody().getParagraphArray(curP);
                if (ph != null) {
                    // htmlText = htmlText+readParagraphX(ph);
                    addParagraphInDocFooter(ph, null, parametersMap, 0);

                    curP++;
                }
            }

        }
        // 处理完毕模板，删除文本中的模板内容
        for (int a = 0; a < templateBodySize; a++) {
            document.removeBodyElement(0);
        }

    }


    /**
     * 根据 模板表格 和 数据list 在word文档末尾生成表格
     *
     * @param templateTable 模板表格
     * @param list          循环数据集
     * @param parametersMap 不循环数据集
     * @param flag          (0为静态表格，1为表格整体循环，2为表格内部行循环，3为表格不循环仅简单替换标签即可)
     */
    public void addTableInDocFooter(XWPFTable templateTable, List<Map<String, Object>> list,
                                    Map<String, Object> parametersMap, int flag) {

        // 表格整体循环
        if (flag == 1) {
            for (Map<String, Object> map : list) {
                // 获取模板表格所有行
                List<XWPFTableRow> templateTableRows = templateTable.getRows();
                // 创建新表格,默认一行一列
                XWPFTable newCreateTable = document.createTable();
                for (int i = 1; i < templateTableRows.size(); i++) {
                    XWPFTableRow newCreateRow = newCreateTable.createRow();
                    // 复制模板行文本和样式到新行
                    copyTableRow(newCreateRow, templateTableRows.get(i));
                }
                // 移除多出来的第一行
                newCreateTable.removeRow(0);
                // 添加回车换行
                document.createParagraph();
                // 替换标签
                replaceTable(newCreateTable, map);
            }

        }
        // 表格表格内部行循环
        else if (flag == 2) {
            // 创建新表格,默认一行一列
            XWPFTable newCreateTable = document.createTable();
            // 获取模板表格所有行
            List<XWPFTableRow> TempTableRows = templateTable.getRows();
            // 标签行indexs
            int tagRowsIndex = 0;
            for (int i = 0, size = TempTableRows.size(); i < size; i++) {
                // 获取到表格行的第一个单元格
                String rowText = TempTableRows.get(i).getCell(0).getText();
                if (rowText.indexOf("##{foreachRows}##") > -1) {
                    tagRowsIndex = i;
                    break;
                }
            }

            /* 复制模板行和标签行之前的行 */
            for (int i = 1; i < tagRowsIndex; i++) {
                XWPFTableRow newCreateRow = newCreateTable.createRow();
                // 复制行
                copyTableRow(newCreateRow, TempTableRows.get(i));
                // 处理不循环标签的替换
                replaceTableRow(newCreateRow, parametersMap);
            }

            /* 循环生成模板行 */
            // 获取到模板行
            XWPFTableRow tempRow = TempTableRows.get(tagRowsIndex + 1);
            for (int i = 0; i < list.size(); i++) {
                XWPFTableRow newCreateRow = newCreateTable.createRow();
                // 复制模板行
                copyTableRow(newCreateRow, tempRow);
                // 处理标签替换
                replaceTableRow(newCreateRow, list.get(i));
            }

            /* 复制模板行和标签行之后的行 */
            for (int i = tagRowsIndex + 2; i < TempTableRows.size(); i++) {
                XWPFTableRow newCreateRow = newCreateTable.createRow();
                // 复制行
                copyTableRow(newCreateRow, TempTableRows.get(i));
                // 处理不循环标签的替换
                replaceTableRow(newCreateRow, parametersMap);
            }
            // 移除多出来的第一行
            newCreateTable.removeRow(0);
            // 添加回车换行
            document.createParagraph();

        } else if (flag == 3) {
            // 表格不循环仅简单替换标签
            // 获取模板表格所有行
            List<XWPFTableRow> templateTableRows = templateTable.getRows();
            // 创建新表格,默认一行一列
            XWPFTable newCreateTable = document.createTable();
            for (int i = 0; i < templateTableRows.size(); i++) {
                XWPFTableRow newCreateRow = newCreateTable.createRow();
                // 复制模板行文本和样式到新行
                copyTableRow(newCreateRow, templateTableRows.get(i));
            }
            // 移除多出来的第一行
            newCreateTable.removeRow(0);
            // 添加回车换行
            document.createParagraph();
            replaceTable(newCreateTable, parametersMap);

        } else if (flag == 0) {
            // 获取模板表格所有行
            List<XWPFTableRow> templateTableRows = templateTable.getRows();
            // 创建新表格,默认一行一列
            XWPFTable newCreateTable = document.createTable();
            for (int i = 0; i < templateTableRows.size(); i++) {
                XWPFTableRow newCreateRow = newCreateTable.createRow();
                // 复制模板行文本和样式到新行
                copyTableRow(newCreateRow, templateTableRows.get(i));
            }
            // 移除多出来的第一行
            newCreateTable.removeRow(0);
            // 添加回车换行
            document.createParagraph();
        }

    }


    /**
     * 根据 模板段落 和 数据 在文档末尾生成段落
     *
     * @param templateParagraph 模板段落
     * @param list              循环数据集
     * @param parametersMap     不循环数据集
     * @param flag              (0为不循环替换，1为循环替换)
     */
    public void addParagraphInDocFooter(XWPFParagraph templateParagraph,
                                        List<Map<String, String>> list, Map<String, Object> parametersMap, int flag) {

        if (flag == 0) {
            XWPFParagraph createParagraph = document.createParagraph();
            // 设置段落样式
            createParagraph.getCTP().setPPr(templateParagraph.getCTP().getPPr());
            // 移除原始内容
            for (int pos = 0; pos < createParagraph.getRuns().size(); pos++) {
                createParagraph.removeRun(pos);
            }
            // 添加Run标签
            for (XWPFRun s : templateParagraph.getRuns()) {
                XWPFRun targetRun = createParagraph.createRun();
                copyRun(targetRun, s);
            }

            replaceParagraph(createParagraph, parametersMap);

        } else if (flag == 1) {
            // 暂无实现
        }

    }


    /**
     * 根据map替换段落元素内的{**}标签
     *
     * @param xWPFParagraph
     * @param parametersMap
     */
    public void replaceParagraph(XWPFParagraph xWPFParagraph, Map<String, Object> parametersMap) {
        List<XWPFRun> runs = xWPFParagraph.getRuns();
        String xWPFParagraphText = xWPFParagraph.getText();
        String regEx = "\\{.+?\\}";
        Pattern pattern = Pattern.compile(regEx);
        // 正则匹配字符串{****}
        Matcher matcher = pattern.matcher(xWPFParagraphText);

        if (matcher.find()) {
            // 查找到有标签才执行替换
            // 标签开始run位置
            int beginRunIndex = xWPFParagraph.searchText("{", new PositionInParagraph()).getBeginRun();
            // 结束标签
            int endRunIndex = xWPFParagraph.searchText("}", new PositionInParagraph()).getEndRun();
            StringBuffer key = new StringBuffer();

            if (beginRunIndex == endRunIndex) {
                // {**}在一个run标签内
                XWPFRun beginRun = runs.get(beginRunIndex);
                String beginRunText = beginRun.text();

                int beginIndex = beginRunText.indexOf("{");
                int endIndex = beginRunText.indexOf("}");
                int length = beginRunText.length();

                if (beginIndex == 0 && endIndex == length - 1) {
                    // 该run标签只有{**}
                    XWPFRun insertNewRun = xWPFParagraph.insertNewRun(beginRunIndex);
                    insertNewRun.getCTR().setRPr(beginRun.getCTR().getRPr());
                    key.append(beginRunText, 1, endIndex);
                    Object paramValue = getValueByKey(key.toString(), parametersMap);
                    if (paramValue instanceof String) {
                        // 设置文本
                        insertNewRun.setText(paramValue.toString());
                    }
                    if (paramValue instanceof Map) {
                        // 替换为图片
                        replacePicture(xWPFParagraph, insertNewRun, String.valueOf(((Map) paramValue).get("pictureLocation")));
                    }
                    xWPFParagraph.removeRun(beginRunIndex + 1);

                } else {
                    // 该run标签为**{**}** 或者 **{**} 或者{**}**，替换key后，还需要加上原始key前后的文本
                    XWPFRun insertNewRun = xWPFParagraph.insertNewRun(beginRunIndex);
                    insertNewRun.getCTR().setRPr(beginRun.getCTR().getRPr());
                    // 设置文本
                    key.append(beginRunText, beginRunText.indexOf("{") + 1, beginRunText.indexOf("}"));
                    String textString = beginRunText.substring(0, beginIndex) + getValueByKey(key.toString(), parametersMap)
                            + beginRunText.substring(endIndex + 1);
                    insertNewRun.setText(textString);
                    xWPFParagraph.removeRun(beginRunIndex + 1);
                }

            } else {
                // {**}被分成多个run

                //先处理起始run标签,取得第一个{key}值
                XWPFRun beginRun = runs.get(beginRunIndex);
                String beginRunText = beginRun.text();
                int beginIndex = beginRunText.indexOf("{");
                if (beginRunText.length() > 1) {
                    key.append(beginRunText.substring(beginIndex + 1));
                }

                // 需要移除的run
                ArrayList<Integer> removeRunList = new ArrayList<>();

                // 处理中间的run
                for (int i = beginRunIndex + 1; i < endRunIndex; i++) {
                    XWPFRun run = runs.get(i);
                    String runText = run.text();
                    key.append(runText);
                    removeRunList.add(i);
                }

                // 获取endRun中的key值
                XWPFRun endRun = runs.get(endRunIndex);
                String endRunText = endRun.text();
                int endIndex = endRunText.indexOf("}");
                //run中**}或者**}**
                if (endRunText.length() > 1 && endIndex != 0) {
                    key.append(endRunText.substring(0, endIndex));
                }


                //*******************************************************************
                // 取得key值后替换标签

                // 先处理开始标签
                if (beginRunText.length() == 2) {
                    // run标签内文本{
                    XWPFRun insertNewRun = xWPFParagraph.insertNewRun(beginRunIndex);
                    insertNewRun.getCTR().setRPr(beginRun.getCTR().getRPr());
                    // 设置文本
                    insertNewRun.setText(getValueByKey(key.toString(), parametersMap).toString());
                    // 移除原始的run
                    xWPFParagraph.removeRun(beginRunIndex + 1);
                } else {
                    // 该run标签为**{**或者 {** ，替换key后，还需要加上原始key前的文本
                    XWPFRun insertNewRun = xWPFParagraph.insertNewRun(beginRunIndex);
                    insertNewRun.getCTR().setRPr(beginRun.getCTR().getRPr());
                    key.append(beginRunText, beginRunText.indexOf("{") + 1, beginRunText.indexOf("}"));
                    Object paramValue = getValueByKey(key.toString(), parametersMap);
                    if (paramValue instanceof String) {
                        // 设置文本
                        String textString = beginRunText.substring(0, beginIndex) + paramValue.toString()
                                + beginRunText.substring(endIndex + 1);
                        insertNewRun.setText(textString);
                    }
                    if (paramValue instanceof Map) {
                        // 替换为图片
                        replacePicture(xWPFParagraph, insertNewRun, String.valueOf(((Map) paramValue).get("pictureLocation")));
                    }
                    // 移除原始的run
                    xWPFParagraph.removeRun(beginRunIndex + 1);
                }

                // 处理结束标签
                if (endRunText.length() == 1) {
                    // run标签内文本只有}
                    XWPFRun insertNewRun = xWPFParagraph.insertNewRun(endRunIndex);
                    insertNewRun.getCTR().setRPr(endRun.getCTR().getRPr());
                    // 设置文本
                    insertNewRun.setText("");
                    // 移除原始的run
                    xWPFParagraph.removeRun(endRunIndex + 1);

                } else {
                    // 该run标签为**}**或者 }** 或者**}，替换key后，还需要加上原始key后的文本
                    XWPFRun insertNewRun = xWPFParagraph.insertNewRun(endRunIndex);
                    insertNewRun.getCTR().setRPr(endRun.getCTR().getRPr());
                    Object paramValue = getValueByKey(key.toString(), parametersMap);
                    if (paramValue instanceof String) {
                        // 设置文本
                        insertNewRun.setText(beginRunText.substring(0, beginRunText.indexOf("{")) + paramValue.toString());
                    }
                    if (paramValue instanceof Map) {
                        // 替换为图片
                        replacePicture(xWPFParagraph, insertNewRun, String.valueOf(((Map) paramValue).get("pictureLocation")));
                    }
                    // 移除原始的run
                    xWPFParagraph.removeRun(endRunIndex + 1);
                }

                // 处理中间的run标签
                for (int i = 0; i < removeRunList.size(); i++) {
                    // 原始run
                    XWPFRun xWPFRun = runs.get(removeRunList.get(i));
                    XWPFRun insertNewRun = xWPFParagraph.insertNewRun(removeRunList.get(i));
                    insertNewRun.getCTR().setRPr(xWPFRun.getCTR().getRPr());
                    insertNewRun.setText("");
                    // 移除原始的run
                    xWPFParagraph.removeRun(removeRunList.get(i) + 1);
                }

                // word表格一段文字中包含换行符("\r")，则换行
                List<XWPFTable> tables = document.getTables();
                if (CollectionUtils.isNotEmpty(tables)) {
                    tables.stream().forEach(table -> {
                        List<XWPFTableRow> rows = table.getRows();
                        if (rows != null && rows.size() > 1) {
                            for (int i = 1; i < rows.size(); i++) {
                                List<XWPFTableCell> tableCells = rows.get(i).getTableCells();
                                if (CollectionUtils.isNotEmpty(tableCells)) {
                                    tableCells.stream().forEach(tableCell -> {
                                        List<XWPFParagraph> paragraphs = tableCell.getParagraphs();
                                        if (CollectionUtils.isNotEmpty(paragraphs)) {
                                            for (XWPFParagraph paragraph : paragraphs) {
                                                newLine(paragraph);
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    });
                }

                // word段落一段文字中包含换行符("\r")，则换行
                newLine(xWPFParagraph);

            }// 处理${**}被分成多个run

            replaceParagraph(xWPFParagraph, parametersMap);

        }//if 有标签

    }

    /**
     * 根据段落中的换行符（"\r"）换行
     *
     * @param paragraph
     */
    private void newLine(XWPFParagraph paragraph) {
        List<XWPFRun> runList = paragraph.getRuns();
        if (CollectionUtils.isNotEmpty(runList)) {
            int runSize = paragraph.getRuns().size();
            int runSizeCount = runSize;
            String[] pTexts = paragraph.getText().split("\r");
            if (pTexts.length > 1) {
                XWPFRun oldRun = paragraph.getRuns().get(0);
                for (int j = 0; j < pTexts.length; j++) {
                    // 在段落原有的 XWPFRun 后新增 XWPFRun
                    XWPFRun run = paragraph.insertNewRun(runSizeCount++);
                    run.setFontFamily("仿宋");
                    run.setFontSize(oldRun.getFontSize());
                    run.setText(pTexts[j]);
                    run.addBreak();
                }

                // 删除旧的 XWPFRun
                if (runSizeCount > runSize) {
                    for (int j = 0; j < runSize; j++) {
                        paragraph.removeRun(0);
                    }
                }
            }
        }
    }


    /**
     * 复制表格行XWPFTableRow格式
     *
     * @param target 待修改格式的XWPFTableRow
     * @param source 模板XWPFTableRow
     */
    private void copyTableRow(XWPFTableRow target, XWPFTableRow source) {
        // 模板行的列数
        int tempRowCellSize = source.getTableCells().size();
        for (int i = 0; i < tempRowCellSize - 1; i++) {
            // 为新添加的行添加与模板表格对应行行相同个数的单元格
            target.addNewTableCell();
        }
        // 复制样式
        target.getCtRow().setTrPr(source.getCtRow().getTrPr());
        // 复制单元格
        for (int i = 0; i < target.getTableCells().size(); i++) {
            copyTableCell(target.getCell(i), source.getCell(i));
        }
    }


    /**
     * 复制单元格XWPFTableCell格式
     *
     * @param newTableCell      新创建的的单元格
     * @param templateTableCell 模板单元格
     */
    private void copyTableCell(XWPFTableCell newTableCell, XWPFTableCell templateTableCell) {
        // 列属性
        newTableCell.getCTTc().setTcPr(templateTableCell.getCTTc().getTcPr());
        // 删除目标 targetCell 所有文本段落
        for (int pos = 0; pos < newTableCell.getParagraphs().size(); pos++) {
            newTableCell.removeParagraph(pos);
        }
        // 添加新文本段落
        for (XWPFParagraph sp : templateTableCell.getParagraphs()) {
            XWPFParagraph targetP = newTableCell.addParagraph();
            copyParagraph(targetP, sp);
        }
    }

    /**
     * 根据参数parametersMap对表格的一行进行标签的替换
     *
     * @param tableRow      表格行
     * @param parametersMap 参数map
     */
    public void replaceTableRow(XWPFTableRow tableRow, Map<String, Object> parametersMap) {

        List<XWPFTableCell> tableCells = tableRow.getTableCells();
        for (XWPFTableCell xWPFTableCell : tableCells) {
            List<XWPFParagraph> paragraphs = xWPFTableCell.getParagraphs();
            for (XWPFParagraph xwpfParagraph : paragraphs) {

                replaceParagraph(xwpfParagraph, parametersMap);
            }
        }

    }


    /**
     * 根据map替换表格中的{key}标签
     *
     * @param xwpfTable
     * @param parametersMap
     */
    public void replaceTable(XWPFTable xwpfTable, Map<String, Object> parametersMap) {
        List<XWPFTableRow> rows = xwpfTable.getRows();
        for (XWPFTableRow xWPFTableRow : rows) {
            List<XWPFTableCell> tableCells = xWPFTableRow.getTableCells();
            for (XWPFTableCell xWPFTableCell : tableCells) {
                List<XWPFParagraph> paragraphs2 = xWPFTableCell.getParagraphs();
                for (XWPFParagraph xWPFParagraph : paragraphs2) {
                    replaceParagraph(xWPFParagraph, parametersMap);
                }
            }
        }

    }

    /**
     * 复制文本段落XWPFParagraph格式
     *
     * @param newParagraph      新创建的的段落
     * @param templateParagraph 模板段落
     */
    private void copyParagraph(XWPFParagraph newParagraph, XWPFParagraph templateParagraph) {
        // 设置段落样式
        newParagraph.getCTP().setPPr(templateParagraph.getCTP().getPPr());
        // 添加Run标签
        for (int pos = 0; pos < newParagraph.getRuns().size(); pos++) {
            newParagraph.removeRun(pos);

        }
        for (XWPFRun s : templateParagraph.getRuns()) {
            XWPFRun targetRun = newParagraph.createRun();
            copyRun(targetRun, s);
        }

    }

    /**
     * 复制文本节点run
     *
     * @param newRun      新创建的的文本节点
     * @param templateRun 模板文本节点
     */
    private void copyRun(XWPFRun newRun, XWPFRun templateRun) {
        newRun.getCTR().setRPr(templateRun.getCTR().getRPr());
        // 设置文本
        newRun.setText(templateRun.text());

    }


    private Object getValueByKey(String key, Map<String, Object> map) {
        Object returnValue = "";
        if (key != null) {
            try {
                returnValue = map.get(key) != null ? map.get(key) : "";
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("key:" + key + "***" + e);
                returnValue = "";
            }
        }
        return returnValue;
    }

    /**
     * 替换指定位置处的图片
     * 参考：https://www.cnblogs.com/chonghua/p/4148706.html
     *
     * @param paragraph
     * @param run
     * @param pictureLocation
     */
    public void replacePicture(XWPFParagraph paragraph, XWPFRun run, String pictureLocation) {

        if (StringUtils.isNotEmpty(pictureLocation) && !"null".equals(pictureLocation)) {
            try {
                // 图片输入流
                InputStream is;
                BufferedImage bufferedImage;
                File pic = new File(pictureLocation);

                if (pic.exists()) {
                    // 本地图片
                    bufferedImage = ImageIO.read(pic);
                    is = new FileInputStream(pic);
                } else {
                    // 线上图片
                    URL url = new URL(pictureLocation);
                    HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
                    httpUrl.connect();
                    bufferedImage = ImageIO.read(url);
                    is = new BufferedInputStream(httpUrl.getInputStream());
                }

                // 图片替换
                paragraph.setSpacingLineRule(LineSpacingRule.AUTO);
                String blipId = document.addPictureData(is, Document.PICTURE_TYPE_GIF);
                int id = document.getAllPackagePictures().size() + 1;
                CTInline ctinline = run.getCTR().addNewDrawing().addNewInline();
                document.createPicture(blipId, id, bufferedImage.getWidth(), bufferedImage.getHeight(), ctinline);

            } catch (IOException | InvalidFormatException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 替换文档段落中指定位置处的图片（$img 标识表示要插入的图片的位置）
     * 参考：https://www.cnblogs.com/chonghua/p/4148706.html
     *
     * @param pictureLocation
     * @return
     */
//    public void replaceParagraphPicture(String pictureLocation) {
//
//        if (StringUtils.isNotEmpty(pictureLocation)) {
//            try {
//                // 图片输入流
//                InputStream is;
//                BufferedImage bufferedImage;
//                File pic = new File(pictureLocation);
//                if (pic.exists()) { // 本地图片
//                    bufferedImage = ImageIO.read(pic);
//                    is = new FileInputStream(pic);
//                } else { // 线上图片
//                    URL url = new URL(pictureLocation);
//                    HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
//                    httpUrl.connect();
//                    bufferedImage = ImageIO.read(url);
//                    is = new BufferedInputStream(httpUrl.getInputStream());
//                }
//
//                List<XWPFParagraph> paragraphList = document.getParagraphs();
//                if (CollectionUtils.isNotEmpty(paragraphList)) {
//                    Iterator paragraphIterator = paragraphList.iterator();
//                    while (paragraphIterator.hasNext()) {
//                        XWPFParagraph paragraph = (XWPFParagraph) paragraphIterator.next();
//                        List<XWPFRun> runList = paragraph.getRuns();
//                        if (CollectionUtils.isNotEmpty(runList)) {
//                            int i = 0;
//                            Iterator runIterator = runList.iterator();
//                            while (runIterator.hasNext()) {
//                                XWPFRun xwpfRun = (XWPFRun) runIterator.next();
//                                if (StringUtils.isNotEmpty(xwpfRun.text()) && xwpfRun.text().contains("$img")) {
//                                    // 清空 $img 字符串
//                                    xwpfRun.setText("", i);
//
//                                    // 图片替换
//                                    paragraph.setSpacingLineRule(LineSpacingRule.AUTO);
//                                    CTInline ctinline = xwpfRun.getCTR().addNewDrawing().addNewInline();
//                                    String blipId = document.addPictureData(is, Document.PICTURE_TYPE_GIF);
//                                    int id = document.getAllPackagePictures().size() + 1;
//                                    document.createPicture(blipId, id, bufferedImage.getWidth(), bufferedImage.getHeight(), ctinline);
//                                }
//                            }
//                        }
//                    }
//                }
//
//            } catch (IOException | InvalidFormatException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    /**
     * 替换表格中指定位置处的图片（$img 标识表示要插入的图片的位置）
     * 参考：http://zyn010101.iteye.com/blog/1927081
     *
     * @param pictureLocation
     */
//    public void replaceTablePicture(String pictureLocation) {
//
//        if (StringUtils.isNotEmpty(pictureLocation)) {
//            try {
//                // 图片输入流
//                InputStream is;
//                BufferedImage bufferedImage;
//                File pic = new File(pictureLocation);
//                if (pic.exists()) { // 本地图片
//                    bufferedImage = ImageIO.read(pic);
//                    is = new FileInputStream(pic);
//                } else { // 线上图片
//                    URL url = new URL(pictureLocation);
//                    HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
//                    httpUrl.connect();
//                    bufferedImage = ImageIO.read(url);
//                    is = new BufferedInputStream(httpUrl.getInputStream());
//                }
//
//                Iterator<XWPFTable> it = document.getTablesIterator();
//                while (it.hasNext()) {
//                    XWPFTable table = it.next();
//                    List<XWPFTableRow> rows = table.getRows();
//                    for (XWPFTableRow row : rows) {
//                        List<XWPFTableCell> cells = row.getTableCells();
//                        for (XWPFTableCell cell : cells) {
//                            if (cell.getText().endsWith("$img")) {
//                                cell.removeParagraph(0);
//                                XWPFParagraph paragraph = cell.addParagraph();
//                                document.addPictureData(is, getPictureType(getExt(pictureLocation)));
//                                document.createPicture(paragraph, document.getAllPictures().size() - 1,
//                                        bufferedImage.getWidth(), bufferedImage.getHeight(), "");
//                            }
//
//                            List<XWPFParagraph> pars = cell.getParagraphs();
//                            for (XWPFParagraph par : pars) {
//                                List<XWPFRun> runs = par.getRuns();
//                                for (XWPFRun run : runs) {
//                                    run.removeBreak();
//                                }
//                            }
//                        }
//                    }
//                }
//
//            } catch (IOException | InvalidFormatException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    /**
     * 向 word 中文档插入图片（会创建一个新的段落，然后插入图片）
     *
     * @param pictureLocation
     * @param outputFile
     */
//    public void addPicture(String pictureLocation, String outputFile) {
//
//        try {
//            //读取word模板
//            XWPFParagraph picParagraph = document.createParagraph();
//            InputStream imageInputStream = new FileInputStream(new File(pictureLocation));
//            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(pictureLocation));
//            document.addPictureData(imageInputStream, getPictureType(getExt(pictureLocation)));
//            document.createPicture(picParagraph, document.getAllPictures().size() - 1,
//                    bufferedImage.getWidth(), bufferedImage.getHeight(), "");
//            OutputStream outputStream = new FileOutputStream(new File(outputFile));
//            document.write(outputStream);
//            outputStream.close();
//            imageInputStream.close();
//
//        } catch (InvalidFormatException | IOException e) {
//            e.printStackTrace();
//        }
//    }


    /**
     * 合并 word 文档
     * 参考： https://www.cnblogs.com/tianyublog/p/6957953.html
     *
     * @param inputStream
     */
    public void mergeDoc(InputStream inputStream) {
        if (inputStream != null) {
            try {
                OPCPackage opcPackage = OPCPackage.open(inputStream);
                CTBody body = new XWPFDocument(opcPackage).getDocument().getBody();
                appendBody(document.getDocument().getBody(), body);
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 在word文档后面追加一个word文档
     *
     * @param src
     * @param append
     */
    public void appendBody(CTBody src, CTBody append) {
        try {
            XmlOptions optionsOuter = new XmlOptions();
            optionsOuter.setSaveOuter();
            String appendString = append.xmlText(optionsOuter);
            String srcString = src.xmlText();
            String prefix = srcString.substring(0, srcString.indexOf(">") + 1);
            String mainPart = srcString.substring(srcString.indexOf(">") + 1, srcString.lastIndexOf("<"));
            String suffix = srcString.substring(srcString.lastIndexOf("<"));
            String addPart = appendString.substring(appendString.indexOf(">") + 1, appendString.lastIndexOf("<"));
            CTBody makeBody = CTBody.Factory.parse(prefix + mainPart + addPart + suffix);
            src.set(makeBody);
        } catch (XmlException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出word文档方法2（无表格循环）
     *
     * @param dataMap
     */
    public void processDocument(Map<String, Object> dataMap) {
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        processParagraphs(paragraphs, dataMap);
        processTables(dataMap);
    }

    /**
     * 处理段落
     */
    public void processParagraphs(List<XWPFParagraph> paragraphs, Map<String, Object> dataMap) {
        if (paragraphs != null && paragraphs.size() > 0) {
            for (XWPFParagraph paragraph : paragraphs) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    String text = run.getText(0);
                    if (text != null) {
                        boolean isSetText = false;
                        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                            String key = entry.getKey();
                            if (text.contains(key)) {
                                isSetText = true;
                                Object value = entry.getValue();
                                //文本替换
                                if (value instanceof String) {
                                    text = text.replace(key, value.toString());
                                }
                                //图片替换
                                else if (value instanceof Map) {
                                    text = text.replace(key, "");
                                    Map pic = (Map) value;
                                    Object content = pic.get("content");
//                                    int width = Integer.parseInt(pic.get("width").toString());
//                                    int height = Integer.parseInt(pic.get("height").toString());
//                                    int picType = getPictureType(pic.get("type").toString());
//                                    byte[] byteArray = (byte[]) pic.get("content");
//                                    ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteArray);
//                                    try {
//                                        int ind = document.addPicture(byteInputStream, picType);
//                                        doc.createPicture(ind, width, height, paragraph);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
                                    // 图片链接
                                    if (content instanceof String) {
                                        replacePicture(paragraph, run, String.valueOf(content));
                                    }
                                }
                            }
                        }
                        if (isSetText) {
                            run.setText(text, 0);
                        }
                    }
                }
            }
        }
    }

    //处理表格
    public void processTables(Map<String, Object> dataMap) {
        Iterator<XWPFTable> it = document.getTablesIterator();
        while (it.hasNext()) {
            XWPFTable table = it.next();
            List<XWPFTableRow> rows = table.getRows();
            for (XWPFTableRow row : rows) {
                List<XWPFTableCell> cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    List<XWPFParagraph> paragraphListTable = cell.getParagraphs();
                    processParagraphs(paragraphListTable, dataMap);
                }
            }
        }
    }

    /**
     * 获取文件名称
     *
     * @param pictureLocation
     * @return
     */
    private static String getFilename(String pictureLocation) {
        if (StringUtils.isNotEmpty(pictureLocation)) {
            return pictureLocation.substring(pictureLocation.lastIndexOf("/") + 1, pictureLocation.lastIndexOf("."));
        } else {
            return "";
        }
    }

    /**
     * 根据文件名获取拓展名
     *
     * @param fileName
     * @return
     */
    private static String getExt(String fileName) {
        if (StringUtils.isNotEmpty(fileName)) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    /**
     * 根据图片类型，取得对应的图片类型代码
     *
     * @param picType
     * @return int
     */
    private static int getPictureType(String picType) {
        int res = CustomXWPFDocument.PICTURE_TYPE_PICT;
        if (StringUtils.isNotEmpty(picType)) {
            if (picType.equalsIgnoreCase("png")) {
                res = CustomXWPFDocument.PICTURE_TYPE_PNG;
            } else if (picType.equalsIgnoreCase("dib")) {
                res = CustomXWPFDocument.PICTURE_TYPE_DIB;
            } else if (picType.equalsIgnoreCase("emf")) {
                res = CustomXWPFDocument.PICTURE_TYPE_EMF;
            } else if (picType.equalsIgnoreCase("jpg") || picType.equalsIgnoreCase("jpeg")) {
                res = CustomXWPFDocument.PICTURE_TYPE_JPEG;
            } else if (picType.equalsIgnoreCase("wmf")) {
                res = CustomXWPFDocument.PICTURE_TYPE_WMF;
            }
        }
        return res;
    }

}
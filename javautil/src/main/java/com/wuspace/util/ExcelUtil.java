package com.wuspace.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 解析 Excel 文件
 * <p>
 * 导入依赖:
 * compile "org.apache.poi:poi:3.8"
 * compile "org.apache.poi:poi-ooxml:3.8-beta3"
 * compile "org.apache.poi:poi-ooxml-schemas:3.8-beta4"
 * <p>
 * 使用:
 * List<Sheet> sheets = new ArrayList<>();
 * Map<Sheet, ArrayList[]> sheetMap = new HashMap<>();
 * ExcelUtil.loadExcel(file, sheets);
 * ExcelUtil.init(sheets, sheetMap);
 */
public class ExcelUtil {
    private static Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    private ExcelUtil() {
    }

    //读取excel文件，创建表格实例
    public static void loadExcel(File file, List<Sheet> sheets) {
        FileInputStream inStream;

        try {
            inStream = new FileInputStream(file);
            Workbook workBook = WorkbookFactory.create(inStream);
            for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
                sheets.add(workBook.getSheetAt(i));
            }
            if (inStream != null) {
                inStream.close();
            }
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (InvalidFormatException e) {
            log.error(e.getMessage());
        }
    }

    //读取表格输入流，创建表格实例
    public static void loadExcel(MultipartFile file, List<Sheet> sheets) {
        InputStream inputStream;

        try {
            inputStream = file.getInputStream();
            Workbook workBook = WorkbookFactory.create(inputStream);
            for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
                sheets.add(workBook.getSheetAt(i));
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (InvalidFormatException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    //获取单元格的值
    private static String getCellValue(Cell cell) {
        String cellValue = null;
        DataFormatter formatter = new DataFormatter();

        if (cell != null) {
            //判断单元格数据的类型，不同类型调用不同的方法
            switch (cell.getCellType()) {
                //数值类型
                case Cell.CELL_TYPE_NUMERIC:
                    //进一步判断 ，单元格格式是日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = formatter.formatCellValue(cell);
                    } else {
                        //数值
                        double value = cell.getNumericCellValue();
                        int intValue = (int) value;
                        cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                //判断单元格是公式格式，需要做一种特殊处理来得到相应的值
                case Cell.CELL_TYPE_FORMULA: {
                    try {
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    } catch (IllegalStateException e) {
                        cellValue = String.valueOf(cell.getRichStringCellValue());
                    }

                }
                break;
                case Cell.CELL_TYPE_BLANK:
                    cellValue = null;
                    break;
                case Cell.CELL_TYPE_ERROR:
                    cellValue = null;
                    break;
                default:
                    cellValue = cell.toString().trim();
                    break;
            }
        }

        return cellValue == null ? null : cellValue.trim();
    }

    //解析 Sheet
    private static ArrayList[] getSheetRowNumValue(Sheet sheet) {
        int rowNum = sheet.getLastRowNum() + 1;
        ArrayList[] result = new ArrayList[rowNum];

        for (int i = 0; i < rowNum; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                for (int k = 0; k < row.getLastCellNum(); k++) {
                    Cell cell = row.getCell(k);
                    result[i].add(getCellValue(cell));
                }
            }
        }

        return result;
    }

    //初始化表格中的每一行，并得到每一个单元格的值
    public static void init(List<Sheet> sheets, Map<Sheet, ArrayList[]> sheetMap) {
        for (Sheet sheet : sheets) {
            sheetMap.put(sheet, getSheetRowNumValue(sheet));
        }
    }
}

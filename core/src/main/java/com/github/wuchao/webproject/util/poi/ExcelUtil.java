package com.github.wuchao.webproject.util.poi;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;
import java.util.Random;

public class ExcelUtil {

    public static void replacePicture(String excelFilePath, Integer order, LocalDate now, Map<Integer, Map> users) {
        FileOutputStream fileOut = null;
        BufferedImage bufferImg = null;//图片

        try {

            // 创建一个工作薄
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(excelFilePath));

            //创建一个sheet
            XSSFSheet sheet = wb.getSheetAt(0);
            if (sheet != null) {

                // 最后一天
                LocalDate lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
                int rowNum = lastDayOfMonth.getDayOfMonth();
                int colNum = 15;

                // 第一行设置标题
                Row row = sheet.getRow(0);
                String title = now.getYear() + "年" + now.getMonth().getValue() + "月考勤记录";
                if (row != null) {
                    row.getCell(0).setCellValue(title);
                }

                int i;
                for (i = 1; i <= rowNum; i++) {

                    // 从第四行开始
                    int rowIndex = i + 2;

                    row = sheet.getRow(rowIndex);
                    if (row == null) {
                        row = sheet.createRow(rowIndex);

                        for (int k = 0; k < colNum; k++) {
                            row.createCell(k);
                        }

                    }

                    LocalDate tmpDate = LocalDate.of(lastDayOfMonth.getYear(), lastDayOfMonth.getMonth(), i);

                    row.getCell(0).setCellValue(i);
                    row.getCell(1).setCellValue(tmpDate.toString());


                    Random random = new Random();
                    int startIndex = 3;
                    int rightIndex = 7;
                    int dayOfWeek = tmpDate.getDayOfWeek().getValue();

                    if (dayOfWeek < 6) {
                        row.getCell(2).setCellValue("工作日");

                        if (users.size() >= 1) {
                            row.getCell(3).setCellValue("08:30");
                            row.getCell(5).setCellValue(dayOfWeek == 5 ? "17:30" : "21:00");
                        }
                        if (users.size() >= 2) {
                            row.getCell(7).setCellValue("08:30");
                            row.getCell(9).setCellValue(dayOfWeek == 5 ? "17:30" : "21:00");
                        }
                        if (users.size() >= 3) {
                            row.getCell(11).setCellValue("08:30");
                            row.getCell(13).setCellValue(dayOfWeek == 5 ? "17:30" : "21:00");
                        }


//                        while (startIndex < colNum && users.get(rightIndex) != null) {
//                            if (startIndex % 2 == 0 && startIndex < rightIndex) {
//
//                                // 利用 XSSFDrawing 将图片写入 EXCEL
//                                XSSFDrawing patriarch = sheet.createDrawingPatriarch();
//
//                                /**
//                                 * 该构造函数有8个参数
//                                 * 前四个参数是控制图片在单元格的位置，分别是图片距离单元格 left，top，right，bottom 的像素距离
//                                 * 后四个参数，前连个表示图片左上角所在的cellNum和 rowNum，后天个参数对应的表示图片右下角所在的cellNum和 rowNum，
//                                 * excel中的cellNum和rowNum的index都是从0开始的
//                                 */
//
//                                //图片一导出到单元格中
//                                XSSFClientAnchor anchor = new XSSFClientAnchor(1000, 200, 300, 200,
//                                        (short) (startIndex), rowIndex, (short) (startIndex + 1), rowIndex + 1);
//
//                                // 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
//                                ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
//
//                                //将图片读到BufferedImage
//                                bufferImg = ImageIO.read(new File((String) users.get(rightIndex).get(random.nextInt(3) + 1)));
//
//                                // 将图片写入流中
//                                ImageIO.write(bufferImg, "png", byteArrayOut);
//
//                                // 插入图片
//                                patriarch.createPicture(anchor, wb.addPicture(byteArrayOut
//                                        .toByteArray(), XSSFWorkbook.PICTURE_TYPE_PNG));
//
//
//                            }
//
//                            startIndex++;
//
//                            if (startIndex >= rightIndex) {
//                                rightIndex += 4;
//                            }
//
//                        }

                        for (int j = 3; j < colNum; j++) {

                        }

                    } else {
                        row.getCell(2).setCellValue("休息日");

                        XSSFCellStyle style = wb.createCellStyle();
                        style.setAlignment(HorizontalAlignment.CENTER);
                        style.setFillBackgroundColor(new XSSFColor(new Color(192, 192, 192)));
                        style.setBorderBottom(BorderStyle.THIN);
                        row.getCell(2).setCellStyle(style);
                    }


                }

                //生成的excel文件地址
                fileOut = new FileOutputStream(System.getProperty("user.dir") + File.separatorChar + title + order + ".xlsx");

                // 写入excel文件
                wb.write(fileOut);

            }

        } catch (IOException io) {
            io.printStackTrace();
            System.out.println("io erorr : " + io.getMessage());
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

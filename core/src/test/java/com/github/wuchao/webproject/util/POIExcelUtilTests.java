package com.github.wuchao.webproject.util;

import com.github.wuchao.webproject.util.poi.ExcelUtil;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class POIExcelUtilTests {

    private String signFilePathPrefix = System.getProperty("user.dir") + File.separatorChar + "files" + File.separatorChar + "sign" + File.separatorChar;
    private String excelFilePath = (signFilePathPrefix + "sign-table.xlsx").replace(File.separator, File.separator + File.separator);


    @Test
    public void testExcel() {
        LocalDate signDate = LocalDate.of(2018, 8, 1);

        testExcel1(excelFilePath, 1, signDate);
        testExcel2(excelFilePath, 2, signDate);
        testExcel3(excelFilePath, 3, signDate);
        testExcel4(excelFilePath, 4, signDate);
    }

    public void testExcel1(String excelFilePath, Integer order, LocalDate signDate) {

        Map<Integer, Map> users = new HashMap() {{
            put(7, null);
            put(11, null);
            put(15, null);
        }};

        List<String> userNamePrefixs = new ArrayList() {{
            add("wujx_");
            add("kongh_");
            add("renpy_");
        }};

        int[] index = {3};
        userNamePrefixs.forEach(userNamePrefix -> {
            Map<Integer, String> signs = new HashMap<>();
            for (int i = 1; i <= 3; i++) {
                signs.put(i, signFilePathPrefix + userNamePrefix + i + ".png");
            }
            index[0] += 4;
            users.put(index[0], signs);
        });

        ExcelUtil.replacePicture(excelFilePath, order, signDate, users);

    }

    public void testExcel2(String excelFilePath, Integer order, LocalDate signDate) {

        Map<Integer, Map> users = new HashMap() {{
            put(7, null);
        }};

        List<String> userNamePrefixs = new ArrayList() {{
            add("huly_");
        }};

        int[] index = {3};
        userNamePrefixs.forEach(userNamePrefix -> {
            Map<Integer, String> signs = new HashMap<>();
            for (int i = 1; i <= 3; i++) {
                signs.put(i, signFilePathPrefix + userNamePrefix + i + ".png");
            }
            index[0] += 4;
            users.put(index[0], signs);
        });

        ExcelUtil.replacePicture(excelFilePath, order, signDate, users);

    }

    public void testExcel3(String excelFilePath, Integer order, LocalDate signDate) {

        Map<Integer, Map> users = new HashMap() {{
            put(7, null);
            put(11, null);
            put(15, null);
        }};

        List<String> userNamePrefixs = new ArrayList() {{
            add("jinz_");
            add("huangq_");
            add("wuc_");
        }};

        int[] index = {3};
        userNamePrefixs.forEach(userNamePrefix -> {
            Map<Integer, String> signs = new HashMap<>();
            for (int i = 1; i <= 3; i++) {
                signs.put(i, signFilePathPrefix + userNamePrefix + i + ".png");
            }
            index[0] += 4;
            users.put(index[0], signs);
        });

        ExcelUtil.replacePicture(excelFilePath, order, signDate, users);

    }

    public void testExcel4(String excelFilePath, Integer order, LocalDate signDate) {

        Map<Integer, Map> users = new HashMap() {{
            put(7, null);
            put(11, null);
            put(15, null);
        }};

        List<String> userNamePrefixs = new ArrayList() {{
            add("fupy_");
            add("zengxl_");
            add("wangxy_");
        }};

        int[] index = {3};
        userNamePrefixs.forEach(userNamePrefix -> {
            Map<Integer, String> signs = new HashMap<>();
            for (int i = 1; i <= 3; i++) {
                signs.put(i, signFilePathPrefix + userNamePrefix + i + ".png");
            }
            index[0] += 4;
            users.put(index[0], signs);
        });

        ExcelUtil.replacePicture(excelFilePath, order, signDate, users);

    }

}

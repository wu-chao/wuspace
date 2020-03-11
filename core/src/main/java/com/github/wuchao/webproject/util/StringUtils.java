package com.github.wuchao.webproject.util;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.lang.Nullable;
import org.springframework.util.NumberUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class StringUtils extends org.apache.commons.lang3.StringUtils {

    @Nullable
    private static ConversionService conversionService = DefaultConversionService.getSharedInstance();

    private StringUtils() {
    }

    /**
     * @param ig
     * @param count
     * @return
     */
    public static String convertArrayToString(Object[] ig, int count) {
        if (ig != null && ig.length > 0) {
            StringBuilder str = new StringBuilder("");
            for (int i = 0; i < ig.length && i < count; i++) {
                str.append(ig[i]).append(",");
            }
            return str.substring(0, str.length() - 1);
        }
        return "";
    }

    public static Double getPercentValue(String percent) {
        if (!isEmpty(percent)) {
            return Double.valueOf(percent.substring(0, percent.length() - 1));
        } else {
            return null;
        }
    }

    // 首字母转小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }


    // 首字母转大写
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    private static Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");

    /**
     * 驼峰命名转为下划线命名
     * https://www.cnblogs.com/zhuhui-site/p/10090880.html
     */
    public static String hump2lowerCaseUnderline(String name) {
        Matcher matcher = HUMP_PATTERN.matcher(name);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /***
     * 驼峰命名转为下划线命名
     *
     * @param name 驼峰命名的字符串
     *
     */
    public static String hump2upperCaseUnderline(String name) {
        if (isNotBlank(name)) {
            StringBuilder sb = new StringBuilder(name);
            // 定位
            int temp = 0;
            for (int i = 0; i < name.length(); i++) {
                if (Character.isUpperCase(name.charAt(i))) {
                    sb.insert(i + temp, "_");
                    temp += 1;
                }
            }
            return sb.toString().toUpperCase();
        }
        return name;
    }

    /**
     * 驼峰命名转为下划线命名
     * https://blog.csdn.net/sunhuwh/article/details/79016237
     *
     * @param name
     * @return
     */
    public static String hump2upperCaseUnderline2(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }

    /**
     * 下划线命名转为驼峰命名
     * https://blog.csdn.net/sunhuwh/article/details/79016237
     *
     * @param name
     * @return
     */
    public static String underline2hump(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 将以逗号分隔的字符串转换成指定类型的 List 集合
     *
     * @param strs
     * @param clazz
     * @return
     */
    public static <T> List<T> split(String strs, Class<T> clazz) {
        if (StringUtils.isNotBlank(strs)) {
            String[] strAry = strs.split(",");

            if (Objects.isNull(clazz)) {
                clazz = (Class<T>) String.class;
            }
            Class clazz1 = clazz;
            return Arrays.stream(strAry)
                    .map(str -> (T) convertValueToRequiredType(str, clazz1))
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    /**
     * 复制自：org.springframework.jdbc.core.SingleColumnRowMapper
     *
     * @param value
     * @param requiredType
     * @return
     */
    @Nullable
    protected static Object convertValueToRequiredType(Object value, Class<?> requiredType) {
        if (String.class == requiredType) {
            return value.toString();
        } else if (Number.class.isAssignableFrom(requiredType)) {
            if (value instanceof Number) {
                // Convert original Number to target Number class.
                return org.springframework.util.NumberUtils.convertNumberToTargetClass(((Number) value), (Class<Number>) requiredType);
            } else {
                // Convert stringified value to target Number class.
                return NumberUtils.parseNumber(value.toString(), (Class<Number>) requiredType);
            }
        } else if (conversionService != null && conversionService.canConvert(value.getClass(), requiredType)) {
            return conversionService.convert(value, requiredType);
        } else {
            throw new IllegalArgumentException(
                    "Value [" + value + "] is of type [" + value.getClass().getName() +
                            "] and cannot be converted to required type [" + requiredType.getName() + "]");
        }
    }

    /**
     * 去掉数字字符串中末尾的 0
     * https://www.ibm.com/developerworks/community/blogs/mrcoolK/entry/How_to_remove_trailing_zero_in_a_string_in_Java?lang=en
     *
     * @param numberStr
     * @return
     */
    public static String removeTrailingZero(String numberStr) {
        return numberStr.indexOf(".") < 0
                ? numberStr
                : numberStr.replaceAll("0*$", "").replaceAll("\\.$", "");
    }

    /**
     * 去掉数字字符串中末尾的 0
     * https://www.ibm.com/developerworks/community/blogs/mrcoolK/entry/How_to_remove_trailing_zero_in_a_string_in_Java?lang=en
     *
     * @param numberStr
     * @return
     */
    public static String removeTrailingZero2(String numberStr) {
        return numberStr.indexOf(".") < 0
                ? numberStr
                : numberStr.replaceFirst("\\.0*$|(\\.\\d*?)0+$", "$1");
    }

    /**
     * 去掉数字字符串中末尾的 0
     * https://www.ibm.com/developerworks/community/blogs/mrcoolK/entry/How_to_remove_trailing_zero_in_a_string_in_Java?lang=en
     *
     * @param numberStr
     * @return
     */
    public static String removeTrailingZero3(String numberStr) {
        return numberStr.indexOf(".") < 0
                ? numberStr
                : numberStr.replaceAll("0*$", "").replaceAll("\\.$", "");
    }


    /**
     * [最长公共子序列问题（Java）-动态规划](https://blog.csdn.net/Heated_Youth/article/details/53260333)
     *
     * @param str1
     * @param str2
     */
    //在动态规划矩阵生成方式当中，每生成一行，前面的那一行就已经没有用了，因此这里只需使用一维数组，而不是常用的二位数组
    public static void getLCString(char[] str1, char[] str2) {
        int len1, len2;
        len1 = str1.length;
        len2 = str2.length;
        int maxLen = len1 > len2 ? len1 : len2;
        int[] max = new int[maxLen];// 保存最长子串长度的数组
        int[] maxIndex = new int[maxLen];// 保存最长子串长度最大索引的数组
        int[] c = new int[maxLen];
        int i, j;
        for (i = 0; i < len2; i++) {
            for (j = len1 - 1; j >= 0; j--) {
                if (str2[i] == str1[j]) {
                    if ((i == 0) || (j == 0))
                        c[j] = 1;
                    else
                        c[j] = c[j - 1] + 1;//此时C[j-1]还是上次循环中的值，因为还没被重新赋值
                } else {
                    c[j] = 0;
                }
                // 如果是大于那暂时只有一个是最长的,而且要把后面的清0;
                if (c[j] > max[0]) {
                    max[0] = c[j];
                    maxIndex[0] = j;
                    for (int k = 1; k < maxLen; k++) {
                        max[k] = 0;
                        maxIndex[k] = 0;
                    }
                }
                // 有多个是相同长度的子串
                else if (c[j] == max[0]) {
                    for (int k = 1; k < maxLen; k++) {
                        if (max[k] == 0) {
                            max[k] = c[j];
                            maxIndex[k] = j;
                            break; // 在后面加一个就要退出循环了
                        }
                    }
                }
            }
            for (int temp : c) {
                System.out.print(temp);
            }
            System.out.println();
        }
        //打印最长子字符串
        for (j = 0; j < maxLen; j++) {
            if (max[j] > 0) {
                System.out.println("第" + (j + 1) + "个公共子串:");
                for (i = maxIndex[j] - max[j] + 1; i <= maxIndex[j]; i++) {
                    System.out.print(str1[i]);
                }
                System.out.println(" ");
            }
        }
    }


}

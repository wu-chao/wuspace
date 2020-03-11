package com.github.wuchao.webproject.util;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * [java获取中文的拼音和获取中文的拼音首字母](https://blog.csdn.net/chaoyue1861/article/details/91042682)
 */
public abstract class StringPinYinUtils {

    /**
     * 获取中文汉字拼音
     *
     * @param strName
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static String getPinyin(String strName) throws BadHanyuPinyinOutputFormatCombination {
        char[] charArray = strName.toCharArray();
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 设置大小写格式
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        // 设置声调格式
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charArray.length; i++) {
            // 匹配中文,非中文转换会转换成null
            if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                String[] hanyuPinyinStringArray = PinyinHelper
                        .toHanyuPinyinStringArray(charArray[i], defaultFormat);
                String string = hanyuPinyinStringArray[0];
                pinyin.append(string + " ");
            } else {
                pinyin.append(charArray[i]);
            }
        }
        return pinyin.toString();
    }


    /**
     * 获取中文拼音的首字母
     *
     * @param strName
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static String getPinyinInitial(String strName)
            throws BadHanyuPinyinOutputFormatCombination {
        char[] charArray = strName.toCharArray();
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 设置大小写格式
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        // 设置声调格式：
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charArray.length; i++) {
            // 匹配中文,非中文转换会转换成null
            if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                String[] hanyuPinyinStringArray = PinyinHelper
                        .toHanyuPinyinStringArray(charArray[i], defaultFormat);
                if (hanyuPinyinStringArray != null) {
                    pinyin.append(hanyuPinyinStringArray[0].charAt(0));
                }
            }
        }
        return pinyin.toString();
    }


}

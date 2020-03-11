package com.github.wuchao.webproject.util;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Test;

public class StringPinYinUtilsTests {

    @Test
    public void testGetPinyin() throws BadHanyuPinyinOutputFormatCombination {
        String str1 = "江苏省";
        String str2 = "江苏";

        System.out.println(StringPinYinUtils.getPinyin(str1));
        System.out.println(StringPinYinUtils.getPinyin(str2));

        /**
         * 执行结果：
         *
         * JIANG SU SHENG
         * JIANG SU
         */
    }


    @Test
    public void testGetPinyinInitial() throws BadHanyuPinyinOutputFormatCombination {
        String str1 = "江苏省";
        String str2 = "江苏";

        System.out.println(StringPinYinUtils.getPinyinInitial(str1));
        System.out.println(StringPinYinUtils.getPinyinInitial(str2));

        /**
         * 执行结果：
         *
         * JSS
         * JS
         */
    }
}

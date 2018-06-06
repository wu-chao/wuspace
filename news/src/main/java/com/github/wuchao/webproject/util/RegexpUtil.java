package com.github.wuchao.webproject.util;

import org.apache.oro.text.regex.*;

public final class RegexpUtil {

    /**
     * 大陆手机号码 11 位数，匹配格式：前三位固定格式 + 后 8 位任意数
     * 此方法中前三位格式有：
     * 13 + 任意数
     * 15 + 除 4 的任意数
     * 18 + 除 1 和 4 的任意数
     * 17 + 除 9 的任意数
     * 147
     */
    // public static final String PRC_PHONE_REGEXP = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";

    /**
     * 大陆手机号码正则表达式
     * 为拓展性好，不验证具体规则，只要满足以数字1开头，后跟10位数组即可
     */
    public static final String PRC_PHONE_REGEXP = "^(1)\\d{10}$";

    /**
     * 邮箱正则表达式
     */
    public static final String EMAIL = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";

    /**
     * 微信号正则表达式
     * 1、微信号是微信的唯一凭证，只能设置 1 次；
     * 2、可使用 6-20 个字母、数字、下划线和减号；
     * 3、必须以字母开头（字母不区分大小写）；
     * 4、不支持设置中文。
     */
    public static final String WECHAT_REGEXP = "^[a-zA-Z]{1}[-_a-zA-Z0-9]{5,19}$";

    /**
     * 身份证号码正则表达式
     * 18 位身份证号码各位的含义:
     * 1-2 位省、自治区、直辖市代码；
     * 3-4 位地级市、盟、自治州代码；
     * 5-6 位县、县级市、区代码；
     * 7-14 位出生年月日，比如 19670401 代表 1967 年 4 月 1 日；
     * 15-17 位为顺序号，其中 17 位（倒数第二位）男为单数，女为双数；
     * 18 位为校验码，0-9 和 X。作为尾号的校验码，是由把前十七位数字带入统一的公式计算出来的，计算的结果是 0-10，
     * 如果某人的尾号是 0－9，都不会出现 X，但如果尾号是 10，那么就得用 X 来代替，因为如果用 10 做尾号，那么此人的身份证就变成了 19 位。X 是罗马数字的 10，用 X 来代替 10。
     */
    public static final String ID_REGEXP = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";

    /**
     * 邮编（6位纯数字）正则表达式
     */
    public static final String ZIP_REGEXP = "^[0-9]{6}$";

    /**
     * IP 正则表达式
     */
    public static final String IP_REGEXP = "(25[0-5]|2[0-4]\\\\d|1\\\\d{2}|[1-9]?\\\\d)(\\\\.(25[0-5]|2[0-4]\\\\d|1\\\\d{2}|[1-9]?\\\\d)){3}";

    /**
     * 域名正则表达式
     * DNS (https://baike.baidu.com/item/dns/427444) 规定:
     * 域名中的标号都由英文字母和数字组成，每一个标号不超过 63 个字符，也不区分大小写字母
     * 标号中除连字符（-）外不能使用其他的标点符号。级别最低的域名写在最左边，而级别最高的域名写在最右边
     * 由多个标号组成的完整域名总共不超过 255 个字符
     */
    public static final String DOMAIN = " ^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$";

    /**
     * 网址正则表达式
     */
    public static final String URL_PREFIX = "(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s]*";

    public static final String URL = "[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)";

    public static final String FAX = "/^((0\\d{2,3}-)?\\d{7,8})$/";

    public static boolean isMatched(String source, String regexp) {
        // 依赖：https://mvnrepository.com/artifact/org.apache.oro/com.springsource.org.apache.oro
        PatternCompiler patternCompiler = new Perl5Compiler();
        PatternMatcher patternMatcher = new Perl5Matcher();

        try {
            Pattern pattern = patternCompiler.compile(regexp);
            return patternMatcher.contains(source, pattern);
        } catch (MalformedPatternException e) {
            e.printStackTrace();
            return false;
        }
    }

}

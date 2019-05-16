package com.github.wuchao.webproject.util;

import lombok.Cleanup;
import org.w3c.tidy.Tidy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

/**
 * html 转 xhtml
 * https://www.cnblogs.com/ncyhl/p/7868789.html
 *
 * @author wuchao
 * @date 2019/5/11 20:19
 */
public class XHtmlUtils {

    /**
     * html 转 xhtml
     *
     * @param html
     * @return xhtml
     */
    public static String html2xhtmlByTidy(String html) {

        try {

            @Cleanup ByteArrayInputStream stream = new ByteArrayInputStream(html.getBytes());
            @Cleanup ByteArrayOutputStream tidyOutStream = new ByteArrayOutputStream();

            // 实例化Tidy对象
            Tidy tidy = new Tidy();
            // 设置输入
            tidy.setInputEncoding("utf-8");
            // 如果是true 不输出注释，警告和错误信息
            tidy.setQuiet(true);
            // 设置输出
            tidy.setOutputEncoding("utf-8");
            // 不显示警告信息
            tidy.setShowWarnings(false);
            // 缩进适当的标签内容。
            tidy.setIndentContent(true);
            // 内容缩进
            tidy.setSmartIndent(true);
            tidy.setIndentAttributes(false);
            // 只输出body内部的内容
            tidy.setPrintBodyOnly(true);
            // 多长换行
            tidy.setWraplen(1024);
            // 输出为xhtml
            tidy.setXHTML(true);
            // 去掉没用的标签
            tidy.setMakeClean(true);
            // 清洗word2000的内容
            tidy.setWord2000(true);
            // 设置错误输出信息
            tidy.setErrout(new PrintWriter(System.out));
            tidy.parse(stream, tidyOutStream);

            return tidyOutStream.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

//    public static String html2XhtmlByJsoup(final String html) {
//        final Document document = Jsoup.parse(html);
//        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
//        return document.html();
//    }

}

package com.github.wuchao.webproject.util;

import lombok.Cleanup;
import org.apache.commons.lang3.StringUtils;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class HtmlUtils {

    /**
     * 去除 HTML 标签
     *
     * @param content
     * @return
     */
    public static String cleanHTML(String content) {
        return content.replaceAll("</?[^>]+>", "").replaceAll("<a>\\s*|\t|\r|\n</a>", "");
    }

    /**
     * 得到字符串中 <img> 标签的 src 地址
     *
     * @param htmlStr
     * @return
     */
    public static Set<String> parseHtmlImgSrc(String htmlStr) {
        Set<String> pics = new HashSet<>();
        if (StringUtils.isNotBlank(htmlStr)) {
            String img = "";
            Pattern p_image;
            Matcher m_image;
            // String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
            String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
            p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
            m_image = p_image.matcher(htmlStr);
            while (m_image.find()) {
                // 得到<img />数据
                img = m_image.group();
                // 匹配<img>中的src数据
                Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
                while (m.find()) {
                    pics.add(m.group(1));
                }
            }
        }
        return pics;
    }


    /**
     * HTML 转 Text
     *
     * @param html
     * @return
     */
    public static String html2text(String html) {
        Html2Text html2Text = new Html2Text();
        try {
            html2Text.parse(html);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return html2Text.getText();
    }

    public static class Html2Text extends HTMLEditorKit.ParserCallback {

        StringBuffer s;

        public void parse(String str) throws IOException {
            @Cleanup InputStream inputStream = new ByteArrayInputStream(str.getBytes());
            @Cleanup Reader reader = new InputStreamReader(inputStream);
            s = new StringBuffer();
            ParserDelegator delegator = new ParserDelegator();
            // the third parameter is TRUE to ignore charset directive
            delegator.parse(reader, this, Boolean.TRUE);
        }

        public void handleText(char[] text, int pos) {
            s.append(text);
        }

        public String getText() {
            return s.toString();
        }


    }

}

package com.github.wuchao.webproject.util;

import org.xml.sax.InputSource;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

public abstract class XmlUtils {

    /**
     * 使用 xml+xsl 转换成 html 文件
     * https://blog.csdn.net/wts/article/details/42263263
     *
     * @param xml          xml 内容字符串
     * @param htmlFilePath 目标 html 文件路径
     */
    public static void xml2html(String xml, String htmlFilePath) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        try {
            Transformer transformer = transformerFactory.newTransformer();
            // 设置输出文件的字符集
            transformer.setOutputProperty("encoding", "UTF-8");
            StringReader xmlReader = new StringReader(xml);
            StreamSource xmlSource = new StreamSource(xmlReader);

            File htmlFile = new File(htmlFilePath);
            // 如果不使用 htmlFile.toURI().getPath() 的话，可能出现 FileNotFoundException
            Result result = new StreamResult(htmlFile.toURI().getPath());
            transformer.transform(xmlSource, result);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    /**
     * xml 转 html
     * https://www.programcreek.com/java-api-examples/?class=javax.xml.transform.TransformerFactory&method=newTransformer
     *
     * @param xml
     * @return
     */
    public static String xml2html(String xml) {

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("encoding", "UTF-8");
            transformer.setOutputProperty(OutputKeys.METHOD, "html");

//            StringReader xmlReader = new StringReader(xml);
//            StreamSource xmlSource = new StreamSource(xmlReader);
            InputSource in = new InputSource(new StringReader(xml));
            SAXSource xmlSource = new SAXSource(in);

            StringWriter stringWriter = new StringWriter();
            Result result = new StreamResult(stringWriter);

            transformer.transform(xmlSource, result);

            return stringWriter.toString();

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return "";

    }

}

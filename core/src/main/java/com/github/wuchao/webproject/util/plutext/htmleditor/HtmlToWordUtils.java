package com.github.wuchao.webproject.util.plutext.htmleditor;

import com.github.wuchao.webproject.util.XHtmlUtils;
import com.github.wuchao.webproject.util.plutext.htmleditor.toDocx.RoundtripXHTMLImporter;
import com.github.wuchao.webproject.util.plutext.htmleditor.toDocx.XHTMLtoDocxImageHandler;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Style;
import org.springframework.util.ResourceUtils;

import java.io.File;

/**
 * @author wuchao
 * @date 2019/5/11 18:43
 */
public class HtmlToWordUtils {


    /**
     * 富文本转 docx
     * https://github.com/plutext/docx-html-editor/blob/master/src/main/java/org/plutext/htmleditor/toDocx/Saver.java
     *
     * @param richText
     * @param fileName
     * @param path
     */
    public static String richText2Docx2(String richText, String fileName, String path) {
        try {

            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();

            // contains &nbsp;
            String xhtml = XHtmlUtils.html2xhtmlByTidy(richText);
            xhtml = xhtml.replaceAll("&nbsp;", "&#160;")
                    .replace("<br>", "<br/>");

            // we need to feed our css to Flying Saucer, or it'll use inappropriate defaults
//            xhtml = "<html><head><style type=\"text/css\">" + session.getAttribute("css") + "</style></head>"
//                    + "<body>" + xhtml + "</body></html>";
            xhtml = "<html><head></head>"
                    + "<body>" + xhtml + "</body></html>";

            XHTMLImporterImpl xHTMLImporter = new RoundtripXHTMLImporter(wordMLPackage);
            xHTMLImporter.setXHTMLImageHandler(new XHTMLtoDocxImageHandler(xHTMLImporter));

            wordMLPackage.getMainDocumentPart().getContent().clear();
            wordMLPackage.getMainDocumentPart().getContent().addAll(
                    xHTMLImporter.convert(xhtml, null));

            // Remove DocDefault virtual style, since it upsets table formatting
            // (applies space after for example, when that wouldn't otherwise be used)
            Style normal = wordMLPackage.getMainDocumentPart().getStyleDefinitionsPart().getDefaultParagraphStyle();
            if (normal != null) {
                normal.setBasedOn(null);
            }

            // Now stream the docx
//            final SaveToZipFile saver = new SaveToZipFile(wordMLPackage);
//            OutputStream output = new FileOutputStream(file);
//            saver.save(output);
            File file = ResourceUtils.getFile(path + File.separator + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            wordMLPackage.save(file);

            return path + File.separator + fileName;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }


}

package com.github.wuchao.webproject.util;

import com.github.wuchao.webproject.util.poi.WordTemplate;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.docx4j.XmlUtils;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Slf4j
public class POIUtil {

    public static WordTemplate loadDocument(String resourceLocation) throws IOException {
        if (StringUtils.isNotEmpty(resourceLocation)) {
            // 读取word模板
            @Cleanup InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation);
            log.info("================================= 获取文件输入流>：{}", "Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation)");
            if (is == null) {
                is = new FileInputStream(ResourceUtils.getFile(resourceLocation));
                log.info("================================= 获取文件输入流>：{}", "new FileInputStream(ResourceUtils.getFile(resourceLocation))");
            }
            return new WordTemplate(is);
        }
        return null;
    }

    /**
     * 替换模板文件
     *
     * @param resourceLocation 模板文件位置
     * @param dataMap
     */
    public static WordTemplate replaceDocument(String resourceLocation, Map<String, Object> dataMap) {
        if (StringUtils.isNotEmpty(resourceLocation) && MapUtils.isNotEmpty(dataMap)) {
            try {
                WordTemplate template = loadDocument(resourceLocation);

                if (MapUtils.isNotEmpty(dataMap)) {
                    //替换数据
                    template.replaceDocument(dataMap);
                }

                return template;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static void writeFileStream(WordTemplate template, String outFileName, HttpServletResponse response) {
        if (template != null) {
            try {
                // 文件名称转码，防止文件名称出现乱码
                outFileName = StringUtils.isNotBlank(outFileName) ? URLEncoder.encode(outFileName, "UTF-8") : "";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setHeader("Connection", "close");
            response.setHeader("Content-Disposition", "attachment; filename=" + outFileName);

            try {
                //下载文件
                OutputStream os = response.getOutputStream();
                template.getDocument().write(os);
                os.flush();
                os.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 浏览器导出文件
     *
     * @param resourceLocation 文件模板位置
     * @param dataMap          模板填充数据
     * @param outFileName      导出后的文件名称
     * @param response
     */
    public static void exportDocument(String resourceLocation, Map<String, Object> dataMap, String outFileName, HttpServletResponse response) {

        WordTemplate template = replaceDocument(resourceLocation, dataMap);
        writeFileStream(template, outFileName, response);

    }

    public static void exportDocument(String resourceLocation, String outFileName, HttpServletResponse response) throws IOException {

        WordTemplate template = loadDocument(resourceLocation);
        writeFileStream(template, outFileName, response);

    }

    /**
     * 本地测试导出word文档到本地
     *
     * @param resourceLocation
     * @param dataMap
     * @param descLocation
     */
    public static void exportDocument(String resourceLocation, Map<String, Object> dataMap, String descLocation) {

        WordTemplate template = replaceDocument(resourceLocation, dataMap);
        if (template != null) {
            try {
                //下载文件
                OutputStream os = new FileOutputStream(ResourceUtils.getFile(descLocation));
                template.getDocument().write(os);
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 合并文档(合并后的文件中的图片无法显示，表格无边框)
     *
     * @param resourceLocation
     * @param dataMap
     * @param descLocation
     * @param docLocations
     */
    public static void mergeDoc(String resourceLocation, Map<String, Object> dataMap, List<String> docLocations, String descLocation) {
        WordTemplate wordTemplate = replaceDocument(resourceLocation, dataMap);
        if (wordTemplate != null && CollectionUtils.isNotEmpty(docLocations) && StringUtils.isNotEmpty(descLocation)) {
            docLocations.stream().forEach(docLocation -> {
                try {
                    File file = ResourceUtils.getFile(docLocation);
                    wordTemplate.mergeDoc(new FileInputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            try {
                // 导出文件
                wordTemplate.write(new FileOutputStream(ResourceUtils.getFile(descLocation)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 富文本转 doc
     *
     * @param richText ueditor 生成的内容
     * @param fileName 生成的 word 文件名
     * @param path     生成的 word 文件目录
     */
    public static String richText2Doc(String richText, String fileName, String path) {

        // 这里也可以使用 “.docx” 格式，但是如果用 Docx4j 打开的话则会报错。
        String docLocation = path + File.separator + fileName + ".doc";

        try {
            FileOutputStream os = new FileOutputStream(docLocation);

            String str = " <!--[if gte mso 9]><xml><w:WordDocument><w:View>Print</w:View><w:TrackMoves>false</w:TrackMoves><w:TrackFormatting/><w:ValidateAgainstSchemas/><w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid><w:IgnoreMixedContent>false</w:IgnoreMixedContent><w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText><w:DoNotPromoteQF/><w:LidThemeOther>EN-US</w:LidThemeOther><w:LidThemeAsian>ZH-CN</w:LidThemeAsian><w:LidThemeComplexScript>X-NONE</w:LidThemeComplexScript><w:Compatibility><w:BreakWrappedTables/><w:SnapToGridInCell/><w:WrapTextWithPunct/><w:UseAsianBreakRules/><w:DontGrowAutofit/><w:SplitPgBreakAndParaMark/><w:DontVertAlignCellWithSp/><w:DontBreakConstrainedForcedTables/><w:DontVertAlignInTxbx/><w:Word11KerningPairs/><w:CachedColBalance/><w:UseFELayout/></w:Compatibility><w:BrowserLevel>MicrosoftInternetExplorer4</w:BrowserLevel><m:mathPr><m:mathFont m:val='Cambria Math'/><m:brkBin m:val='before'/><m:brkBinSub m:val='--'/><m:smallFrac m:val='off'/><m:dispDef/><m:lMargin m:val='0'/> <m:rMargin m:val='0'/><m:defJc m:val='centerGroup'/><m:wrapIndent m:val='1440'/><m:intLim m:val='subSup'/><m:naryLim m:val='undOvr'/></m:mathPr></w:WordDocument></xml><![endif]-->";
            String h = " <html xmlns:v='urn:schemas-microsoft-com:vml'xmlns:o='urn:schemas-microsoft-com:office:office'xmlns:w='urn:schemas-microsoft-com:office:word'xmlns:m='http://schemas.microsoft.com/office/2004/12/omml'xmlns='http://www.w3.org/TR/REC-html40'  ";

            richText = h + "<head>" + "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />" + str + "</head><body>" + richText + "</body> </html>";

            // 这里是必须要设置编码的，不然导出中文就会乱码。
            byte b[] = richText.getBytes("utf-8");
            // 将字节数组包装到流中
            ByteArrayInputStream bais = new ByteArrayInputStream(b);

            // 生成 word 文件
            POIFSFileSystem document = new POIFSFileSystem();
            DirectoryEntry directory = document.getRoot();
            directory.createDocument("WordDocument", bais);
            // 输出文件
            document.writeFilesystem(os);
            os.flush();
            os.close();
            bais.close();

            return docLocation;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 富文本转 docx（文字大小和颜色没有转换成功）
     * https://github.com/plutext/docx4j-ImportXHTML/blob/master/src/samples/java/org/docx4j/samples/XhtmlToDocxAndBack.java#L77
     *
     * @throws Docx4JException
     */
    public static String richText2Docx(String richText, String fileName, String path) {
        if (StringUtils.isNotBlank(richText)) {
            try {
                String escapeRichText = richText.replaceAll("&nbsp;", "&#160;");
                escapeRichText = escapeRichText.replace("<br>", "<br/>");
//                escapeRichText = escapeRichText.replace("<p>", "<p style=\"text-indent: 2em;\" >");

                //        String str = " <!--[if gte mso 9]><xml><w:WordDocument><w:View>Print</w:View><w:TrackMoves>false</w:TrackMoves><w:TrackFormatting/><w:ValidateAgainstSchemas/><w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid><w:IgnoreMixedContent>false</w:IgnoreMixedContent><w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText><w:DoNotPromoteQF/><w:LidThemeOther>EN-US</w:LidThemeOther><w:LidThemeAsian>ZH-CN</w:LidThemeAsian><w:LidThemeComplexScript>X-NONE</w:LidThemeComplexScript><w:Compatibility><w:BreakWrappedTables/><w:SnapToGridInCell/><w:WrapTextWithPunct/><w:UseAsianBreakRules/><w:DontGrowAutofit/><w:SplitPgBreakAndParaMark/><w:DontVertAlignCellWithSp/><w:DontBreakConstrainedForcedTables/><w:DontVertAlignInTxbx/><w:Word11KerningPairs/><w:CachedColBalance/><w:UseFELayout/></w:Compatibility><w:BrowserLevel>MicrosoftInternetExplorer4</w:BrowserLevel><m:mathPr><m:mathFont m:val='Cambria Math'/><m:brkBin m:val='before'/><m:brkBinSub m:val='--'/><m:smallFrac m:val='off'/><m:dispDef/><m:lMargin m:val='0'/> <m:rMargin m:val='0'/><m:defJc m:val='centerGroup'/><m:wrapIndent m:val='1440'/><m:intLim m:val='subSup'/><m:naryLim m:val='undOvr'/></m:mathPr></w:WordDocument></xml><![endif]-->";
                String str = "";
                String h = " <html xmlns:v='urn:schemas-microsoft-com:vml' xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:w='urn:schemas-microsoft-com:office:word' xmlns:m='http://schemas.microsoft.com/office/2004/12/omml' xmlns='http://www.w3.org/TR/REC-html40'> ";
                String xhtml = h + "<head>" + "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />" + str + "</head><body>" + escapeRichText + "</body> </html>";

                // To docx, with content controls
                WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
                XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
                wordMLPackage.getMainDocumentPart().getContent().addAll(
                        XHTMLImporter.convert(xhtml, null));

                System.out.println(XmlUtils.marshaltoString(wordMLPackage
                        .getMainDocumentPart().getJaxbElement(), true, true));

                String filePath = path + File.separator + fileName + ".docx";
                wordMLPackage.save(ResourceUtils.getFile(filePath));

                return filePath;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;

    }

}

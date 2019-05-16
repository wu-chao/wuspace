package com.github.wuchao.webproject.util.poi;

import com.github.wuchao.webproject.util.FileUtils;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
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
        log.info("================================= resourceLocation：{}", resourceLocation);
        if (StringUtils.isNotEmpty(resourceLocation)) {
            InputStream is = null;
            try {
                is = FileUtils.loadFileInputStream(resourceLocation);
                if (is != null) {
                    //读取 word 模板
                    return new WordTemplate(is);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            } finally {
                if (is != null) {
                    is.close();
                }
            }
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
     * https://stackoverflow.com/questions/13103421/converting-a-docx-to-html-using-apache-poi-and-getting-no-text
     */
    public static void doc2html() {

    }


    /**
     * docx 转 html
     * https://stackoverflow.com/questions/24652953/convert-docx-to-html-using-java
     *
     * @param inputfilepath
     * @return
     * @throws IOException
     */
    public static String docx2html(String inputfilepath) throws IOException {
        @Cleanup InputStream in = new FileInputStream(ResourceUtils.getFile(inputfilepath));
        XWPFDocument document = new XWPFDocument(in);

        File imageFile = ResourceUtils
                .getFile(System.getProperty("user.dir") + File.separator + "word_files");
        XHTMLOptions options = XHTMLOptions.create()
                .URIResolver(new FileURIResolver(imageFile));
        options.setExtractor(new FileImageExtractor(imageFile));

        @Cleanup OutputStream out = new ByteArrayOutputStream();

        XHTMLConverter.getInstance().convert(document, out, options);

        return out.toString();
    }


    public static void docx2html2(String inputfilepath, String outfilepath) throws IOException {
        XHTMLConverter xhtmlConverter = new XHTMLConverter();

        XWPFDocument document = new XWPFDocument(XWPFDocument.openPackage(inputfilepath));

        @Cleanup OutputStream outputStream = new FileOutputStream(ResourceUtils.getFile(outfilepath));

        XHTMLOptions options = XHTMLOptions.create()
                .URIResolver(new FileURIResolver(ResourceUtils
                        .getFile(System.getProperty("user.dir"))));
        options.setExtractor(new FileImageExtractor(ResourceUtils
                .getFile(System.getProperty("user.dir"))));

        xhtmlConverter.convert(document, outputStream, options);
    }

    /**
     * doc 转 pdf（效果不好）
     * https://stackoverflow.com/questions/6201736/javausing-apache-poi-how-to-convert-ms-word-file-to-pdf
     *
     * @param inpufilepath
     * @param outfilepath
     */
    public static void doc2pdf(String inpufilepath, String outfilepath) {
        POIFSFileSystem fs;
        Document document = new Document();

        try {
            System.out.println("Starting the test");
            fs = new POIFSFileSystem(new FileInputStream(inpufilepath));

            HWPFDocument doc = new HWPFDocument(fs);
            WordExtractor we = new WordExtractor(doc);

            OutputStream file = new FileOutputStream(new File(outfilepath));

            PdfWriter writer = PdfWriter.getInstance(document, file);

            Range range = doc.getRange();
            document.open();
            writer.setPageEmpty(true);
            document.newPage();
            writer.setPageEmpty(true);

            String[] paragraphs = we.getParagraphText();
            for (int i = 0; i < paragraphs.length; i++) {

                org.apache.poi.hwpf.usermodel.Paragraph pr = range.getParagraph(i);
                // CharacterRun run = pr.getCharacterRun(i);
                // run.setBold(true);
                // run.setCapitalized(true);
                // run.setItalic(true);
                paragraphs[i] = paragraphs[i].replaceAll("\\cM?\r?\n", "");
                System.out.println("Length:" + paragraphs[i].length());
                System.out.println("Paragraph" + i + ": " + paragraphs[i].toString());

                // add the paragraph to the document
                document.add(new Paragraph(paragraphs[i]));
            }

            System.out.println("Document testing completed");
        } catch (Exception e) {
            System.out.println("Exception during test");
            e.printStackTrace();
        } finally {
            // close the document
            document.close();
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
        String docLocation = path + File.separator + fileName;

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

}

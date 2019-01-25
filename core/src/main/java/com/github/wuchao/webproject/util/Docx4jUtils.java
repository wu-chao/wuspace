package com.github.wuchao.webproject.util;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.github.wuchao.webproject.domain.docstopdfconverter.Converter;
import com.github.wuchao.webproject.domain.docstopdfconverter.DocxToPDFConverter;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.WordprocessingML.AlternativeFormatInputPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.org.apache.poi.util.IOUtils;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.CTAltChunk;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
/**
 * docx4j基本操作: https://www.cnblogs.com/cxxjohnson/p/7833911.html
 */
public abstract class Docx4jUtils {

    private static final String DOC = ".doc";
    private static final String DOCX = ".docx";

    /**
     * doc 文档转 docx 文档
     *
     * @param docLocation
     * @param deleteResource 是否删除原文件
     * @return
     */
    public static String doc2docx(String docLocation, boolean deleteResource) {
        String docxLocation = null;
        try {
            // 如果是.doc，则转换成 .docx
            if (docLocation.endsWith(DOC)) {
                Document doc = new Document(docLocation);
                docxLocation = docLocation.replace(DOC, DOCX);
                File docx = new File(docxLocation);
                if (!docx.exists() && !docx.createNewFile()) {
                    return null;
                }
                doc.save(docxLocation);

                WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(docx);
                List<Object> objects = mlPackage.getMainDocumentPart().getContent();
                Iterator iterator = objects.iterator();
                String objStr;
                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    objStr = obj.toString();
                    if (objStr.contains("Evaluation Only. Created with Aspose.Words.")) {
                        iterator.remove();
                    } else if (objStr.contains("it was created using Aspose.Words in Evaluation Mode.")) {
                        iterator.remove();
                    }
                    break;
                }
                mlPackage.save(docx);
            }
            return docxLocation;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (deleteResource && docxLocation != null) {
                FileUtils.delete(docLocation);
            }
        }

        return null;

    }

    /**
     * 合并文件
     * 参考：https://blog.csdn.net/pengchong333/article/details/53816356
     *
     * @param docLocations
     * @param path
     */
    public static void mergeDoc(List<String> docLocations, String path) {
        List<InputStream> inList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(docLocations)) {
            for (String docLocation : docLocations) {
                File file;
                try {
                    InputStream i;
                    file = ResourceUtils.getFile(docLocation);
                    if (file.exists()) {
                        i = new FileInputStream(file);
                    } else {
                        i = Thread.currentThread().getContextClassLoader().getResourceAsStream(docLocation);
                    }
                    inList.add(i);
                } catch (FileNotFoundException e) {
                    log.warn("文件不存在");
                    return;
                }
            }

            try {
                @Cleanup InputStream inputStream = mergeDoc(inList);
                saveTemplate(inputStream, path);
            } catch (IOException | Docx4JException e) {
                e.printStackTrace();
            } finally {
                // 关闭所有文件流
                for (InputStream stream : inList) {
                    if (stream != null) {
                        try {
                            stream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 合并文件
     *
     * @param streams
     * @return
     * @throws IOException
     * @throws Docx4JException
     */
    private static InputStream mergeDoc(final List<InputStream> streams) throws IOException, Docx4JException {
        WordprocessingMLPackage target = null;
        final File generated = File.createTempFile("generated", ".docx");

        int chunkId = 0;
        Iterator<InputStream> it = streams.iterator();
        while (it.hasNext()) {
            InputStream is = it.next();
            if (is != null) {
                if (target == null) {
                    // Copy first (master) document
                    @Cleanup OutputStream os = new FileOutputStream(generated);
                    os.write(IOUtils.toByteArray(is));

                    target = WordprocessingMLPackage.load(generated);
                } else {
                    // Attach the others (Alternative input parts)
                    insertDoc(target.getMainDocumentPart(),
                            IOUtils.toByteArray(is), chunkId++);
                }
            }
        }

        if (target != null) {
            target.save(generated);
            return new FileInputStream(generated);
        } else {
            return null;
        }
    }

    /**
     * 插入文档
     *
     * @param main
     * @param bytes
     * @param chunkId
     */
    private static void insertDoc(MainDocumentPart main, byte[] bytes, int chunkId) {
        try {
            AlternativeFormatInputPart afiPart =
                    new AlternativeFormatInputPart(new PartName("/part" + chunkId + ".docx"));
            afiPart.setBinaryData(bytes);
            Relationship altChunkRel = main.addTargetPart(afiPart);

            CTAltChunk chunk = Context.getWmlObjectFactory().createCTAltChunk();
            chunk.setId(altChunkRel.getId());

            main.addObject(chunk);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveTemplate(InputStream fis, String toDocPath) {
        if (fis != null && StringUtils.isNotEmpty(toDocPath)) {
            FileOutputStream fos;
            int byteSum = 0;
            int byteRead;
            try {
                fos = new FileOutputStream(toDocPath);
                byte[] buffer = new byte[1444];
                while ((byteRead = fis.read(buffer)) != -1) {
                    // 字节数 文件大小
                    byteSum += byteRead;
                    fos.write(buffer, 0, byteRead);
                }
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * [如何破解aspose.words](https://blog.csdn.net/xiaosanshao9/article/details/51915952)
     *
     * @return
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            // 商用须购买许可
            InputStream is = FileUtils.class.getClassLoader().getResourceAsStream("aspose/license.xml");
            com.aspose.words.License aposeLic = new com.aspose.words.License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * DOC, DOCX 转 PDF
     * https://www.cnblogs.com/zhangzhxb/p/5984766.html
     * https://www.cnblogs.com/qiwu1314/p/6101400.html
     *
     * @param inputStream
     * @param fileName
     */
    public static String word2PdfByAspose(InputStream inputStream, String fileName) {

        // 验证 License 若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
            return null;
        }

        StringBuilder pdfPath = new StringBuilder()
                .append(System.getProperty("user.dir"))
                .append(File.separator)
                .append("src")
                .append(File.separator)
                .append("main")
                .append(File.separator)
                .append("resources")
                .append(File.separator)
                .append("public")
                .append(File.separator);
        if (StringUtils.isBlank(fileName)) {
            fileName = System.currentTimeMillis() + ".pdf";
        }
        pdfPath.append(fileName);

        try {

            File file = FileUtils.createFile(pdfPath.toString());
            FileOutputStream os = new FileOutputStream(file);
            // Address 是将要被转化的 word 文档
            Document doc = new Document(inputStream);
            // 全面支持 DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            doc.save(os, SaveFormat.PDF);

            log.info("=================================> convert doc/docx to pdf successfully.");
            return file.getAbsolutePath();

        } catch (Exception e) {
            e.printStackTrace();
            log.warn("=================================> convert doc/docx to pdf failed.");
            return null;
        }

    }

    /**
     * DOCX 转 PDF/XHTML converter based on Apache POI+iText
     * https://github.com/yeokm1/docs-to-pdf-converter/blob/master/docs-to-pdf-converter/src/com/yeokhengmeng/docstopdfconverter/MainClass.java
     *
     * @param fileLocation
     * @param pdfName
     * @param deleteResource
     * @return
     */
    public static String word2pdf(String fileLocation, String pdfName, boolean deleteResource) {
        if (StringUtils.isNotBlank(fileLocation)) {
            Converter converter;
            String docxLocation = fileLocation;
            try {
                if (docxLocation.endsWith(DOC)) {
                    docxLocation = doc2docx(docxLocation, false);
                }

                StringBuilder pdfPath = new StringBuilder()
                        .append(System.getProperty("user.dir"))
                        .append(File.separator)
                        .append("src")
                        .append(File.separator)
                        .append("main")
                        .append(File.separator)
                        .append("resources")
                        .append(File.separator)
                        .append("public")
                        .append(File.separator);
                if (StringUtils.isBlank(pdfName)) {
                    pdfName = System.currentTimeMillis() + ".pdf";
                }
                pdfPath.append(pdfName);

                @Cleanup InputStream inputStream = new FileInputStream(ResourceUtils.getFile(docxLocation));
                @Cleanup OutputStream outputStream = new FileOutputStream(ResourceUtils.getFile(pdfPath.toString()));
                converter = new DocxToPDFConverter(inputStream, outputStream, true, true);

                if (converter != null) {
                    converter.convert();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (deleteResource) {
                    FileUtils.delete(fileLocation);
                }
                if (!docxLocation.equalsIgnoreCase(fileLocation)) {
                    // 删除生成的 docx 文档
                    FileUtils.delete(docxLocation);
                }
            }
        }

        return null;
    }

    /**
     * word 转 html
     * https://github.com/aspose-words/Aspose.Words-for-Java/blob/master/Plugins/Aspose_Words_Java_for_Docx4j/src/main/java/com/aspose/words/examples/featurescomparison/documents/converttoformats/AsposeConvertToFormats.java
     *
     * @param inputStream
     */
    public static String word2html(InputStream inputStream) {
        try {
            String htmlPath = System.getProperty("user.dir") + File.separator + "src" + File.separator +
                    "main" + File.separator + "resources" + File.separator + "public" + File.separator + System.currentTimeMillis() + ".html";

            com.aspose.words.Document document = new com.aspose.words.Document(inputStream);
            document.save(htmlPath, SaveFormat.HTML);

            return htmlPath;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * word 转 html
     * https://docs.aspose.com/display/wordsjava/Convert+Document+to+HTML
     *
     * @param inputStream
     */
    public static void word2html2(InputStream inputStream) {

    }

}

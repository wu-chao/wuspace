package com.github.wuchao.webproject.util.plutext;

import com.aspose.words.Document;
import com.aspose.words.*;
import com.github.wuchao.webproject.util.FileUtils;
import com.github.wuchao.webproject.util.XHtmlUtils;
import com.github.wuchao.webproject.util.ZipUtils;
import com.github.wuchao.webproject.util.plutext.htmleditor.HtmlToWordUtils;
import com.plutext.merge.BlockRange;
import com.plutext.merge.DocumentBuilder;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.XmlUtils;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.Parts;
import org.docx4j.openpackaging.parts.WordprocessingML.*;
import org.docx4j.org.apache.poi.util.IOUtils;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.CommentRangeEnd;
import org.docx4j.wml.CommentRangeStart;
import org.docx4j.wml.*;
import org.springframework.util.ResourceUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.List;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * docx4j基本操作: https://www.cnblogs.com/cxxjohnson/p/7833911.html
 */
@Slf4j
public abstract class Docx4jUtils {

    private final static ObjectFactory objectFactory = Context.getWmlObjectFactory();

    private static final String DOC = ".doc";
    private static final String DOCX = ".docx";


    /**
     * 合并文档方法一
     */

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

    /**
     * 插入文档
     *
     * @param main
     * @param bytes
     * @param chunkId
     * @param text    在插入的地方增加摘要信息（如：附件一）
     */
    private static void insertDocx(MainDocumentPart main, byte[] bytes, int chunkId, String text) {
        try {

            // 分页插入
            addPageBreak(main);

            // 增加摘要信息（宋体（中文标题），小四号字体）
            addHeading(main, text, "majorEastAsia", 24);

            // 开始合并文档

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
            int byteRead;
            try {
                fos = new FileOutputStream(toDocPath);
                byte[] buffer = new byte[1444];
                while ((byteRead = fis.read(buffer)) != -1) {
                    // 字节数 文件大小
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
     * 合并文档方法二
     */

    /**
     * 合并多个 .Docx 文档（合并后能保留批注）
     * 直接在上一个文件后 append 下一个文件
     * 实现简单，自主可控，效率也很高，其它方法都有一些小问题和不确定因素
     *
     * @param mergeDocLocations
     * @throws Exception
     */
    public static void mergeDocx2(List<String> mergeDocLocations) throws Exception {
        if (CollectionUtils.isNotEmpty(mergeDocLocations)) {

            List<String> deleteLocations = new ArrayList<>();
            deleteLocations.add(mergeDocLocations.get(0));
            // 批注索引
            int commentIndex = 0;

            try {

                // 批注
                Map<BigInteger, Comments.Comment> commentMaps = new HashMap<>();

                File outFile = ResourceUtils.getFile(mergeDocLocations.get(0));
                WordprocessingMLPackage mainDocx = WordprocessingMLPackage.load(outFile);
                MainDocumentPart mainDocumentPart = mainDocx.getMainDocumentPart();

                for (int i = 1; i < mergeDocLocations.size(); i++) {

                    // 分页插入
                    addPageBreak(mainDocumentPart);

                    // 附件
                    File file;
                    String docLocation = mergeDocLocations.get(i);
                    String docLocation2 = docLocation;
                    if (docLocation.endsWith(".doc")) {
                        // 如果是 .doc，则转换成 .docx
                        docLocation2 = docLocation.replace(".doc", ".docx");
                        File file1 = new File(docLocation2);

                        if (!file1.exists() && !file1.createNewFile()) {
                            break;
                        }

                        Document doc = new Document(docLocation);
                        doc.save(docLocation2);
                        deleteLocations.add(docLocation2);

                        file = file1;

                    } else {

                        file = ResourceUtils.getFile(docLocation2);
                    }

                    WordprocessingMLPackage wordprocessingMLPackage = Docx4J.load(file);

                    // 获取附件的批注
                    List<Comments.Comment> commentList = getComments(wordprocessingMLPackage);
                    int comIndex = commentIndex;
                    if (CollectionUtils.isNotEmpty(commentList)) {
                        for (int j = 0; j < commentList.size(); j++) {
                            Comments.Comment comment = commentList.get(j);
                            // 修改批注id
                            comment.setId(BigInteger.valueOf(comIndex++));
                            commentMaps.put(comment.getId(), comment);
                        }

                    }

                    P p = createHeading("附件" + (i), "宋体", 24);
                    if (docLocation.endsWith(".doc")) {
                        wordprocessingMLPackage.getMainDocumentPart().getContent().set(0, p);
                    } else {
                        mainDocumentPart.getContent().add(p);
                    }

                    // 追加附件
                    if (comIndex == commentIndex) {
                        append(mainDocumentPart, wordprocessingMLPackage.getMainDocumentPart().getContent(), null, false);
                    } else {
                        append(mainDocumentPart, wordprocessingMLPackage.getMainDocumentPart().getContent(), commentIndex, true);
                        commentIndex = comIndex;
                    }

                    deleteLocations.add(docLocation);

                }

                // 在合并后的文档中重新设置批注
                if (MapUtils.isNotEmpty(commentMaps)) {
                    Comments comments = addDocumentCommentsPart(mainDocx);
                    List<Comments.Comment> commentList = commentMaps.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
                    setComments(comments, commentList);
                }

                mainDocx.save(outFile);

            } finally {

                if (CollectionUtils.isNotEmpty(deleteLocations)) {
                    // 删除本地临时文件
                    FileUtils.delete(deleteLocations);
                }

            }

        }

    }

    /**
     * 合并文档方法三
     */
    /**
     * 合并文档（合并后能保留批注）
     * 从下一页开始合并
     *
     * @param mergeDocLocations
     */
    public static void mergeDocx3(List<String> mergeDocLocations) {
        if (CollectionUtils.isNotEmpty(mergeDocLocations)) {
            List<String> deleteLocations = new ArrayList<>(mergeDocLocations.size());
            try {
                // Create list of docx packages to merge
                List<WordprocessingMLPackage> wmlPkgList = new ArrayList<>(mergeDocLocations.size());

                for (int i = 0; i < mergeDocLocations.size(); i++) {
                    System.out.println("Loading " + mergeDocLocations.get(i));

                    // 附件
                    File file;
                    String docLocation = mergeDocLocations.get(i);
                    String docLocation2 = docLocation;
                    deleteLocations.add(docLocation2);

                    if (docLocation2.endsWith(".doc")) {
                        // 如果是.doc，则转换成 .docx
                        docLocation2 = docLocation.replace(".doc", ".docx");
                        deleteLocations.add(docLocation2);

                        File file1 = new File(docLocation2);

                        if (!file1.exists() && !file1.createNewFile()) {
                            break;
                        }

                        Document doc = new Document(docLocation);
                        doc.save(docLocation2);

                        file = file1;

                    } else {

                        file = ResourceUtils.getFile(docLocation2);
                    }

                    WordprocessingMLPackage wordprocessingMLPackage = WordprocessingMLPackage.load(file);
                    // 去除 Aspose Words 水印
                    if (docLocation.endsWith(".doc")) {
                        wordprocessingMLPackage.getMainDocumentPart().getContent().remove(0);
                    }

                    wmlPkgList.add(wordprocessingMLPackage);

                }

                // Use reflection, so docx4j can be built
                // by users who don't have the MergeDocx utility
                Class<?> documentBuilder = Class.forName("com.plutext.merge.DocumentBuilder");
                Method[] methods = documentBuilder.getMethods();
                Method method = null;
                for (int j = 0; j < methods.length; j++) {
                    System.out.println(methods[j].getName());
                    if (methods[j].getName().equals("merge")) {
                        method = methods[j];
                        break;
                    }
                }

                if (method == null) {
                    throw new NoSuchMethodException();
                }

                WordprocessingMLPackage resultPkg = (WordprocessingMLPackage) method.invoke(null, wmlPkgList);
                resultPkg.save(ResourceUtils.getFile(mergeDocLocations.get(0)));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 合并文档方法四
     */
    /**
     * 合并文档
     * MergeDocx（https://www.plutext.com/MergeDocx/docx4j_Enterprise_Manual_3_3_0_MergeDocx.htm）
     * MergeDocx（https://github.com/plutext/docx4j/blob/master/src/samples/docx4j/org/docx4j/samples/MergeDocx.java）
     * 使用docx4j合并多个word文档（http://qryt520.iteye.com/blog/2088495）
     * 如何使用Docx4J合并docx及pptx文档（https://blog.csdn.net/zhyh1986/article/details/78813857）
     *
     * @param mergeDocLocations
     * @param nextPage          是否从下一页开始合并
     * @throws Exception
     */
    public static void mergeDocx4(List<String> mergeDocLocations, boolean nextPage) throws Exception {
        if (CollectionUtils.isNotEmpty(mergeDocLocations)) {

            List<String> deleteLocations = new ArrayList<>();
            List<BlockRange> blockRanges = new ArrayList<>(mergeDocLocations.size() - 1);

            for (int i = 0; i < mergeDocLocations.size(); i++) {

                // 附件
                File file;
                String docLocation = mergeDocLocations.get(i);
                String docLocation2 = docLocation;
                if (docLocation.endsWith(".doc")) {
                    // 如果是.doc，则转换成 .docx
                    docLocation2 = docLocation.replace(".doc", ".docx");
                    File file1 = new File(docLocation2);

                    if (!file1.exists() && !file1.createNewFile()) {
                        break;
                    }

                    Document doc = new Document(docLocation);
                    doc.save(docLocation2);

                    file = file1;

                } else {

                    file = ResourceUtils.getFile(docLocation2);
                }

                WordprocessingMLPackage wordprocessingMLPackage = Docx4J.load(file);

                // 去除 Aspose Words 水印
                if (docLocation.endsWith(".doc")) {
                    wordprocessingMLPackage.getMainDocumentPart().getContent().remove(0);

                    deleteLocations.add(docLocation2);
                }

                BlockRange block = new BlockRange(wordprocessingMLPackage);
                if (i > 0) {
                    if (nextPage) {
                        // add pages breaks
                        block.setSectionBreakBefore(BlockRange.SectionBreakBefore.NEXT_PAGE);
                    }
                    block.setNumberingHandler(BlockRange.NumberingHandler.ADD_NEW_LIST);
                    block.setRestartPageNumbering(false);
                    block.setHeaderBehaviour(BlockRange.HfBehaviour.DEFAULT);
                    block.setFooterBehaviour(BlockRange.HfBehaviour.DEFAULT);
                    block.setStyleHandler(BlockRange.StyleHandler.RENAME_RETAIN);
                }

                blockRanges.add(block);

            }

            // Perform the actual merge
            DocumentBuilder documentBuilder = new DocumentBuilder();
            WordprocessingMLPackage output = documentBuilder.buildOpenDocument(blockRanges);

            // Save the result
            Docx4J.save(output,
                    ResourceUtils.getFile(mergeDocLocations.get(0)),
                    Docx4J.FLAG_SAVE_ZIP_FILE);

            if (CollectionUtils.isNotEmpty(deleteLocations)) {
                deleteLocations.forEach(file -> FileUtils.delete(file));
            }

        }
    }


    /**
     * 拆分文档（代码中是根据标题的附件1、附件2、...附件n来分隔的）
     *
     * @param destPath       目标文件位置
     * @param attachmentSize 意见附件数量
     */
    public static void splitDocx(String destPath, Integer attachmentSize) {
        List<File> files = null;
        try {
            File descFile = ResourceUtils.getFile(destPath);
            WordprocessingMLPackage wordprocessingMLPackage = Docx4J.load(descFile);
            MainDocumentPart mainDocumentPart = wordprocessingMLPackage.getMainDocumentPart();
            if (mainDocumentPart != null) {

                // 批注
                Map<BigInteger, Comments.Comment> commentMap = getCommentMap(wordprocessingMLPackage);

                List<Object> objs = mainDocumentPart.getContent();

                if (objs != null) {

                    files = new ArrayList<>(attachmentSize);

                    boolean attachmentStart = false;
                    boolean[] flag = {false};
                    List<Object> objects = new ArrayList<>();

                    List<String> markNames = new ArrayList<>(attachmentSize);
                    for (int i = 1; i <= attachmentSize; i++) {
                        markNames.add("附件" + i);
                    }

                    int objSize = objs.size();
                    String objStr;
                    for (int i = 0; i < objSize; i++) {
                        Object obj = objs.get(i);

                        if ((objStr = obj.toString()).contains("附件")) {
                            objects.add(obj);

                            for (String name : markNames) {
                                if (!attachmentStart) {
                                    objects.clear();
                                }
                                if (name.equals(objStr)) {
                                    flag[0] = !flag[0];
                                    attachmentStart = true;
                                    if (objects.size() > 0) {
                                        objects.remove(objects.size() - 1);
                                    }

                                    break;
                                }
                            }

                        }
                        // 最后一个附件
                        else if (i == objSize - 1) {
                            flag[0] = !flag[0];

                        } else if (flag[0]) {
                            objects.add(obj);
                        }

                        // 导出
                        if (attachmentStart && !flag[0] && CollectionUtils.isNotEmpty(objects)) {
                            try {

                                // 删除文档内容最后面的空白内容，否则导出的包含修订的文档打开会提示错误
                                for (int j = objects.size() - 1; j > 0; j--) {
                                    if (StringUtils.isNotBlank(objects.get(j).toString())) {
                                        break;
                                    } else {
                                        objects.remove(j);
                                    }
                                }

                                // Creating new documents
                                WordprocessingMLPackage docx = WordprocessingMLPackage.createPackage();

                                append(docx, objects, null, true);

                                String title = "";
                                for (Object o : docx.getMainDocumentPart().getContent()) {
                                    if (StringUtils.isNotBlank(o.toString())) {
                                        title = o.toString();
                                        break;
                                    }
                                }
                                String descLocation = System.getProperty("user.dir") + File.separator + title + ".docx";

                                File file = new File(descLocation);
                                if (!file.exists()) {
                                    if (!file.createNewFile()) {

                                    }
                                }

                                docx.save(file);
                                objects.clear();
                                flag[0] = true;

                                files.add(file);

                            } catch (IOException | Docx4JException e) {
                                e.printStackTrace();
                            }

                        }

                    }

                    try {
                        if (files.size() > 0) {

                            if (MapUtils.isNotEmpty(commentMap)) {
                                for (File file : files) {
                                    WordprocessingMLPackage docx = Docx4J.load(file);
                                    for (Map.Entry map : commentMap.entrySet()) {
                                        Comments.Comment comment = (Comments.Comment) map.getValue();

                                        setComment(docx, (BigInteger) map.getKey(), comment.getAuthor(), comment.getDate(), comment.getContent());
                                    }
                                    docx.save(file);
                                }
                            }

                            // 打包
                            String zipFilePath = System.getProperty("usr.dir") + File.pathSeparator + System.currentTimeMillis() + ".zip";
                            ZipUtils.compress(zipFilePath, files);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

        } catch (FileNotFoundException | Docx4JException e) {
            e.printStackTrace();
        } finally {

            // 删除文件
            if (files != null) {
                files.forEach(file -> FileUtils.delete(file.getAbsolutePath()));
            }

        }
    }

    /**
     * 文档结尾追加内容
     *
     * @param docDest
     * @param docSource
     */
    public static void append(WordprocessingMLPackage docDest, WordprocessingMLPackage docSource) {
        List<Object> objects = docSource.getMainDocumentPart().getContent();
        for (Object o : objects) {
            docDest.getMainDocumentPart().getContent().add(o);
        }
    }

    /**
     * 文档结尾追加内容
     *
     * @param docDest
     * @param objects
     */
    public static void append(WordprocessingMLPackage docDest, List<Object> objects, Integer commentId, boolean removeLastEmptyOfContent) {
        if (docDest != null) {
            append(docDest.getMainDocumentPart(), objects, commentId, removeLastEmptyOfContent);
        }
    }

    /**
     * 文档结尾追加内容
     *
     * @param docDest
     * @param objects
     */
    public static void append(WordprocessingMLPackage docDest, List<Object> objects) {
        if (docDest != null) {
            append(docDest.getMainDocumentPart(), objects, null, false);
        }
    }

    /**
     * 文档结尾追加内容
     *
     * @param mainDocumentPart
     * @param objects
     */
    public static void append(MainDocumentPart mainDocumentPart, List<Object> objects, Integer commentId, boolean removeLastEmptyOfContent) {
        if (mainDocumentPart != null && CollectionUtils.isNotEmpty(objects)) {

            Integer currentCommentId = commentId != null && commentId >= 0 ? commentId : null;
            List<Object> content = mainDocumentPart.getContent();

            for (Object o : objects) {
                if (currentCommentId != null) {
                    if (o instanceof P) {
                        P p = (P) o;
                        List<Object> contents;
                        if (CollectionUtils.isNotEmpty(contents = p.getContent())) {
                            int size = contents.size();
                            for (int i = 0; i < size; i++) {
                                // 暂时只讨论有 CommentRangeStart 和 CommentRangeEnd 的批注
                                Object e = contents.get(i);
                                if (e instanceof CommentRangeStart) {
                                    ((CommentRangeStart) e).setId(BigInteger.valueOf(currentCommentId));
                                }
                                if (e instanceof CommentRangeEnd) {
                                    ((CommentRangeEnd) e).setId(BigInteger.valueOf(currentCommentId));
                                    p.getContent().set(i + 1, createRunCommentReference(BigInteger.valueOf(currentCommentId++)));
                                }
                            }
                        }
                    }
                }

                content.add(o);
            }

            if (removeLastEmptyOfContent) {
                for (int i = content.size() - 1; i > 0; i--) {
                    if (StringUtils.isBlank(content.get(i).toString())) {
                        content.remove(i);
                    } else {
                        break;
                    }
                }
            }

        }
    }

    /**
     * 增加分页，从当前行直接跳转到下页
     * https://blog.csdn.net/MAOZEXIJR/article/details/78813891
     *
     * @param mainPart 文档主体
     */
    public static void addPageBreak(MainDocumentPart mainPart) {
        // 换行
        Br br = new Br();
        // 换页方式
        br.setType(STBrType.PAGE);

        // 段落
        P paragraph = objectFactory.createP();
        paragraph.getContent().add(br);

        mainPart.getContent().add(paragraph);
    }

    /**
     * 设置行文字字体、大小、加粗、颜色
     *
     * @param factory  wml 的文档工厂，是用以创建相关 element 的工具类
     * @param fontVal  字体名称，如黑体、微软雅黑、宋体
     * @param fontSize 字体大小，镑，如12
     * @param isBlod   是否加粗
     * @param colorVal 文字颜色，如 FFFF00
     * @return
     */
    private static RPr setFontPr4Row(ObjectFactory factory, String fontVal, int fontSize, boolean isBlod, String
            colorVal) {
        RPr rowPr = factory.createRPr();

        RFonts rowFont = factory.createRFonts();
        rowFont.setHint(STHint.EAST_ASIA);
        rowFont.setAscii(fontVal);
        rowFont.setHAnsi(fontVal);
        rowPr.setRFonts(rowFont);

        HpsMeasure _fontSize = factory.createHpsMeasure();
        _fontSize.setVal(BigInteger.valueOf(fontSize));
        rowPr.setSz(_fontSize);
        rowPr.setSzCs(_fontSize);

        BooleanDefaultTrue fontBold = factory.createBooleanDefaultTrue();
        rowPr.setBCs(fontBold);
        if (isBlod) {
            rowPr.setB(fontBold);
        }

        Color color = factory.createColor();
        color.setVal(colorVal);
        rowPr.setColor(color);

        return rowPr;
    }

    /**
     * 得到指定类型的元素
     *
     * @param obj
     * @param toSearch
     * @return
     */
    public static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
        List<Object> result = new ArrayList<Object>();
        if (obj instanceof JAXBElement) {
            obj = ((JAXBElement<?>) obj).getValue();
        }
        if (obj.getClass().equals(toSearch)) {
            result.add(obj);
        } else if (obj instanceof ContentAccessor) {
            List<?> children = ((ContentAccessor) obj).getContent();
            for (Object child : children) {
                result.addAll(getAllElementFromObject(child, toSearch));
            }
        }
        return result;
    }

    public static PPr getPPr(P p) {
        PPr ppr = p.getPPr();
        if (ppr == null) {
            ppr = new PPr();
            p.setPPr(ppr);
        }
        return ppr;
    }

    public static RPr getRPr(R r) {
        RPr rpr = r.getRPr();
        if (rpr == null) {
            rpr = new RPr();
            r.setRPr(rpr);
        }
        return rpr;
    }

    /**
     * 设置字体信息
     * [简单]docx4j常用方法小结（https://www.cnblogs.com/cuizhf/p/5837363.html）
     *
     * @param p
     * @param cnFontFamily
     * @param enFontFamily
     * @param fontSize
     * @param color
     */
    public static void setFontStyle(P p, String cnFontFamily, String enFontFamily,
                                    Integer fontSize, String color) {
        List<Object> objRList = getAllElementFromObject(p, R.class);
        if (objRList != null) {
            for (Object objR : objRList) {
                if (objR instanceof R) {
                    R r = (R) objR;
                    RPr rpr = getRPr(r);
                    setFontStyle(rpr, cnFontFamily, enFontFamily, fontSize, color);
                }
            }
        }
    }

    /**
     * 设置字体信息
     *
     * @param runProperties
     * @param cnFontFamily
     * @param enFontFamily
     * @param fontSize
     * @param color
     */
    public static void setFontStyle(RPr runProperties, String cnFontFamily,
                                    String enFontFamily, Integer fontSize, String color) {
        setFontFamily(runProperties, cnFontFamily, enFontFamily);
        setFontSize(runProperties, fontSize);
        setFontColor(runProperties, color);
    }

    /**
     * 设置字体大小
     *
     * @param runProperties
     * @param fontSize
     */
    public static void setFontSize(RPr runProperties, Integer fontSize) {
        if (runProperties != null && fontSize != null) {
            HpsMeasure size = new HpsMeasure();
            size.setVal(BigInteger.valueOf(fontSize));
            runProperties.setSz(size);
            runProperties.setSzCs(size);
        }
    }

    public static RFonts createRFonts(String cnFontFamily) {
        RFonts rf = objectFactory.createRFonts();
        rf.setAscii(cnFontFamily);
        rf.setCs(cnFontFamily);
        rf.setHAnsi(cnFontFamily);
        rf.setEastAsia(cnFontFamily);
        return rf;
    }

    public static RFonts createRFonts(STTheme fontFamilyTheme) {
        RFonts rf = objectFactory.createRFonts();
        rf.setCs("Times New Roman");
        rf.setAsciiTheme(fontFamilyTheme);
        rf.setHAnsiTheme(fontFamilyTheme);
        rf.setEastAsiaTheme(fontFamilyTheme);
        return rf;
    }

    public static RFonts createRFonts(STTheme fontFamilyTheme, String cnFontFamily) {
        RFonts rf = objectFactory.createRFonts();
        if (fontFamilyTheme != null) {
            rf.setAsciiTheme(fontFamilyTheme);
            rf.setEastAsiaTheme(fontFamilyTheme);
            rf.setHAnsiTheme(fontFamilyTheme);
        }
        if (StringUtils.isNotBlank(cnFontFamily)) {
            rf.setCs(cnFontFamily);
            rf.setAscii(cnFontFamily);
            rf.setHAnsi(cnFontFamily);
            rf.setHint(STHint.EAST_ASIA);
        }

        return rf;
    }

    public static void setRFonts(P p, String cnFontFamily) {
        p.getPPr().getRPr().setRFonts(createRFonts(cnFontFamily));
    }

    public static void setRFonts(P p, STTheme fontFamilyTheme) {
        p.getPPr().getRPr().setRFonts(createRFonts(fontFamilyTheme));
    }

    public static void setRFonts(P p, STTheme fontFamilyTheme, String cnFontFamily) {
        p.getPPr().getRPr().setRFonts(createRFonts(fontFamilyTheme, cnFontFamily));
    }

    /**
     * 设置字体
     *
     * @param runProperties
     * @param cnFontFamily
     * @param enFontFamily
     */
    public static void setFontFamily(RPr runProperties, String cnFontFamily, String enFontFamily) {
        if (runProperties != null && (StringUtils.isNotBlank(cnFontFamily) || StringUtils.isNotBlank(enFontFamily))) {
            RFonts rf = runProperties.getRFonts();
            if (rf == null) {
                rf = new RFonts();
                runProperties.setRFonts(rf);
            }
            if (StringUtils.isNotBlank(cnFontFamily)) {
                rf.setEastAsia(cnFontFamily);
            }
            if (StringUtils.isNotBlank(enFontFamily)) {
                rf.setAscii(enFontFamily);
            }
        }
    }

    /**
     * 设置字体颜色
     *
     * @param runProperties
     * @param color
     */
    public static void setFontColor(RPr runProperties, String color) {
        if (runProperties != null && StringUtils.isNotBlank(color)) {
            Color c = new Color();
            c.setVal(color);
            runProperties.setColor(c);
        }
    }

    /**
     * 添加段落标题 Title of Content
     *
     * @param main
     * @param text
     * @param fontFamily
     * @param fontSize
     * @throws JAXBException
     */
    public static void addHeading(MainDocumentPart main, String text, String fontFamily, Integer
            fontSize) throws JAXBException {
        P paragraph = createHeading(text, fontFamily, fontSize);
        main.getContent().add(0, paragraph);
    }

    /**
     * 创建段落标题 Title of Content
     *
     * @param text
     * @param fontFamily
     * @param fontSize
     * @throws JAXBException
     */
    public static P createHeading(String text, String fontFamily, Integer fontSize) throws JAXBException {
        StringBuilder xml = new StringBuilder()
                .append("<w:p xmlns:w='http://schemas.openxmlformats.org/wordprocessingml/2006/main' ")
                .append("    w:rsidR='00457DDE' w:rsidRPr='0025511D' w:rsidRDefault='00581D8F' w:rsidP='0025511D'> ")
                .append("    <w:pPr> ")
                .append("        <w:adjustRightInd w:val='0'/>")
                .append("        <w:snapToGrid w:val='0'/>")
                .append("        <w:ind w:firstLine='0'/>")
                .append("        <w:rPr> ")
                .append("            <w:rFonts w:asciiTheme='").append(fontFamily).append("' w:eastAsiaTheme='").append(fontFamily).append("' w:hAnsiTheme='").append(fontFamily).append("' w:hint='eastAsia'/> ")
                .append("            <w:sz w:val='").append(fontSize).append("'/> ")
                .append("            <w:szCs w:val='").append(fontSize).append("'/> ")
                .append("        </w:rPr> ")
                .append("    </w:pPr> ")
                .append("    <w:r w:rsidRPr='0025511D'> ")
                .append("        <w:rPr> ")
                .append("            <w:rFonts w:asciiTheme='").append(fontFamily).append("' w:eastAsiaTheme='").append(fontFamily).append("' w:hAnsiTheme='").append(fontFamily).append("' w:hint='eastAsia'/> ")
                .append("            <w:sz w:val='").append(fontSize).append("'/> ")
                .append("            <w:szCs w:val='").append(fontSize).append("'/> ")
                .append("        </w:rPr> ")
                .append("        <w:lastRenderedPageBreak/>")
                .append("        <w:t>").append(text).append("</w:t> ")
                .append("    </w:r> ")
                .append("</w:p> ");

        P paragraph = (P) XmlUtils.unmarshalString(xml.toString());
        return paragraph;
    }


    /**
     * 获取所有批注（List 形式）
     *
     * @param wordMLPackage
     * @return
     */
    public static List<Comments.Comment> getComments(WordprocessingMLPackage wordMLPackage) {
        try {
            Parts parts = wordMLPackage.getParts();
            HashMap<PartName, Part> partMap = parts.getParts();
            CommentsPart commentPart = (CommentsPart) partMap.get(new CommentsPart().getPartName());

            if (commentPart != null) {
                Comments comments = commentPart.getContents();
                List<Comments.Comment> commentList = comments.getComment();
                return commentList;
            }

        } catch (Docx4JException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 获取所有批注（Map 形式）
     * <p>
     * https://www.programcreek.com/java-api-examples/?api=org.docx4j.wml.Comments
     *
     * @param wordMLPackage
     * @return
     */
    public static Map<BigInteger, Comments.Comment> getCommentMap(WordprocessingMLPackage wordMLPackage) {
        List<Comments.Comment> commentList = getComments(wordMLPackage);
        if (CollectionUtils.isNotEmpty(commentList)) {
            return commentList.stream().collect(Collectors.toMap(CTMarkup::getId, c -> c, (oldValue, newValue) -> newValue));
        }

        return null;
    }


    /**
     * 重置批注
     * <p>
     * https://github.com/plutext/docx4j/blob/master/src/samples/docx4j/org/docx4j/samples/CommentsSample.java
     *
     * @param wordMLPackage
     * @param commentId
     * @param author
     * @param date
     * @param message
     * @throws Exception
     */
    public static void setComment(WordprocessingMLPackage wordMLPackage, BigInteger commentId, String author, XMLGregorianCalendar date, List<Object> message) throws Exception {

        // Create and add a Comments Part
        CommentsPart cp = new CommentsPart();
        wordMLPackage.getMainDocumentPart().addTargetPart(cp);

        // Part must have minimal contents
        Comments comments = objectFactory.createComments();
        cp.setJaxbElement(comments);

        // Add a comment to the comments part
        Comments.Comment theComment = createComment(commentId, author, date, message);
        comments.getComment().add(theComment);

    }

    public static void setComment(WordprocessingMLPackage wordMLPackage, Comments.Comment comment) throws Exception {

        // Create and add a Comments Part
        CommentsPart cp = new CommentsPart();
        wordMLPackage.getMainDocumentPart().addTargetPart(cp);

        // Part must have minimal contents
        Comments comments = objectFactory.createComments();
        cp.setJaxbElement(comments);

        comments.getComment().add(comment);

    }

    public static Comments addDocumentCommentsPart(
            WordprocessingMLPackage wordMLPackage) throws Exception {
        CommentsPart cp = new CommentsPart();
        wordMLPackage.getMainDocumentPart().addTargetPart(cp);
        Comments comments = objectFactory.createComments();
        cp.setJaxbElement(comments);
        return comments;
    }

    public static void setComments(Comments comments, List<Comments.Comment> commentList) {

        comments.getComment().addAll(commentList);

    }


    /**
     * 创建批注内容
     *
     * @param commentId
     * @param author
     * @param date
     * @param commentContents
     * @return
     */
    private static org.docx4j.wml.Comments.Comment createComment(java.math.BigInteger commentId,
                                                                 String author, XMLGregorianCalendar date, List<Object> commentContents) {

        org.docx4j.wml.Comments.Comment comment = objectFactory.createCommentsComment();

        comment.setId(commentId);
        if (author != null) {
            comment.setAuthor(author);
        }
        if (date != null) {
            comment.setDate(date);
        }

        org.docx4j.wml.P commentP = objectFactory.createP();
        comment.getEGBlockLevelElts().add(commentP);
        org.docx4j.wml.R commentR = objectFactory.createR();
        commentP.getContent().add(commentR);

        commentR.getContent().addAll(commentContents);

        return comment;
    }


    /**
     * 创建批注内容
     *
     * @param commentId
     * @param author
     * @param date
     * @param commentContent
     * @param commentRPr
     * @return
     */
    public static Comments.Comment createComment(BigInteger commentId, String author, Date date,
                                                 String commentContent, RPr commentRPr) {

        Comments.Comment comment = objectFactory.createCommentsComment();
        comment.setId(commentId);
        if (author != null) {
            comment.setAuthor(author);
        }
        if (date != null) {
            comment.setDate(Docx4jUtils.toXMLCalendar(date));
        }
        P commentP = objectFactory.createP();
        comment.getEGBlockLevelElts().add(commentP);
        R commentR = objectFactory.createR();
        commentP.getContent().add(commentR);
        Text commentText = objectFactory.createText();
        commentR.getContent().add(commentText);
        if (commentRPr != null) {
            commentR.setRPr(commentRPr);
        }
        commentText.setValue(commentContent);
        return comment;
    }


    /**
     * 创建批注
     *
     * @param comments
     * @param p
     * @param pContent
     * @param commentId
     * @param author
     * @param commentContent
     * @param fontRPr
     * @param commentRPr
     */
    public static void createCommentEnd(Comments comments, P p, String pContent,
                                        BigInteger commentId, String author, String commentContent,
                                        RPr fontRPr, RPr commentRPr) {

        // 要批注的段落文本内容
        Text txt = objectFactory.createText();
        txt.setValue(pContent);

        R run = objectFactory.createR();
        run.getContent().add(txt);
        if (fontRPr != null) {
            run.setRPr(fontRPr);
        }

        p.getContent().add(run);

        // 批注
        Comments.Comment commentOne = createComment(commentId, author, new Date(), commentContent, commentRPr);
        comments.getComment().add(commentOne);

        // 关联批注和文本内容
        p.getContent().add(createRunCommentReference(commentId));

    }

    /**
     * 创建批注(选定范围)
     */
    public static void createCommentRound(Comments comments, P p, String pContent,
                                          BigInteger commentId, String author, String commentContent,
                                          RPr fontRPr, RPr commentRPr) {

        // 批注起始位置
        CommentRangeStart startComment = objectFactory.createCommentRangeStart();
        startComment.setId(commentId);
        p.getContent().add(startComment);

        // 要批注的段落文本内容
        R run = objectFactory.createR();
        Text txt = objectFactory.createText();
        txt.setValue(pContent);
        run.getContent().add(txt);
        run.setRPr(fontRPr);
        p.getContent().add(run);

        // 批注结束位置
        CommentRangeEnd endComment = objectFactory.createCommentRangeEnd();
        endComment.setId(commentId);
        p.getContent().add(endComment);

        // 批注
        Comments.Comment commentOne = createComment(commentId, author,
                new Date(), commentContent, commentRPr);
        comments.getComment().add(commentOne);

        // // 关联批注和文本内容
        p.getContent().add(createRunCommentReference(commentId));

    }


    /**
     * 创建批注和文本内容的关联引用
     *
     * @param commentId
     * @return
     */
    public static R createRunCommentReference(BigInteger commentId) {
        R run = objectFactory.createR();
        R.CommentReference commentRef = objectFactory.createRCommentReference();
        run.getContent().add(commentRef);
        commentRef.setId(commentId);
        return run;
    }


    // 字体样式
    public static RPr getRPrStyle(String fontFamily, String colorVal, String fontSize, STHint sTHint, boolean isBlod,
                                  boolean isItalic, boolean isStrike, boolean isUnderLine,
                                  UnderlineEnumeration underLineStyle, String underLineColor,
                                  boolean isHightLight, String hightLightValue, boolean isShd,
                                  STShd shdValue, String shdColor, CTVerticalAlignRun stRunEnum) {
        RPr rPr = objectFactory.createRPr();
        RFonts rf = new RFonts();
        if (sTHint != null) {
            rf.setHint(sTHint);
        }
        if (fontFamily != null) {
            rf.setAscii(fontFamily);
            rf.setEastAsia(fontFamily);
            rf.setHAnsi(fontFamily);
        }
        rPr.setRFonts(rf);
        if (colorVal != null) {
            Color color = new Color();
            color.setVal(colorVal);
            rPr.setColor(color);
        }
        if (fontSize != null) {
            HpsMeasure sz = new HpsMeasure();
            sz.setVal(new BigInteger(fontSize));
            rPr.setSz(sz);
            rPr.setSzCs(sz);
        }


        BooleanDefaultTrue bdt = objectFactory.createBooleanDefaultTrue();
        if (isBlod) {
            rPr.setB(bdt);
        }
        if (isItalic) {
            rPr.setI(bdt);
        }
        if (isStrike) {
            rPr.setStrike(bdt);
        }
        if (isUnderLine) {
            U underline = new U();
            if (underLineStyle != null) {
                underline.setVal(underLineStyle);
            }
            if (underLineColor != null) {
                underline.setColor(underLineColor);
            }
            rPr.setU(underline);
        }
        if (isHightLight) {
            Highlight hight = new Highlight();
            hight.setVal(hightLightValue);
            rPr.setHighlight(hight);
        }
        if (isShd) {
            CTShd shd = new CTShd();
            if (shdColor != null) {
                shd.setColor(shdColor);
            }
            if (shdValue != null) {
                shd.setVal(shdValue);
            }
            rPr.setShd(shd);
        }
        if (stRunEnum != null) {
            rPr.setVertAlign(stRunEnum);
        }
        return rPr;
    }


    // 段落底纹
    public static void setParagraphShdStyle(P p, boolean isShd,
                                            STShd shdValue, String shdColor) {
        if (isShd) {
            PPr ppr = objectFactory.createPPr();
            CTShd shd = new CTShd();
            if (shdColor != null) {
                shd.setColor(shdColor);
            }
            if (shdValue != null) {
                shd.setVal(shdValue);
            }
            ppr.setShd(shd);
            p.setPPr(ppr);
        }
    }


    // 段落间距
    public static void setParagraphSpacing(P p, boolean isSpace, String before, String after, boolean isLines,
                                           String beforeLines, String afterLines, boolean isLineRule,
                                           String lineValue, STLineSpacingRule sTLineSpacingRule) {
        PPr pPr = p.getPPr();
        if (pPr == null) {
            pPr = objectFactory.createPPr();
        }
        PPrBase.Spacing spacing = new PPrBase.Spacing();
        if (isSpace) {
            if (before != null) {
// 段前磅数
                spacing.setBefore(new BigInteger(before));
            }
            if (after != null) {
// 段后磅数
                spacing.setAfter(new BigInteger(after));
            }
        }
        if (isLines) {
            if (beforeLines != null) {
// 段前行数
                spacing.setBeforeLines(new BigInteger(beforeLines));
            }
            if (afterLines != null) {
// 段后行数
                spacing.setAfterLines(new BigInteger(afterLines));
            }
        }
        if (isLineRule) {
            if (lineValue != null) {
                spacing.setLine(new BigInteger(lineValue));
            }
            spacing.setLineRule(sTLineSpacingRule);
        }
        pPr.setSpacing(spacing);
        p.setPPr(pPr);
    }


    // 段落对齐方式
    public void setParagraphAlign(P p, JcEnumeration jcEnumeration, PPrBase.TextAlignment textAlign) {
        PPr pPr = p.getPPr();
        if (pPr == null) {
            pPr = objectFactory.createPPr();
        }
        if (jcEnumeration != null) {
            Jc jc = pPr.getJc();
            if (jc == null) {
                jc = new Jc();
            }
            jc.setVal(jcEnumeration);
            pPr.setJc(jc);
        }
        if (textAlign != null) {
            pPr.setTextAlignment(textAlign);
        }
        p.setPPr(pPr);
    }

    public static XMLGregorianCalendar toXMLCalendar(Date d) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(d);
        XMLGregorianCalendar xml = null;

        try {
            xml = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar();
            xml.setYear(gc.get(Calendar.YEAR));
            xml.setMonth(gc.get(Calendar.MONTH) + 1);
            xml.setDay(gc.get(Calendar.DAY_OF_MONTH));
            xml.setHour(gc.get(Calendar.HOUR_OF_DAY));
            xml.setMinute(gc.get(Calendar.MINUTE));
            xml.setSecond(gc.get(Calendar.SECOND));

        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        return xml;
    }

    public static void addLineBreak(MainDocumentPart mainDocumentPart) throws JAXBException {
        List<Object> objects = mainDocumentPart.getContent();
        if (CollectionUtils.isNotEmpty(objects)) {
            int size = objects.size();
            for (int i = 0; i < size; i++) {
                if (objects.get(i) instanceof P) {
                    P p = addLineBreak(objects.get(i).toString(), "黑体", 28);
                    if (p != null) {
                        P oldP = (P) objects.get(i);
                        p.setRsidRPr(oldP.getRsidRPr());
                        p.setRsidR(oldP.getRsidR());
                        p.setRsidP(oldP.getRsidP());
                        p.setRsidDel(oldP.getRsidDel());
                        p.setRsidRDefault(oldP.getRsidRDefault());
                        p.setParaId(oldP.getParaId());
                        p.setTextId(oldP.getTextId());
                        p.setParent(oldP.getParent());
                        objects.set(i, p);
                    }
                }
            }
        }
    }

    /**
     * 换行
     *
     * @param text
     * @param fontFamily
     * @param fontSize
     * @return
     * @throws JAXBException
     */
    public static P addLineBreak(String text, String fontFamily, Integer fontSize) throws JAXBException {
        String[] texts = text.split("\\\\r|\\\\n|\\\\p");
        if (texts.length > 1) {
            StringBuilder xml = new StringBuilder()
                    .append("<w:p xmlns:w='http://schemas.openxmlformats.org/wordprocessingml/2006/main'> ")
                    .append("    <w:pPr> ")
                    .append("        <w:ind w:left='720'/>")
                    .append("    </w:pPr> ")
                    .append("    <w:r> ")
                    .append("        <w:rPr> ")
                    .append("            <w:rFonts w:asciiTheme='").append(fontFamily).append("' w:eastAsiaTheme='").append(fontFamily).append("' w:hAnsiTheme='").append(fontFamily).append("' w:hint='eastAsia'/> ")
                    .append("            <w:sz w:val='").append(fontSize).append("'/> ")
                    .append("            <w:szCs w:val='").append(fontSize).append("'/> ")
                    .append("        </w:rPr> ");
            for (int i = 0; i < texts.length; i++) {
                xml.append("        <w:t>").append(texts[i]).append("</w:t> ");
                if (i < texts.length - 1) {
                    xml.append("        <w:br/>");
                }
            }
            xml.append("    </w:r> ")
                    .append("</w:p> ");

            P paragraph = (P) XmlUtils.unmarshalString(xml.toString());
            return paragraph;

        } else {
            return null;
        }
    }

    /**
     * 换行
     *
     * @param mainDocumentPart
     */
    public static void addLineBreak2(MainDocumentPart mainDocumentPart) {
        List<Object> objects = mainDocumentPart.getContent();
        if (CollectionUtils.isNotEmpty(objects)) {
            for (int i = 0; i < objects.size(); i++) {
                String text = objects.get(i).toString();
                if (text.length() > 0) {
                    String[] texts = text.split("\\\\r|\\\\n|\\\\p");
                    if (texts.length > 1) {
                        P spc = objectFactory.createP();
                        R rspc = objectFactory.createR();

                        for (int j = 0; j < texts.length; j++) {
                            Text t1 = objectFactory.createText();
                            if (j > 0) {
                                t1.setValue("\r\n " + texts[j]);
                            } else {
                                t1.setValue(texts[j]);
                            }
                            rspc.getContent().add(t1);

                            if (j < texts.length - 1) {
                                // this Br element is used break the current and go for next line
                                Br br = objectFactory.createBr();
                                rspc.getContent().add(br);
                            }
                        }

                        spc.getContent().add(rspc);
                        objects.set(i, spc);

                    }
                }
            }
        }
    }

    /**
     * 提取word图片
     *
     * @param wordMLPackage
     * @param savePath
     * @throws Exception
     */
    public static List<String> saveDocxImg(WordprocessingMLPackage wordMLPackage, String savePath) throws Exception {
        List<String> imgPaths = new ArrayList<>();
        for (Map.Entry<PartName, Part> entry : wordMLPackage.getParts().getParts().entrySet()) {
            if (entry.getValue() instanceof BinaryPartAbstractImage) {
                BinaryPartAbstractImage binImg = (BinaryPartAbstractImage) entry
                        .getValue();
                // 图片minetype
                String imgContentType = binImg.getContentType();
                PartName pt = binImg.getPartName();
                String fileName = null;
                if (pt.getName().indexOf("word/media/") != -1) {
                    fileName = pt.getName().substring(
                            pt.getName().indexOf("word/media/")
                                    + "word/media/".length());
                }
                System.out.println(String.format("mimetype=%s,filePath=%s",
                        imgContentType, pt.getName()));
                String path = savePath + fileName;
                imgPaths.add(path);
                FileOutputStream fos = new FileOutputStream(path);
                ((BinaryPart) entry.getValue()).writeDataToOutputStream(fos);
                fos.close();
            }
        }
        return imgPaths;
    }

    /**
     * 替换变量并输出word文档
     * [完美解决docx4j变量替换问题](https://blog.csdn.net/qq_35598240/article/details/84439929)
     *
     * @param inFile
     * @param map
     * @param outFile
     */
    public static void replaceDocUseDoc4j(File inFile, HashMap<String, String> map, File outFile) {
        try {
            WordprocessingMLPackage doc = WordprocessingMLPackage.load(inFile);
            MainDocumentPart mainDocumentPart = doc.getMainDocumentPart();
            if (null != map && !map.isEmpty()) {
                // 将${}里的内容结构层次替换为一层
                Docx4jUtils.cleanDocumentPart(mainDocumentPart);
                // 替换文本内容
                mainDocumentPart.variableReplace(map);
            }

            // 输出word文件
            doc.save(outFile);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    /**
     * cleanDocumentPart
     *
     * @param documentPart
     */
    public static boolean cleanDocumentPart(MainDocumentPart documentPart) throws Exception {
        if (documentPart == null) {
            return false;
        }
        org.docx4j.wml.Document document = documentPart.getContents();
        String wmlTemplate =
                XmlUtils.marshaltoString(document, true, false, Context.jc);
        document = (org.docx4j.wml.Document) XmlUtils.unwrap(DocxVariableClearUtils.doCleanDocumentPart(wmlTemplate, Context.jc));
        documentPart.setContents(document);
        return true;
    }

    /**
     * 清扫 docx4j 模板变量字符,通常以${variable}形式
     * <p>
     * XXX: 主要在上传模板时处理一下, 后续
     *
     * @author liliang
     * @since 2018-11-07
     */
    private static class DocxVariableClearUtils {


        /**
         * 去任意XML标签
         */
        private static final Pattern XML_PATTERN = Pattern.compile("<[^>]*>");

        private DocxVariableClearUtils() {
        }

        /**
         * start符号
         */
        private static final char PREFIX = '$';

        /**
         * 中包含
         */
        private static final char LEFT_BRACE = '{';

        /**
         * 结尾
         */
        private static final char RIGHT_BRACE = '}';

        /**
         * 未开始
         */
        private static final int NONE_START = -1;

        /**
         * 未开始
         */
        private static final int NONE_START_INDEX = -1;

        /**
         * 开始
         */
        private static final int PREFIX_STATUS = 1;

        /**
         * 左括号
         */
        private static final int LEFT_BRACE_STATUS = 2;

        /**
         * 右括号
         */
        private static final int RIGHT_BRACE_STATUS = 3;


        /**
         * doCleanDocumentPart
         *
         * @param wmlTemplate
         * @param jc
         * @return
         * @throws JAXBException
         */
        private static Object doCleanDocumentPart(String wmlTemplate, JAXBContext jc) throws JAXBException {
            // 进入变量块位置
            int curStatus = NONE_START;
            // 开始位置
            int keyStartIndex = NONE_START_INDEX;
            // 当前位置
            int curIndex = 0;
            char[] textCharacters = wmlTemplate.toCharArray();
            StringBuilder documentBuilder = new StringBuilder(textCharacters.length);
            documentBuilder.append(textCharacters);
            // 新文档
            StringBuilder newDocumentBuilder = new StringBuilder(textCharacters.length);
            // 最后一次写位置
            int lastWriteIndex = 0;
            for (char c : textCharacters) {
                switch (c) {
                    case PREFIX:
                        // TODO 不管其何状态直接修改指针,这也意味着变量名称里面不能有PREFIX
                        keyStartIndex = curIndex;
                        curStatus = PREFIX_STATUS;
                        break;
                    case LEFT_BRACE:
                        if (curStatus == PREFIX_STATUS) {
                            curStatus = LEFT_BRACE_STATUS;
                        }
                        break;
                    case RIGHT_BRACE:
                        if (curStatus == LEFT_BRACE_STATUS) {
                            // 接上之前的字符
                            newDocumentBuilder.append(documentBuilder.substring(lastWriteIndex, keyStartIndex));
                            // 结束位置
                            int keyEndIndex = curIndex + 1;
                            // 替换
                            String rawKey = documentBuilder.substring(keyStartIndex, keyEndIndex);
                            // 干掉多余标签
                            String mappingKey = XML_PATTERN.matcher(rawKey).replaceAll("");
                            if (!mappingKey.equals(rawKey)) {
                                char[] rawKeyChars = rawKey.toCharArray();
                                // 保留原格式
                                StringBuilder rawStringBuilder = new StringBuilder(rawKey.length());
                                // 去掉变量引用字符
                                for (char rawChar : rawKeyChars) {
                                    if (rawChar == PREFIX || rawChar == LEFT_BRACE || rawChar == RIGHT_BRACE) {
                                        continue;
                                    }
                                    rawStringBuilder.append(rawChar);
                                }
                                // FIXME 要求变量连在一起
                                String variable = mappingKey.substring(2, mappingKey.length() - 1);
                                int variableStart = rawStringBuilder.indexOf(variable);
                                if (variableStart > 0) {
                                    rawStringBuilder = rawStringBuilder.replace(variableStart, variableStart + variable.length(), mappingKey);
                                }
                                newDocumentBuilder.append(rawStringBuilder.toString());
                            } else {
                                newDocumentBuilder.append(mappingKey);
                            }
                            lastWriteIndex = keyEndIndex;

                            curStatus = NONE_START;
                            keyStartIndex = NONE_START_INDEX;
                        }
                    default:
                        break;
                }
                curIndex++;
            }
            // 余部
            if (lastWriteIndex < documentBuilder.length()) {
                newDocumentBuilder.append(documentBuilder.substring(lastWriteIndex));
            }
            return XmlUtils.unmarshalString(newDocumentBuilder.toString(), jc);
        }

    }

    /**
     * 清除段落布局（aspose.words）
     * 在设置文档为只读文档时，出现过文档有部分内容格式乱了，但是在 WPS 打开显示正确，WPS 上提示清楚段落布局
     * https://github.com/Aspose/Aspose.Words-for-Java/blob/master/Examples/src/main/java/com/aspose/words/examples/programming_documents/document/RemovePageAndSectionBreaks.java
     *
     * @param filePath
     */
    public static void clearParagraphLayout(String filePath) {
        try {
            Document document = new Document(filePath);

            // Remove the page and section breaks from the document.
            // In Aspose.Words section breaks are represented as separate Section nodes in the document.
            // To remove these separate sections the sections are combined.
            removePageBreaks(document);
            removeSectionBreaks(document);

            document.save(filePath);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Docx4JException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Removes all page breaks from the document
     *
     * @param doc
     * @throws Exception
     */
    private static void removePageBreaks(Document doc) throws Exception {
        // Retrieve all paragraphs in the document.
        NodeCollection paragraphs = doc.getChildNodes(NodeType.PARAGRAPH, true);

        // Iterate through all paragraphs
        for (Paragraph para : (Iterable<Paragraph>) paragraphs) {
            // If the paragraph has a page break before set then clear it.
            if (para.getParagraphFormat().getPageBreakBefore()) {
                para.getParagraphFormat().setPageBreakBefore(false);
            }

            // Check all runs in the paragraph for page breaks and remove them.
            for (Run run : para.getRuns()) {
                if (run.getText().contains(ControlChar.PAGE_BREAK)) {
                    run.setText(run.getText().replace(ControlChar.PAGE_BREAK, ""));
                }
            }
        }
    }

    /**
     * Combines all sections in the document into one
     *
     * @param doc
     * @throws Exception
     */
    private static void removeSectionBreaks(Document doc) throws Exception {
        // Loop through all sections starting from the section that precedes the last one
        // and moving to the first section.
        for (int i = doc.getSections().getCount() - 2; i >= 0; i--) {
            // Copy the content of the current section to the beginning of the last section.
            doc.getLastSection().prependContent(doc.getSections().get(i));
            // Remove the copied section.
            doc.getSections().get(i).remove();
        }
    }

    /**
     * 文档加密（设置成只读模式）
     *
     * @param filePath
     * @param protect
     * @param password
     */
    public static void encrypt(String filePath, STDocProtect protect, String password) {
        try {
            File outFile = ResourceUtils.getFile(filePath);
            WordprocessingMLPackage word = WordprocessingMLPackage.load(outFile);
            word.getProtectionSettings()
                    .restrictEditing(protect, password);
            word.save(outFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Docx4JException e) {
            e.printStackTrace();
        }
    }

    /**
     * outputStream 转 inputStream
     *
     * @param out
     * @return
     */
    public static ByteArrayInputStream parse(OutputStream out) {
        ByteArrayOutputStream baos = (ByteArrayOutputStream) out;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }

    /**
     * doc 转 docx
     *
     * @param docLocation
     * @param deleteResource 是否删除原文件
     * @return
     */
    public static String doc2docxByAspose(String docLocation, boolean deleteResource) {
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
     * 1. Aspose word 转 pdf，收费
     * 2. [docs-to-pdf-converter](https://github.com/yeokm1/docs-to-pdf-converter)，该方法不好用，
     *    还有 [How to convert MS doc to pdf](https://stackoverflow.com/questions/3022376/how-to-convert-ms-doc-to-pdf)
     * 3. 服务器上调用 LibreOffice 或 OpenOffice 提供的 word 转 pdf 服务
     * 4. LibreOffice Online
     */

    /**
     * DOC, DOCX 转 PDF
     * https://www.cnblogs.com/zhangzhxb/p/5984766.html
     * https://www.cnblogs.com/qiwu1314/p/6101400.html
     *
     * @param docLocation
     * @param fileName
     * @param deleteResource
     */
    public static String word2PdfByAspose(String docLocation, String fileName, boolean deleteResource) {

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

            // Address 是将要被转化的 word 文档
            @Cleanup InputStream inputStream = new FileInputStream(ResourceUtils.getFile(docLocation));
            Document doc = new Document(inputStream);

            File file = FileUtils.createFile(pdfPath.toString());
            @Cleanup FileOutputStream os = new FileOutputStream(file);

            // 全面支持 DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            doc.save(os, SaveFormat.PDF);

            log.info("=================================> convert doc/docx to pdf successfully.");
            return file.getAbsolutePath();

        } catch (Exception e) {
            e.printStackTrace();
            log.warn("=================================> convert doc/docx to pdf failed.");
            return null;

        } finally {
            if (deleteResource) {
                FileUtils.delete(docLocation);
            }
        }

    }

    /**
     * word 转 html
     * https://github.com/aspose-words/Aspose.Words-for-Java/blob/master/Plugins/Aspose_Words_Java_for_Docx4j/src/main/java/com/aspose/words/examples/featurescomparison/documents/converttoformats/AsposeConvertToFormats.java
     *
     * @param inputStream
     */
    public static String word2htmlByAspose(InputStream inputStream) {
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
     * docx 转 html
     * https://docs.aspose.com/display/wordsjava/Convert+Document+to+HTML
     * https://sourcegraph.com/github.com/plutext/docx4j/-/blob/docx4j-samples-docx4j/src/main/java/org/docx4j/samples/ConvertOutHtml.java
     *
     * @param inputfilepath
     */
    public static void docx2html(String inputfilepath) throws FileNotFoundException, Docx4JException {
        // Document loading (required)
        WordprocessingMLPackage wordMLPackage;
        if (inputfilepath == null) {
            // Create a docx
            System.out.println("No imput path passed, creating dummy document");
            wordMLPackage = WordprocessingMLPackage.createPackage();
            SampleDocument.createContent(wordMLPackage.getMainDocumentPart());
        } else {
            System.out.println("Loading file from " + inputfilepath);
            wordMLPackage = Docx4J.load(new java.io.File(inputfilepath));
        }

        // HTML exporter setup (required)
        // .. the HTMLSettings object
        HTMLSettings htmlSettings = Docx4J.createHTMLSettings();

        htmlSettings.setImageDirPath(System.getProperty("user.dir") + File.separator + "word_files");
        htmlSettings.setImageTargetUri("word_files");

        htmlSettings.setWmlPackage(wordMLPackage);

        /* CSS reset, see http://itumbcom.blogspot.com.au/2013/06/css-reset-how-complex-it-should-be.html
         *
         * motivated by vertical space in tables in Firefox and Google Chrome.

            If you have unwanted vertical space, in Chrome this may be coming from -webkit-margin-before and -webkit-margin-after
            (in Firefox, margin-top is set to 1em in html.css)

            Setting margin: 0 on p is enough to fix it.

            See further http://www.css-101.org/articles/base-styles-sheet-for-webkit-based-browsers/
        */
        String userCSS = "html, body, div, span, h1, h2, h3, h4, h5, h6, p, a, img,  ol, ul, li, table, caption, tbody, tfoot, thead, tr, th, td " +
                "{ margin: 0; padding: 0; border: 0;}" +
                "body {line-height: 1;} ";
        htmlSettings.setUserCSS(userCSS);

        // output to an OutputStream.
        OutputStream os;
        os = new FileOutputStream(inputfilepath + ".html");

        // If you want XHTML output
        Docx4jProperties.setProperty("docx4j.Convert.Out.HTML.OutputMethodXML", true);

        // Don't care what type of exporter you use
        //      Docx4J.toHTML(htmlSettings, os, Docx4J.FLAG_NONE);
        // Prefer the exporter, that uses a xsl transformation
        Docx4J.toHTML(htmlSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
        // Prefer the exporter, that doesn't use a xsl transformation (= uses a visitor)
        // Docx4J.toHTML(htmlSettings, os, Docx4J.FLAG_EXPORT_PREFER_NONXSL);

        System.out.println("Saved: " + inputfilepath + ".html ");
    }

    /**
     * 富文本转 docx（文字大小没有转换成功）
     * https://github.com/plutext/docx4j-ImportXHTML/blob/master/src/samples/java/org/docx4j/samples/XhtmlToDocxAndBack.java#L77
     *
     * @throws Docx4JException
     */
    public static WordprocessingMLPackage richText2Docx(String richText) {
        if (StringUtils.isNotBlank(richText)) {
            try {

                String xhtml = XHtmlUtils.html2xhtmlByTidy(richText);
                xhtml = xhtml.replaceAll("&nbsp;", "&#160;")
                        .replaceAll("<br>", "<br/>")
                        .replaceAll("<p>", "<p>&#160;&#160;&#160;&#160;&#160;&#160;");

                //        String str = " <!--[if gte mso 9]><xml><w:WordDocument><w:View>Print</w:View><w:TrackMoves>false</w:TrackMoves><w:TrackFormatting/><w:ValidateAgainstSchemas/><w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid><w:IgnoreMixedContent>false</w:IgnoreMixedContent><w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText><w:DoNotPromoteQF/><w:LidThemeOther>EN-US</w:LidThemeOther><w:LidThemeAsian>ZH-CN</w:LidThemeAsian><w:LidThemeComplexScript>X-NONE</w:LidThemeComplexScript><w:Compatibility><w:BreakWrappedTables/><w:SnapToGridInCell/><w:WrapTextWithPunct/><w:UseAsianBreakRules/><w:DontGrowAutofit/><w:SplitPgBreakAndParaMark/><w:DontVertAlignCellWithSp/><w:DontBreakConstrainedForcedTables/><w:DontVertAlignInTxbx/><w:Word11KerningPairs/><w:CachedColBalance/><w:UseFELayout/></w:Compatibility><w:BrowserLevel>MicrosoftInternetExplorer4</w:BrowserLevel><m:mathPr><m:mathFont m:val='Cambria Math'/><m:brkBin m:val='before'/><m:brkBinSub m:val='--'/><m:smallFrac m:val='off'/><m:dispDef/><m:lMargin m:val='0'/> <m:rMargin m:val='0'/><m:defJc m:val='centerGroup'/><m:wrapIndent m:val='1440'/><m:intLim m:val='subSup'/><m:naryLim m:val='undOvr'/></m:mathPr></w:WordDocument></xml><![endif]-->";
                String str = "";
                String h = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n" +
                        "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">" +
                        "<html xmlns:v='urn:schemas-microsoft-com:vml' " +
                        "xmlns:o='urn:schemas-microsoft-com:office:office' " +
                        "xmlns:w='urn:schemas-microsoft-com:office:word' " +
                        "xmlns:m='http://schemas.microsoft.com/office/2004/12/omml' " +
                        "xmlns='http://www.w3.org/TR/REC-html40'> ";
                xhtml = h + "<head>" + "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />" + str + "</head><body>" + xhtml + "</body> </html>";

                // To docx, with content controls
                WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
                XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
                wordMLPackage.getMainDocumentPart().getContent().addAll(
                        XHTMLImporter.convert(xhtml, null));

                System.out.println(XmlUtils.marshaltoString(wordMLPackage
                        .getMainDocumentPart().getJaxbElement(), true, true));

                return wordMLPackage;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;

    }

    public static String richText2Docx(String richText, String fileName, String path) {
        if (StringUtils.isNotBlank(richText)) {
            try {

                WordprocessingMLPackage wordMLPackage = richText2Docx(richText);

                String filePath = path + File.separator + fileName;
                wordMLPackage.save(ResourceUtils.getFile(filePath));

                return filePath;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;

    }

    /**
     * 富文本转 docx（文字大小没有转换成功）
     * https://github.com/plutext/docx-html-editor/blob/master/src/main/java/org/plutext/htmleditor/toDocx/Saver.java
     *
     * @param richText
     * @param fileName
     * @param path
     */
    public static String richText2Docx2(String richText, String fileName, String path) {
        return HtmlToWordUtils.richText2Docx2(richText, fileName, path);
    }

}

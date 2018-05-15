package com.wuspace.util;

import lombok.Cleanup;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author wuchao
 * @date 2018/5/15 10:16
 */
public abstract class Docx4jUtils {

    private final static Logger logger = LoggerFactory.getLogger(Docx4jUtils.class);

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
                    logger.warn("文件不存在");
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

}

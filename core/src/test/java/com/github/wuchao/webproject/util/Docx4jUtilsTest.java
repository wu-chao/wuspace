package com.github.wuchao.webproject.util;

import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class Docx4jUtilsTest {

    @Test
    public void testDoc2Pdf() {
        String resourceLocation = "classpath:files/DocxStructures.doc";
        try {
            File file = ResourceUtils.getFile(resourceLocation);
            Docx4jUtils.word2pdf(file.getAbsolutePath(), "DocxStructures.pdf", false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDocx2Pdf() {
        String resourceLocation = "classpath:files/DocxResume.docx";
        Docx4jUtils.word2pdf(resourceLocation, "DocxResume.pdf", false);
    }

}

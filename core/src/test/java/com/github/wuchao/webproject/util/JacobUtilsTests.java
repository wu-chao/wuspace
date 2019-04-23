package com.github.wuchao.webproject.util;

import org.junit.Test;

import java.io.File;

/**
 * @author wuchao
 * @date 2019/4/22 16:14
 */
public class JacobUtilsTests {

    @Test
    public void testWord2Pdf() {
        String resourceLocation = System.getProperty("user.dir") + File.separator + "Python-Django.doc";
//        String resourceLocation2 = System.getProperty("user.dir") + File.separator + "Python-Django.docx";
        String outPdfFileLocation = System.getProperty("user.dir") + File.separator + "Python-Django.pdf";
        JacobUtils.word2pdf(resourceLocation, outPdfFileLocation);
    }

    @Test
    public void testWord2Pdf2() {
        String resourceLocation = System.getProperty("user.dir") + File.separator + "Python-Django.doc";
        String resourceLocation2 = System.getProperty("user.dir") + File.separator + "Python-Django.docx";
        String outPdfFileLocation = System.getProperty("user.dir") + File.separator + "Python-Django.pdf";
        JacobUtils.convertWordFmt(resourceLocation, outPdfFileLocation, JacobUtils.PDF_FMT);
    }

    @Test
    public void testDocx2Doc() {
        String resourceLocation = System.getProperty("user.dir") + File.separator + "Python-Django.docx";
        String outPdfFileLocation = System.getProperty("user.dir") + File.separator + "Python-Django-docx-to-doc.doc";
        JacobUtils.convertWordFmt(resourceLocation, outPdfFileLocation, JacobUtils.DOC_FMT);
    }

    @Test
    public void testDoc2Docx() {
//        String resourceLocation = System.getProperty("user.dir") + File.separator + "Python-Django-docx-to-doc.doc";
        String resourceLocation = System.getProperty("user.dir") + File.separator + "1556182206361.doc";
        String outPdfFileLocation = System.getProperty("user.dir") + File.separator + "Python-Django-doc-to-docx.docx";
        JacobUtils.convertWordFmt(resourceLocation, outPdfFileLocation, JacobUtils.DOCX_FMT);
    }

}

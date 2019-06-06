package com.github.wuchao.webproject.util;

import org.junit.Test;

public class OfficeConverterTests {

    @Test
    public void testOfficeConverterToPDF() {
        OfficeConverter.wordToPdfByLibreOffice("D:\\Downloads\\aaa.doc", false);
    }

    @Test
    public void testOfficeConverterToPDF2() {
        OfficeConverter.wordToPdfByLibreOffice2("D:\\Downloads\\aaa.doc", false);
    }

    @Test
    public void testDocConverterToDocx() {
        OfficeConverter.docToDocxByLibreOffice("D:\\Downloads\\aaa.doc", false);
    }

}

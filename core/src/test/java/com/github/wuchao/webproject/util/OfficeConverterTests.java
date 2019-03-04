package com.github.wuchao.webproject.util;

import org.junit.Test;

public class OfficeConverterTests {

    @Test
    public void testOfficeConverterToPDF() {
        OfficeConverter converter = new OfficeConverter();
        converter.wordToPdfByLibreOffice("C:\\Users\\ecidi\\Desktop\\坝监安监函【2018】451号-关于印发《云南南极洛河水电站首次大坝安全注册登记检查意见》的通知.doc", false);
    }

    @Test
    public void testDocConverterToDocx() {
        OfficeConverter converter = new OfficeConverter();
        converter.docToDocxByLibreOffice("C:\\Users\\ecidi\\Desktop\\坝监安监函【2018】451号-关于印发《云南南极洛河水电站首次大坝安全注册登记检查意见》的通知.doc", false);
    }

}

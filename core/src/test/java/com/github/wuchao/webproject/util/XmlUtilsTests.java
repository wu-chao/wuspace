package com.github.wuchao.webproject.util;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XmlUtilsTests {

    /*String xml = "<w:document xmlns:dsp=\"http://schemas.microsoft.com/office/drawing/2008/diagram\" \n" +
            "    xmlns:odx=\"http://opendope.org/xpaths\" \n" +
            "    xmlns:xdr=\"http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing\" \n" +
            "    xmlns:odgm=\"http://opendope.org/SmartArt/DataHierarchy\" \n" +
            "    xmlns:wp14=\"http://schemas.microsoft.com/office/word/2010/wordprocessingDrawing\" \n" +
            "    xmlns:dgm=\"http://schemas.openxmlformats.org/drawingml/2006/diagram\" \n" +
            "    xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\" \n" +
            "    xmlns:we=\"http://schemas.microsoft.com/office/webextensions/webextension/2010/11\" \n" +
            "    xmlns:ns13=\"http://schemas.openxmlformats.org/drawingml/2006/chartDrawing\" \n" +
            "    xmlns:ns35=\"http://schemas.openxmlformats.org/drawingml/2006/lockedCanvas\" \n" +
            "    xmlns:ns34=\"http://schemas.openxmlformats.org/drawingml/2006/compatibility\" \n" +
            "    xmlns:mc=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" \n" +
            "    xmlns:ns10=\"http://schemas.openxmlformats.org/schemaLibrary/2006/main\" \n" +
            "    xmlns:w10=\"urn:schemas-microsoft-com:office:word\" \n" +
            "    xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" \n" +
            "    xmlns:w15=\"http://schemas.microsoft.com/office/word/2012/wordml\" \n" +
            "    xmlns:w14=\"http://schemas.microsoft.com/office/word/2010/wordml\" \n" +
            "    xmlns:ns18=\"urn:schemas-microsoft-com:office:excel\" \n" +
            "    xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\" \n" +
            "    xmlns:b=\"http://schemas.openxmlformats.org/officeDocument/2006/bibliography\" \n" +
            "    xmlns:c=\"http://schemas.openxmlformats.org/drawingml/2006/chart\" \n" +
            "    xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\" \n" +
            "    xmlns:oda=\"http://opendope.org/answers\" \n" +
            "    xmlns:wne=\"http://schemas.microsoft.com/office/word/2006/wordml\" \n" +
            "    xmlns:o=\"urn:schemas-microsoft-com:office:office\" \n" +
            "    xmlns:odc=\"http://opendope.org/conditions\" \n" +
            "    xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" \n" +
            "    xmlns:ns24=\"http://schemas.microsoft.com/office/2006/coverPageProps\" \n" +
            "    xmlns:odi=\"http://opendope.org/components\" \n" +
            "    xmlns:v=\"urn:schemas-microsoft-com:vml\" \n" +
            "    xmlns:ns22=\"urn:schemas-microsoft-com:office:powerpoint\" \n" +
            "    xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" \n" +
            "    xmlns:odq=\"http://opendope.org/questions\" \n" +
            "    xmlns:wetp=\"http://schemas.microsoft.com/office/webextensions/taskpanes/2010/11\">\n" +
            "    <w:body>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"2Introduction\"/>\n" +
            "            </w:pPr>\n" +
            "            <w:r>\n" +
            "                <w:rPr>\n" +
            "                    <w:sz w:val=\"28\"/>\n" +
            "                    <w:szCs w:val=\"28\"/>\n" +
            "                </w:rPr>\n" +
            "                <w:t>Ingénieur JEE – Eclipse RCP – 9</w:t>\n" +
            "            </w:r>\n" +
            "            <w:r>\n" +
            "                <w:t xml:space=\"preserve\">  ans d’expérience</w:t>\n" +
            "            </w:r>\n" +
            "        </w:p>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"934\"/>\n" +
            "                <w:gridCol w:w=\"2634\"/>\n" +
            "                <w:gridCol w:w=\"5639\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"454\" w:hRule=\"exact\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10463\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"3\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"385068\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"3titrerubrique\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"it-IT\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>DOMAINES DE COMPETENCES</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"285\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1988\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10463\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00F3274E\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t xml:space=\"preserve\"></w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"568\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1988\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge w:val=\"restart\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10463\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"4soustitre\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Compétences Fonctionnelles</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1988\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10463\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t xml:space=\"preserve\">Gestion documentaire, Logistique/Transport, Nucléaire, Agroalimentaire </w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"570\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1988\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10463\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"4soustitre\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Compétences Process</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1988\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10463\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:t xml:space=\"preserve\">Analyse, conception et développement Nouvelles </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:t>Technologies de logiciels et applications WEB.s</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:t>Rédaction de documents, manuel d’utilisation, proposition commerciales</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:t xml:space=\"preserve\">Génération code : développement plugin Eclipse </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:hyperlink r:id=\"rId7\">\n" +
            "                            <w:r w:rsidRPr=\"00F63452\">\n" +
            "                                <w:rPr>\n" +
            "                                    <w:rStyle w:val=\"LienInternet\"/>\n" +
            "                                </w:rPr>\n" +
            "                                <w:t>Akrogen</w:t>\n" +
            "                            </w:r>\n" +
            "                        </w:hyperlink>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:t>.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"568\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1988\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10463\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"4soustitre\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Compétences Techniques</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1988\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4244\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"6ajouttexte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Système :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10463\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Windows</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1988\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4244\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"6ajouttexte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>SGBD :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10463\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>Oracle 8i-9i-10g, SQL Server 7/2k, MySQL, Sybase ASA 7 et ASE 11.9.2</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1988\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4244\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"6ajouttexte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Langage :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10463\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:numPr>\n" +
            "                                <w:ilvl w:val=\"0\"/>\n" +
            "                                <w:numId w:val=\"2\"/>\n" +
            "                            </w:numPr>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\">Java : OSGI , Spring DM, Eclipse RCP, SWT/JFace, EMF, GEF, J2EE, JSP, Struts, Ant, POI, Hibernate, </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:lastRenderedPageBreak/>\n" +
            "                            <w:t>Spring, EJB2, Freemarker, Velocity, Web Service (</w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>AXIS).</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:numPr>\n" +
            "                                <w:ilvl w:val=\"0\"/>\n" +
            "                                <w:numId w:val=\"2\"/>\n" +
            "                            </w:numPr>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>HTML, CSS, JavaScript, XML, XSL, Ajax, XQuery</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1988\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4244\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"6ajouttexte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Technologies :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10463\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Java</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1988\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4244\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"6ajouttexte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Logiciels:</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10463\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:numPr>\n" +
            "                                <w:ilvl w:val=\"0\"/>\n" +
            "                                <w:numId w:val=\"3\"/>\n" +
            "                            </w:numPr>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:t>Serveurs applicatifs : Apache/Tomcat 5.0, BEA/WebLogic 6.1-8.1, Orion</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:numPr>\n" +
            "                                <w:ilvl w:val=\"0\"/>\n" +
            "                                <w:numId w:val=\"3\"/>\n" +
            "                            </w:numPr>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-GB\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>Eclipse, JBuilder 7 et 9, JBuilder X, Visual Studio</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"568\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1988\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10463\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"4soustitre\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Méthodes et Outils associés</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1988\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10463\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"68\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-GB\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\">Merise, UML </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-GB\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>(Power Designer)</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"1473\"/>\n" +
            "                <w:gridCol w:w=\"7738\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"432\" w:hRule=\"exact\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10477\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"385068\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"3titrerubrique\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"it-IT\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>REFERENCES SIGNIFICATIVES</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"285\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1950\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10477\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1950\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:ind w:firstLine=\"465\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:ascii=\"Arial\" w:hAnsi=\"Arial\" w:cs=\"Arial\"/>\n" +
            "                                <w:lang w:val=\"it-IT\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\">2009 </w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10477\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t xml:space=\"preserve\">Commiteur Eclipse depuis janvier 2009. Participe à la future version de l'IDE Eclipse E4 (IBM).  Créateur du moteur CSS de E4. </w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1950\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:ind w:firstLine=\"465\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:ascii=\"Arial\" w:hAnsi=\"Arial\" w:cs=\"Arial\"/>\n" +
            "                                <w:lang w:val=\"it-IT\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>2005- 2009</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10477\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t xml:space=\"preserve\">Projets Open Source : voir blog </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:hyperlink r:id=\"rId8\">\n" +
            "                            <w:r>\n" +
            "                                <w:rPr>\n" +
            "                                    <w:rStyle w:val=\"LienInternet\"/>\n" +
            "                                </w:rPr>\n" +
            "                                <w:t>http://angelozerr.wordpress.com/about/</w:t>\n" +
            "                            </w:r>\n" +
            "                        </w:hyperlink>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1950\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:ind w:firstLine=\"465\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10477\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:bookmarkStart w:name=\"REF\" w:id=\"0\"/>\n" +
            "                        <w:bookmarkEnd w:id=\"0\"/>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"1437\"/>\n" +
            "                <w:gridCol w:w=\"7774\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"420\" w:hRule=\"exact\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10455\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"385068\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"3titrerubrique\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"it-IT\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:lastRenderedPageBreak/>\n" +
            "                            <w:t xml:space=\"preserve\">FORMATION </w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"285\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1961\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10455\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"1098\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1961\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10455\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rStyle w:val=\"6ajouttexteCar\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\">2001 </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:t>: Diplôme d’ingénieur en informatique à l’INSA de Lyon (69).</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rStyle w:val=\"6ajouttexteCar\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>1996</w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:t>:  BAC S option Physique C</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"1694\"/>\n" +
            "                <w:gridCol w:w=\"7517\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"420\" w:hRule=\"exact\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10520\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"385068\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"3titrerubrique\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"it-IT\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>LANGUES</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"285\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1952\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10520\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1952\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:ind w:firstLine=\"708\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:ascii=\"Arial\" w:hAnsi=\"Arial\" w:cs=\"Arial\"/>\n" +
            "                                <w:lang w:val=\"it-IT\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>Anglais</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10520\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Niveau Technique</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"1952\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:ind w:firstLine=\"708\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:ascii=\"Arial\" w:hAnsi=\"Arial\" w:cs=\"Arial\"/>\n" +
            "                                <w:lang w:val=\"it-IT\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>Allemand</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10520\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t xml:space=\"preserve\">Scolaire – Pas de  pratique </w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"9211\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"454\" w:hRule=\"exact\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10594\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"385068\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"3titrerubrique\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"it-IT\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>EXPERIENCES PROFESSIONNELLES</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"1578\"/>\n" +
            "                <w:gridCol w:w=\"2557\"/>\n" +
            "                <w:gridCol w:w=\"5076\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\" w:rsidTr=\"00F63452\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"342\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge w:val=\"restart\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"8Datemissions\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:spacing w:before=\"60\" w:after=\"0\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Avril 2009 - Aujourd'hui</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"9clientmission\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>CAF</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\" w:rsidTr=\"00F63452\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet SIDoc</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\" w:rsidTr=\"00F63452\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Mission :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Conception / Développement</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\" w:rsidTr=\"00F63452\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Technique :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Java JVM Sun (JDK 6), JEE</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>base XML X-DB, XQuery, XSL.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t xml:space=\"preserve\">Ajax (Dojo </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Toolkit)</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\" w:rsidTr=\"00F63452\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Fonctionnel :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Gestion documentaire.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\" w:rsidTr=\"00F63452\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"1140\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Détail :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>Mise en place de l'application WEB de diffusion (qui sera accéssible dans les accueils des CAF) qui permet de publier les documents XML produits par l'application WEB de production.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"1520\"/>\n" +
            "                <w:gridCol w:w=\"2531\"/>\n" +
            "                <w:gridCol w:w=\"5160\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"342\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge w:val=\"restart\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"8Datemissions\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:spacing w:before=\"60\" w:after=\"0\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Septembre 2007 – Mars 2008</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"9clientmission\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>INFOLOGIC</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>ERP AgroV3</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Mission :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Conception / Développement</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Technique :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>Java Eclipse SWT, JFace, Hibernate.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Oracle 10g, Tomcat, Eclipse 3.3</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Fonctionnel :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Agroalimentaire</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"1140\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Détail :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\">Conception et </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>développement de fonctionnalités dans le  module VENTES/ACHATS et COMPTABILITE de l'ERP agrolimentaire AgroV3 de INFOLOGIC. Cet ERP est basé sur les technologies d'Eclipse SWT et JFace.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"1222\"/>\n" +
            "                <w:gridCol w:w=\"2645\"/>\n" +
            "                <w:gridCol w:w=\"5344\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"342\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge w:val=\"restart\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"8Datemissions\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:spacing w:before=\"60\" w:after=\"0\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Août 2006 à Août 2007</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"9clientmission\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>CAF</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet SIDoc</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Mission :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Conception / Développement</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Technique :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Java JVM Sun (JDK 1.4),JavaScript, ANT</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>XML X-Hive, XQuery, les Schémas XML, XForms, et DOJO Toolkit (AJAX).</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Fonctionnel :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Gestion documentaire.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"1140\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Détail :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\">Conception et développement d’un composant </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>XML Java de production et gestion documentaire (SIDoc). Intégration d’une nouvelle collection documentaire.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"1350\"/>\n" +
            "                <w:gridCol w:w=\"2613\"/>\n" +
            "                <w:gridCol w:w=\"5248\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"342\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge w:val=\"restart\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"8Datemissions\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:spacing w:before=\"60\" w:after=\"0\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Janvier 2006 à Juillet 2006</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"9clientmission\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Lafarge (Sword)</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet LAFARGE - SAFARI</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Mission :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Conception / Développement</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Technique :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\">Java </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>JVM Sun (JDK 1.4),  SPRING, Struts et Hibernate</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Oracle 0, Eclipse 3.2, Tomcat</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Fonctionnel :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Calcul scientifique &amp; beton.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"1140\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Détail :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\">Spécification, Conception et réalisation de l’application WEB Safari permettant d’effectuer des calculs </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>mathématiques à partir de modèles saisis. Les calculs étant complexes et long, AJAX a été mis en place pour afficher une barre de progression du calcul.  .</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"1496\"/>\n" +
            "                <w:gridCol w:w=\"2577\"/>\n" +
            "                <w:gridCol w:w=\"5138\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"342\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge w:val=\"restart\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"8Datemissions\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:spacing w:before=\"60\" w:after=\"0\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t xml:space=\"preserve\">Août 2005 à </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:lastRenderedPageBreak/>\n" +
            "                            <w:t>Décembre 2005</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"9clientmission\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:lastRenderedPageBreak/>\n" +
            "                            <w:t>Lafarge (Sword)</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet LAFARGE - CPM</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Mission :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Conception /</w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:t xml:space=\"preserve\"> Développement</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Technique :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Java JVM Sun (JDK 1.4),  Struts et Hibernate</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Oracle 0, Eclipse 3.2, Tomcat</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Fonctionnel :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Calcul scientifique &amp; beton.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"1140\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Détail :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\">Réalisation de l’application WEB CPM permettant d’effectuer des calculs mathématiques à partir de modèles saisis. </w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"1286\"/>\n" +
            "                <w:gridCol w:w=\"2634\"/>\n" +
            "                <w:gridCol w:w=\"5291\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"342\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge w:val=\"restart\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"8Datemissions\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:spacing w:before=\"60\" w:after=\"0\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Juin 2005 à Juillet 2005</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"9clientmission\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Wyniwyg</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Etude JSF &amp; Hibernate</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Mission :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>R&amp;D</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Technique :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t xml:space=\"preserve\">Java JVM Sun (JDK 1.4),  JSF et </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Hibernate</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Oracle 0, Eclipse 3.2, Tomcat</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Fonctionnel :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>R&amp;D</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"1140\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Détail :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Étude du nouveau framework Java Server Faces. Comparaison avec STRUTS. Test des outils de développement JSF.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t xml:space=\"preserve\">Etude Hibernate : développement d’une Application WEB qui utilise </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Hibernate dans différents contextes.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "                <w:pageBreakBefore/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"794\"/>\n" +
            "                <w:gridCol w:w=\"935\"/>\n" +
            "                <w:gridCol w:w=\"7482\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"342\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge w:val=\"restart\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"8Datemissions\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:spacing w:before=\"60\" w:after=\"0\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Septembre 2004 à Mai  2005</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"9clientmission\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>EDF</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet EDF – BRHM</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Mission :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Conception / Développement</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Technique :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\">Java JVM Sun (JDK 1.3), EJB 2.0, Framework Struts, JSP, JavaScript, ANT </w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\">Oracle 8i, WebLogic </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>6.1, Borland JBuilder X, Windows 2000, XML, XSL, Apache FOP.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Fonctionnel :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Nucléaire</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"1140\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Détail :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:tab/>\n" +
            "                            <w:t xml:space=\"preserve\">Développement d’une application nationale permettant de gérer l’annuaire des agents EDF et des agents extérieurs ainsi que leurs accueils, badges, </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>habilitations, autorisations, etc …Prise en charge de l’interfaçage de cette application avec les autres applications existantes.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"1188\"/>\n" +
            "                <w:gridCol w:w=\"2577\"/>\n" +
            "                <w:gridCol w:w=\"5446\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"342\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge w:val=\"restart\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"8Datemissions\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:spacing w:before=\"60\" w:after=\"0\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Juin 2004 à Août  2004</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"9clientmission\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Wyniwyg</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Génération code</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Mission :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Conception / Développement</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Technique :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>Windows 2000, ANT, Java, XML (JDOM), Xpath, Velocity, ANT, JavaScript, Tomcat, AXIS (Web Service)</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Fonctionnel :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Génération code</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"1140\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Détail :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\">Projet OGC, Réalisation d’un outil de génération de code :développement du moteur de génération de codes </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>s’appuyant sur ANT</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>Réalisation du module Java Struts permettant de générer toutes les entités constituant  un projet Struts.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"1501\"/>\n" +
            "                <w:gridCol w:w=\"2576\"/>\n" +
            "                <w:gridCol w:w=\"5134\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"342\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge w:val=\"restart\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"8Datemissions\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:spacing w:before=\"60\" w:after=\"0\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Novembre 2003 à Mai  2004</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"9clientmission\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>CCIP</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet CCIP - Réalisation du portail géomatique de la CCIP</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Mission :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Développement</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Technique :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>MapXtreme (MapInfo), ASP, Oracle 8i</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Fonctionnel :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Cartographie</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"1140\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Détail :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\">Cet Intranet permet la visualisation des informations statistiques de la CCIP (taux de chômage, population) en les recoupant avec une base de données </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>géographique. Les données géographiques s'appuient sur le composant MapXtreme de MapInfo.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "                <w:pageBreakBefore/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"1385\"/>\n" +
            "                <w:gridCol w:w=\"2605\"/>\n" +
            "                <w:gridCol w:w=\"5221\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"342\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge w:val=\"restart\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"8Datemissions\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:spacing w:before=\"60\" w:after=\"0\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Février 2003 à Octobre  2003</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"9clientmission\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>FFT</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Gestion sportive de la FFT</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Mission :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Conception / Développement</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Technique :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t xml:space=\"preserve\">Java Struts, Sybase ASE, HP UX 11, Linux, WebSphere 5, WSAD 5   Java Struts, Sybase ASE, HP UX 11, Linux, WebSphere 5, WSAD 5   </w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Fonctionnel :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Tennis</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"1140\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Détail :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>Réalisation d’une Application Web de gestions des tournois s’appuyant sur le framework</w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\"> STRUTS. Le langage VTXML (XML + XSL) a été utilisé pour rendre opérationnelle l’application Web sur Minitel.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"1338\"/>\n" +
            "                <w:gridCol w:w=\"2585\"/>\n" +
            "                <w:gridCol w:w=\"5288\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"342\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge w:val=\"restart\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"8Datemissions\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:spacing w:before=\"60\" w:after=\"0\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Juillet 2002 à Janvier  2003</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"9clientmission\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>CETE (Ministére Equipement)</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>CETE (Ministère de l’Équipement) - WebService</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Mission :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Développement</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Technique :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>Windows  98,Oracle 8i, Apache Tomcat 4, JBuilder 7,  Web Service Kit, Visual Basic, JAVA, XML/XSD , SOAP, Velocity , Struts.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Fonctionnel :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Ministère de l’Équipement</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"1140\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Détail :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\">Conception et développement d’un prototype de </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>WebService dans le cadre de la refonte des applications nationales du ministère initiée par un projet de dématérialisation.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>Mise en évidence les contraintes techniques, budgétaires et organisationnelles liées à l’architecture logicielle retenue (WebServic</w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>e)</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"1215\"/>\n" +
            "                <w:gridCol w:w=\"2647\"/>\n" +
            "                <w:gridCol w:w=\"5349\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"342\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge w:val=\"restart\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"8Datemissions\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:spacing w:before=\"60\" w:after=\"0\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Mai 2002 à Juin  2002</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"9clientmission\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Wyniwyg</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Génération code</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Mission :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Conception / Développement</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Technique :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>JAVA, Velocity , Struts.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Fonctionnel :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Génération code</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"1140\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Détail :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10566\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>Réalisation d’une Application Web  permettant de générer le</w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\"> code STRUTS à partir d’une requête SQL (mise à jour du struts-config, génération des JSP et des actions JAVA).</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "                <w:pageBreakBefore/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"1199\"/>\n" +
            "                <w:gridCol w:w=\"2586\"/>\n" +
            "                <w:gridCol w:w=\"5426\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"342\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge w:val=\"restart\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"8Datemissions\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:spacing w:before=\"60\" w:after=\"0\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Mars 2002 à Avril 2002</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10567\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"9clientmission\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t xml:space=\"preserve\">APAVE Lyon </w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10567\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>APAVE Lyon – Extranet Bugless</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Mission :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10567\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>Conception / Développement</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Technique :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10567\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>Java,</w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r w:rsidRPr=\"00F63452\">\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\"> Framework Struts, Velocity, JVM Sun (JDK 1.3/1.4), Windows 2000, SQL Serveur 2000, Apache/Tomcat 4.0, Orion AppServer, Borland JBuilder 7</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidRPr=\"00F63452\" w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                            <w:rPr>\n" +
            "                                <w:lang w:val=\"en-US\"/>\n" +
            "                            </w:rPr>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Fonctionnel :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10567\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Gestion de projets</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"1140\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Détail :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10567\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\">Conception, réalisation, maintenance d’une webapp de suivi de projet, incluant un module de gestion des dysfonctionnements, un module de gestion des tâches d’un projet et un module de gestion documentaire. </w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"1476\"/>\n" +
            "                <w:gridCol w:w=\"2517\"/>\n" +
            "                <w:gridCol w:w=\"5218\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"342\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge w:val=\"restart\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"8Datemissions\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:spacing w:before=\"60\" w:after=\"0\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Décembre 2001 à Février 2002</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10567\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"9clientmission\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Norbert Dentressan</w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:t>gle</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10567\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Application Client/Serveur</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Mission :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10567\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>Développement</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Technique :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10567\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Microsoft NT, MS Access, VB6</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Fonctionnel :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10567\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Logistique/Transport</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"1140\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Détail :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10567\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\">Réalisation Application Cliente. Migration de l’application existante de gestion </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>des transports de ND en client/serveur, et développement de nouvelles fonctionnalités.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:tbl>\n" +
            "            <w:tblPr>\n" +
            "                <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
            "                <w:jc w:val=\"center\"/>\n" +
            "                <w:tblCellMar>\n" +
            "                    <w:left w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                    <w:right w:w=\"10\" w:type=\"dxa\"/>\n" +
            "                </w:tblCellMar>\n" +
            "                <w:tblLook w:val=\"0000\"/>\n" +
            "            </w:tblPr>\n" +
            "            <w:tblGrid>\n" +
            "                <w:gridCol w:w=\"1531\"/>\n" +
            "                <w:gridCol w:w=\"2565\"/>\n" +
            "                <w:gridCol w:w=\"5115\"/>\n" +
            "            </w:tblGrid>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:trHeight w:val=\"342\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge w:val=\"restart\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"8Datemissions\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                            <w:spacing w:before=\"60\" w:after=\"0\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:lastRenderedPageBreak/>\n" +
            "                            <w:t>Septembre 2001 à Novembre 2001</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10567\" w:type=\"dxa\"/>\n" +
            "                        <w:gridSpan w:val=\"2\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"9clientmission\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>SOITEC</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Projet :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10567\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Extranet SOITEC</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Mission :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10567\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>Développement</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Technique :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10567\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t xml:space=\"preserve\">WebIntelligence 2.6, Windows NT4.0 Server, Oracle </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:t>8.0.5, IIS4, ASP</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"360\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Envir. Fonctionnel :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10567\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Semi-conducteur</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "            <w:tr w:rsidR=\"00983F1B\">\n" +
            "                <w:tblPrEx>\n" +
            "                    <w:tblCellMar>\n" +
            "                        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                    </w:tblCellMar>\n" +
            "                </w:tblPrEx>\n" +
            "                <w:trPr>\n" +
            "                    <w:cantSplit/>\n" +
            "                    <w:trHeight w:val=\"1140\"/>\n" +
            "                    <w:jc w:val=\"center\"/>\n" +
            "                </w:trPr>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"2080\" w:type=\"dxa\"/>\n" +
            "                        <w:vMerge/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"A5A5A5\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"Standard\"/>\n" +
            "                        </w:pPr>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"4360\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"52textebold\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:t>Détail :</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "                <w:tc>\n" +
            "                    <w:tcPr>\n" +
            "                        <w:tcW w:w=\"10567\" w:type=\"dxa\"/>\n" +
            "                        <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"auto\"/>\n" +
            "                        <w:tcMar>\n" +
            "                            <w:top w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:left w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                            <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" +
            "                            <w:right w:w=\"70\" w:type=\"dxa\"/>\n" +
            "                        </w:tcMar>\n" +
            "                    </w:tcPr>\n" +
            "                    <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"001B0BE3\">\n" +
            "                        <w:pPr>\n" +
            "                            <w:pStyle w:val=\"51texte\"/>\n" +
            "                            <w:snapToGrid w:val=\"false\"/>\n" +
            "                        </w:pPr>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t xml:space=\"preserve\">Réalisation du site extranet SOITEC autour de WEBI.  Cet extranet permet de mettre à disposition les informations des produits que l'entreprise créée, pour que ses clients (IBM…) puissent </w:t>\n" +
            "                        </w:r>\n" +
            "                        <w:r>\n" +
            "                            <w:rPr>\n" +
            "                                <w:rFonts w:cs=\"Arial\"/>\n" +
            "                                <w:szCs w:val=\"20\"/>\n" +
            "                            </w:rPr>\n" +
            "                            <w:t>les visualiser à l'aide d'un browser WEB.</w:t>\n" +
            "                        </w:r>\n" +
            "                    </w:p>\n" +
            "                </w:tc>\n" +
            "            </w:tr>\n" +
            "        </w:tbl>\n" +
            "        <w:p w:rsidR=\"00983F1B\" w:rsidRDefault=\"00983F1B\">\n" +
            "            <w:pPr>\n" +
            "                <w:pStyle w:val=\"Standard\"/>\n" +
            "            </w:pPr>\n" +
            "        </w:p>\n" +
            "        <w:sectPr w:rsidR=\"00983F1B\" w:rsidSect=\"00983F1B\">\n" +
            "            <w:headerReference w:type=\"default\" r:id=\"rId9\"/>\n" +
            "            <w:footerReference w:type=\"default\" r:id=\"rId10\"/>\n" +
            "            <w:headerReference w:type=\"first\" r:id=\"rId11\"/>\n" +
            "            <w:footerReference w:type=\"first\" r:id=\"rId12\"/>\n" +
            "            <w:pgSz w:w=\"11905\" w:h=\"16837\"/>\n" +
            "            <w:pgMar w:top=\"1417\" w:right=\"1417\" w:bottom=\"1417\" w:left=\"1417\" w:header=\"720\" w:footer=\"720\" w:gutter=\"0\"/>\n" +
            "            <w:cols w:space=\"720\"/>\n" +
            "            <w:formProt w:val=\"false\"/>\n" +
            "            <w:titlePg/>\n" +
            "            <w:docGrid w:linePitch=\"360\"/>\n" +
            "        </w:sectPr>\n" +
            "    </w:body>\n" +
            "</w:document>";*/

    /*@Test
    public void testXml2Html() throws IOException {
        String html = XmlUtils.xml2html(xml);
        File file = new File(System.currentTimeMillis() + ".html");
        file.createNewFile();
        InputStream inputStream = new FileInputStream(file);
        inputStream.read(html.getBytes("UTF-8"));
    }*/

}

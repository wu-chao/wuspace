package com.github.wuchao.webproject.domain.docstopdfconverter;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.DocumentProperties;
import org.apache.poi.hwpf.model.ListTables;
import org.apache.poi.hwpf.model.StyleSheet;
import org.apache.poi.hwpf.usermodel.*;
import org.docx4j.UnitsOfMeasurement;
import org.docx4j.XmlUtils;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Copied from org.docx4j:docx4j:3.2.1
 */
public class Doc {

    private static Logger log = LoggerFactory.getLogger(Doc.class);
    private static int ID1 = 1;
    private static int ID2 = 2;

    public Doc() {
    }

    public static WordprocessingMLPackage convert(InputStream in) throws Exception {
        HWPFDocument doc = new HWPFDocument(in);
        WordprocessingMLPackage out = WordprocessingMLPackage.createPackage();
        convert(doc, out);
        return out;
    }

    private static void convert(HWPFDocument doc, WordprocessingMLPackage wordMLPackage) throws Exception {
        StyleSheet stylesheet = doc.getStyleSheet();
        ListTables listTables = doc.getListTables();
        DocumentProperties docProps = doc.getDocProperties();
        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
        ObjectFactory factory = new ObjectFactory();
        Range r = doc.getRange();

        for (int x = 0; x < r.numSections(); ++x) {
            Section s = r.getSection(x);

            for (int y = 0; y < s.numParagraphs(); ++y) {
                Paragraph p = s.getParagraph(y);
                if (p.isInTable()) {
                    Table t = s.getTable(p);
                    int cl = numCol(t);
                    log.info("Found " + t.numRows() + "x" + cl + " table - TODO - convert");
                    handleTable(wordMLPackage, doc, t, stylesheet, documentPart, factory);
                    y += t.numParagraphs() - 1;
                } else {
                    P paraToAdd = handleP(wordMLPackage, doc, p, stylesheet, documentPart, factory);
                    documentPart.addObject(paraToAdd);
                }
            }
        }

    }

    private static P handleP(WordprocessingMLPackage wordMLPackage, HWPFDocument doc, Paragraph p, StyleSheet stylesheet, MainDocumentPart documentPart, ObjectFactory factory) {
        P wmlP = null;
        if (p.getStyleIndex() > 0) {
            log.debug("Styled paragraph, with index: " + p.getStyleIndex());
            String styleName = stylesheet.getStyleDescription(p.getStyleIndex()).getName();
            log.debug(styleName);
            wmlP = documentPart.createStyledParagraphOfText(stripSpace(styleName), (String) null);
        } else {
            wmlP = documentPart.createParagraphOfText((String) null);
        }

        for (int z = 0; z < p.numCharacterRuns(); ++z) {
            CharacterRun run = p.getCharacterRun(z);
            RPr rPr = null;
            if (run.isBold()) {
                if (rPr == null) {
                    rPr = factory.createRPr();
                }

                BooleanDefaultTrue boldOn = factory.createBooleanDefaultTrue();
                boldOn.setVal(Boolean.TRUE);
                rPr.setB(boldOn);
            }

            if (doc instanceof HWPFDocument && doc.getPicturesTable().hasPicture(run)) {
                Picture picture = doc.getPicturesTable().extractPicture(run, true);

                try {
                    BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, picture.getContent());
                    long cx = UnitsOfMeasurement.twipToEMU((double) Math.round((double) imagePart.getImageInfo().getSize().getWidthMpt() * (double) picture.getHorizontalScalingFactor() * 1.0E-5D)) * 2L;
                    long cy = UnitsOfMeasurement.twipToEMU((double) Math.round((double) imagePart.getImageInfo().getSize().getHeightMpt() * (double) picture.getVerticalScalingFactor() * 1.0E-5D)) * 2L;
                    Inline inline = imagePart.createImageInline((String) null, "", ID1++, ID2++, cx, cy, false);
                    R imgrun = factory.createR();
                    Drawing drawing = factory.createDrawing();
                    imgrun.getContent().add(drawing);
                    drawing.getAnchorOrInline().add(inline);
                    wmlP.getContent().add(imgrun);
                } catch (Exception var19) {
                    var19.printStackTrace();
                }
            } else {
                String text = run.text();
                log.debug("Processing: " + text);
                String cleansed = stripNonValidXMLCharacters(text);
                if (!text.equals(cleansed)) {
                    log.warn("Cleansed..");
                }

                Text t = factory.createText();
                t.setValue(cleansed);
                R wmlRun = factory.createR();
                if (rPr != null) {
                    wmlRun.setRPr(rPr);
                }

                wmlRun.getRunContent().add(t);
                wmlP.getParagraphContent().add(wmlRun);
            }
        }

        System.out.println(XmlUtils.marshaltoString(wmlP, true, true));
        return wmlP;
    }

    private static String stripSpace(String in) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < in.length(); ++i) {
            if (in.charAt(i) != ' ') {
                sb.append(in.charAt(i));
            }
        }

        return sb.toString();
    }

    private static void addTODO(ObjectFactory factory, P wmlP, String message) {
        Text t = factory.createText();
        t.setValue(message);
        R wmlRun = factory.createR();
        wmlRun.getRunContent().add(t);
        wmlP.getParagraphContent().add(wmlRun);
    }

    public static String stripNonValidXMLCharacters(String in) {
        StringBuffer out = new StringBuffer();
        if (in != null && !"".equals(in)) {
            for (int i = 0; i < in.length(); ++i) {
                char current = in.charAt(i);
                if (current == '\t' || current == '\n' || current == '\r' || current >= ' ' && current <= '\ud7ff' || current >= '\ue000' && current <= '�' || current >= 65536 && current <= 1114111) {
                    out.append(current);
                } else {
                    out.append("[#?]");
                }
            }

            return out.toString();
        } else {
            return "";
        }
    }

    private static int numCol(Table t) {
        int col = 0;

        for (int i = 0; i < t.numRows(); ++i) {
            if (t.getRow(i).numCells() > col) {
                col = t.getRow(i).numCells();
            }
        }

        return col;
    }

    private static void handleTable(WordprocessingMLPackage wordMLPackage, HWPFDocument doc, Table t, StyleSheet stylesheet, MainDocumentPart documentPart, ObjectFactory factory) {
        Tbl tbl = factory.createTbl();
        documentPart.addObject(tbl);
        TblPr tblPr = factory.createTblPr();
        tbl.setTblPr(tblPr);
        TblGrid tblGrid = factory.createTblGrid();
        tbl.setTblGrid(tblGrid);

        for (int i = 0; i < t.numRows(); ++i) {
            TableRow tr = t.getRow(i);
            Tr trOut = factory.createTr();
            tbl.getEGContentRowContent().add(trOut);

            for (int j = 0; j < tr.numCells(); ++j) {
                TableCell tc = tr.getCell(j);
                Tc tcOut = factory.createTc();
                trOut.getEGContentCellContent().add(tcOut);

                for (int y = 0; y < tc.numParagraphs(); ++y) {
                    Paragraph p = tc.getParagraph(y);
                    P paraToAdd = handleP(wordMLPackage, doc, p, stylesheet, documentPart, factory);
                    tcOut.getEGBlockLevelElts().add(paraToAdd);
                    log.debug("Added p to tc");
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        String localPath = System.getProperty("user.dir") + "/LineSpacing.doc";
        WordprocessingMLPackage out = convert(new FileInputStream(localPath));
    }
}


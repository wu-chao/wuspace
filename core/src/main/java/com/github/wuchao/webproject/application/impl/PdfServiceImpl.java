package com.github.wuchao.webproject.application.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;
import org.icepdf.ri.util.FontPropertiesManager;
import org.icepdf.ri.util.PropertiesManager;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

@Service
public class PdfServiceImpl {

    private final static Log log = LogFactory.getLog(PdfServiceImpl.class);

    private final static String separator = System.getProperty("file.separator");

    private final static String FILE_TYPE_PNG = "png";

    public static final String PDF_TO_IMAGE_PREFIX = "pdf_to_images_";

    static {
        System.setProperty("org.icepdf.core.streamcache.enabled", "true");
    }

    /**
     * 向给定的PDF文档中每页的中间加上水印，奇数页加上文字水印，偶数页加上图片水印
     * 参考： https://developers.itextpdf.com/examples/stamping-content-existing-pdfs/watermark-examples
     *
     * @param src                PDF文档路径
     * @param dest               加水印后的PDF文档路径（可以不存在）
     * @param watermarkText      文字水印
     * @param watermarkImagePath 图片水印
     */
    public static void addWatermarkImageToPDF(String src, String dest, String watermarkText, String watermarkImagePath) {
        try {
            PdfReader reader = new PdfReader(src);
            int n = reader.getNumberOfPages();
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
            stamper.setRotateContents(false);
            // text watermark
            Font f = new Font(Font.FontFamily.HELVETICA, 30);
            Phrase p = new Phrase(watermarkText, f);
            // image watermark
            Image img = Image.getInstance(watermarkImagePath);
            float w = img.getScaledWidth();
            float h = img.getScaledHeight();
            // transparency
            PdfGState gs1 = new PdfGState();
            gs1.setFillOpacity(0.5f);
            // properties
            PdfContentByte over;
            Rectangle pageSize;
            float x, y;
            // loop over every page
            for (int i = 1; i <= n; i++) {
                pageSize = reader.getPageSize(i);
                x = (pageSize.getLeft() + pageSize.getRight()) / 2;
                y = (pageSize.getTop() + pageSize.getBottom()) / 2;
                over = stamper.getOverContent(i);
                over.saveState();
                over.setGState(gs1);
                if (i % 2 == 1) {
                    ColumnText.showTextAligned(over, Element.ALIGN_CENTER, p, x, y, 0);
                } else {
                    over.addImage(img, w, 0, 0, h, x - (w / 2), y - (h / 2));
                }
                over.restoreState();
            }
            stamper.close();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 向给定的PDF文档中每页的右下方加上图片水印
     *
     * @param src                PDF文档路径
     * @param dest               加水印后的PDF文档路径（可以不存在）
     * @param watermarkImagePath 图片水印
     */
    public static void addWatermarkImageToPDF(String src, String dest, String watermarkImagePath, float w, float h, float x, float y) {
        try {
            PdfReader reader = new PdfReader(src);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
            stamper.setRotateContents(false);

            // image watermark
            Image img = Image.getInstance(watermarkImagePath);

            // transparency
            PdfGState pdfGState = new PdfGState();
            pdfGState.setFillOpacity(0.8f);

            // properties
            PdfContentByte over;

            // loop over every page
            for (int i = 1, n = reader.getNumberOfPages(); i <= n; i++) {
                over = stamper.getOverContent(i);
                over.saveState();
                over.setGState(pdfGState);

                over.addImage(img, w, 0, 0, h, x, y);
                over.restoreState();
            }
            stamper.close();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * icepdf 将指定pdf文件的每一页都转成图片
     * 参考： http://zhuyufufu.iteye.com/blog/2015028
     *
     * @param filepath
     * @param imageDirection
     */
    public static void executorServicePdf2Images(String imageDirection, String filepath) {
        // read/store the font cache
        ResourceBundle messageBundle = ResourceBundle.getBundle(
                PropertiesManager.DEFAULT_MESSAGE_BUNDLE);
        PropertiesManager properties = new PropertiesManager(System.getProperties(),
                ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE));
        new FontPropertiesManager(properties, System.getProperties(), messageBundle);

        // start the capture
        PageCapture pageCapture = new PageCapture();
        pageCapture.capturePages(imageDirection, filepath);
    }

    /**
     * icepdf 将指定pdf文件的每一页都转成图片
     * <p>
     * 参考： https://www.cnblogs.com/101key/p/5455505.html
     * http://www.jb51.net/article/99745.htm
     *
     * @param filepath       原文件路径
     * @param imageDirection 图片生成目录
     */
    public static void pdf2Images(String imageDirection, String filepath) {

        org.icepdf.core.pobjects.Document document = new org.icepdf.core.pobjects.Document();
        String imageNamePrefix = new StringBuilder().append(imageDirection).append(PDF_TO_IMAGE_PREFIX).toString();

        try {

            document.setFile(filepath);

            float rotation = 0f;
            float scale = 1f;

            for (int i = 0, n = document.getNumberOfPages(); i < n; i++) {

                BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN,
                        Page.BOUNDARY_CROPBOX, rotation, scale);
                RenderedImage rendImage = image;

                try {
                    if (log.isDebugEnabled()) {
                        log.info("capturing page " + i);
                    }

                    String imageName = new StringBuilder().append(imageNamePrefix).append(i).append(".png").toString();
                    File file = new File(imageName);
                    ImageIO.write(rendImage, FILE_TYPE_PNG, file);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                image.flush();
            }

        } catch (PDFSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PDFException e) {
            e.printStackTrace();
        } finally {
            document.dispose();
        }
    }

    /**
     * icepdf 将指定pdf文件的第一页转成图片
     *
     * @param fileName
     * @return
     */
    public static String convertOnePage2Image(String fileName) {

        org.icepdf.core.pobjects.Document document = new org.icepdf.core.pobjects.Document();
        String imageName = "";

        float rotation = 0f;
        float scale = 1f;

        try {
            Resource resource = new ClassPathResource("config/application-dev.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            String imgUploadPath = props.getProperty("web.tmp.path");

            document.setFile(imgUploadPath + fileName);

            BufferedImage image = (BufferedImage) document.getPageImage(0, GraphicsRenderingHints.SCREEN,
                    Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;

            imageName = fileName.substring(0, fileName.lastIndexOf('.')) + ".png";

            try {
                File file = new File(imgUploadPath + imageName);
                ImageIO.write(rendImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                image.flush();
            }
        } catch (PDFSecurityException e) {
        } catch (IOException e) {
        } catch (PDFException e) {
        } finally {
            document.dispose();
            return imageName;
        }
    }

    /**
     * 将图片转换成pdf文件
     * imgFilePath 需要被转换的img所存放的位置。 例如imgFilePath="D:\\projectPath\\55555.jpg";
     * pdfFilePath 转换后的pdf所存放的位置 例如pdfFilePath="D:\\projectPath\\test.pdf";
     *
     * @param imgFilePath
     * @param pdfFilePath
     * @return
     */
    public static boolean image2Pdf(String imgFilePath, String pdfFilePath, float urx, float ury) {
        File file = new File(imgFilePath);

        if (file.exists()) {
            Document document = new Document();
            FileOutputStream fos = null;

            try {
                fos = new FileOutputStream(pdfFilePath);
                PdfWriter.getInstance(document, fos);

                // 添加PDF文档的某些信息，比如作者，主题等等
//                document.addAuthor("arui");
//                document.addSubject("test pdf.");

                // 设置文档的大小
//                document.setPageSize(PageSize.A4);
                document.setPageSize(new RectangleReadOnly(urx, ury));

                // 打开文档
                document.open();

                // 写入一段文字
                //document.add(new Paragraph("JUST TEST ..."));

                // 读取一个图片
                Image image = Image.getInstance(imgFilePath);
                image.scalePercent(100);

//                float imageHeight = image.getScaledHeight();
//                float imageWidth = image.getScaledWidth();
//
//                int i = 0;
//
//                while (imageHeight > 500 || imageWidth > 500) {
//                    image.scalePercent(100 - i);
//                    i++;
//                    imageHeight = image.getScaledHeight();
//                    imageWidth = image.getScaledWidth();
//                    System.out.println("imageHeight->" + imageHeight);
//                    System.out.println("imageWidth->" + imageWidth);
//                }

                image.setAlignment(Image.ALIGN_JUSTIFIED_ALL);

                // 设置图片的绝对位置（不设置每次pdf都会变小）
                image.setAbsolutePosition(0, 0);
                image.scaleAbsolute(image.getWidth(), image.getScaledHeight());

                // 插入一个图片
                document.add(image);
            } catch (DocumentException e) {
                log.error(e.getMessage());
            } catch (IOException e) {
                log.error(e.getMessage());
            } finally {
                document.close();

                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }

                return true;
            }
        } else {
            return false;
        }
    }

}

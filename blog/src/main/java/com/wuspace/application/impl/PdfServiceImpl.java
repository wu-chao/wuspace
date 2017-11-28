package com.wuspace.application.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;
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
     * 向给定的PDF文档中每页的中间加上图片水印
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
     * 将指定pdf文件的每一页都转成图片
     * <p>
     * 参考： https://www.cnblogs.com/101key/p/5455505.html
     *
     * @param filepath       原文件路径
     * @param imageDirection 图片生成目录
     */
    public static String[] pdf2Image(String filepath, String imageDirection) {

        org.icepdf.core.pobjects.Document document = new org.icepdf.core.pobjects.Document();
        String imageNamePrefix = new StringBuilder().append(imageDirection).append(PDF_TO_IMAGE_PREFIX).toString();
        String imageName;
        String[] imageNames = new String[document.getNumberOfPages()];

        try {

            document.setFile(filepath);

            float rotation = 0f;
            float scale = 2f;

            for (int i = 0; i < document.getNumberOfPages(); i++) {

                BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN,
                        Page.BOUNDARY_CROPBOX, rotation, scale);
                RenderedImage rendImage = image;

                try {
                    if (log.isDebugEnabled()) {
                        log.info("capturing page " + i);
                    }

                    imageName = new StringBuilder().append(imageNamePrefix).append(i).append(".png").toString();
                    File file = new File(imageName);
                    ImageIO.write(rendImage, FILE_TYPE_PNG, file);

                    imageNames[i] = imageName;
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    image.flush();
                    return imageNames;
                }
            }
        } catch (PDFSecurityException e) {
        } catch (IOException e) {
        } catch (PDFException e) {
        } catch (InterruptedException e) {
        } finally {
            document.dispose();
            return imageNames;
        }
    }

    /**
     * 将指定pdf文件的第一页转成图片
     *
     * @param filepath
     * @return
     */
    public static String convertOnePage2Image(String filepath) {

        org.icepdf.core.pobjects.Document document = new org.icepdf.core.pobjects.Document();
        String imageName = "";

        try {

            document.setFile(filepath);

            BufferedImage image = (BufferedImage) document.getPageImage(0, GraphicsRenderingHints.SCREEN,
                    Page.BOUNDARY_CROPBOX, 0f, 2f);
            RenderedImage rendImage = image;

            Resource resource = new ClassPathResource("config/application-dev.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            String imageTmpDirection = props.getProperty("web.tmp.path");

            imageName = new StringBuilder().append(PDF_TO_IMAGE_PREFIX).append(0).append(".png").toString();

            try {
                File file = new File(imageTmpDirection + imageName);
                ImageIO.write(rendImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                image.flush();
            }
        } catch (PDFSecurityException e) {
        } catch (IOException e) {
        } catch (PDFException e) {
        } catch (InterruptedException e) {
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
    public static boolean image2Pdf(String imgFilePath, String pdfFilePath) {
        File file = new File(imgFilePath);

        if (file.exists()) {
            Document document = new Document();
            FileOutputStream fos = null;

            try {
                fos = new FileOutputStream(pdfFilePath);
                PdfWriter.getInstance(document, fos);

//                添加PDF文档的某些信息，比如作者，主题等等
//                document.addAuthor("arui");
//                document.addSubject("test pdf.");

                // 设置文档的大小
                document.setPageSize(PageSize.A4);

                // 打开文档
                document.open();

                // 写入一段文字
                //document.add(new Paragraph("JUST TEST ..."));

                // 读取一个图片
                Image image = Image.getInstance(imgFilePath);
                float imageHeight = image.getScaledHeight();
                float imageWidth = image.getScaledWidth();

                int i = 0;

                while (imageHeight > 500 || imageWidth > 500) {
                    image.scalePercent(100 - i);
                    i++;
                    imageHeight = image.getScaledHeight();
                    imageWidth = image.getScaledWidth();
                    System.out.println("imageHeight->" + imageHeight);
                    System.out.println("imageWidth->" + imageWidth);
                }

                image.setAlignment(Image.ALIGN_CENTER);
                // 设置图片的绝对位置
                // image.setAbsolutePosition(0, 0);
                // image.scaleAbsolute(500, 400);
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

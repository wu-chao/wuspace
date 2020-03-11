package com.github.wuchao.webproject.util;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Slf4j
public abstract class FileUtils {

    /**
     * 获取文件输入流
     *
     * @param resourceLocation
     * @return
     */
    public static InputStream loadFileInputStream(String resourceLocation) {
        log.info("================================= resourceLocation：{}", resourceLocation);
        if (StringUtils.isNotEmpty(resourceLocation)) {
            InputStream is;
            try {

                try {
                    is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation);
                    log.info("================================= 获取文件输入流>：{}",
                            "Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation)");

                } catch (Exception e) {
                    log.error(e.getMessage());
                    File file = new File(resourceLocation);
                    is = new FileInputStream(file);
                    log.info("================================= 获取文件输入流>：{}",
                            "new FileInputStream(new File(resourceLocation))");
                }

                if (is == null) {
                    is = new FileInputStream(ResourceUtils.getFile(resourceLocation));
                    log.info("================================= 获取文件输入流>：{}",
                            "new FileInputStream(ResourceUtils.getFile(resourceLocation))");
                }

                return is;

            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        return null;
    }

    /**
     * 创建包含多级目录的文件
     *
     * @param path
     * @throws IOException
     */
    public static File createFile(String path) throws IOException {
        if (StringUtils.isNotEmpty(path)) {
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            return file;
        }
        throw new RuntimeException("文件目录为空");
    }

    /**
     * 上传文件
     *
     * @param fileUploadPath
     * @param uploadFile
     * @return
     */
    public static String uploadFile(String fileUploadPath, MultipartFile uploadFile) {
        String savedFilePath;
        String savedFileName;
        try {
            File savePath = new File(fileUploadPath);
            if (!savePath.exists()) {
                savePath.mkdirs();
            }

            // Get the filename and build the local file path (be sure that the
            // application have write permissions on such directory)
            String originalFileName = uploadFile.getOriginalFilename();
            String fileExtName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            savedFileName = UUID.randomUUID() + "." + fileExtName;
            savedFilePath = new StringBuilder().append(fileUploadPath).append(savedFileName).toString();

            // Save the file locally
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(savedFilePath)));
            stream.write(uploadFile.getBytes());
            stream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return savedFileName;
    }

    /**
     * 下载本地系统文件
     *
     * @param filePath
     * @param fileName
     * @param request
     * @param response
     */
    public static void downloadSystemFile(String filePath, String fileName, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNotBlank(filePath)) {

            if (StringUtils.isBlank(fileName)) {
                fileName = filePath.substring(fileName.lastIndexOf(File.separator) + 1);
            }

            try (InputStream inputStream = new FileInputStream(filePath)) {
                // 处理文件名称，防止乱码
                fileName = processFileName(fileName, request);

                response.setCharacterEncoding("UTF-8");
                response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

                IOUtils.copy(inputStream, response.getOutputStream());

            } catch (IOException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理文件名称，防止乱码
     *
     * @param fileName
     * @param request
     * @return
     */
    public static String processFileName(String fileName, HttpServletRequest request) {
        String userAgent = request.getHeader("USER-AGENT");
        try {
            if (userAgent.toUpperCase().indexOf("MSIE") > 0) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                // Google Chrome 浏览器使用 fileName = URLEncoder.encode(fileName, "UTF-8"); 也行？？？
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 匹配中文的正则表达式
     */
    private final static Pattern CHINESE_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");

    /**
     * Downloads a file from a URL
     * <p>
     * Use HttpURLConnection to download file from an HTTP URL（https://www.codejava.net/java-se/networking/use-httpurlconnection-to-download-file-from-an-http-url）
     *
     * @param fileURL HTTP URL of the file to be downloaded
     * @param saveDir path of the directory to save the file
     * @return
     * @throws IOException
     */
    public static String saveFile(String fileURL, String saveDir) throws IOException {

        String url = fileURL;
        String saveFilePath;

        // fileUrl中如果包含中文，则进行转码
        if (CHINESE_PATTERN.matcher(url).find()) {
            int index = url.lastIndexOf('/') + 1;
            url = url.substring(0, index) + URLEncoder.encode(url.substring(index), "UTF-8");
        }

        HttpURLConnection httpConn = (HttpURLConnection) new URL(url).openConnection();
        // 防止屏蔽程序抓取而返回403错误
        httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows XP; DigExt)");
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
            }

            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);

            if (StringUtils.isBlank(saveDir)) {
                saveDir = System.getProperty("user.dir");
            }
            saveFilePath = saveDir + File.separator + fileName;

            try (InputStream inputStream = httpConn.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(saveFilePath)) {
                IOUtils.copy(inputStream, outputStream);
            }

        } else {

            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
            saveFilePath = null;
        }

        httpConn.disconnect();
        return saveFilePath;
    }

    /**
     * 删除单个文件
     *
     * @param fileLocation
     */
    public static void delete(String fileLocation) {
        try {
            File file = ResourceUtils.getFile(fileLocation);
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    log.info("删除文件（" + fileLocation + "）成功");
                } else {
                    log.info("删除文件（" + fileLocation + "）失败");
                }
            }
        } catch (FileNotFoundException e) {
            log.warn("文件（" + fileLocation + "）不存在");
        }
    }

    /**
     * 批量删除文件
     *
     * @param fileLocations
     */
    public static void delete(List<String> fileLocations) {
        if (CollectionUtils.isNotEmpty(fileLocations)) {
            fileLocations.forEach(fileLocation -> delete(fileLocation));
        }
    }

    /**
     * 删除文件/目录（包括该目录下的所有文件）
     *
     * @param file
     */
    public static void delete(File file) {
        if (!file.exists()) {
            return;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delete(f);
            }
        }

        file.delete();
    }


    /**
     * 获取文件拓展名
     *
     * @param fileName
     * @return
     */
    public static String getExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 获取 mimeType
     *
     * @param fileUrl
     * @return
     */
    public static String getMimeType(String fileUrl) {
        return URLConnection.getFileNameMap().getContentTypeFor(fileUrl);
    }

    /**
     * 将输入流保存到文件中
     *
     * @param inputStream
     * @param outFile
     */
    public static void saveFile(InputStream inputStream, String outFile) {
        try {
            @Cleanup OutputStream outputStream = new FileOutputStream(outFile);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * File 转 MultipartFile
     *
     * @param file
     * @param contentType
     * @return
     */
    public static MultipartFile file2CommonsMultipartFile(File file, String contentType) {
        try (InputStream inputStream = new FileInputStream(file)) {
            Path tmpInputFile = null;
            FileItem fileItem = new DiskFileItem("file", contentType, false,
                    tmpInputFile.getFileName().toString(), (int) Files.size(tmpInputFile), tmpInputFile.getParent().toFile());
            IOUtils.copy(inputStream, fileItem.getOutputStream());
            return new CommonsMultipartFile(fileItem);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * File 转 MultipartFile
     *
     * @param file
     * @param contentType
     * @return
     */
    public static MultipartFile file2MockMultipartFile(File file, String contentType) {
        try (InputStream inputStream = new FileInputStream(file)) {
            return new MockMultipartFile("file", file.getName(), contentType, IOUtils.toByteArray(inputStream));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean getLicense() {
        boolean result = false;
        try {
            // license.xml应放在..\WebRoot\WEB-INF\classes路径下
            InputStream is = FileUtils.class.getClassLoader().getResourceAsStream("license.xml");
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
     * https://www.cnblogs.com/zhangzhxb/p/5984766.html
     * https://www.cnblogs.com/qiwu1314/p/6101400.html
     *
     * @param inputStream
     */
    public static String word2pdf(InputStream inputStream) {

        // 验证 License 若不验证则转化出的pdf文档会有水印产生
//        if (!getLicense()) {
//            return;
//        }

        String pdfPath = System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "main" + File.separator + "resources" + File.separator + "public" + File.separator + System.currentTimeMillis() + ".pdf";

        try {

            File file = new File(pdfPath);
            FileOutputStream os = new FileOutputStream(file);
            // Address 是将要被转化的 word 文档
            Document doc = new Document(inputStream);
            // 全面支持 DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            doc.save(os, SaveFormat.PDF);

            return pdfPath;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * word 转 html
     * https://github.com/aspose-words/Aspose.Words-for-Java/blob/master/Plugins/Aspose_Words_Java_for_Docx4j/src/main/java/com/aspose/words/examples/featurescomparison/documents/converttoformats/AsposeConvertToFormats.java
     *
     * @param inputStream
     */
    public static String word2html(InputStream inputStream) {
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
     * word 转 html
     * https://docs.aspose.com/display/wordsjava/Convert+Document+to+HTML
     *
     * @param inputStream
     */
    public static void word2html2(InputStream inputStream) {

    }

    /**
     * 预览 pdf
     *
     * @param pdfLocation
     * @param fileName
     * @param response
     */
    public static void previewPDF(String fileName, String pdfLocation, HttpServletResponse response) {
        InputStream inputStream = null;
        try {

            try {
                File outFile = ResourceUtils.getFile(pdfLocation);
                inputStream = new FileInputStream(outFile);
                log.info("================================= 获取文件输入流>：{}", "new FileInputStream(ResourceUtils.getFile(resourceLocation))");
                log.info("================================= 下载的文件路径为>：{}", outFile);

            } catch (IOException e) {
                e.printStackTrace();
                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(pdfLocation);
                log.info("================================= 获取文件输入流>：{}", "Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation)");
            }

            previewPDF(fileName, inputStream, response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 预览 pdf
     *
     * @param fileName
     * @param inputStream
     * @param response
     */
    public static void previewPDF(String fileName, InputStream inputStream, HttpServletResponse response) throws
            IOException {

        @Cleanup BufferedInputStream br = new BufferedInputStream(inputStream);
        @Cleanup OutputStream out = response.getOutputStream();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "inline; filename=" + URLEncoder.encode(fileName, "UTF-8"));

        byte[] buf = new byte[1024];
        int len;
        while ((len = br.read(buf)) != -1) {
            out.write(buf, 0, len);
        }

        inputStream.close();

    }

}

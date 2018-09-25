package com.github.wuchao.webproject.util;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;
import java.util.regex.Pattern;

@Slf4j
public abstract class FileUtils {

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
     * 下载文件
     *
     * @param fileDownloadPath
     * @param fileName
     * @param response
     */
    public static void download(String fileDownloadPath, String fileName, HttpServletResponse response) {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String downloadPath = fileDownloadPath + fileName;

        // response.setContentType("application/x-download");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try {
            @Cleanup InputStream in = new FileInputStream(downloadPath);
            @Cleanup OutputStream out = response.getOutputStream();
            byte[] b = new byte[1024];
            int length;
            while ((length = in.read(b)) > 0) {
                out.write(b, 0, length);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 匹配中文的正则表达式
     */
    private final static Pattern CHINESE_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");

    private static final int BUFFER_SIZE = 4096;

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
    public static String downloadFile(String fileURL, String saveDir) throws IOException {

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

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            if (StringUtils.isBlank(saveDir)) {
                saveDir = System.getProperty("user.dir");
            }
            saveFilePath = saveDir + File.separator + fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");

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
     * 获取文件拓展名
     *
     * @param fileName
     * @return
     */
    public String getExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}

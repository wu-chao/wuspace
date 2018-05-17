package com.wuspace.util;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

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

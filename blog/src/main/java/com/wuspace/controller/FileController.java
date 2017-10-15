package com.wuspace.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class FileController {

    @Value("${web.file.upload-path}")
    String fileUploadPath;

    @Value("${web.image.upload-path}")
    String imgUploadPath;

    /**
     * 参考：http://blog.netgloo.com/2015/02/08/spring-boot-file-upload-with-ajax/
     * https://spring.io/guides/gs/uploading-files/
     * https://developer.mozilla.org/en-US/docs/Web/API/FormData
     */

    @PostMapping("/imgCut")
    public ResponseEntity imgCut(@RequestParam("file") MultipartFile file, @RequestParam("ext") String ext) {
        String fileName;

        try {
            fileName = uploadFile(file, imgUploadPath);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

        fileName = fileName + "." + ext;

        return ResponseEntity.ok(fileName);
    }

    @RequestMapping("/returnBlob")
    public void returnBlob(@RequestParam("path") String path, HttpServletResponse response) {
        try {
            if (StringUtils.isEmpty(path)) {
                return;
            }
            int extIndex = path.lastIndexOf(".");
            String ext = path.substring(extIndex);
            String fileName = path.substring(0, extIndex);
            response.setHeader("Content-Type", "image/" + ext);
            response.getOutputStream().write(file2ByteArray(imgUploadPath + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * fileinput 上传文件
     *
     * @param file
     * @return
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = uploadFile(file, fileUploadPath);

            Map<String, String> res = new HashMap<String, String>();
            res.put("url", fileName);
            res.put("name", file.getOriginalFilename());

            return new ResponseEntity<Map<String, String>>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping(value = "/uploadImg")
    @ResponseBody
    public String uploadImg(@RequestParam("file") MultipartFile file) {
        String data = "";
        try {
            String fileName = uploadImgs(file, imgUploadPath);

            data = "{\"fileName\":\"" + fileName + "\",\"path\":\"" + imgUploadPath + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @RequestMapping(value = "/download/{fileName:.+}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        downloadFile(fileUploadPath, fileName, response);
    }

    /**
     * 图片上传
     */
    public String uploadImgs(MultipartFile file, String imgUploadPath) {

        return uploadFile(file, imgUploadPath);
    }

    /**
     * 上传文件
     */
    public String uploadFile(MultipartFile file, String fileUploadPath) {
        String savedPath;
        String fileRename = "";
        try {
            //若路径不存在,创建
            File savePath = new File(fileUploadPath);
            if (!savePath.exists()) {
                savePath.mkdirs();
            }

            String originalFileName = file.getOriginalFilename();
            String suffixName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            fileRename = generateRadomName() + "." + suffixName;
            savedPath = fileUploadPath + fileRename;

            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(savedPath)));
            stream.write(file.getBytes());
            stream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return fileRename;
    }

//    @RequestMapping("/swfFile")
//    public void swfFile(@RequestParam("path") String path, HttpServletResponse response) throws Exception {
//        DocConverter docConverter = new DocConverter();
//        String swfFilePath = docConverter.convertToSwf(fileUploadPath + path);
//        response.setHeader("Content-Type", "application/x-shockwave-flash");
//        response.getOutputStream().write(file2ByteArray(swfFilePath));
//    }

    /**
     * 下载文件
     *
     * @param fileUploadPath
     * @param fileName
     * @param response
     */
    public static void downloadFile(String fileUploadPath, String fileName, HttpServletResponse response) {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String downloadPath = fileUploadPath + fileName;
        response.setContentType("application/x-download");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(downloadPath);
            out = response.getOutputStream();
            byte[] b = new byte[1024];
            int length;
            while ((length = in.read(b)) > 0) {
                out.write(b, 0, length);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 给文件生成随机文件名
     */
    public String generateRadomName() {

        return UUID.randomUUID().toString();
    }

    /**
     * 文件转字节数组
     *
     * @param path
     * @return
     */
    public static byte[] file2ByteArray(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        FileInputStream stream = null;
        ByteArrayOutputStream out = null;
        try {
            stream = new FileInputStream(file);
            out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1) {
                out.write(b, 0, n);
            }
            return out.toByteArray();// 此方法大文件OutOfMemory
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                stream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}

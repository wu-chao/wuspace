package com.wuspace.controller;

import com.wuspace.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class FileController {

    @Value("${web.file.upload-path}")
    String fileUploadPath;

    @Value("${web.image.upload-path}")
    String imageUploadPath;

    /**
     * 参考：http://blog.netgloo.com/2015/02/08/spring-boot-file-upload-with-ajax/
     * https://spring.io/guides/gs/uploading-files/
     * https://developer.mozilla.org/en-US/docs/Web/API/FormData
     */

    @RequestMapping(value = "/upload/image", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("uploadFile") MultipartFile uploadFile) {
        String fileName = FileUtils.uploadFile(imageUploadPath, uploadFile);
        if (fileName == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            Map<String, String> res = new HashMap<String, String>();
            res.put("name", "/" + fileName);
            res.put("oldName", uploadFile.getOriginalFilename());
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(res);
        }
    }

    @RequestMapping(value = "/upload/download", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> uploadDownload(@RequestParam("uploadFile") MultipartFile uploadFile) {
        String fileName = FileUtils.uploadFile(fileUploadPath, uploadFile);
        if (fileName == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            Map<String, String> res = new HashMap<String, String>();
            res.put("name", "/" + fileName);
            res.put("oldName", uploadFile.getOriginalFilename());
            return new ResponseEntity<Map<String, String>>(res, HttpStatus.OK);
        }
    }
}

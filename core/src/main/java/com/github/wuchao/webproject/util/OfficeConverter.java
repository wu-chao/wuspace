package com.github.wuchao.webproject.util;

import lombok.Cleanup;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.TimeUnit;

@Component
public class OfficeConverter {

    @Value("${libre-office.home-location}")
    private String libreOfficeLocation;

    /**
     * word 转 pdf
     * [libreoffice转换文档的方法（支持各平台各版本的libreoffice）](https://www.cnblogs.com/Mr-Nobody/p/3537072.html)
     *
     * @param docLocation
     * @param deleteResource
     * @return
     */
    public String wordToPdfByLibreOffice(String docLocation, boolean deleteResource) {
        if (StringUtils.isBlank(docLocation) || docLocation.contains(" ")) {
            return "Error:word 文件名不能包含空格";
        }
        try {
            StringBuilder command = new StringBuilder()
                    .append("cd ").append(libreOfficeLocation).append(File.separator).append("program")
                    .append(" && ")
                    .append("soffice.bin --headless --invisible --convert-to pdf ").append(docLocation)
                    .append(" --outdir ").append(docLocation, 0, docLocation.lastIndexOf(File.separator));
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", command.toString());
            builder.redirectErrorStream(true);
            Process process = builder.start();

            @Cleanup InputStream fis = process.getInputStream();
            @Cleanup InputStreamReader isr = new InputStreamReader(fis);
            @Cleanup BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor(10, TimeUnit.SECONDS);

            String txtName = FileUtils.getExt(docLocation);
            return docLocation.replace(txtName, "pdf");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (deleteResource) {
                FileUtils.delete(docLocation);
            }
        }
        return null;
    }

}

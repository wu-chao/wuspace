package com.github.wuchao.webproject.util;

import lombok.Cleanup;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.TimeUnit;

@Component
public class OfficeConverter {

    /**
     * 服务器需要安装 LibreOffice 软件
     * 需要配置 LibreOffice 的环境变量，如：D:\Program Files\LibreOffice\program
     */


    /**
     * word（doc/docx） 转 pdf
     * D:\Program Files\LibreOffice\program>soffice.bin --headless --invisible --convert-to pdf c:\Users\xx.doc --outdir c:\Users
     * <p>
     * 参考：[libreoffice转换文档的方法（支持各平台各版本的libreoffice）](https://www.cnblogs.com/Mr-Nobody/p/3537072.html)
     *
     * @param docLocation
     * @param deleteResource
     * @return
     */
    public static String wordToPdfByLibreOffice(String docLocation, boolean deleteResource) {
        String targetLocation = docLocation.substring(0, docLocation.lastIndexOf(File.separator));
        return convertOfficeByLibreOffice(docLocation, targetLocation, "pdf", deleteResource);
    }

    /**
     * doc 转 docx
     * D:\Program Files\LibreOffice\program>soffice.bin --headless --invisible --convert-to docx c:\Users\xx.doc --outdir c:\Users
     *
     * @param docLocation
     * @param deleteResource
     * @return
     */
    public static String docToDocxByLibreOffice(String docLocation, boolean deleteResource) {
        String targetLocation = docLocation.substring(0, docLocation.lastIndexOf(File.separator));
        return convertOfficeByLibreOffice(docLocation, targetLocation, "docx", deleteResource);
    }

    /**
     * Office 文档格式转换
     *
     * @param docLocation    原文件位置
     * @param targetLocation 目标文件目录，如果目录文件目录是 C:\、D:\、D: 等，则转换后的文件会自动保存到 %LibreOffice_HOME%\program\ 目录下
     * @param targetFormat   目标格式
     * @param deleteResource 转换后是否删除原文件
     * @return
     */
    private static String convertOfficeByLibreOffice(String docLocation, String targetLocation, String targetFormat, boolean deleteResource) {
        if (StringUtils.isBlank(docLocation) || docLocation.contains(" ")) {
            return "Error:word 文件名不能包含空格";
        }
        try {
            String osName = System.getProperty("os.name");
            StringBuilder command = new StringBuilder();
            if (osName.contains("Windows")) {
                command.append("soffice --headless --invisible --convert-to ").append(targetFormat).append(" ").append(docLocation)
                        .append(" --outdir ").append(targetLocation);
            } else {

                command.append("");
            }

            exeLibreOfficeCMD(command.toString());
            String txtName = FileUtils.getExt(docLocation);
            return docLocation.replace(txtName, targetFormat);

        } finally {
            if (deleteResource) {
                FileUtils.delete(docLocation);
            }
        }
    }

    private static void exeLibreOfficeCMD(String command) {
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", command);
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

package com.github.wuchao.webproject.util;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.concurrent.TimeUnit;

@Slf4j
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
     * word（doc/docx） 转 pdf
     * https://github.com/AryaRicky/toPdfUtils/blob/master/src/LibreOffice.java
     *
     * @param docLocation
     * @param deleteResource
     * @return
     */
    public static String wordToPdfByLibreOffice2(String docLocation, boolean deleteResource) {
        if (StringUtils.isBlank(docLocation) || docLocation.contains(" ")) {
            return "Error:word 文件名不能包含空格";
        }
        try {
            String targetLocation = docLocation.substring(0, docLocation.lastIndexOf(File.separator));

            String osName = System.getProperty("os.name");
            String command;
            if (osName.contains("Windows")) {
                command = "soffice --headless --convert-to pdf:writer_pdf_Export " + docLocation + " --outdir " + targetLocation;
            } else {
                File file = new File(docLocation);
                String path = file.getParent();
                command = "doc2pdf --output=" + path + File.separator +
                        file.getName().replaceAll(".(?i)docx", ".pdf") + " " + targetLocation;
            }
            if (OfficeConverter.exec(command)) {
                return targetLocation;
            }

        } finally {
            if (deleteResource) {
                FileUtils.delete(docLocation);
            }
        }

        log.debug("-------------------------转换失败-------------------------");
        return null;
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
                log.debug(line);
            }
            process.waitFor(10, TimeUnit.SECONDS);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean exec(String command) {
        Process process;// Process可以控制该子进程的执行或获取该子进程的信息
        try {
            log.debug("exec cmd : {}", command);
            process = Runtime.getRuntime().exec(command);// exec()方法指示Java虚拟机创建一个子进程执行指定的可执行程序，并返回与该子进程对应的Process对象实例。
            // 下面两个可以获取输入输出流
            InputStream errorStream = process.getErrorStream();
            InputStream inputStream = process.getInputStream();
        } catch (IOException e) {
            log.error(" exec {} error", command, e);
            return false;
        }

        int exitStatus = 0;
        try {
            exitStatus = process.waitFor();// 等待子进程完成再往下执行，返回值是子线程执行完毕的返回值,返回0表示正常结束
            // 第二种接受返回值的方法
            int i = process.exitValue(); // 接收执行完毕的返回值
            log.debug("i----" + i);
        } catch (InterruptedException e) {
            log.error("InterruptedException  exec {}", command, e);
            return false;
        }

        if (exitStatus != 0) {
            log.error("exec cmd exitStatus {}", exitStatus);
        } else {
            log.debug("exec cmd exitStatus {}", exitStatus);
        }

        process.destroy(); // 销毁子进程
        process = null;

        return true;
    }


}

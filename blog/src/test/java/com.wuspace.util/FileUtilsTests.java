package com.wuspace.util;

import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileUtilsTests {

    @Test
    public void testGetFile() {
        String resourceLocation1 = "files/file-utils.docx";
        String resourceLocation2 = "classpath:files/file-utils.docx";


        try {
//            File file1 = ResourceUtils.getFile(resourceLocation1);
//            System.out.println("\n1: exists=" + file1.exists() + ", name=" + file1.getName());
//
//            File file2 = new File(resourceLocation1);
//            System.out.println("2: exists=" + file2.exists() + ", name=" + file2.getName());

            File file3 = ResourceUtils.getFile(resourceLocation2);
            System.out.println("3: exists=" + file3.exists() + ", name=" + file3.getName());

//            File file4 = new File(resourceLocation2);
//            System.out.println("4: exists=" + file4.exists() + ", name=" + file4.getName());

            /**
             * ResourceUtils.getFile() 先从 classpath 目录下加在文件，
             * 如果 classpath 下不存在，就从远程服务器加在，如果远程服务器加在不到，
             * 就从当前系统的系统目录中查找（绝对位置）文件。
             *
             *
             * classpath 目录下加在文件原理：
             *
             */

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetInputStream() {
        String resourceLocation1 = "files/file-utils.docx";
        String resourceLocation2 = "classpath:files/file-utils.docx";

        try {
            File file = ResourceUtils.getFile(resourceLocation2);
            InputStream inputStream2 = new FileInputStream(file);
            System.out.println(inputStream2.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        InputStream inputStream3 = Thread.currentThread()
                .getContextClassLoader().getResourceAsStream(resourceLocation1);
        System.out.println(inputStream3.toString());

//        InputStream inputStream4 = Thread.currentThread()
//                .getContextClassLoader().getResourceAsStream(resourceLocation2);
//        System.out.println(inputStream4.toString());

        /**
         * Thread.currentThread().getContextClassLoader().getResourceAsStream()
         * 方法参数的路径中不能包含 classpath 标识
         *
         *
         * 加载方式：
         *
         *
         *
         *
         */
    }
}

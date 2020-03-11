package com.github.wuchao.webproject.util;

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
             * ResouceUtils.getFile() 是专门用来加载非压缩和非jar包的文件类型的资源
             * https://www.cnblogs.com/chyu/p/8407541.html。
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
         * 默认则是从ClassPath根下获取，path不能以'/'开头。
         * 另外与该方法类似的方法 Class.getResourceAsStream(String path) ：
         * path 不以'/'开头时默认是从此类所在的包下取资源，以'/'开头则是从ClassPath(Src根目录)根下获取
         * 参考：https://www.cnblogs.com/yadongliang/p/7920053.html
         *
         */
    }

    @Test
    public void testSeparator() {
        System.out.println("\n-------------- " + System.getProperty("os.name") + " --------------");
        System.out.println("pathSeparator:" + File.pathSeparator);
        System.out.println("pathSeparatorChar:" + File.pathSeparatorChar);
        System.out.println("separator:" + File.separator);
        System.out.println("separatorChar:" + File.separatorChar);

        /**
         * 打印结果：
         * pathSeparator:;
         * pathSeparatorChar:;
         * separator:\
         * separatorChar:\
         */

        /**
         * result:
         * Char结尾的表示char类型，否则是String类型
         */

    }

    @Test
    public void testGetMimeType() {
        String url1 = "D:/1.txt";
        String url2 = "http://www.sss.com/222.pdf";
        System.out.println(FileUtils.getMimeType(url1));
        System.out.println(FileUtils.getMimeType(url2));

        /**
         * 打印结果：
         * text/plain
         * application/pdf
         */
    }

}

package com.github.wuchao.webproject.util;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JacobUtils {

    /**
     * // 格式大全：前缀对应以下方法的 fmt 值
     * // 0:Microsoft Word 97 - 2003 文档 (.doc)
     * // 1:Microsoft Word 97 - 2003 模板 (.dot)
     * // 2:文本文档 (.txt)
     * // 3:文本文档 (.txt)
     * // 4:文本文档 (.txt)
     * // 5:文本文档 (.txt)
     * // 6:RTF 格式 (.rtf)
     * // 7:文本文档 (.txt)
     * // 8:HTML 文档 (.htm)(带文件夹)
     * // 9:MHTML 文档 (.mht)(单文件)
     * // 10:MHTML 文档 (.mht)(单文件)
     * // 11:XML 文档 (.xml)
     * // 12:Microsoft Word 文档 (.docx)
     * // 13:Microsoft Word 启用宏的文档 (.docm)
     * // 14:Microsoft Word 模板 (.dotx)
     * // 15:Microsoft Word 启用宏的模板 (.dotm)
     * // 16:Microsoft Word 文档 (.docx)
     * // 17:PDF 文件 (.pdf)
     * // 18:XPS 文档 (.xps)
     * // 19:XML 文档 (.xml)
     * // 20:XML 文档 (.xml)
     * // 21:XML 文档 (.xml)
     * // 22:XML 文档 (.xml)
     * // 23:OpenDocument 文本 (.odt)
     * // 24:WTF 文件 (.wtf)
     */

    /**
     * docx 转 pdf（本机需安装 Office/WPS）
     * https://blog.csdn.net/tuesdayma/article/details/81286746#commentBox
     * https://sourceforge.net/p/jacob-project/discussion/375946/thread/10814901/
     * https://www.cnblogs.com/qlqwjy/p/8193281.html
     *
     * @param inputFile
     * @param pdfFile
     * @return
     */
    public static boolean docx2Pdf(String inputFile, String pdfFile) {
        long startTime = System.currentTimeMillis();
        int fmt = 17;

        /**
         * 参数为 Word.Application：需要安装 Office
         * 参数为 kwps.application：需要安装 WPS
         */
        ActiveXComponent app = new ActiveXComponent("kwps.Application");

        try {
            // 设置 word 不可见
            app.setProperty("Visible", false);
            // 获得 word 中所有打开的文档,返回 Documents 对象
            Dispatch docs = app.getProperty("Documents").toDispatch();
            // 调用 Documents 对象中 Open 方法打开文档
            Variant foo = Dispatch.call(docs, "Open", inputFile, false, true);
            if (foo.getvt() == 0) {
                app.invoke("Quit", 0);
                return false;
            }
            // 返回打开的文档对象 Document
            Dispatch doc = foo.toDispatch();
            // 调用 Document 对象的 SaveAs 方法，将文档保存为 pdf 格式
            Dispatch.call(doc, "SaveAs", pdfFile, fmt);
            // 关闭文档
            Dispatch.call(doc, "Close", false);
            // 关闭 word 应用程序
            app.invoke("Quit", 0);

            System.out.println("word convert to pdf successfully, " +
                    "take " + (System.currentTimeMillis() - startTime) / 1000 + " seconds.");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            app.invoke("Quit", 0);

            System.out.println("word convert to pdf failed, " +
                    "take " + (System.currentTimeMillis() - startTime) / 1000 + " seconds.");
            return false;
        }
    }

    /**
     * word 转 pdf（本机需安装 WPS）
     * https://blog.csdn.net/iteye_6153/article/details/82651765
     *
     * @param word
     * @param pdf
     * @return
     */
    public static boolean word2pdf(String word, String pdf) {
        ActiveXComponent wps = null;
        Dispatch doc = null;
        int fmt = 17;
        long start = System.currentTimeMillis();
        try {
            wps = new ActiveXComponent("kwps.application");
            wps.setProperty("visible", new Variant(false));
            Dispatch docs = wps.getProperty("Documents").toDispatch();

//                doc = Dispatch.invoke(docs, "Open", Dispatch.Method,
//                		new Object[]{word, new Variant(false),new Variant(true)},
//                		new int[1]).toDispatch();
            doc = Dispatch.call(docs, "Open", word, false, true).toDispatch();
//                Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[]{pdf,new Variant(17)}, new int[1]);
            Dispatch.call(doc, "SaveAs", pdf, fmt);
            long end = System.currentTimeMillis();
            System.out.println("耗时：" + (end - start) + "ms.");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("转化出错：" + ex.getMessage());
            return false;
        } finally {
            Dispatch.call(doc, "Close", false);
            System.out.println("关闭WPS");
            if (wps != null) {
                wps.invoke("Quit", new Variant[]{});
            }
        }
    }

    /**
     * docx2doc
     *
     * @param srcPath  源文件
     * @param descPath 目标文件
     * @param fmt      目标格式
     * @return
     */
    public static void convertWordFmt(String srcPath, String descPath, int fmt) {
        long startTime = System.currentTimeMillis();

        // 实例化 ComThread 线程与 ActiveXComponent
        ComThread.InitSTA();

        /**
         * 参数为 Word.Application：需要安装 Office
         * 参数为 kwps.application：需要安装 WPS
         * doc 转 docx 时，用 Word.Application 报错
         */
        ActiveXComponent app = new ActiveXComponent("kwps.Application");

        try {
            // 文档隐藏时进行应用操作
            app.setProperty("Visible", new Variant(false));
            // 实例化模板 Document 对象
            Dispatch document = app.getProperty("Documents").toDispatch();
            // 打开 Document 进行另存为操作
            Dispatch doc = Dispatch.invoke(document, "Open", Dispatch.Method,
                    new Object[]{srcPath, new Variant(true), new Variant(true)}, new int[1]).toDispatch();
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[]{descPath, new Variant(fmt)}, new int[1]);
            Dispatch.call(doc, "Close", new Variant(false));
        } finally {
            // 释放线程与 ActiveXComponent
            app.invoke("Quit", new Variant[]{});
            ComThread.Release();
        }

        System.out.println("convert word format successfully, " +
                "take " + (System.currentTimeMillis() - startTime) / 1000 + " seconds.");
    }

}

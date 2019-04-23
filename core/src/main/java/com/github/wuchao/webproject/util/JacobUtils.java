package com.github.wuchao.webproject.util;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import lombok.extern.slf4j.Slf4j;

/**
 * 只能在 Windows 系统下使用
 */
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
     * doc 格式
     */
    public static final int DOC_FMT = 0;

    /**
     * docx 格式
     */
    public static final int DOCX_FMT = 12;

    /**
     * pdf 格式
     */
    public static final int PDF_FMT = 17;

    /**
     * word 转 pdf
     * https://blog.csdn.net/iteye_6153/article/details/82651765
     * https://blog.csdn.net/tuesdayma/article/details/81286746#commentBox
     * https://sourceforge.net/p/jacob-project/discussion/375946/thread/10814901/
     * https://www.cnblogs.com/qlqwjy/p/8193281.html
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
            ComThread.InitSTA(true);
            /**
             * 参数为 Word.Application：需要安装 Office
             * 参数为 kwps.application：需要安装 WPS
             */
            wps = new ActiveXComponent("Word.application");
            wps.setProperty("visible", new Variant(false));
            Dispatch docs = wps.getProperty("Documents").toDispatch();

//                doc = Dispatch.invoke(docs, "Open", Dispatch.Method,
//                		new Object[]{word, new Variant(false),new Variant(true)},
//                		new int[1]).toDispatch();
            // 如何手动用 Office 打开文档，显示“受保护的视图，Office已检测到该文件存在问题。编辑此文件可能会损坏您的计算机”，
            // 那么这里打开也会报一下异常信息：
            // com.jacob.com.ComFailException: Invoke of: Open
            //
            // Source: Microsoft Office Word
            Variant foo = Dispatch.call(docs, "Open", word, true, true);
            if (foo.getvt() == 0) {
                return false;
            }
            doc = foo.toDispatch();
//                Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[]{pdf,new Variant(fmt)}, new int[1]);
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
     * doc 和 docx 格式互相转换、doc/docx 转 pdf
     *
     * @param srcPath  源文件
     * @param descPath 目标文件
     * @param fmt      目标格式
     * @return
     */
    public static boolean convertWordFmt(String srcPath, String descPath, int fmt) {
        long startTime = System.currentTimeMillis();

        // 实例化 ComThread 线程与 ActiveXComponent
        ComThread.InitSTA(true);

        /**
         * 参数为 Word.Application：需要安装 Office
         * 参数为 kwps.application：需要安装 WPS
         */
        ActiveXComponent app = new ActiveXComponent("Word.Application");

        try {
            // 文档隐藏时进行应用操作
            app.setProperty("Visible", new Variant(false));
            // 实例化模板 Document 对象
            Dispatch document = app.getProperty("Documents").toDispatch();
            // 打开 Document
            // 如何手动用 Office 打开文档，显示“受保护的视图，Office已检测到该文件存在问题。编辑此文件可能会损坏您的计算机”，
            // 那么这里打开也会报一下异常信息：
            // com.jacob.com.ComFailException: Invoke of: Open
            //
            // Source: Microsoft Office Word
            // Variant foo = Dispatch.call(document, "Open", srcPath, true, true);
            Variant foo = Dispatch.invoke(document, "Open", Dispatch.Method,
                    new Object[]{srcPath, new Variant(true), new Variant(true)}, new int[1]);
            if (foo.getvt() == 0) {
                return false;
            }
            Dispatch doc = foo.toDispatch();
            // 另存为
            // Dispatch.call(doc, "SaveAs", descPath, fmt);
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[]{descPath, new Variant(fmt)}, new int[1]);
            Dispatch.call(doc, "Close", new Variant(false));

            System.out.println("convert word format successfully, " +
                    "take " + (System.currentTimeMillis() - startTime) / 1000 + " seconds.");

            return true;
        } finally {
            // 释放线程与 ActiveXComponent
            app.invoke("Quit", new Variant[]{});
            ComThread.Release();
        }
    }

}

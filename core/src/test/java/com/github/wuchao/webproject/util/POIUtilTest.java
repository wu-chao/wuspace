package com.github.wuchao.webproject.util;

import com.github.wuchao.webproject.util.poi.POIUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class POIUtilTest {

    @Test
    public void test() {
        Map<String, Object> parametersMap = new HashMap<>();

        List<Map<String, Object>> table1 = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("${name}", "张三\r李四\r王五");
        map1.put("${age}", "23");
        map1.put("{email}", "12121@qq.com");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("${name}", "李四");
        map2.put("${age}", "45");
        map2.put("{email}", "45445@qq.com");

        Map<String, Object> map3 = new HashMap<>();
        map3.put("${name}", "Tom");
        map3.put("${age}", "34");
        map3.put("{email}", "6767@qq.com");

        table1.add(map1);
        table1.add(map2);
        table1.add(map3);
        parametersMap.put("{table1}", table1);

        parametersMap.put("{img1}", new HashMap<String, String>() {{
            put("content", "http://www.baidu.com/img/bd_logo1.png?where=super");
        }});

        parametersMap.put("{test}", "success");

        // 测试导出word
        POIUtil.exportDocument("files/testPOI.docx", parametersMap,
                System.getProperty("user.dir") + "/result.docx");

    }


    @Test
    public void testDocx2Html() throws IOException {
        String resourceLocation = "D:\\IdeaProjects\\wuchao\\web-project\\core\\DocxResume.docx";
        String html = POIUtil.docx2html(resourceLocation);
        System.out.println(html);
    }

    @Test
    public void testDocx2Html2() throws IOException {
        String resourceLocation = "D:\\IdeaProjects\\wuchao\\web-project\\core\\DocxStructures.docx";
        POIUtil.docx2html2(resourceLocation, System.currentTimeMillis() + ".html");
    }

    @Test
    public void testDoc2Pdf() {
        String resourceLocation = "D:\\IdeaProjects\\wuchao\\web-project\\core\\DocxStructures.doc";
        POIUtil.doc2pdf(resourceLocation, System.currentTimeMillis() + ".pdf");
    }

    // ueditor 富文本编辑器保存到数据库的内容
    String richText = "<p>虽然 HTTP/2 解决了很多之前旧版本的问题，但是它还是存在一个巨大的问题，主要是底层支撑的 TCP 协议造成的。\n" +
            "&nbsp;</p><p><font color=\"#008080\">上文提到 HTTP/2 使用了多路复用，</font><font face=\"PMingLiU\"><font color=\"#008080\">一般</font><sup style=\"background-color: rgb(0, 255, 255);\">来说同一域名下只需</sup>要</font>使用一个 TCP 连接。<sub style=\"background-color: rgb(0, 255, 255);\">但当这个连接</sub>中出现了丢包的情况，那就会导致 HTTP/2 的表现情况反倒不如 HTTP/1 了。&nbsp;\n" +
            "</p><p><font size=\"5\">因为在出现丢包的情况下，整个 TCP 都要开始等待重传，也就导致了后面的所有数据都被阻塞了。但是对于 HTTP/1.1 来说，可以开启多个 </font>T<span style=\"background-color: rgb(255, 0, 0);\">CP 连接，出现这种情况反到只</span>会影响其中一个连接，剩余的 TCP 连接还可以正常传输数据。\n" +
            "\n" +
            "那么可能就会有人考虑到去修改 TCP 协议，其实这已经是一件不可能完成的任务了，因为 TCP 存在的时间实在太长，已经充斥在各种设备中，并且这个协议是由操作系统实现的，更新起来不大现实。\n" +
            "&nbsp;</p><p><u>基于这个原因，Google 就自己架起炉灶搞了一个基于 UDP 协议的 QUIC 协议</u>，并且使用在了 HTTP/3 上，HTTP/3 之前名为 HTTP-over-QUIC，从这个名字中我们也可以发现，HTTP/3 最大的改造就是使用了 QUIC。\n" +
            "\n" +
            "QUIC 虽然基于 UDP，但是在原本的基础上新增了很多功能，接下来我们重点介绍几个 QUIC 功能。</p><p>&nbsp;QUIC 功能\n" +
            "0RTT\n" +
            "通过使用类似 TCP 快速打开的技术，缓存当前会话的上下文，在下次恢复会话的时候，只需要将之前的缓存传递给服务端验证通过就可以进行传输了。0RTT 建连可以说是 QUIC 相比 HTTP/2 最大的性能优势。那什么是 0RTT 建连呢？\n" +
            "\n" +
            "这里面有两层含义:&nbsp;</p><p>&nbsp;1、传输层 0RTT 就能建立连接。&nbsp;</p><p>&nbsp;2、加密层 0RTT 就能建立加密连接&nbsp;</p><p><br></p>\n" +
            "<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:100%;\"><tbody><tr><td>test</td><td>test</td></tr><tr><td>test</td><td>test</td></tr><tr><td>test</td><td>test</td></tr></tbody></table>";

    @Test
    public void testRichText2Doc() {
        String docLocation = POIUtil.richText2Doc(richText, System.currentTimeMillis() + ".doc", System.getProperty("user.dir"));
        if (docLocation != null) {
            String docxLocation = docLocation.replace("doc", "docx");
            if (JacobUtils.convertWordFmt(docLocation, docxLocation, JacobUtils.DOCX_FMT)) {
                System.out.println("转换成功");
            } else {
                System.out.println("转换失败");
            }
        }
    }

}

package com.wuspace.util;

import com.wuspace.util.poi.WordTemplate;
import lombok.Cleanup;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class POIUtil {

    /**
     * 替换模板文件
     *
     * @param resourceLocation 模板文件位置
     * @param dataMap
     */
    public static WordTemplate replaceDocument(String resourceLocation, Map<String, Object> dataMap) {
        if (StringUtils.isNotEmpty(resourceLocation) && MapUtils.isNotEmpty(dataMap)) {
            try {
                //读取word模板
                @Cleanup InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation);
                WordTemplate template = new WordTemplate(is);
                //替换数据
                template.replaceDocument(dataMap);
                is.close();

                return template;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 浏览器导出文件（resourceLocation文件中原有的图片丢失了）
     *
     * @param resourceLocation 文件模板位置
     * @param dataMap          模板填充数据
     * @param outFileName      导出后的文件名称
     * @param response
     */
    public static void exportDocument(String resourceLocation, Map<String, Object> dataMap, String outFileName, HttpServletResponse response) {

        WordTemplate template = replaceDocument(resourceLocation, dataMap);
        if (template != null) {
            try {
                // 文件名称转码，防止文件名称出现乱码
                outFileName = StringUtils.isNotBlank(outFileName) ? URLEncoder.encode(outFileName, "UTF-8") : "";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setHeader("Connection", "close");
            response.setHeader("Content-Disposition", "attachment; filename=" + outFileName);

            try {
                //下载文件
                OutputStream os = response.getOutputStream();
                template.getDocument().write(os);
                os.flush();
                os.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void exportDocument2(String resourceLocation, Map<String, Object> dataMap, String descLocation) {
        try {
            @Cleanup InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation);
            @Cleanup OutputStream os = new FileOutputStream(ResourceUtils.getFile(descLocation));
            WordTemplate template = new WordTemplate(is);
            template.processParagraphs(dataMap);
            template.getDocument().write(os);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 本地测试导出word文档到本地
     *
     * @param resourceLocation
     * @param dataMap
     * @param descLocation
     */
    public static void exportDocument(String resourceLocation, Map<String, Object> dataMap, String descLocation) {

        WordTemplate template = replaceDocument(resourceLocation, dataMap);
        if (template != null) {
            try {
                //下载文件
                OutputStream os = new FileOutputStream(ResourceUtils.getFile(descLocation));
                template.getDocument().write(os);
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 合并文档(合并后的文件中的图片无法显示，表格无边框)
     *
     * @param resourceLocation
     * @param dataMap
     * @param descLocation
     * @param docLocations
     */
    public static void mergeDoc(String resourceLocation, Map<String, Object> dataMap, List<String> docLocations, String descLocation) {
        WordTemplate wordTemplate = replaceDocument(resourceLocation, dataMap);
        if (wordTemplate != null && CollectionUtils.isNotEmpty(docLocations) && StringUtils.isNotEmpty(descLocation)) {
            docLocations.stream().forEach(docLocation -> {
                try {
                    File file = ResourceUtils.getFile(docLocation);
                    wordTemplate.mergeDoc(new FileInputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            try {
                // 导出文件
                wordTemplate.write(new FileOutputStream(ResourceUtils.getFile(descLocation)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

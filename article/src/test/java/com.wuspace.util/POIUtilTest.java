package com.wuspace.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class POIUtilTest {

    @Test
    public void test() {
        Map<String, Object> wordDataMap = new HashMap<>();// 存储报表全部数据
        Map<String, Object> parametersMap = new HashMap<String, Object>();// 存储报表中不循环的数据

        List<Map<String, Object>> table1 = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "张三\r李四\r王五");
        map1.put("age", "23");
        map1.put("email", "12121@qq.com");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "李四");
        map2.put("age", "45");
        map2.put("email", "45445@qq.com");

        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "Tom");
        map3.put("age", "34");
        map3.put("email", "6767@qq.com");

        table1.add(map1);
        table1.add(map2);
        table1.add(map3);

        parametersMap.put("img1", new HashMap<String, String>() {{
            put("content", "http://www.baidu.com/img/bd_logo1.png?where=super");
        }});

        parametersMap.put("test", "success");

        wordDataMap.put("table1", table1);
        wordDataMap.put("parametersMap", parametersMap);

        // 测试导出word
        POIUtil.exportDocument2("files/testPOI.docx", wordDataMap,
                System.getProperty("user.dir") + "/result.docx");


    }

}

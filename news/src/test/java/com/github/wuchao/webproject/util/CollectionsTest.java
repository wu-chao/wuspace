package com.github.wuchao.webproject.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CollectionsTest {

    ///////////////////////// list new /////////////////////////
    @Test
    public void testNewList() {

    }

    ///////////////////////// list delete /////////////////////////
    @Test
    public void testDeleteList1() {
        /**
         * 这种不使用增强的for循环的也可以正常删除和遍历,
         * 这里所谓的正常是指它不会报异常，但是删除后得到的
         * 数据不一定是正确的，这主要是因为删除元素后，被删除元素后
         * 的元素索引发生了变化。假设被遍历list中共有10个元素，当
         * 删除了第3个元素后，第4个元素就变成了第3个元素了，第5个就变成
         * 了第4个了，但是程序下一步循环到的索引是第4个，
         * 这时候取到的就是原本的第5个元素了。
         */
        List<String> elements1 = new ArrayList() {{
            add("aaaa");
            add("bbbb");
            add("cccc");
            add("dddd");
            add("eeee");
        }};

        for (int i = 0; i < elements1.size(); i++) {
            /**
             * 元素删除了，原来正常索引位置的元素都向前移了一位，而索引还是往后递增的，所以出现有的元素捕捉不到。
             */
            System.out.println(elements1.size() + "--" + i + "--" + elements1.get(i));
            /**
             * 5--0--aaaa
             * 4--1--cccc
             * 3--2--eeee
             */
            elements1.remove(i);
        }

    }

    @Test
    public void testDeleteList2() {
        /**
         * 使用Iterator的方式可以顺利删除和遍历
         */
        List<String> elements2 = new ArrayList() {{
            add("aaaa");
            add("bbbb");
            add("cccc");
            add("dddd");
            add("eeee");
        }};
        Iterator iterator = elements2.iterator();
        while (iterator.hasNext()) {
            String e = (String) iterator.next();
            System.out.println(e);
            // 这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException
            iterator.remove();
        }
        System.out.println(elements2.size());

        /**
         * aaaa
         * bbbb
         * cccc
         * dddd
         * eeee
         * 0
         */
    }

    @Test
    public void convertList2Array() {
        /**
         * List 转 Array
         */
        List<String> elements = new ArrayList() {{
            add("aaaa");
            add("bbbb");
            add("cccc");
            add("dddd");
            add("eeee");
        }};
        //elements.toArray()返回的是Object[]

        String[] strAry = elements.toArray(new String[0]);
        System.out.println(Arrays.toString(strAry));
    }

    @Test
    public void convertArray2List() {
        /**
         * 数组转List
         */
        String[] strAry = {"aaaa", "bbbb", "cccc", "dddd", "eeee"};
        List list1 = Arrays.asList(strAry);
        System.out.println(list1);
    }
}

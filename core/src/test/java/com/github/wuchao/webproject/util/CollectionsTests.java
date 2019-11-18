package com.github.wuchao.webproject.util;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.*;

public class CollectionsTests {

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


    @Test
    public void testMapValues() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "AA");
        map.put("2", "BB");
        map.put("3", "CC");
        map.put("4", "DD");

        Collection<String> valueCollection = map.values();
        List<Object> valueList = new ArrayList<>(valueCollection);
        valueList.forEach(e -> System.out.println(e));
    }

    @Test
    public void testInstanceOf() {
        List<String> obj = Lists.newArrayList("111", "222", "333");

        if (obj instanceof List) {
            List<String> list = obj;
            System.out.println(list.size());
            System.out.println(list);
        }

    }

    @Test
    public void testArray() {
        int[] ints = {1, 2, 3, 4, 5};
        List<int[]> intList = Arrays.asList(ints);
        System.out.println(intList.size());

        /**
         * 结果：1
         */

        System.out.println(int.class);
        System.out.println(ints.getClass());
        System.out.println(Integer.class);
        System.out.println(new Integer[1].getClass());
    }

    /**
     * 两集合相减
     * [JAVA集合操作的利器:CollectionUtils](https://blog.csdn.net/gaozhlzh/article/details/6914859)
     */
    @Test
    public void testCollectionSubtract() {
        List<Integer> ints1 = Lists.newArrayList(1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> ints2 = Lists.newArrayList(1, 1, 2, 10);
        List<Integer> ints3 = (List<Integer>) CollectionUtils.subtract(ints1, ints2);

        System.out.println(Arrays.toString(ints1.toArray()));
        System.out.println(Arrays.toString(ints2.toArray()));
        System.out.println(Arrays.toString(ints3.toArray()));
        System.out.println("-----------------------------------------------------------------");

        /**
         * 打印结果：
         * [1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
         * [1, 1, 2, 10]
         * [3, 4, 5, 6, 7, 8, 9]
         */

        // 减去一个空集合（减去的集合为 null 时，会报空指针异常）
        System.out.println(CollectionUtils.subtract(ints1, Collections.emptyList()));
        System.out.println("-----------------------------------------------------------------");

        /**
         * 打印结果：
         * [1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
         */

        // 小集合减去大集合
        System.out.println(CollectionUtils.subtract(Lists.newArrayList(8, 9, 10, 11, 12), ints1));

        /**
         * 打印结果：
         * [11, 12]
         */

    }

    /**
     * 求两集合并集
     * [JAVA集合操作的利器:CollectionUtils](https://blog.csdn.net/gaozhlzh/article/details/6914859)
     */
    @Test
    public void testCollectionUnion() {
        List<Integer> ints1 = Lists.newArrayList(1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> ints2 = Lists.newArrayList(1, 1, 2, 11, 12);
        List<Integer> ints3 = (List<Integer>) CollectionUtils.union(ints1, ints2);

        System.out.println(Arrays.toString(ints1.toArray()));
        System.out.println(Arrays.toString(ints2.toArray()));
        System.out.println(Arrays.toString(ints3.toArray()));

        /**
         * 打印结果：
         * [1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
         * [1, 1, 2, 11, 12]
         * [1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
         */
    }

    /**
     * 求集合交集
     * [JAVA集合操作的利器:CollectionUtils](https://blog.csdn.net/gaozhlzh/article/details/6914859)
     */
    @Test
    public void testCollectionIntersection() {
        List<Integer> ints1 = Lists.newArrayList(1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> ints2 = Lists.newArrayList(1, 1, 2, 10, 11, 12);

        System.out.println(Arrays.toString(ints1.toArray()));
        System.out.println(Arrays.toString(ints2.toArray()));

        if (CollectionUtils.containsAny(ints1, ints2)) {
            List<Integer> ints3 = (List<Integer>) CollectionUtils.intersection(ints1, ints2);
            System.out.println(Arrays.toString(ints3.toArray()));
        }
        if (CollectionUtils.containsAny(ints2, ints1)) {
            List<Integer> ints4 = (List<Integer>) CollectionUtils.intersection(ints2, ints1);
            System.out.println(Arrays.toString(ints4.toArray()));
        }

        /**
         * 打印结果：
         * [1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
         * [1, 1, 2, 10, 11, 12]
         * [1, 1, 2, 10]
         * [1, 1, 2, 10]
         */
    }

    /**
     * 求集合交集的补集
     * [JAVA集合操作的利器:CollectionUtils](https://blog.csdn.net/gaozhlzh/article/details/6914859)
     */
    @Test
    public void testCollectionDisjunction() {
        List<Integer> ints1 = Lists.newArrayList(1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> ints2 = Lists.newArrayList(8, 9, 10, 11, 12);
        List<Integer> ints3 = (List<Integer>) CollectionUtils.disjunction(ints1, ints2);

        System.out.println(Arrays.toString(ints1.toArray()));
        System.out.println(Arrays.toString(ints2.toArray()));
        System.out.println(Arrays.toString(ints3.toArray()));

        /**
         * 打印结果：
         * [1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
         * [8, 9, 10, 11, 12]
         * [1, 1, 2, 3, 4, 5, 6, 7, 11, 12]
         */
    }

}

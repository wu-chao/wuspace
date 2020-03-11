package com.github.wuchao.webproject.java.java8;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class StreamTests {

    @Test
    public void testEmptyListStream() {
        List<Integer> ints = new ArrayList<>(0);
        ints.stream().forEach(e -> System.out.println(e));
    }

    @Test
    public void testStreams() {
        List<Integer> ints = Lists.newArrayList(1, 2, 3);
        List<Integer> result = ints.stream()
                .filter(e -> e > 3)
                .collect(toList());
        System.out.printf(String.valueOf(result));
    }

    @Test
    public void testMapList() {
        Object[] objects = {"111", 222, 333, "444", null, "555"};
        List<Object> objectList = Arrays.stream(objects).collect(Collectors.toList());
        objectList.forEach(s -> {
            if (s instanceof Integer) {
                Integer i = (Integer) s;
                if (i != null) {
                    System.out.println("Integer: " + i);
                }

            } else if (s instanceof String) {
                System.out.println("String: " + s);

            } else {

                System.out.println("Other: " + s);
            }
        });

        List<Object> objects2 = objectList.stream().map(o -> {
            if (o != null) {
                return o;
            } else {
                return null;
            }
        }).collect(Collectors.toList());
        objects2.forEach(o -> System.out.println(o));


        System.out.println("----------------- Test Empty Stream Map List ----------------");
        List<String> emptyList = new ArrayList<>(0);
        List<Integer> intList = emptyList.stream().map(e -> e.length()).collect(toList());
        System.out.println(intList.size());
        intList.forEach(e -> System.out.println(e));

    }


    /**
     * limit 返回 Stream 的前面 n 个元素
     */
    @Test
    public void testLimit() {
        /**
         * peek 和 map 方法一样，只是 peek 是 Consumer，map 是 Function，peek 没有返回值，
         * 类似 forEach 方法，但是 forEach 方法只能放在 Stream 最后面，peek 可以在 Stream 中间反复使用
         */
        Stream.of(1, 2, 3, 4, 5)
                .peek(x -> System.out.print("\nA" + x))
                .limit(3)
                .peek(x -> System.out.print("B" + x))
                .forEach(x -> System.out.print("C" + x));
    }

    /**
     * skip 则是扔掉前 n 个元素（它是由一个叫 subStream 的方法改名而来）
     */
    @Test
    public void testSkip() {
        Stream.of(1, 2, 3, 4, 5)
                .peek(x -> System.out.print("\nA" + x))
                .skip(3)
                .peek(x -> System.out.print("B" + x))
                .forEach(x -> System.out.print("C" + x));
    }


    /**
     * flatMap 方法把每个集合元素（每个集合元素也是一个集合）都转换成一个stream对象，然后压缩到父集合stream对象中
     * <p>
     * [java8 map flatmap](https://www.cnblogs.com/diegodu/p/8794857.html)
     */
    @Test
    public void testFlatMap() {
        List<List<String>> lists = Lists.newArrayList(
                Lists.asList("aaa", "bbb", new String[]{}),
                Lists.asList("ccc", "ddd", new String[]{}),
                Arrays.asList("ddd", "fff")
        );

        List<String> elements = new ArrayList<>();
        for (List<String> strList : lists) {
            for (String str : strList) {
                elements.add(str);
            }
        }

        System.out.println("---------------------------------");
        System.out.println(elements);


        elements = lists.stream()
                .flatMap(strList -> strList.stream())
                .collect(Collectors.toList());

        System.out.println("---------------------------------");
        System.out.println(elements);

    }


    /**
     * [Java8特性③Stream的使用](https://segmentfault.com/a/1190000009270758)
     */
    @Test
    public void testMapToInt() {
        /**
         * Java 8引入了三个原始类型特化流接口来解决这个问题： IntStream 、 DoubleStream 和 LongStream，分别将流中的元素特化为int、long和double，从而避免了暗含的装箱成本。每 个接口都带来了进行常用数值归约的新方法，比如对数值流求和的sum，找到最大元素的max。 此外还有在必要时再把它们转换回对象流的方法。这些特化的原因并不在于流的复杂性，而是装箱造成的复杂性——即类似int和Integer之间的效率差异。
         */

        List<Integer> ints = Arrays.asList(1, 3, 6, 4, 5, 2);
        ints.stream()
                .mapToInt(i -> i.intValue())
                .max()
                .ifPresent(max -> System.out.println("max：" + max));


        List<String> strs = Arrays.asList("1", "3", "6", "4", "5", "2");
        strs.stream()
                .mapToInt(Integer::valueOf)
                .boxed()
                .forEach(i -> System.out.println(i.intValue()));
    }


    @Test
    public void testPartition() {
        System.out.println("Dishes partitioned by vegetarian: " + partitionByVegeterian());
        System.out.println("Vegetarian Dishes by type: " + vegetarianDishesByType());
        System.out.println("Most caloric dishes by vegetarian: " + mostCaloricPartitionedByVegetarian());
    }

    private static Map<Boolean, List<Dish>> partitionByVegeterian() {
        return Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }

    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType() {
        return Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
    }

    private static Object mostCaloricPartitionedByVegetarian() {
        return Dish.menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
    }


}

package com.wuspace.util;

import com.wuspace.domain.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ApacheCommonsTests {

    @Test
    public void testObjectUtils() {
        /**
         * 集合
         */
        List variables1 = null;
        List variables2 = new ArrayList(0);
        List variables3 = new ArrayList();
        List<String> variables4 = new ArrayList() {{
            add("fff");
            add("bbb");
        }};
        List<String> variables5 = new ArrayList() {{
            add("ccc");
            add(null);
        }};

        /**
         * 数组
         */
        Object[] variables6 = null;
        Object[] variables7 = {};
        Object[] variables8 = {null};
        Object[] variables9 = {"ddd"};
        Object[] variables10 = new Object[0];
        Object[] variables11 = new Object[1];


        ///////////////////////// java.util.Objects /////////////////////////
        System.out.println("-------------- java.util.Objects --------------");
        System.out.println("1: isNull=" + Objects.isNull(variables1)); // true
        System.out.println("2: isNull=" + Objects.isNull(variables2)); // false
        System.out.println("3: isNull=" + Objects.isNull(variables3)); // false
        System.out.println("4: isNull=" + Objects.isNull(variables4)); // false
        System.out.println("5: isNull=" + Objects.isNull(variables5)); // false

        /**
         * Objects.isNull() 参数是 null，则结果为 true， 否则为 false。
         */

        System.out.println("1: nonNull=" + Objects.nonNull(variables1)); // false
        System.out.println("2: nonNull=" + Objects.nonNull(variables2)); // true
        System.out.println("3: nonNull=" + Objects.nonNull(variables3)); // true
        System.out.println("4: nonNull=" + Objects.nonNull(variables4)); // true
        System.out.println("5: nonNull=" + Objects.nonNull(variables5)); // true

        /**
         * result:
         * Objects.nonNull() 参数不为 null，则结果 true，否则为 false。
         */


        ///////////////////////// CollectionUtils /////////////////////////
        System.out.println("-------------- CollectionUtils --------------");
        System.out.println("1: isNotEmpty=" + CollectionUtils.isNotEmpty(variables1)); // false
        System.out.println("2: isNotEmpty=" + CollectionUtils.isNotEmpty(variables2)); // false
        System.out.println("3: isNotEmpty=" + CollectionUtils.isNotEmpty(variables3)); // false
        System.out.println("4: isNotEmpty=" + CollectionUtils.isNotEmpty(variables4)); // true
        System.out.println("5: isNotEmpty=" + CollectionUtils.isNotEmpty(variables5)); // true

        /**
         * result:
         * CollectionUtils.isNotEmpty() 参数不为 null，并且集合元素个数大于 0，则结果为 true， 否则为 false。
         * CollectionUtils.isEmpty() 与 isNotEmpty() 结果相反。
         */


        ///////////////////////// ObjectUtils /////////////////////////
        System.out.println("-------------- ObjectUtils --------------");
        System.out.println("1: anyNotNull=" + ObjectUtils.anyNotNull(variables1)); // false
        System.out.println("2: anyNotNull=" + ObjectUtils.anyNotNull(variables2)); // true
        System.out.println("3: anyNotNull=" + ObjectUtils.anyNotNull(variables3)); // true
        System.out.println("4: anyNotNull=" + ObjectUtils.anyNotNull(variables4)); // true
        System.out.println("5: anyNotNull=" + ObjectUtils.anyNotNull(variables5)); // true

        System.out.println("6: anyNotNull=" + ObjectUtils.anyNotNull(variables6)); // false
        System.out.println("7: anyNotNull=" + ObjectUtils.anyNotNull(variables7)); // false
        System.out.println("8: anyNotNull=" + ObjectUtils.anyNotNull(variables8)); // false
        System.out.println("9: anyNotNull=" + ObjectUtils.anyNotNull(variables9)); // true
        System.out.println("10: anyNotNull=" + ObjectUtils.anyNotNull(variables10)); // false
        System.out.println("11: anyNotNull=" + ObjectUtils.anyNotNull(variables11)); // false


        /**
         * result:
         *
         * ObjectUtils.anyNotNull() 传递的参数为 null，结果是 false；
         * 参数不为 null，并且数组元素个数大于 0，只要有一个元素不为 null， 则结果为 true，否则为 false。
         *
         * 当参数接收的是一个数组对象，当传递的是一个集合类型的变量是，会转成数组对象。
         * 这个转变后的数组对象只有一个数组元素，就是传递进去的集合，所以只要当集合不为空时，数组元素就不为空，结果就是 true。
         *
         * 当传递进去的是数组对象时不需要转换，只要数组对象不为空，数组元素个数不等于0，数组元素中有一个非 null 的元素，结果就是 true。
         *
         */


        System.out.println("1: allNotNull=" + ObjectUtils.allNotNull(variables1)); // false
        System.out.println("2: allNotNull=" + ObjectUtils.allNotNull(variables2)); // true
        System.out.println("3: allNotNull=" + ObjectUtils.allNotNull(variables3)); // true
        System.out.println("4: allNotNull=" + ObjectUtils.allNotNull(variables4)); // true
        System.out.println("5: allNotNull=" + ObjectUtils.allNotNull(variables5)); // true

        System.out.println("6: allNotNull=" + ObjectUtils.allNotNull(variables6)); // false
        System.out.println("7: allNotNull=" + ObjectUtils.allNotNull(variables7)); // true
        System.out.println("8: allNotNull=" + ObjectUtils.allNotNull(variables8)); // false
        System.out.println("9: allNotNull=" + ObjectUtils.allNotNull(variables9)); // true
        System.out.println("10: allNotNull=" + ObjectUtils.allNotNull(variables10)); // true
        System.out.println("11: allNotNull=" + ObjectUtils.allNotNull(variables11)); // false

        /**
         * result:
         *
         * ObjectUtils.allNotNull() 参数对象为 null，结果为 false；
         * 参数对象不为 null，如果元素个数大于 0，则全部元素都不为 null，则结果是 true，否则是 false。
         *
         * 当传递的对象是集合时，情况同上，所以判断集合是否是空时，最好使用 CollectionUtils 工具类判断。
         */


        ///////////////////////// ArrayUtils /////////////////////////
        System.out.println("-------------- ArrayUtils --------------");

        /**
         * result:
         * ArrayUtils.isNotEmpty() 和 isEmpty() 方法与 CollectionUtils 工具类的 isNotEmpty() 方法和 isEmpty() 方法一样。
         */


        ///////////////////////// SerializationUtils /////////////////////////
        System.out.println("-------------- SerializationUtils --------------");


    }

    ///////////////////////// StringUtils /////////////////////////
    @Test
    public void testStringUtils() {
        System.out.println("-------------- StringUtils --------------");
        Object[] stringUtilsVariables1 = {
                new User().id(1L).username("ssss").password("123").activated(false),
                "aaaaaaa",
                new ArrayList<String>() {{
                    add("bbb");
                }}
        };
        System.out.println("1: StringUtils.join=" + StringUtils.join(stringUtilsVariables1, ','));
        // 打印结果：1: StringUtils.join=com.wuspace.domain.User@904fb9ed,aaaaaaa,[bbb]
    }

}

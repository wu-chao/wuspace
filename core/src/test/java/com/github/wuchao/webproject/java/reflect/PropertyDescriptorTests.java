package com.github.wuchao.webproject.java.reflect;

import com.github.wuchao.webproject.domain.User;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PropertyDescriptorTests {

    @Test
    public void testPropertyDescriptor() {
        try {
            User user = new User();

            PropertyDescriptor pd = new PropertyDescriptor("username", User.class);

            // 写（PropertyDescriptor）数据
            pd.setValue("xxx", "222");

            // 读（PropertyDescriptor）数据
            System.out.println(pd.getValue("xxx")); // 222
            System.out.println(pd.getValue("username")); // null

            // 写数据
            Method wm = pd.getWriteMethod();
            wm.invoke(user, "333");

            // 读数据
            Method rm = pd.getReadMethod();
            System.out.println(rm.invoke(user));

        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBeanUtils() {

    }

}

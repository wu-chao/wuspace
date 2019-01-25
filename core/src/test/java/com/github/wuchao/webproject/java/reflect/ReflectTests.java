package com.github.wuchao.webproject.java.reflect;

import com.github.wuchao.webproject.domain.User;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

public class ReflectTests {

    @Test
    public void testReflect() {


    }

    @Test
    public void testField() {
        Class clazz = User.class;
        Field[] fields = clazz.getFields();
        Field[] declaredFields = clazz.getDeclaredFields();
        if (fields.length > 0) {
            System.out.println("------------------fields------------------");
            Arrays.stream(fields).forEach(field -> {
                // 字段名称
                System.out.println(field.getName());
                System.out.println(field.getType());
                System.out.println(field.getAnnotatedType());
                // 当前 Field 所在类或接口的 Class
                System.out.println(field.getDeclaringClass());
                System.out.println(field.getGenericType());
                // 返回修饰该参数对象修饰符的整数形式，使用 Modifier 类对其进行解码
                System.out.println(field.getModifiers());
                if (field.getAnnotations().length > 0) {
                    System.out.println("------------------annotations------------------");
                    Arrays.stream(field.getAnnotations()).forEach(a -> {
                        System.out.println(a.annotationType().getName());
                        System.out.println(a.getClass().getName());
                    });
                }
                if (field.getDeclaredAnnotations().length > 0) {
                    System.out.println("------------------declaredAnnotations------------------");
                    Arrays.stream(field.getAnnotations()).forEach(a -> {
                        System.out.println(a.annotationType().getName());
                        System.out.println(a.getClass().getName());
                    });
                }
                System.out.println("------------------------------------------------------");
            });
        }

        if (declaredFields.length > 0) {
            System.out.println("------------------declaredFields------------------");
            Arrays.stream(declaredFields).forEach(field -> {
                System.out.println(field.getName());
                System.out.println(field.getType());
                System.out.println(field.getAnnotatedType().getType());
                System.out.println(field.getDeclaringClass());
                System.out.println(field.getGenericType());
                System.out.println(field.getModifiers());
                if (field.getAnnotations().length > 0) {
                    System.out.println("------------------annotations------------------");
                    Arrays.stream(field.getAnnotations()).forEach(a -> {
                        System.out.println(a.annotationType().getName());
                        System.out.println(a.getClass().getName());
                    });
                }
                if (field.getDeclaredAnnotations().length > 0) {
                    System.out.println("------------------declaredAnnotations------------------");
                    Arrays.stream(field.getAnnotations()).forEach(a -> {
                        System.out.println(a.annotationType().getName());
                        System.out.println(a.getClass().getName());
                    });
                }
                System.out.println("------------------------------------------------------");
            });
        }
    }

    @Test
    public void testMethod() {
        Class clazz = User.class;
        Method[] methods = clazz.getMethods();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        if (methods.length > 0) {
            System.out.println("------------------methods------------------");
            Arrays.stream(methods).forEach(method -> {
                System.out.println(method.getName());
                System.out.println(method.getReturnType());
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes != null && parameterTypes.length > 0) {
                    System.out.println("------------------parameter type------------------");
                    Arrays.stream(parameterTypes).forEach(t -> {
                        System.out.println(t.getName());
                        System.out.println(t.getTypeName());
                    });
                }
                System.out.println("------------------------------------------------------");
            });
        }
        if (declaredMethods.length > 0) {
            System.out.println("------------------declaredMethods------------------");
            Arrays.stream(declaredMethods).forEach(method -> {
                System.out.println(method.getName());
                System.out.println(method.getReturnType());
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes != null && parameterTypes.length > 0) {
                    System.out.println("------------------parameter type------------------");
                    Arrays.stream(parameterTypes).forEach(t -> {
                        System.out.println(t.getName());
                        System.out.println(t.getTypeName());
                    });
                }
                System.out.println("------------------------------------------------------");
            });
        }
    }


    @Test
    public void testParameter() {
        try {
            Method method1 = User.class.getMethod("getId", null);
            System.out.println(method1.getName());
            System.out.println(method1.getReturnType().getName());
            System.out.println(method1.getReturnType().getTypeName());

            System.out.println("------------------------------------------------------");

            Method method2 = User.class.getMethod("setId", Long.class);
            Parameter[] parameters = method2.getParameters();
            if (parameters != null && parameters.length > 0) {
                Arrays.stream(parameters).forEach(parameter -> {
                    System.out.println(parameter.getName());
                    System.out.println(parameter.getType());
                });
            }

            System.out.println("------------------------------------------------------");

            Class[] classes = method2.getParameterTypes();
            if (classes != null && classes.length > 0) {
                Arrays.stream(classes).forEach(clazz -> {
                    System.out.println(clazz.getName());
                    System.out.println(clazz.getTypeName());
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPropertyDescriptor() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        User user = new User().id(1L).username("admin");
        Class clazz = user.getClass();
        PropertyDescriptor pd = new PropertyDescriptor("username", clazz);
        Method getNameMethod = pd.getReadMethod();
        String name = (String) getNameMethod.invoke(user, null);
        System.out.println(name);
    }

}

package com.github.wuchao.webproject.java.reflect;

import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTests {

    /**
     * 测试字符串的 length() 方法
     * <p>
     * [Java7新特性——MethodHandle](https://blog.csdn.net/qq_31142553/article/details/81638027)
     */
    @Test
    public void testMethodHandle() {

        /**
         * MethodTYpe 是表示方法签名类型的不可变对象。每个方法句柄都有一个 MethodType 实例，
         * 用来指明方法的返回类型和参数类型。但它没有方法的名字和“接收者类型”，即调用的实例方法的类型。
         *
         * 主要 API（static）：Finds or creates a method type with the given components.
         * MethodType methodType(Class<?> rtype, Class<?>[] ptypes)
         * MethodType methodType(Class<?> rtype, List<Class<?>> ptypes)
         * MethodType methodType(Class<?> rtype, Class<?> ptype0, Class<?>... ptypes)
         * MethodType methodType(Class<?> rtype)
         * MethodType methodType(Class<?> rtype, Class<?> ptype0)
         * MethodType methodType(Class<?> rtype, MethodType ptypes)
         */
        MethodType methodType = MethodType.methodType(int.class); // 严格数据类型

        /**
         * 需要从 lookup 对象中得到方法句柄，你需要给出持有所需方法的类、方法的名称，以及跟你所需的方法签名相匹配的 MethodType。
         */
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        try {
            MethodHandle methodHandle = lookup.findVirtual(String.class, "length", methodType);

            /**
             * 调用方法句柄
             *
             * 主要 API：
             *
             * MethodType type()：Reports the type of this method handle.
             *
             * Object invoke(Object... args)：Invokes the method handle, allowing any caller type descriptor,
             * and optionally performing conversions on arguments and return values.
             *
             * Object invokeExact(Object... args)：Invokes the method handle, allowing any caller type descriptor, but requiring an exact type match.
             *
             * Object invokeWithArguments(Object... arguments)：Performs a variable arity invocation, passing the arguments in the given list to the method handle, as if via an inexact invoke from a call site which mentions only the type Object, and whose arity is the length of the argument list.
             *
             * Object invokeWithArguments(java.util.List<?> arguments)：Performs a variable arity invocation, passing the arguments in the given array to the method handle, as if via an inexact invoke from a call site which mentions only the type Object, and whose arity is the length of the argument array.
             *
             * MethodHandle bindTo(Object x)：Binds a value x to the first argument of a method handle, without invoking it.
             */
            Object result = methodHandle.invoke("aaaaaaaaaaaaaaaaa");

            if (result != null) {
                System.out.printf(String.valueOf(result));
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}

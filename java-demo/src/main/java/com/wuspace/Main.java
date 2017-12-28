package com.wuspace;

public class Main {

    public static void main(String[] args) {
        // 对于静态字段，只有直接定义这个字段的类才会被初始化，因此通过其子类来引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化。
        // 上面就牵涉到了虚拟机类加载机制
        System.out.println(SubClass.value);

        System.out.println("-------------------------");

        System.out.println(new SuperClass().value);

        System.out.println("-------------------------");

        System.out.println(new SubClass().a);

        System.out.println("-------------------------");

        System.out.println(SubClass.a);
    }
}

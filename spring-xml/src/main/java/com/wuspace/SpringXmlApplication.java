package com.wuspace;

import com.wuspace.domain.Student;
import com.wuspace.util.MyClassPathXmlApplicationContext;

/**
 * Created by WUCHAO on 2017/3/2.
 */
public class SpringXmlApplication {
    public static void main(String[] args) {
        MyClassPathXmlApplicationContext applicationContext = new MyClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) applicationContext.getBean("student1");
        System.out.println("学生姓名 ======= " + student.getName());
        System.out.println("学生年龄 ======= " + student.getAge());
        System.out.println("学生老师 ======= " + student.getTeacher().getName());
    }
}

package com.wuspace.domain;

/**
 * Created by WUCHAO on 2017/3/2.
 */
public class Student implements java.io.Serializable {

    private String name;
    private int age;
    private Teacher teacher;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", teacher=" + teacher + "]";
    }

}

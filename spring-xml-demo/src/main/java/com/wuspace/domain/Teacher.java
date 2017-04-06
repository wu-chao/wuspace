package com.wuspace.domain;

/**
 * Created by WUCHAO on 2017/3/2.
 */
public class Teacher implements java.io.Serializable {

    private String name;
    private String classes;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getClasses() {
        return classes;
    }
    public void setClasses(String classes) {
        this.classes = classes;
    }
    @Override
    public String toString() {
        return "Teacher [name=" + name + ", classes=" + classes + "]";
    }

}

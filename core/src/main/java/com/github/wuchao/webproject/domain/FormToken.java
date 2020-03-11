package com.github.wuchao.webproject.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在需要生成token的controller上增加@FormToken(create=true)，
 * 而在需要检查重复提交的controller上添加@FormToken(remove=true)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormToken {

    boolean create() default false;

    boolean remove() default false;

}

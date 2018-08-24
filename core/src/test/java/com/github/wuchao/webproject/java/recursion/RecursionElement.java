package com.github.wuchao.webproject.java.recursion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author wuchao
 * @date 2018/8/16 16:46
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecursionElement {

    /**
     * 节点 id
     */
    private String id;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 父节点 id
     */
    private String parentId;

    /**
     * 是否是加星标记节点
     */
    private Integer hasStar;

}

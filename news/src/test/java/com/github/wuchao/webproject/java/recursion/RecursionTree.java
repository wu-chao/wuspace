package com.github.wuchao.webproject.java.recursion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author wuchao
 * @date 2018/8/20 14:51
 */
@Getter
@Setter
@AllArgsConstructor
public class RecursionTree extends RecursionElement {

    private List<RecursionTree> children;

    public RecursionTree(String id, String name, String parentId, Integer hasStar) {
        super(id, name, parentId, hasStar);
    }

}

package com.github.wuchao.webproject.java.recursion;

import lombok.*;

import java.util.List;

/**
 * @author wuchao
 * @date 2018/8/20 14:51
 */
@Getter
@Setter
@AllArgsConstructor
public class RecursionTree extends RecursionElement {

    private List<RecursionElement> children;

   public RecursionTree(String id, String name, String parentId) {
       super(id, name, parentId);
   }

}

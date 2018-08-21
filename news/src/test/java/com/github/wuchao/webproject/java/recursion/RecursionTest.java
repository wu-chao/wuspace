package com.github.wuchao.webproject.java.recursion;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 递归
 *
 * @date 2018/8/16 16:42
 */
public class RecursionTest {

    /**
     * 递归查找树
     */
    @Test
    public void testRecursion() {
        RecursionTree[] eleAry = {
                new RecursionTree("1", "父节点-01", "0"),
                new RecursionTree("2", "子节点-01-01", "1"),
                new RecursionTree("3", "子节点-01-02", "1"),
                new RecursionTree("4", "子节点-01-03", "1"),
                new RecursionTree("5", "子节点-01-01-01", "2"),
                new RecursionTree("6", "子节点-01-01-02", "2"),
                new RecursionTree("7", "子节点-01-01-03", "2"),
                new RecursionTree("8", "子节点-01-02-01", "3"),
                new RecursionTree("9", "子节点-01-03-01", "4"),
                new RecursionTree("10", "父节点-02", "0"),
                new RecursionTree("11", "子节点-02-01", "10"),
                new RecursionTree("12", "子节点-02-02", "10"),
                new RecursionTree("13", "子节点-02-03", "10"),
                new RecursionTree("14", "子节点-02-04", "10"),
                new RecursionTree("15", "子节点-02-01-01", "11"),
        };
        List<RecursionTree> eleList = Lists.newArrayList(eleAry);
        List<RecursionTree> treeList = new ArrayList<>();
        eleList.forEach(node -> {
            if ("0".equals(node.getParentId())) {
                findChildren(eleList, node);
                treeList.add(node);
            }
        });
        return;
    }

    /**
     * 查找子节点
     *
     * @param list
     * @param parentNode
     * @return
     */
    public List<RecursionTree> findChildren(List<RecursionTree> list, RecursionTree parentNode) {
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(node -> {
                if (StringUtils.isNotEmpty(node.getParentId()) && node.getParentId().equals(parentNode.getId())) {
                    if (parentNode.getChildren() == null) {
                        parentNode.setChildren(new ArrayList<>());
                    }
                    findChildren(list, node);
                    parentNode.getChildren().add(node);
                }
            });
        }
        return list;
    }

}

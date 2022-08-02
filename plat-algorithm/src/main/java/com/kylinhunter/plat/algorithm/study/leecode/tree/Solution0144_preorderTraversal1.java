package com.kylinhunter.plat.algorithm.study.leecode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 前序遍历
 *
 * @author BiJi'an
 * @description
 * @date 2022-08-03 00:55
 **/
public class Solution0144_preorderTraversal1 {
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        preorderTraversal(root, result);
        return result;

    }

    public void preorderTraversal(TreeNode node, List result) {

        if (node != null) {
            result.add(node.val);
            preorderTraversal(node.left, result);
            preorderTraversal(node.right, result);

        }

    }
}

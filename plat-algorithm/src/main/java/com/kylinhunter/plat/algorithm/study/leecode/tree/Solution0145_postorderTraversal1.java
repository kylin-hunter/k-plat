package com.kylinhunter.plat.algorithm.study.leecode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 中序遍历
 *
 * @author BiJi'an
 * @description
 * @date 2022-08-03 00:55
 **/
public class Solution0145_postorderTraversal1 {
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;

    }

    public void inorderTraversal(TreeNode node, List result) {

        if (node != null) {
            inorderTraversal(node.left, result);
            inorderTraversal(node.right, result);
            result.add(node.val);
        }

    }
}

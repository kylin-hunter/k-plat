package com.kylinhunter.plat.algorithm.study.leecode.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 前序遍历
 *
 * @author BiJi'an
 * @description
 * @date 2022-08-03 00:55
 **/
public class Solution0144_preorderTraversal2 {
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        preorderTraversal(root, result);
        return result;

    }

    public void preorderTraversal(TreeNode node, List result) {

        if (node == null) {
            return;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = node;
        while (true) {

            while (cur != null) {
                result.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }

            if (stack.isEmpty()) {
                break;
            }

            TreeNode pop = stack.pop();

            cur = pop.right;
        }

    }
}

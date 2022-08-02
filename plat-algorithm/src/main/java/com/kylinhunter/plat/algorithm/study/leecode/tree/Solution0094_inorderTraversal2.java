package com.kylinhunter.plat.algorithm.study.leecode.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 中序遍历
 *
 * @author BiJi'an
 * @description
 * @date 2022-08-03 00:55
 **/
public class Solution0094_inorderTraversal2 {
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;

    }

    public void inorderTraversal(TreeNode node, List result) {

        if (node == null) {
            return;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = node;

        while (true) {

            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            if (stack.isEmpty()) {
                break;
            }

            TreeNode pop = stack.pop();

            result.add(pop.val);

            cur = pop.right;

        }

    }
}

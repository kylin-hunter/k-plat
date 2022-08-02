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
public class Solution0144_preorderTraversal3 {
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

        stack.push(cur);
        while (!stack.isEmpty()) {
            cur=stack.pop();

            result.add(cur.val);

            if(cur.right!=null){
                stack.push(cur.right);
            }

            if(cur.left!=null){
                stack.push(cur.left);
            }
        }

    }
}

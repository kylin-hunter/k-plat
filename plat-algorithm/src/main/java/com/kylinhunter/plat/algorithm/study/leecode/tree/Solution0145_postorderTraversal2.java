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
public class Solution0145_postorderTraversal2 {
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

        Deque<TreeNode> stackOut = new LinkedList<>();
        TreeNode cur = node;

        stack.push(cur);
        
        while(!stack.isEmpty()){
            TreeNode pop = stack.pop();
            stackOut.push(pop);

            if(pop.left!=null){
                stack.push(pop.left);
            }

            if(pop.right!=null){
                stack.push(pop.right);
            }
        }

        while (!stackOut.isEmpty()){
            result.add(stackOut.pop().val);
        }

        

    }
}

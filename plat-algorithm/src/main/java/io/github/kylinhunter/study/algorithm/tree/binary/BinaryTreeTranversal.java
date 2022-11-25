package io.github.kylinhunter.study.algorithm.tree.binary;

import java.util.LinkedList;
import java.util.Queue;

import io.github.kylinhunter.study.algorithm.tree.common.TreeVisitor;

import lombok.Getter;
import lombok.Setter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-20 23:20
 **/
@Getter
@Setter
public class BinaryTreeTranversal<N, R> {

    public void traversePre(BinaryTree<N> node, TreeVisitor<BinaryTree<N>, N, R> treeVisitor) {
        if (node != null) {
            treeVisitor.visit(node);
            this.traversePre(node.left, treeVisitor);
            this.traversePre(node.right, treeVisitor);
        }
    }

    public void traverseIN(BinaryTree<N> node, TreeVisitor<BinaryTree<N>, N, R> treeVisitor) {
        if (node != null) {
            this.traverseIN(node.left, treeVisitor);
            treeVisitor.visit(node);
            this.traverseIN(node.right, treeVisitor);
        }
    }

    public void traversePost(BinaryTree<N> node, TreeVisitor<BinaryTree<N>, N, R> treeVisitor) {
        if (node != null) {
            this.traversePost(node.right, treeVisitor);
            treeVisitor.visit(node);
            this.traversePost(node.left, treeVisitor);

        }
    }

    public void traverseLevel(BinaryTree<N> node, TreeVisitor<BinaryTree<N>, N, R> treeVisitor) {
        if (node != null) {
            Queue<BinaryTree<N>> queue = new LinkedList<>();
            queue.offer(node);

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    BinaryTree<N> cur = queue.poll();
                    if (cur != null) {
                        treeVisitor.visit(cur);
                        if (cur.left != null) {
                            queue.offer(cur.left);
                        }
                        if (cur.right != null) {
                            queue.offer(cur.right);
                        }
                    }

                }
            }

        }
    }

}

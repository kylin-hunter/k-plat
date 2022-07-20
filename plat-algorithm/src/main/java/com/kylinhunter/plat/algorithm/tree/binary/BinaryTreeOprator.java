package com.kylinhunter.plat.algorithm.tree.binary;

import com.kylinhunter.plat.algorithm.tree.AbstractTreeOprator;
import com.kylinhunter.plat.algorithm.tree.binary.init.BinaryInitializer;

import lombok.Getter;
import lombok.Setter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-20 23:20
 **/
@Getter
@Setter
public class BinaryTreeOprator<N> extends AbstractTreeOprator<BinaryTree<N>, N> {

    private BinaryInitializer<N> binaryInitializer = new BinaryInitializer<>();

    public void preOrder(BinaryTree<N> node) {
        if (node != null) {
            System.out.print(" " + node.getData());
            this.preOrder(node.left);
            this.preOrder(node.right);
        }
    }

    public void inOrder(BinaryTree<N> node) {
        if (node != null) {
            this.inOrder(node.left);
            System.out.print(" " + node.getData());
            this.inOrder(node.right);
        }
    }

    public void postOrder(BinaryTree<N> node) {
        if (node != null) {
            this.postOrder(node.right);
            System.out.print(" " + node.getData());
            this.postOrder(node.left);

        }
    }

}

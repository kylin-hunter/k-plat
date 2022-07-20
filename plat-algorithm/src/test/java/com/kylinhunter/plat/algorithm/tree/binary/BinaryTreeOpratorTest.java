package com.kylinhunter.plat.algorithm.tree.binary;

import org.junit.jupiter.api.Test;

import com.kylinhunter.plat.algorithm.tree.binary.data.TreeDataGenerator;

class BinaryTreeOpratorTest {
    BinaryTreeOprator<Integer> binaryTree = new BinaryTreeOprator<>();

    @Test
    void preOrder() {

        final BinaryTree<Integer> binaryTree = TreeDataGenerator.getTree();
        System.out.println(binaryTree);
        this.binaryTree.preOrder(binaryTree);
        System.out.println();
    }

    @Test
    void inOrder() {

        final BinaryTree<Integer> binaryTree = TreeDataGenerator.getTree();
        System.out.println(binaryTree);
        this.binaryTree.inOrder(binaryTree);
        System.out.println();

    }

    @Test
    void postOrder() {

        final BinaryTree<Integer> binaryTree = TreeDataGenerator.getTree();
        System.out.println(binaryTree);
        this.binaryTree.postOrder(binaryTree);
        System.out.println();
    }
}
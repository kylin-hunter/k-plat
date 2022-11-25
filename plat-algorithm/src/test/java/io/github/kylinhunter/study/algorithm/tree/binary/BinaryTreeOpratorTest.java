package io.github.kylinhunter.study.algorithm.tree.binary;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.kylinhunter.study.algorithm.tree.binary.data.TreeData;
import io.github.kylinhunter.study.algorithm.tree.binary.data.TreeDataGenerator;
import io.github.kylinhunter.study.algorithm.tree.common.TreeVisitor;
import io.github.kylinhunter.study.algorithm.tree.constants.Traversal;

class BinaryTreeOpratorTest {
    BinaryTreeOprator<Integer> binaryTreeOprator = new BinaryTreeOprator<>();

    @Test
    void traversePre() {
        traverse(DefaultBinaryTreeVisitor.create(Integer.class, Traversal.PRE));

    }

    @Test
    void traverseIn() {
        traverse(DefaultBinaryTreeVisitor.create(Integer.class, Traversal.IN));

    }

    @Test
    void traversePost() {
        traverse(DefaultBinaryTreeVisitor.create(Integer.class, Traversal.POST));

    }

    @Test
    void traverseLevel() {
        traverse(DefaultBinaryTreeVisitor.create(Integer.class, Traversal.LEVEL));
    }

    private void traverse(TreeVisitor<BinaryTree<Integer>, Integer, List<Integer>> treeVisitor) {
        System.out.println("==========================>");
        TreeData<Integer> treeData = TreeDataGenerator.initTree();
        BinaryTree<Integer> binaryTree = treeData.getData();
        System.out.println(binaryTree);
        List<Integer> traversalResult = this.binaryTreeOprator.traverse(binaryTree, treeVisitor);
        treeVisitor.printResult();
        Integer[] result = traversalResult.toArray(new Integer[0]);
        Traversal traversal = treeVisitor.getTraversal();
        switch (traversal) {
            case IN:
                Assertions.assertArrayEquals(result, treeData.getTraverseIn());
                break;
            case PRE:
                Assertions.assertArrayEquals(result, treeData.getTraversePre());
                break;
            case POST:
                Assertions.assertArrayEquals(result, treeData.getTraversePost());
                break;
            case LEVEL:
                Assertions.assertArrayEquals(result, treeData.getTraverseLevel());
                break;
        }

    }
}
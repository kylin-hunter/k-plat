package io.github.kylinhunter.study.algorithm.tree.binary.init;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.kylinhunter.study.algorithm.tree.binary.BinaryTree;
import io.github.kylinhunter.study.algorithm.tree.binary.data.TreeDataGenerator;
import io.github.kylinhunter.study.algorithm.tree.constants.Traversal;

class BinaryInitializerTest {

    BinaryInitializer<Integer> binaryInitializer = new BinaryInitializer<>();

    @Test
    void init() {

        final BinaryTree<Integer> binaryTree1 = binaryInitializer.init(TreeDataGenerator.INIT_DATA_LEVEL,
                Traversal.LEVEL);
        final BinaryTree<Integer> binaryTree2 = binaryInitializer.init(TreeDataGenerator.INIT_DATA_PRE, Traversal.PRE);

        Assertions.assertTrue(binaryTree1.equals(binaryTree2));
        binaryTree1.setVal(2);
        Assertions.assertFalse(binaryTree1.equals(binaryTree2));

    }
}
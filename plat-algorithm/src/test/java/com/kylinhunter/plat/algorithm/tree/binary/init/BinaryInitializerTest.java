package com.kylinhunter.plat.algorithm.tree.binary.init;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.kylinhunter.plat.algorithm.tree.binary.BinaryTree;
import com.kylinhunter.plat.algorithm.tree.binary.data.TreeDataGenerator;
import com.kylinhunter.plat.algorithm.tree.constants.Traversal;

class BinaryInitializerTest {

    BinaryInitializer<Integer> binaryInitializer = new BinaryInitializer<>();

    @Test
    void init() {

        final BinaryTree<Integer> binaryTree1 = binaryInitializer.init(TreeDataGenerator.INIT_DATA_LEVEL,
                Traversal.LEVEL);
        final BinaryTree<Integer> binaryTree2 = binaryInitializer.init(TreeDataGenerator.INIT_DATA_PRE, Traversal.PRE);

        Assertions.assertTrue(binaryTree1.equals(binaryTree2));
        binaryTree1.setData(2);
        Assertions.assertFalse(binaryTree1.equals(binaryTree2));

    }
}
package com.kylinhunter.plat.algorithm.tree.binary.data;

import com.kylinhunter.plat.algorithm.tree.binary.BinaryTree;
import com.kylinhunter.plat.algorithm.tree.binary.BinaryTreeOprator;
import com.kylinhunter.plat.algorithm.tree.binary.init.BinaryInitializer;
import com.kylinhunter.plat.algorithm.tree.constants.Traversal;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:34
 **/
public class TreeDataGenerator {
    private static BinaryTreeOprator<Integer> binaryTreeOprator = new BinaryTreeOprator<>();

    public static Integer[] INIT_DATA_PRE =
            new Integer[] {1, 2, null, 3, 4, null, null, null, 5, 6, null, null, 7, 8, 9, null, null, null,
                    null};
    public static Integer[] INIT_DATA_LEVEL =
            new Integer[] {1, 2, 5, null, 3, 6, 7, null, null, 4, null, null, null, 8, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    9, null, null, null};

    public static BinaryTree<Integer> getTree() {
        final BinaryInitializer<Integer> binaryInitializer = binaryTreeOprator.getBinaryInitializer();
        return binaryInitializer.init(INIT_DATA_LEVEL, Traversal.LEVEL);
    }

}

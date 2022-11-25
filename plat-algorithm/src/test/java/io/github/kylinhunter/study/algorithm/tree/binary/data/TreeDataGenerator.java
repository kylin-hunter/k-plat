package io.github.kylinhunter.study.algorithm.tree.binary.data;

import io.github.kylinhunter.study.algorithm.tree.binary.BinaryTree;
import io.github.kylinhunter.study.algorithm.tree.binary.BinaryTreeOprator;
import io.github.kylinhunter.study.algorithm.tree.binary.init.BinaryInitializer;
import io.github.kylinhunter.study.algorithm.tree.constants.Traversal;

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

    public static Integer[] TRAVERSAL_LEVEL = new Integer[] {1, 2, 5, 3, 6, 7, 4, 8, 9};
    public static Integer[] TRAVERSAL_PRE = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static Integer[] TRAVERSAL_POST = new Integer[] {7, 8, 9, 5, 6, 1, 3, 4, 2};
    public static Integer[] TRAVERSAL_IN = new Integer[] {2, 4, 3, 1, 6, 5, 9, 8, 7};

    public static TreeData<Integer> initTree() {
        final BinaryInitializer<Integer> binaryInitializer = binaryTreeOprator.getBinaryInitializer();
        BinaryTree<Integer> binaryTree = binaryInitializer.init(INIT_DATA_LEVEL, Traversal.LEVEL);
        TreeData<Integer> treeData = new TreeData<>(binaryTree);
        treeData.setTraverseIn(TRAVERSAL_IN);
        treeData.setTraversePost(TRAVERSAL_POST);
        treeData.setTraversePre(TRAVERSAL_PRE);
        treeData.setTraverseLevel(TRAVERSAL_LEVEL);
        return treeData;
    }

}

package com.kylinhunter.plat.algorithm.tree.binary;

import java.util.List;

import com.google.common.collect.Lists;
import com.kylinhunter.plat.algorithm.tree.common.TreeVisitor;
import com.kylinhunter.plat.algorithm.tree.constants.Traversal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-21 16:55
 **/
@Getter
@RequiredArgsConstructor
public class DefaultBinaryTreeVisitor<N> implements TreeVisitor<BinaryTree<N>, N, List<N>> {
    private final List<N> result = Lists.newArrayList();
    private final Traversal traversal;

    @Override
    public void visit(BinaryTree<N> binaryTree) {
        result.add(binaryTree.getData());
    }

    public static <T> DefaultBinaryTreeVisitor<T> create(Class<T> dataClazz, Traversal traversal) {
        return new DefaultBinaryTreeVisitor<>(traversal);
    }

    public void printResult() {
        System.out.println("traversal:" + traversal + ":print visitor start");
        result.stream().map(e -> e + " ").forEach(System.out::print);
        System.out.println();
        System.out.println("traversal:" + traversal + ":print visitor end");
    }

}

package com.kylinhunter.plat.algorithm.tree.common;

import com.kylinhunter.plat.algorithm.tree.Tree;
import com.kylinhunter.plat.algorithm.tree.constants.Traversal;

public interface TreeVisitor<T extends Tree<N>, N, R> {
    void visit(T tree);

    default Traversal getTraversal() {
        return Traversal.LEVEL;
    }

    default void printResult() {
    }

    default R getResult() {
        return null;
    }

}

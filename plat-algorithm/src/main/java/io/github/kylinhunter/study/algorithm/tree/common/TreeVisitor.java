package io.github.kylinhunter.study.algorithm.tree.common;

import io.github.kylinhunter.study.algorithm.tree.Tree;
import io.github.kylinhunter.study.algorithm.tree.constants.Traversal;

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

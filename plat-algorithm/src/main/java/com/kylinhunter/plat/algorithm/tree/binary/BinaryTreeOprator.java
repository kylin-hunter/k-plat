package com.kylinhunter.plat.algorithm.tree.binary;

import java.util.List;

import com.kylinhunter.plat.algorithm.tree.binary.init.BinaryInitializer;
import com.kylinhunter.plat.algorithm.tree.common.AbstractTreeOprator;
import com.kylinhunter.plat.algorithm.tree.common.TreeVisitor;
import com.kylinhunter.plat.algorithm.tree.constants.Traversal;

import lombok.Getter;
import lombok.Setter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-20 23:20
 **/
@Getter
@Setter
public class BinaryTreeOprator<N> extends AbstractTreeOprator<BinaryTree<N>, N, List<N>> {

    private BinaryInitializer<N> binaryInitializer = new BinaryInitializer<>();
    private BinaryTreeTranversal<N, List<N>> binaryTreeTranversal = new BinaryTreeTranversal<>();

    public List<N> traverse(BinaryTree<N> node, TreeVisitor<BinaryTree<N>, N, List<N>> treeVisitor) {
        Traversal traversal = treeVisitor.getTraversal();
        switch (traversal) {
            case IN:
                binaryTreeTranversal.traverseIN(node, treeVisitor);
                break;
            case PRE:
                binaryTreeTranversal.traversePre(node, treeVisitor);
                break;
            case POST:
                binaryTreeTranversal.traversePost(node, treeVisitor);
                break;
            case LEVEL:
                binaryTreeTranversal.traverseLevel(node, treeVisitor);
                break;
        }
        return treeVisitor.getResult();

    }

}

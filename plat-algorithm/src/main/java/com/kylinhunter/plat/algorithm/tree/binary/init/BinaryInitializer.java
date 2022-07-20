package com.kylinhunter.plat.algorithm.tree.binary.init;

import java.util.concurrent.atomic.AtomicInteger;

import com.kylinhunter.plat.algorithm.tree.binary.BinaryTree;
import com.kylinhunter.plat.algorithm.tree.constants.Traversal;
import com.kylinhunter.plat.commons.exception.inner.InitException;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-21 02:30
 **/
public class BinaryInitializer<N> {
    public BinaryTree<N> init(N[] datas, Traversal traversal) {
        if (traversal == Traversal.LEVEL) {
            return initLevel(datas, 0);
        } else if (traversal == Traversal.PRE) {
            return initPre(datas, new AtomicInteger(0));
        } else {
            throw new InitException("unsupported Traversal:" + traversal);
        }

    }

    private BinaryTree<N> initLevel(N[] datas, int i) {
        if (i < datas.length && datas[i] != null) {
            BinaryTree<N> binaryTree = new BinaryTree<>(datas[i]);
            binaryTree.setLeft(initLevel(datas, 2 * i + 1));
            binaryTree.setRight(initLevel(datas, 2 * i + 2));
            return binaryTree;
        }
        return null;

    }

    private BinaryTree<N> initPre(N[] datas, AtomicInteger i) {

        if (i.get() < datas.length) {
            final N data = datas[i.getAndIncrement()];
            if (data != null) {
                BinaryTree<N> node = new BinaryTree<>(data);
                node.setLeft(initPre(datas, i));
                node.setRight(initPre(datas, i));
                return node;

            } else {
                return null;
            }
        }
        return null;
    }
}

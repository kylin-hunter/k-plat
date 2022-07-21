package com.kylinhunter.plat.algorithm.tree.binary.data;

import com.kylinhunter.plat.algorithm.tree.binary.BinaryTree;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-18 03:06
 **/
@RequiredArgsConstructor
@Getter
@Setter
public class TreeData<T> {
    private final BinaryTree<T> data;
    private T[] traverseLevel;
    private T[] traversePre;
    private T[] traversePost;
    private T[] traverseIn;
}

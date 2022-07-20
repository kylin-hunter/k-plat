package com.kylinhunter.plat.algorithm.tree.binary.data;

import com.kylinhunter.plat.algorithm.tree.binary.BinaryTree;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-18 03:06
 **/
@RequiredArgsConstructor
@Getter
public class TreeData {
    private final BinaryTree<?> data;
    private final BinaryTree<?> rightData;

}

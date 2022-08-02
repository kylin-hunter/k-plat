package com.kylinhunter.plat.algorithm.study.tree;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tree<T> {
    @EqualsAndHashCode.Include
    protected T val;

    public T val() {
        return val;
    }

}

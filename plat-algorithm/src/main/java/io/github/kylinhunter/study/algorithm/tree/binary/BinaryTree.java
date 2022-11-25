package io.github.kylinhunter.study.algorithm.tree.binary;

import java.util.Objects;

import io.github.kylinhunter.study.algorithm.tree.Tree;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-20 10:23
 **/

@Data
@NoArgsConstructor
public class BinaryTree<T> extends Tree<T> {
    @EqualsAndHashCode.Include
    protected BinaryTree<T> left;
    @EqualsAndHashCode.Include
    protected BinaryTree<T> right;

    public BinaryTree(T data) {
        super(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BinaryTree<?> that = (BinaryTree<?>) o;
        if (!this.val.equals(that.val)) {
            return false;
        }
        return Objects.equals(getLeft(), that.getLeft()) && Objects.equals(getRight(), that.getRight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLeft(), getRight());
    }
}


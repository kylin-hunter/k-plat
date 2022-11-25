package io.github.kylinhunter.plat.core.init.data;

public interface InitDatas {
    boolean canBeModified(String code);

    boolean canBeDeleted(String code);
}

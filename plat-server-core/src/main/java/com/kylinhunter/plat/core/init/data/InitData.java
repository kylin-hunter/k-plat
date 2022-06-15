package com.kylinhunter.plat.core.init.data;

public interface InitData {
    boolean canBeModified(String code);

    boolean canBeDeleted(String code);
}

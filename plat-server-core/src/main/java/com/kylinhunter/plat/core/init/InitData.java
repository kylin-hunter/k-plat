package com.kylinhunter.plat.core.init;

public interface InitData {
    boolean canBeModified(String code);

    boolean canBeDeleted(String code);
}

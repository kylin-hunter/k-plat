package com.kylinhunter.plat.algorithm.sort;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.kylinhunter.plat.commons.service.EServices;

public class SortChoiceTest extends SortCommonTest {

    private static Sort sort;

    @BeforeAll
    static void beforeAll() {
        sort = EServices.get(SortType.Choice);
    }

    @Test
    public void sort() {
        this.sort(sort);
    }

}
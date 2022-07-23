package com.kylinhunter.plat.algorithm.sort.imp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.kylinhunter.plat.algorithm.sort.Sort;
import com.kylinhunter.plat.algorithm.sort.SortType;
import com.kylinhunter.plat.commons.service.EServices;

public class SortChoiceTest extends AbstractSortTest {


    @Test
    public void sort() {
        this.sort(SortType.Choice);
    }

}
package com.kylinhunter.plat.dao.exception;

import com.kylinhunter.plat.commons.exception.inner.biz.ex.DBException;

import lombok.extern.slf4j.Slf4j;

/**
 * @description
 * @author BiJi'an
 * @date   2021/8/1
 **/
@Slf4j
public class DAOExceptionConverter {

    private static final String SQLSTATECONSTRAINT = "23";

    static {

    }

    public static DBException convert(Throwable throwable) {
        return new DBException("", throwable);
    }
}

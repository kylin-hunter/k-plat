package com.kylinhunter.plat.commons.exception.info;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022/1/1
 **/
@Data
@AllArgsConstructor
public class ErrInfo {
    private int classCode;
    private int code;
    private String defaultMsg;

    public ErrInfo(ErrClassify errClassify) {
        this.classCode = errClassify.getCode();
        this.code = errClassify.next();
    }

    public ErrInfo(ErrClassify errClassify, String defaultMsg) {
        this.classCode = errClassify.getCode();
        this.code = errClassify.next();
        this.defaultMsg = defaultMsg;
    }

    public ErrInfo(int code, String defaultMsg) {
        this.code = code;
        this.defaultMsg = defaultMsg;
    }
}

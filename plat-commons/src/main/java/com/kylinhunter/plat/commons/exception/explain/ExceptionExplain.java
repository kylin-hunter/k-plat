package com.kylinhunter.plat.commons.exception.explain;

import com.kylinhunter.plat.commons.exception.common.ThrowableEx;
import com.kylinhunter.plat.commons.exception.info.ErrInfo;
import com.kylinhunter.plat.commons.exception.info.ErrInfos;

import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-19 18:59
 **/

@Data
public class ExceptionExplain {
    private ErrInfo errInfo;
    private Object extra;
    private String msg;

    public ExceptionExplain(ThrowableEx throwableEx, String msg) {
        this.errInfo = throwableEx.getErrInfo();
        if (this.errInfo == null) {
            this.errInfo = ErrInfos.UNKNOWN;
        }
        this.extra = throwableEx.getExtra();
        this.msg = msg;
    }

    public ExceptionExplain(ErrInfo errInfo, String msg) {
        this.errInfo = errInfo;
        if (this.errInfo == null) {
            this.errInfo = ErrInfos.UNKNOWN;
        }
        this.msg = msg;
    }

    public String getMsg(boolean defaultIfEmpty) {
        String defaultMsg = this.errInfo.getDefaultMsg();
        if (this.msg != null && this.msg.length() > 0) {
            return this.msg;
        } else {
            return defaultMsg;
        }
    }

}

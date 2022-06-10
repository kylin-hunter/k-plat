package com.kylinhunter.plat.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kylinhunter.plat.commons.exception.info.ErrInfoManager;
import com.kylinhunter.plat.commons.exception.info.ErrInfos;
import com.kylinhunter.plat.web.trace.explain.TraceExplain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @description 默认的响应结果
 * @author BiJi'an
 * @date   2022/01/01
 **/
@Getter
@Setter
@ApiModel("返回bean")
public class DefaultResponse<T> implements Response<T> {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("业务状态码 0正常  非0错误")
    private int code ;
    @ApiModelProperty("业务处理描述")
    private String msg;
    @ApiModelProperty("业务结果")
    private T data;
    @ApiModelProperty("开始处理时间")
    private long time;
    @ApiModelProperty("处理开始时间")
    @JsonInclude(Include.NON_NULL)
    private String startTime;
    @JsonInclude(Include.NON_NULL)
    @ApiModelProperty("处理结束时间")
    private String endTime;
    @ApiModelProperty("整个请求处理耗时")
    private long durationTime;
    @ApiModelProperty("traceId")
    private String traceId;
    @ApiModelProperty(value = "详细细节", hidden = true)
    @JsonInclude(Include.NON_NULL)
    private TraceExplain traceExplain;

    public DefaultResponse() {
        this(ErrInfos.CODE_SUCCESS, null, null);
    }

    public DefaultResponse(T data) {
        this(ErrInfos.CODE_SUCCESS, null, data);
    }

    public DefaultResponse(int code, String msg) {
        this(code, msg, null);
    }

    public DefaultResponse(int code, String msg, T data) {
        this.code = code;
        if (code == ErrInfos.CODE_SUCCESS) {
            this.msg = "ok";
        } else {
            this.msg = ErrInfoManager.getDefaultMsg(this.code);
            if (msg != null && msg.length() > 0) {
                this.msg += ":" + msg;
            }
        }
        this.time = 0;
        this.data = data;
    }

    public DefaultResponse(BasicResponse<T> basicResponse) {
        if (basicResponse != null) {
            this.code = basicResponse.getCode();
            this.msg = basicResponse.getMsg();
            this.data = basicResponse.getData();
        }
    }
}

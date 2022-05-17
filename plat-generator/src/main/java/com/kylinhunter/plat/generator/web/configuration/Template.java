package com.kylinhunter.plat.generator.web.configuration;

import lombok.Getter;

/**
 * @description 模板类型
 * @author BiJi'an
 * @date   2021/8/6
 **/
@Getter
public enum Template {
    VO_CREATE(TemplateType.VO, "", "ReqCreate"),
    VO_UPDATE(TemplateType.VO, "", "ReqUpdate"),
    VO_RESPONSE(TemplateType.VO, "", "Resp"),
    VO_REQ_QUREY(TemplateType.VO, "", "ReqQuery"),
    SERVICE_LOCAL(TemplateType.SERVICE, "", "Service"),
    SERVICE_LOCAL_IMP(TemplateType.SERVICE, "", "ServiceImp"),
    SERVICE_RPC(TemplateType.SERVICE, "Rpc", "Service"),
    SERVICE_RPC_IMP(TemplateType.SERVICE, "Rpc", "ServiceImp"),
    CONTROLLER(TemplateType.CONTROLLER, "", "Controller");
    private TemplateType type;
    private final String suffix;
    private final String prefix;

    Template(TemplateType type, String prefix, String suffix) {
        this.type = type;
        this.prefix = prefix;
        this.suffix = suffix;
    }
}


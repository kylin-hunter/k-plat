package com.kylinhunter.plat.generator.kplat.configuration;


import io.github.kylinhunter.commons.io.file.FileExtensions;
import lombok.Getter;

/**
 * @author BiJi'an
 * @description 模板类型
 * @date 2022/01/01
 **/
@Getter
public enum Template {

    VO_CREATE(TemplateType.VO, "", "ReqCreate", FileExtensions.JAVA),
    VO_UPDATE(TemplateType.VO, "", "ReqUpdate", FileExtensions.JAVA),
    VO_RESPONSE(TemplateType.VO, "", "Resp", FileExtensions.JAVA),
    VO_REQ_QUREY(TemplateType.VO, "", "ReqQuery", FileExtensions.JAVA),
    VO(TemplateType.VO, "", "VO", FileExtensions.JAVA),
    SERVICE_LOCAL(TemplateType.SERVICE, "", "Service", FileExtensions.JAVA),
    SERVICE_LOCAL_IMP(TemplateType.SERVICE, "", "ServiceImp", FileExtensions.JAVA),
    SERVICE_INTERCEPTOR_SAVE_UPDATE(TemplateType.SERVICE_INTERCEPTOR, "", "SaveOrUpdateInterceptor",
            FileExtensions.JAVA),
    SERVICE_INTERCEPTOR_DELETE(TemplateType.SERVICE_INTERCEPTOR, "", "DeleteInterceptor", FileExtensions.JAVA),
    //    SERVICE_RPC(TemplateType.SERVICE, "Rpc", "Service"),
    //    SERVICE_RPC_IMP(TemplateType.SERVICE, "Rpc", "ServiceImp"),
    CONTROLLER(TemplateType.CONTROLLER, "", "Controller", FileExtensions.JAVA);
    private final TemplateType type;
    private final String suffix;
    private final String prefix;
    private final String extension;

    Template(TemplateType type, String prefix, String suffix, String extension) {
        this.type = type;
        this.prefix = prefix;
        this.suffix = suffix;
        this.extension = extension;
    }

    public String getName(String entityName, boolean extension) {
        if (extension) {
            return this.prefix + entityName + this.suffix + this.extension;

        } else {
            return this.prefix + entityName + this.suffix;

        }
    }

}


package com.kylinhunter.plat.generator.auto.core.configuration;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author BiJi'an
 * @description 模板配置
 * @date 2021/8/4
 **/
@Data
@Accessors(chain = true)
public class TemplateConfig {
    private static final String DEFAULT_TEMPLATE_REQ_VO_CREATE = "/generator/templates/req_vo_create.java";
    private static final String DEFAULT_TEMPLATE_REQ_VO_UPDATE = "/generator/templates/req_vo_update.java";
    private static final String DEFAULT_TEMPLATE_RESP_VO_RESPONSE = "/generator/templates/resp_vo.java";
    private static final String DEFAULT_TEMPLATE_RESP_VO_REQ_QUERY = "/generator/templates/req_vo_query.java";
    private static final String DEFAULT_TEMPLATE_RESP_VO = "/generator/templates/vo.java";
    private static final String DEFAULT_TEMPLATE_SERVICE_LOCAL = "/generator/templates/service_local.java";
    private static final String DEFAULT_TEMPLATE_SERVICE_LOCAL_IMP = "/generator/templates/service_local_imp.java";
    private static final String DEFAULT_TEMPLATE_SERVICE_RPC = "/generator/templates/service_rpc.java";
    private static final String DEFAULT_TEMPLATE_SERVICE_RPC_IMP = "/generator/templates/service_rpc_imp.java";
    private static final String DEFAULT_TEMPLATE_CONTROLLER = "/generator/templates/controller.java";

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Map<Template, String> templates = Maps.newHashMap();
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Map<Template, Boolean> templateStates = Maps.newHashMap();

    public TemplateConfig() {
        setTemplate(Template.VO_CREATE, DEFAULT_TEMPLATE_REQ_VO_CREATE);
        setTemplate(Template.VO_UPDATE, DEFAULT_TEMPLATE_REQ_VO_UPDATE);
        setTemplate(Template.VO_RESPONSE, DEFAULT_TEMPLATE_RESP_VO_RESPONSE);
        setTemplate(Template.VO_REQ_QUREY, DEFAULT_TEMPLATE_RESP_VO_REQ_QUERY);
        setTemplate(Template.VO, DEFAULT_TEMPLATE_RESP_VO);
        setTemplate(Template.SERVICE_LOCAL, DEFAULT_TEMPLATE_SERVICE_LOCAL);
        setTemplate(Template.SERVICE_LOCAL_IMP, DEFAULT_TEMPLATE_SERVICE_LOCAL_IMP);
        //        setTemplate(Template.SERVICE_RPC, DEFAULT_TEMPLATE_SERVICE_RPC);
        //        setTemplate(Template.SERVICE_RPC_IMP, DEFAULT_TEMPLATE_SERVICE_RPC_IMP);
        setTemplate(Template.CONTROLLER, DEFAULT_TEMPLATE_CONTROLLER);
        setEnabled(TemplateType.VO, true);
        setEnabled(TemplateType.SERVICE, true);
        setEnabled(TemplateType.CONTROLLER, true);
    }

    public void setTemplate(@NonNull Template templateInst, @NonNull String template) {
        if (StringUtils.isNotBlank(template)) {
            templates.put(templateInst, template);
        }
    }

    /**
     * @param template template
     * @return java.lang.String
     * @title 获取模板
     * @description
     * @author BiJi'an
     * @date 2022/01/01 4:42 下午
     */
    public String getTemplate(@NonNull Template template) {
        return templates.get(template);
    }

    /**
     * @param templateType templateType
     * @return void
     * @title 开启模板
     * @description
     * @author BiJi'an
     * @date 2022/01/01 4:42 下午
     */
    public void setEnabled(TemplateType templateType, boolean enabled) {
        Arrays.stream(Template.values()).filter(t -> t.getType() == templateType)
                .forEach(t -> templateStates.put(t, enabled));
    }

    public void setEnabled(Template template, boolean enabled) {
        templateStates.put(template, enabled);
    }

    /**
     * @param template template
     * @return boolean
     * @title 检查模板是否开启
     * @description
     * @author BiJi'an
     * @date 2022/01/01 4:43 下午
     */
    public boolean isEnabled(Template template) {
        return templateStates.getOrDefault(template, false);
    }
}

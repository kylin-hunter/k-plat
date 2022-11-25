package io.github.kylinhunter.plat.core.init.data;

import java.util.UUID;

import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqCreate;

import lombok.Getter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-13 02:03
 **/
@Component
@Getter
public class TenantCatalogInitDatas extends BasicInitDatas<TenantCatalogReqCreate, TenantCatalog> {
    public static final String DEFAULT_CODE = "default";
    public final static int DEFAULT_TYPE = 0;
    private TenantCatalogReqCreate DEFAULT = createDefaultCatalog();

    private TenantCatalogReqCreate createDefaultCatalog() {

        TenantCatalogReqCreate reqCreate = new TenantCatalogReqCreate();
        reqCreate.setId(UUID.randomUUID().toString().replace("-", ""));
        reqCreate.setType(DEFAULT_TYPE);
        reqCreate.setCode(DEFAULT_CODE);
        reqCreate.setName(DEFAULT_CODE);
        reqCreate.setStatus(0);

        reqCreate.setLevel(0);
        reqCreate.setPath("0");
        reqCreate.setParentId("0");
        reqCreate.setDescription(DEFAULT_CODE);
        this.addInitData(reqCreate.getCode(), reqCreate);
        return reqCreate;
    }

}

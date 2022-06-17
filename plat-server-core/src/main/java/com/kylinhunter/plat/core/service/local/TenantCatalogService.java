package com.kylinhunter.plat.core.service.local;

import com.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogTree;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogVO;
import com.kylinhunter.plat.api.service.local.CommonService;

/**
 * <p>
 * TenantCatalogService 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-17
 */
public interface TenantCatalogService extends CommonService<TenantCatalog,
        TenantCatalogReqCreate, TenantCatalogReqUpdate,
        TenantCatalogResp, TenantCatalogVO, TenantCatalogReqQuery> {
    TenantCatalog queryByCode(int type, String code);

    TenantCatalogTree tree(int type);

}

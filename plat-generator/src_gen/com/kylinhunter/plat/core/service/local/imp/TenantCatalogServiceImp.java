package com.kylinhunter.plat.core.service.local.imp;

import com.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogVO;
import com.kylinhunter.plat.core.service.local.TenantCatalogService;
import com.kylinhunter.plat.core.dao.mapper.TenantCatalogMapper;
import com.kylinhunter.plat.dao.service.local.CommonServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 * TenantCatalogServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-17
 */
@Service
public class TenantCatalogServiceImp
        extends CommonServiceImpl<TenantCatalogMapper, TenantCatalog,
        TenantCatalogReqCreate, TenantCatalogReqUpdate,
        TenantCatalogResp, TenantCatalogVO, TenantCatalogReqQuery> implements TenantCatalogService {

}
package com.kylinhunter.plat.core.service.local.imp;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogTree;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogVO;
import com.kylinhunter.plat.commons.exception.inner.ParamException;
import com.kylinhunter.plat.core.dao.mapper.TenantCatalogMapper;
import com.kylinhunter.plat.core.init.data.TenantCatalogInitDatas;
import com.kylinhunter.plat.core.service.local.TenantCatalogService;
import com.kylinhunter.plat.core.service.local.interceptor.TenantCatalogDeleteInterceptor;
import com.kylinhunter.plat.core.service.local.interceptor.TenantCatalogSaveOrUpdateInterceptor;
import com.kylinhunter.plat.dao.service.local.CommonServiceImpl;

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

    public TenantCatalogServiceImp(TenantCatalogSaveOrUpdateInterceptor tenantCatalogSaveOrUpdateInterceptor,
                                   TenantCatalogDeleteInterceptor tenantCatalogDeleteInterceptor) {
        this.saveOrUpdateInterceptor = tenantCatalogSaveOrUpdateInterceptor;
        this.deleteInterceptor = tenantCatalogDeleteInterceptor;
    }

    @Override
    public TenantCatalog queryByCode(int type, String code) {
        LambdaQueryWrapper<TenantCatalog> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(TenantCatalog::getSysDeleteFlag, false);
        queryWrapper.eq(TenantCatalog::getSysTenantId, userContextHandler.get().getTenantId());
        queryWrapper.eq(TenantCatalog::getType, type);
        queryWrapper.eq(TenantCatalog::getCode, code);
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public TenantCatalogTree tree(int type) {

        LambdaQueryWrapper<TenantCatalog> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(TenantCatalog::getSysDeleteFlag, false);
        queryWrapper.eq(TenantCatalog::getSysTenantId, userContextHandler.get().getTenantId());
        queryWrapper.eq(TenantCatalog::getType, type);
        queryWrapper.orderByAsc(TenantCatalog::getLevel);
        List<TenantCatalog> tenantCatalogs = this.baseMapper.selectList(queryWrapper);
        return tree(tenantCatalogs);
    }

    private TenantCatalogTree tree(List<TenantCatalog> tenantCatalogs) {
        TenantCatalogTree root = new TenantCatalogTree();

        Map<String, TenantCatalogTree> allCatalogs =
                tenantCatalogs.stream().collect(Collectors.toMap(e -> e.getId(), e -> {
                    TenantCatalogTree tmpTreeNode = new TenantCatalogTree();
                    BeanUtils.copyProperties(e, tmpTreeNode);

                    return tmpTreeNode;
                }, (o, n) -> n, LinkedHashMap::new));

        for (Map.Entry<String, TenantCatalogTree> en : allCatalogs.entrySet()) {
            TenantCatalogTree curTreeNode = en.getValue();
            if (TenantCatalogInitDatas.DEFAULT_CODE.equals(curTreeNode.getCode())) {
                root = curTreeNode;
            } else {
                TenantCatalogTree parentNode = allCatalogs.get(curTreeNode.getParentId());
                if (parentNode != null) {
                    parentNode.addChild(curTreeNode);

                } else {
                    throw new ParamException("invalid parentId:" + curTreeNode.getParentId());
                }
            }

        }
        return root;
    }
}
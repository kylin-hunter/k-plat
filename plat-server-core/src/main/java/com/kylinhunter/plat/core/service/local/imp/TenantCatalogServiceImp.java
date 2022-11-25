package com.kylinhunter.plat.core.service.local.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import io.github.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import io.github.kylinhunter.plat.api.bean.vo.delete.ReqDeletes;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogTree;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogVO;
import com.kylinhunter.plat.core.dao.mapper.TenantCatalogMapper;
import com.kylinhunter.plat.core.service.local.TenantCatalogService;
import com.kylinhunter.plat.core.service.local.component.TenantCatalogTreeComponent;
import com.kylinhunter.plat.core.service.local.interceptor.TenantCatalogDeleteInterceptor;
import com.kylinhunter.plat.core.service.local.interceptor.TenantCatalogSaveOrUpdateInterceptor;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;

import io.github.kylinhunter.commons.exception.embed.ParamException;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * TenantCatalogServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-17
 */
@Service
@Slf4j
public class TenantCatalogServiceImp
        extends CommonServiceImpl<TenantCatalogMapper, TenantCatalog,
        TenantCatalogReqCreate, TenantCatalogReqUpdate,
        TenantCatalogResp, TenantCatalogVO, TenantCatalogReqQuery> implements TenantCatalogService {

    @Autowired
    private TenantCatalogTreeComponent tenantCatalogTreeComponent;

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
        return tenantCatalogTreeComponent.tree(tenantCatalogs);
    }

    @Override
    public boolean delete(ReqDelete reqDelete) {

        boolean ok = super.delete(reqDelete);
        log.info("delete catalog id:{}", reqDelete.getId());
        List<TenantCatalog> allChildren = Lists.newArrayList();
        fetchAllChildren(reqDelete.getId(), allChildren);
        if (allChildren.size() > 0) {
            List<String> ids = allChildren.stream().map(e -> {
                log.info("delete child id={},name={}", e.getId(), e.getName());

                return e.getId();
            }).collect(Collectors.toList());
            ok = this.baseMapper.deleteBatchIds(ids) > 0;

        }

        throw new ParamException("回滚");

    }

    public void fetchAllChildren(String parentId, List<TenantCatalog> allChildren) {
        List<TenantCatalog> curChildren = this.baseMapper.selectByParentId(parentId);
        if (curChildren != null && curChildren.size() > 0) {
            curChildren.forEach(child -> {
                allChildren.add(child);
                fetchAllChildren(child.getId(), allChildren);
            });
        }

    }

    @Override
    public boolean delete(ReqDeletes reqDeletes) {
        throw new ParamException("Batch delete operation is not supported");
    }
}
/*
 * Copyright (C) 2023 The k-commons Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kylinhunter.plat.core.service.local.imp;

import com.google.common.collect.Lists;
import io.github.kylinhunter.commons.collections.ListUtils;
import io.github.kylinhunter.commons.exception.embed.ParamException;
import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
import io.github.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import io.github.kylinhunter.plat.api.bean.vo.delete.ReqDeletes;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqCreateBatch;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqMove;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqSort;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogRespTree;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogVO;
import io.github.kylinhunter.plat.core.dao.mapper.TenantCatalogMapper;
import io.github.kylinhunter.plat.core.service.local.TenantCatalogService;
import io.github.kylinhunter.plat.core.service.local.component.TenantCatalogTreeComponent;
import io.github.kylinhunter.plat.core.service.local.interceptor.TenantCatalogDeleteInterceptor;
import io.github.kylinhunter.plat.core.service.local.interceptor.TenantCatalogSaveOrUpdateInterceptor;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * TenantCatalogServiceImp 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2022-06-17
 */
@Service
@Slf4j
public class TenantCatalogServiceImp
    extends CommonServiceImpl<
    TenantCatalogMapper,
    TenantCatalog,
    TenantCatalogReqCreate,
    TenantCatalogReqUpdate,
    TenantCatalogResp,
    TenantCatalogVO,
    TenantCatalogReqQuery>
    implements TenantCatalogService {

  @Autowired
  private TenantCatalogTreeComponent treeComponent;

  public TenantCatalogServiceImp(
      TenantCatalogSaveOrUpdateInterceptor tenantCatalogSaveOrUpdateInterceptor,
      TenantCatalogDeleteInterceptor tenantCatalogDeleteInterceptor) {
    this.saveOrUpdateInterceptor = tenantCatalogSaveOrUpdateInterceptor;
    this.deleteInterceptor = tenantCatalogDeleteInterceptor;
  }

  @Override
  public TenantCatalog findByCode(int type, String code) {
    return this.baseMapper.findByCode(this.getTenanId(), type, code);
  }

  @Override
  public TenantCatalogRespTree tree(int type) {
    return treeComponent.tree(this.baseMapper.findByType(this.getTenanId(), type));
  }

  @Override
  public boolean delete(ReqDelete reqDelete) {
    boolean ok1 = super.delete(reqDelete);
    log.info("delete catalog id:{},{}", reqDelete.getId(), ok1);
    boolean ok2 = false;
    List<TenantCatalog> allChildren = this.findByParentId(reqDelete.getId(), true);
    if (allChildren.size() > 0) {
      List<String> ids = allChildren.stream().map(BaseEntity::getId).collect(Collectors.toList());
      log.info("delete all children ids={}}", ListUtils.toString(ids));
      ok2 = this.baseMapper.deleteBatchIds(ids) > 0;
    }
    return ok1 && ok2;
  }

  @Override
  public List<TenantCatalog> findByParentId(String parentId, boolean recursion) {
    if (recursion) {
      return fetchAllChildren(parentId, Lists.newArrayList());
    } else {
      return this.baseMapper.findByParentId(parentId);
    }
  }

  /**
   * @param parentId    parentId
   * @param allChildren allChildren
   * @return java.util.List<io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog>
   * @title fetchAllChildren
   * @description fetchAllChildren
   * @author BiJi'an
   * @date 2023-10-18 17:28
   */
  private List<TenantCatalog> fetchAllChildren(String parentId, List<TenantCatalog> allChildren) {
    List<TenantCatalog> curChildren = this.baseMapper.findByParentId(parentId);
    if (curChildren != null && curChildren.size() > 0) {
      curChildren.forEach(
          child -> {
            allChildren.add(child);
            fetchAllChildren(child.getId(), allChildren);
          });
    }
    return allChildren;
  }

  @Override
  public boolean delete(ReqDeletes reqDeletes) {
    throw new ParamException(" not supported");
  }

  @Override
  public void init(TenantCatalogReqCreateBatch tenantCatalogReqCreateBatch) {
    this.treeComponent.init(tenantCatalogReqCreateBatch);
  }

  @Override
  public boolean move(TenantCatalogReqMove tenantCatalogReqMove) {
    TenantCatalog curCatalog = this.baseMapper.selectById(tenantCatalogReqMove.getId());
    TenantCatalog parentCatalog = this.baseMapper.selectById(tenantCatalogReqMove.getParentId());

    if (curCatalog != null && parentCatalog != null) {
      setLevelAndPathFromParent(curCatalog, parentCatalog);
      this.baseMapper.updateById(curCatalog);
      updateLevelAndPath(curCatalog);
    }
    return true;
  }

  @Override
  public boolean sort(TenantCatalogReqSort tenantCatalogReqSort) {
    List<String> ids = tenantCatalogReqSort.getIds();
    if (!CollectionUtils.isEmpty(ids)) {
      List<TenantCatalog> catalogs = this.baseMapper.selectBatchIds(ids);

      if (catalogs.size() != ids.size()) {
        throw new ParamException("invalid ids" + ListUtils.toString(ids));
      }
      catalogs = ListUtils.sort(catalogs, TenantCatalog::getId, ids);

      TenantCatalog firstCat = catalogs.get(0);
      for (int i = 0; i < catalogs.size(); i++) {
        TenantCatalog catalog = catalogs.get(i);
        if (!firstCat.getParentId().equals(catalog.getParentId())) {
          throw new ParamException("level not match");
        }
        catalog.setSort(i);
        this.baseMapper.updateById(catalog);
      }
    }

    return true;
  }

  private void updateLevelAndPath(TenantCatalog catalog) {
    List<TenantCatalog> children = this.baseMapper.findByParentId(catalog.getId());
    if (!CollectionUtils.isEmpty(children)) {
      children.forEach(child -> {
        this.setLevelAndPathFromParent(child, catalog);
        this.baseMapper.updateById(child);
        this.updateLevelAndPath(child);
      });
    }
  }

  private void setLevelAndPathFromParent(TenantCatalog cur, TenantCatalog parent) {
    cur.setParentId(parent.getId());
    cur.setLevel(parent.getLevel() + 1);
    cur.setPath(parent.getPath() + "_" + parent.getId());
  }
}

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
package io.github.kylinhunter.plat.core.service.local.component;

import com.google.common.collect.Lists;
import io.github.kylinhunter.commons.exception.embed.InitException;
import io.github.kylinhunter.commons.exception.embed.biz.BizException;
import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqCreateBatch;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogRespTree;
import io.github.kylinhunter.plat.api.module.core.constants.init.DefaultTenantCatalogs;
import io.github.kylinhunter.plat.api.trace.TraceHolder;
import io.github.kylinhunter.plat.core.dao.mapper.TenantCatalogMapper;
import io.github.kylinhunter.plat.dao.service.local.interceptor.BasicInterceptor;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-18 14:56
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class TenantCatalogTreeComponent {

  private final TenantCatalogMapper tenantCatalogMapper;

  /**
   * @param tenantCatalogs tenantCatalogs
   * @return io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogRespTree
   * @title tree
   * @description tree
   * @author BiJi'an
   * @date 2023-10-18 15:42
   */
  public TenantCatalogRespTree tree(List<TenantCatalog> tenantCatalogs) {
    TenantCatalogRespTree root = new TenantCatalogRespTree();

    Map<String, TenantCatalogRespTree> allCatalogs =
        tenantCatalogs.stream()
            .collect(
                Collectors.toMap(
                    BaseEntity::getId,
                    e -> {
                      TenantCatalogRespTree tmpTreeNode = new TenantCatalogRespTree();
                      BeanUtils.copyProperties(e, tmpTreeNode);
                      return tmpTreeNode;
                    },
                    (o, n) -> n,
                    LinkedHashMap::new));

    for (Map.Entry<String, TenantCatalogRespTree> en : allCatalogs.entrySet()) {
      TenantCatalogRespTree curTreeNode = en.getValue();
      if (DefaultTenantCatalogs.ROOT_CODE.equals(curTreeNode.getCode())) {
        root = curTreeNode;
      } else {
        TenantCatalogRespTree parentNode = allCatalogs.get(curTreeNode.getParentId());
        if (parentNode != null) {
          parentNode.addChild(curTreeNode);
        } else {
          throw new BizException("invalid parentId:" + curTreeNode.getParentId());
        }
      }
    }
    return root;
  }

  /**
   * @param tenantCatalogReqCreateBatch tenantCatalogReqInit
   * @return void
   * @title init
   * @description init
   * @author BiJi'an
   * @date 2023-10-19 00:57
   */
  public void init(TenantCatalogReqCreateBatch tenantCatalogReqCreateBatch) {
    log.info("init catalog:" + tenantCatalogReqCreateBatch);
    String tenantId = TraceHolder.get().getUserContext().getTenantId();

    TenantCatalog rootTenantCatalog = tryInitRoot(tenantCatalogReqCreateBatch);
    List<TenantCatalog> allTenantCatalogs = Lists.newArrayList(rootTenantCatalog);
    List<TenantCatalogReqCreateBatch> children = tenantCatalogReqCreateBatch.getChildren();
    for (int i = 0; i < children.size(); i++) {
      TenantCatalogReqCreateBatch child = children.get(i);
      child.setSort(i + 1);
      init(child, rootTenantCatalog.getCode(), allTenantCatalogs);
    }

    log.info("all catalog size =>" + allTenantCatalogs.size());
    List<String> ids = allTenantCatalogs.stream().map(TenantCatalog::getId)
        .collect(Collectors.toList());
    this.tenantCatalogMapper.deleteByTypeAndNotIn(tenantId, tenantCatalogReqCreateBatch.getType(),
        ids);
  }

  /**
   * @param tenantCatalogReqCreateBatch tenantCatalogReqInit
   * @return io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog
   * @title tryInitRoot
   * @description tryInitRoot
   * @author BiJi'an
   * @date 2023-10-19 00:58
   */
  private TenantCatalog tryInitRoot(TenantCatalogReqCreateBatch tenantCatalogReqCreateBatch) {
    String tenantId = TraceHolder.get().getUserContext().getTenantId();

    TenantCatalog tenantCatalog =
        tenantCatalogMapper.findByCode(
            tenantId, tenantCatalogReqCreateBatch.getType(), DefaultTenantCatalogs.ROOT_CODE);
    if (tenantCatalog == null) {
      tenantCatalog = new TenantCatalog();
      tenantCatalog.setType(tenantCatalogReqCreateBatch.getType());
      tenantCatalog.setCode(DefaultTenantCatalogs.ROOT_CODE);
      tenantCatalog.setName(DefaultTenantCatalogs.ROOT_NAME);
      tenantCatalog.setLevel(0);
      tenantCatalog.setPath("0");
      tenantCatalog.setParentId("0");
      tenantCatalog.setSort(0);
      BasicInterceptor.setCreateMsg(tenantCatalog);
      tenantCatalogMapper.insert(tenantCatalog);
    }
    return tenantCatalog;
  }

  /**
   * @param curtCatalog curtCatalog
   * @param parentCode  parentCode
   * @return void
   * @title init
   * @description init
   * @author BiJi'an
   * @date 2023-10-19 00:59
   */
  private void init(TenantCatalogReqCreateBatch curtCatalog, String parentCode,
      List<TenantCatalog> allTenantCatalogs) {

    allTenantCatalogs.add(trySave(curtCatalog, parentCode));
    List<TenantCatalogReqCreateBatch> children = curtCatalog.getChildren();
    if (children != null && children.size() > 0) {
      for (int i = 0; i < children.size(); i++) {
        TenantCatalogReqCreateBatch child = children.get(i);
        child.setSort(i + 1);
        init(child, curtCatalog.getCode(), allTenantCatalogs);
      }
    }
  }

  /**
   * @param curtCatalog curtCatalog
   * @param parentCode  parentCode
   * @return void
   * @title tryInit
   * @description tryInit
   * @author BiJi'an
   * @date 2023-10-19 01:00
   */
  private TenantCatalog trySave(TenantCatalogReqCreateBatch curtCatalog, String parentCode) {

    curtCatalog.setParentCode(parentCode);
    int type = curtCatalog.getType();
    String code = curtCatalog.getCode();
    String tenantId = TraceHolder.get().getUserContext().getTenantId();

    TenantCatalog parent = tenantCatalogMapper.findByCode(tenantId, type, parentCode);
    if (parent == null) {
      throw new InitException("parent is null: " + curtCatalog.getParentCode());
    }

    TenantCatalog catalog = tenantCatalogMapper.findByCode(tenantId, type, code);

    if (catalog != null) {
      catalog.setName(curtCatalog.getName());
      catalog.setParentId(parent.getId());

      catalog.setLevel(parent.getLevel() + 1);
      catalog.setPath(parent.getPath() + "_" + parent.getId());
      catalog.setSort(curtCatalog.getSort());
      BasicInterceptor.setUpdateMsg(catalog);
      tenantCatalogMapper.updateById(catalog);
      log.info("update catalog={}", catalog);

    } else {
      catalog = new TenantCatalog();
      catalog.setType(type);
      catalog.setCode(code);
      catalog.setName(curtCatalog.getName());
      catalog.setParentId(parent.getId());

      catalog.setLevel(parent.getLevel() + 1);
      catalog.setPath(parent.getPath() + "_" + parent.getId());
      catalog.setSort(curtCatalog.getSort());

      BasicInterceptor.setCreateMsg(catalog);
      tenantCatalogMapper.insert(catalog);
      log.info("create catalog={}", code);
    }
    return catalog;
  }
}

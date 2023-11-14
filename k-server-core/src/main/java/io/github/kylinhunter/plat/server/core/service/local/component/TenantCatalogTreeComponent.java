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
package io.github.kylinhunter.plat.server.core.service.local.component;

import com.google.common.collect.Lists;
import io.github.kylinhunter.commons.exception.embed.biz.BizException;
import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqCreateBatch;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogRespTree;
import io.github.kylinhunter.plat.api.module.core.constants.init.DefaultTenantCatalogs;
import io.github.kylinhunter.plat.api.trace.TraceHolder;
import io.github.kylinhunter.plat.server.core.dao.mapper.TenantCatalogMapper;
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
  private final TenantCatalogComponent tenantCatalogComponent;

  /**
   * @param tenantCatalogs tenantCatalogs
   * @return io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogRespTree
   * @title tree
   * @description tree
   * @author BiJi'an
   * @date 2023-10-18 15:42
   */
  public TenantCatalogRespTree tree(List<TenantCatalog> tenantCatalogs, int type) {
    TenantCatalogRespTree root = null;

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
    if (root == null) {
      root = new TenantCatalogRespTree();
      TenantCatalog tenantCatalog = this.tenantCatalogComponent.initRoot(type);
      BeanUtils.copyProperties(tenantCatalog, root);
    }
    return root;
  }

  /**
   * @param createBatch tenantCatalogReqInit
   * @return void
   * @title init
   * @description init
   * @author BiJi'an
   * @date 2023-10-19 00:57
   */
  public void init(TenantCatalogReqCreateBatch createBatch) {
    log.info("init catalog:" + createBatch);
    String tenantId = TraceHolder.get().getUserContext().getTenantId();

    TenantCatalog rootTenantCatalog = tenantCatalogComponent.initRoot(createBatch.getType());
    List<TenantCatalog> allTenantCatalogs = Lists.newArrayList(rootTenantCatalog);
    List<TenantCatalogReqCreateBatch> children = createBatch.getChildren();
    for (int i = 0; i < children.size(); i++) {
      TenantCatalogReqCreateBatch child = children.get(i);
      child.setSort(i + 1);
      child.setType(createBatch.getType());
      init(child, rootTenantCatalog.getCode(), allTenantCatalogs);
    }

    log.info("all catalog size =>" + allTenantCatalogs.size());
    List<String> ids =
        allTenantCatalogs.stream().map(TenantCatalog::getId).collect(Collectors.toList());
    this.tenantCatalogMapper.deleteByTypeAndNotIn(tenantId, createBatch.getType(), ids);
  }

  /**
   * @param catalog catalog
   * @param parentCode parentCode
   * @return void
   * @title init
   * @description init
   * @author BiJi'an
   * @date 2023-10-19 00:59
   */
  private void init(
      TenantCatalogReqCreateBatch catalog, String parentCode, List<TenantCatalog> allCatalogs) {
    TenantCatalog saveCatalog =
        tenantCatalogComponent.save(
            catalog.getType(), catalog.getCode(), catalog.getName(), catalog.getSort(), parentCode);
    allCatalogs.add(saveCatalog);
    List<TenantCatalogReqCreateBatch> children = catalog.getChildren();
    if (children != null && children.size() > 0) {
      for (int i = 0; i < children.size(); i++) {
        TenantCatalogReqCreateBatch child = children.get(i);
        child.setSort(i + 1);
        child.setType(catalog.getType());
        init(child, catalog.getCode(), allCatalogs);
      }
    }
  }
}

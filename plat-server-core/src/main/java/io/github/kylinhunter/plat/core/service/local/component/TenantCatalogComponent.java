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

import io.github.kylinhunter.commons.exception.embed.InitException;
import io.github.kylinhunter.commons.exception.embed.ParamException;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqInit;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogRespTree;
import io.github.kylinhunter.plat.api.trace.TraceHolder;
import io.github.kylinhunter.plat.core.dao.mapper.TenantCatalogMapper;
import io.github.kylinhunter.plat.core.service.local.interceptor.TenantSaveOrUpdateInterceptor;
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
public class TenantCatalogComponent {

  private final TenantCatalogMapper tenantCatalogMapper;

  /**
   * @param tenantCatalogs tenantCatalogs
   * @return io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogRespTree
   * @throws
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
                    e -> e.getId(),
                    e -> {
                      TenantCatalogRespTree tmpTreeNode = new TenantCatalogRespTree();
                      BeanUtils.copyProperties(e, tmpTreeNode);

                      return tmpTreeNode;
                    },
                    (o, n) -> n,
                    LinkedHashMap::new));

    for (Map.Entry<String, TenantCatalogRespTree> en : allCatalogs.entrySet()) {
      TenantCatalogRespTree curTreeNode = en.getValue();
      if (TenantSaveOrUpdateInterceptor.DEFAULT_CODE.equals(curTreeNode.getCode())) {
        root = curTreeNode;
      } else {
        TenantCatalogRespTree parentNode = allCatalogs.get(curTreeNode.getParentId());
        if (parentNode != null) {
          parentNode.addChild(curTreeNode);

        } else {
          throw new ParamException("invalid parentId:" + curTreeNode.getParentId());
        }
      }
    }
    return root;
  }


  public void init(List<TenantCatalogReqInit> tenantCatalogReqInits) {

    log.info("init catalog:" + tenantCatalogReqInits);
    tenantCatalogReqInits.forEach(
        tenantCatalogReqInit -> {
          traversal(tenantCatalogReqInit, TenantSaveOrUpdateInterceptor.DEFAULT_CODE);
        });
  }


  private void traversal(TenantCatalogReqInit curtCatalog, String parentCode) {
    curtCatalog.setParentCode(parentCode);
    log.info(
        " catalog code={},name={},level={},parentCode={}",
        curtCatalog.getCode(),
        curtCatalog.getName(),
        curtCatalog.getLevel(),
        curtCatalog.getParentCode());

    saveC(curtCatalog);
    List<TenantCatalogReqInit> children = curtCatalog.getChildren();
    if (children != null && children.size() > 0) {
      children.forEach(
          child -> {
            traversal(child, curtCatalog.getCode());
          });
    }
  }

  private void saveC(TenantCatalogReqInit tenantCatalogReqInit) {
    String tenantId = TraceHolder.get().getUserContext().getTenantId();

    TenantCatalog parent =
        tenantCatalogMapper.findByCode(tenantId, tenantCatalogReqInit.getType(),
            tenantCatalogReqInit.getParentCode());

    if (parent != null) {

      TenantCatalog catalog = tenantCatalogMapper.findByCode(tenantId,
          tenantCatalogReqInit.getType(),
          tenantCatalogReqInit.getCode());

      if (catalog != null) {
        log.info("exist catalog={}", tenantCatalogReqInit.getCode());

      } else {
        TenantCatalog tenantCatalog = new TenantCatalog();
        tenantCatalog.setType(tenantCatalogReqInit.getType());
        tenantCatalog.setCode(tenantCatalogReqInit.getCode());
        tenantCatalog.setName(tenantCatalogReqInit.getName());
        tenantCatalog.setLevel(parent.getLevel() + 1);
        tenantCatalog.setPath(parent.getPath() + "_" + parent.getId());
        tenantCatalog.setParentId(parent.getId());
        BasicInterceptor.setCreateMsg(tenantCatalog);
        tenantCatalogMapper.insert(tenantCatalog);

        log.info("save catalog {}", tenantCatalog);
      }

    } else {
      throw new InitException("parent is null: " + tenantCatalogReqInit.getParentCode());
    }
  }
}

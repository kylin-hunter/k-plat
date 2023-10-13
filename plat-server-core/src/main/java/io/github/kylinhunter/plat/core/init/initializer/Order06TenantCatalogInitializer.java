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
package io.github.kylinhunter.plat.core.init.initializer;

import io.github.kylinhunter.commons.exception.embed.InitException;
import io.github.kylinhunter.commons.io.ResourceHelper;
import io.github.kylinhunter.commons.utils.json.JsonUtils;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqCreate;
import io.github.kylinhunter.plat.core.init.data.TenantCatalogInitDatas;
import io.github.kylinhunter.plat.core.service.local.TenantCatalogService;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-12 23:53
 */
@Component
@RequiredArgsConstructor
@Slf4j
@Getter
@Setter
public class Order06TenantCatalogInitializer extends BasicInitializer {

  private final TenantCatalogInitDatas tenantCatalogInitData;
  private final TenantCatalogService tenantCatalogService;

  @Override
  public void init() {

    tenantCatalogInitData
        .getInitDatas()
        .values()
        .forEach(
            tenantRoleCreate -> {
              final String code = tenantRoleCreate.getCode();
              final TenantCatalog tenantRole =
                  tenantCatalogService.queryByCode(TenantCatalogInitDatas.DEFAULT_TYPE, code);
              if (tenantRole != null) {
                log.info("default role {} exist", code);
                tenantCatalogInitData.addDbData(code, tenantRole);
              } else {
                tenantCatalogService.save(tenantRoleCreate);
                log.info("default role {} created", code);
                tenantCatalogInitData.addDbData(
                    code,
                    tenantCatalogService.queryByCode(TenantCatalogInitDatas.DEFAULT_TYPE, code));
              }
            });
    try (InputStream inputStream =
        ResourceHelper.getInputStreamInClassPath("/init/tenant_catalog.json")) {
      String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());

      List<CatalogInfo> catalogInfos = JsonUtils.readToListObject(json, CatalogInfo.class);

      log.info("init catalog:" + catalogInfos);
      catalogInfos.forEach(
          catalogInfo -> {
            traversal(catalogInfo, TenantCatalogInitDatas.DEFAULT_CODE);
          });
    } catch (IOException e) {
      throw new InitException("init catalog error", e);
    }
  }

  private void traversal(CatalogInfo curtCatalog, String parentCode) {
    curtCatalog.setParentCode(parentCode);
    log.info(
        " catalog code={},name={},level={},parentCode={}",
        curtCatalog.code,
        curtCatalog.getName(),
        curtCatalog.getLevel(),
        curtCatalog.getParentCode());

    init(curtCatalog);
    List<CatalogInfo> children = curtCatalog.children;
    if (children != null && children.size() > 0) {
      children.forEach(
          child -> {
            child.setLevel(curtCatalog.getLevel() + 1);
            traversal(child, curtCatalog.code);
          });
    }
  }

  private void init(CatalogInfo catalogInfo) {
    TenantCatalog parent =
        tenantCatalogService.queryByCode(catalogInfo.type, catalogInfo.parentCode);

    if (parent != null) {

      TenantCatalog catalog = tenantCatalogService.queryByCode(catalogInfo.type, catalogInfo.code);

      if (catalog != null) {
        log.info("exist catalog={}", catalogInfo.code);

      } else {
        TenantCatalogReqCreate tenantCatalogReqCreate = new TenantCatalogReqCreate();
        tenantCatalogReqCreate.setType(catalogInfo.type);
        tenantCatalogReqCreate.setCode(catalogInfo.code);
        tenantCatalogReqCreate.setName(catalogInfo.name);
        tenantCatalogReqCreate.setLevel(catalogInfo.level);
        tenantCatalogReqCreate.setPath(parent.getPath() + "_" + parent.getId());
        tenantCatalogReqCreate.setParentId(parent.getId());
        tenantCatalogService.save(tenantCatalogReqCreate);
        log.info("save catalog {}", tenantCatalogReqCreate);
      }

    } else {
      throw new InitException("parent is null: " + catalogInfo.getParentCode());
    }
  }

  @ToString
  @Getter
  @Setter
  public static class CatalogInfo {
    String code;
    String name;
    int level = 0;
    int type = TenantCatalogInitDatas.DEFAULT_TYPE;
    String parentCode = "0";
    List<CatalogInfo> children;
  }
}

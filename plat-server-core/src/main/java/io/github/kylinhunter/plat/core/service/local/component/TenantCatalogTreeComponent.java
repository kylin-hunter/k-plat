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

import io.github.kylinhunter.commons.exception.embed.ParamException;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogTree;
import io.github.kylinhunter.plat.core.init.data.TenantCatalogInitDatas;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-18 14:56
 */
@Component
public class TenantCatalogTreeComponent {

  public TenantCatalogTree tree(List<TenantCatalog> tenantCatalogs) {
    TenantCatalogTree root = new TenantCatalogTree();

    Map<String, TenantCatalogTree> allCatalogs =
        tenantCatalogs.stream()
            .collect(
                Collectors.toMap(
                    e -> e.getId(),
                    e -> {
                      TenantCatalogTree tmpTreeNode = new TenantCatalogTree();
                      BeanUtils.copyProperties(e, tmpTreeNode);

                      return tmpTreeNode;
                    },
                    (o, n) -> n,
                    LinkedHashMap::new));

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

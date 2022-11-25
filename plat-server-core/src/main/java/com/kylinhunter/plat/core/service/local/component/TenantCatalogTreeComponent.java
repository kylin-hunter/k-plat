package com.kylinhunter.plat.core.service.local.component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogTree;
import com.kylinhunter.plat.core.init.data.TenantCatalogInitDatas;

import io.github.kylinhunter.commons.exception.embed.ParamException;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-18 14:56
 **/
@Component
public class TenantCatalogTreeComponent {

    public TenantCatalogTree tree(List<TenantCatalog> tenantCatalogs) {
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

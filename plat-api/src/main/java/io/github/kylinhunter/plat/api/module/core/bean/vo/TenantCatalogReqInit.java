package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.module.core.constants.TenantCatalogType;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-18 11:29
 */
@ToString
@Getter
@Setter
public class TenantCatalogReqInit {

  String code;
  String name;
  int level = 0;
  int type = TenantCatalogType.DEFAULT.getCode();
  String parentCode = "0";
  List<TenantCatalogReqInit> children;
}
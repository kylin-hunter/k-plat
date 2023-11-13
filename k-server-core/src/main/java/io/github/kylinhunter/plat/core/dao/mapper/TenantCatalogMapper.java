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
package io.github.kylinhunter.plat.core.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Mapper 接口
 *
 * @author biji'an
 * @since 2022-06-17
 */
@Mapper
@Repository
public interface TenantCatalogMapper extends BaseMapper<TenantCatalog> {

  @Select(
      "SELECT *  FROM  kplat_tenant_catalog  "
          + "WHERE  parent_id = #{parentId}  and sys_delete_flag=0 ")
  List<TenantCatalog> findByParentId(@Param("parentId") String parentId);

  @Select(
      "SELECT count(id)  FROM  kplat_tenant_catalog  "
          + "WHERE  parent_id = #{parentId}  and sys_delete_flag=0 ")
  int selectCountByParentId(@Param("parentId") String parentId);

  @Select(
      "SELECT *  FROM  kplat_tenant_catalog  "
          + "WHERE sys_tenant_id = #{tenantId} and type = #{type} and code = #{code} and sys_delete_flag=0 ")
  TenantCatalog findByCode(
      @Param("tenantId") String tenantId, @Param("type") int type, @Param("code") String code);

  @Select(
      "SELECT *  FROM  kplat_tenant_catalog  "
          + "WHERE sys_tenant_id = #{tenantId} and type = #{type}  and sys_delete_flag=0 order by level,sort asc ")
  List<TenantCatalog> findByType(@Param("tenantId") String tenantId, @Param("type") int type);

  @Delete(
      "<script>"
          + "delete  FROM  kplat_tenant_catalog  "
          + "WHERE sys_tenant_id = #{tenantId} and type = #{type}  "
          + " <choose>"
          + " <when test=\"ids != null and ids.size() > 0\"  >"
          + "     and id not in"
          + "      <foreach collection=\"ids\" item=\"id\" separator=\",\" open=\"(\" close=\")\">"
          + "        #{id}"
          + "      </foreach>"
          + " </when>"
          + "<otherwise>"
          + "   and 1 != 1"
          + "</otherwise>"
          + "</choose>"
          + "</script>")
  int deleteByTypeAndNotIn(
      @Param("tenantId") String tenantId, @Param("type") int type, @Param("ids") List<String> ids);
}

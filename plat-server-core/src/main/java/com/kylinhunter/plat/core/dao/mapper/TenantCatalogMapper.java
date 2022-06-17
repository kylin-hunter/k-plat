package com.kylinhunter.plat.core.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author biji'an
 * @since 2022-06-17
 */
@Mapper
@Repository
public interface TenantCatalogMapper extends BaseMapper<TenantCatalog> {

    @Select("SELECT *  FROM  kplat_tenant_catalog  WHERE"
            + "  parent_id = #{parentId} "
            + " and sys_delete_flag=0 ")
    TenantCatalog selectByParentId(@Param("parentId") String parentId);

}

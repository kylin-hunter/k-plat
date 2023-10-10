package io.github.kylinhunter.plat.core.dao.mapper;
import io.github.kylinhunter.plat.api.module.core.bean.entity.Permission;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author biji'an
 * @since 2023-10-10
 */
@Mapper
@Repository
public interface TenantUserRoleMapper extends BaseMapper<TenantUserRole> {


  @Select(
      "SELECT t3.*  FROM  kplat_tenant_user_role t1,kplat_tenant_role_permission t2,kplat_permission t3  "
          + "  WHERE t1.role_id=t2.role_id and t2.permission_id=t3.id"
          + "  and t1.sys_tenant_id = #{tenantId} and t1.user_id = #{userId}")
  List<Permission> getPermissionsByUserId(@Param("tenantId") String tenantId,@Param("userId") String userId);

}

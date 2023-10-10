package io.github.kylinhunter.plat.core.dao.mapper;
import io.github.kylinhunter.plat.api.module.core.bean.entity.Permission;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import io.github.kylinhunter.plat.api.module.core.bean.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author biji'an
 * @since 2023-10-07
 */
@Mapper
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

  @Select(
      "SELECT t3.*  FROM  kplat_user_role t1,kplat_role_permission t2,kplat_permission t3  "
          + "  WHERE t1.role_id=t2.role_id and t2.permission_id=t3.id"
          + "  and t1.user_id = #{userId}")
  List<Permission> findPermissionsByUserId(@Param("userId") String userId);

}

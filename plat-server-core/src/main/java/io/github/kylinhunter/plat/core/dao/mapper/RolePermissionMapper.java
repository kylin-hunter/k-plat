package io.github.kylinhunter.plat.core.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kylinhunter.plat.api.module.core.bean.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

}

package io.github.kylinhunter.plat.core.dao.mapper;
import org.apache.ibatis.annotations.Mapper;
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

}

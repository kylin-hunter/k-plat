package io.github.kylinhunter.plat.core.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author biji'an
 * @since 2022-01-01
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {



}

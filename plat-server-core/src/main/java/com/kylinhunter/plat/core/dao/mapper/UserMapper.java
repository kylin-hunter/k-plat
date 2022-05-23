package com.kylinhunter.plat.core.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kylinhunter.plat.api.module.core.enity.User;

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

package com.kylinhunter.plat.core.dao.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import io.github.kylinhunter.plat.api.module.core.bean.entity.SysUserConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author biji'an
 * @since 2022-06-23
 */
@Mapper
@Repository
public interface SysUserConfigMapper extends BaseMapper<SysUserConfig> {

}

package com.kylinhunter.plat.core.dao.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.kylinhunter.plat.api.module.core.bean.entity.TenantUserConfig;
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
public interface TenantUserConfigMapper extends BaseMapper<TenantUserConfig> {

}

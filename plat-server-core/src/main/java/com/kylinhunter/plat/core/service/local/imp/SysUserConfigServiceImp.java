package com.kylinhunter.plat.core.service.local.imp;

import com.kylinhunter.plat.api.module.core.bean.entity.SysUserConfig;
import com.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigResp;
import com.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigVO;
import com.kylinhunter.plat.core.service.local.SysUserConfigService;
import com.kylinhunter.plat.core.dao.mapper.SysUserConfigMapper;
import com.kylinhunter.plat.dao.service.local.CommonServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 * SysUserConfigServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-23
 */
@Service
public class SysUserConfigServiceImp
        extends CommonServiceImpl<SysUserConfigMapper, SysUserConfig,
        SysUserConfigReqCreate, SysUserConfigReqUpdate,
        SysUserConfigResp, SysUserConfigVO, SysUserConfigReqQuery> implements SysUserConfigService {

}
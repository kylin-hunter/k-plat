package com.kylinhunter.plat.core.service.local.imp;

import com.kylinhunter.plat.api.module.core.bean.entity.SysConfig;
import com.kylinhunter.plat.api.module.core.bean.vo.SysConfigReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.SysConfigReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.SysConfigReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.SysConfigResp;
import com.kylinhunter.plat.api.module.core.bean.vo.SysConfigVO;
import com.kylinhunter.plat.core.service.local.SysConfigService;
import com.kylinhunter.plat.core.dao.mapper.SysConfigMapper;
import com.kylinhunter.plat.dao.service.local.CommonServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 * SysConfigServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-23
 */
@Service
public class SysConfigServiceImp
        extends CommonServiceImpl<SysConfigMapper, SysConfig,
        SysConfigReqCreate, SysConfigReqUpdate,
        SysConfigResp, SysConfigVO, SysConfigReqQuery> implements SysConfigService {

}
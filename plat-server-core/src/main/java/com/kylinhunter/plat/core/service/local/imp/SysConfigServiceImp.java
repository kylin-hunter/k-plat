package com.kylinhunter.plat.core.service.local.imp;

import org.springframework.stereotype.Service;

import io.github.kylinhunter.plat.api.module.core.bean.entity.SysConfig;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysConfigReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysConfigReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysConfigReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysConfigResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysConfigVO;
import com.kylinhunter.plat.core.dao.mapper.SysConfigMapper;
import com.kylinhunter.plat.core.service.local.SysConfigService;
import com.kylinhunter.plat.dao.service.local.CommonServiceImpl;

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
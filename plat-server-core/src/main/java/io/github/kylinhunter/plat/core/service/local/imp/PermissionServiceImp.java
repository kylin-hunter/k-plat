package io.github.kylinhunter.plat.core.service.local.imp;

import io.github.kylinhunter.plat.api.module.core.bean.entity.Permission;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionVO;
import io.github.kylinhunter.plat.core.dao.mapper.PermissionMapper;
import io.github.kylinhunter.plat.core.service.local.PermissionService;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * PermissionServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-07
 */
@Service
public class PermissionServiceImp
    extends CommonServiceImpl<PermissionMapper, Permission,
    PermissionReqCreate, PermissionReqUpdate,
    PermissionResp, PermissionVO, PermissionReqQuery> implements PermissionService {


}
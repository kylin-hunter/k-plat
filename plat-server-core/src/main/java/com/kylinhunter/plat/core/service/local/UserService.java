package com.kylinhunter.plat.core.service.local;

import com.kylinhunter.plat.api.module.core.bean.entity.User;
import com.kylinhunter.plat.api.module.core.bean.vo.UserReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.UserReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.UserReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.UserResp;
import com.kylinhunter.plat.api.module.core.bean.vo.UserVO;
import com.kylinhunter.plat.api.service.local.CommonService;
import com.kylinhunter.plat.web.auth.LoginForm;

/**
 * <p>
 * UserService 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-01-01
 */
public interface UserService extends CommonService<User, UserReqCreate, UserReqUpdate, UserResp, UserVO, UserReqQuery> {

}
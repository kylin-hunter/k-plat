package com.kylinhunter.plat.core.service.local.imp;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kylinhunter.plat.api.module.core.bean.entity.User;
import com.kylinhunter.plat.api.module.core.bean.vo.UserReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.UserReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.UserReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.UserResp;
import com.kylinhunter.plat.api.module.core.bean.vo.UserVO;
import com.kylinhunter.plat.core.dao.mapper.UserMapper;
import com.kylinhunter.plat.core.service.local.UserService;
import com.kylinhunter.plat.core.service.local.interceptor.UserDeleteInterceptor;
import com.kylinhunter.plat.core.service.local.interceptor.UserSaveOrUpdateInterceptor;
import com.kylinhunter.plat.dao.service.local.CommonServiceImpl;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * UserServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-01-01
 */
@Service
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class UserServiceImp extends CommonServiceImpl<UserMapper, User, UserReqCreate, UserReqUpdate,
        UserResp, UserVO, UserReqQuery> implements UserService {

    public UserServiceImp(UserSaveOrUpdateInterceptor userSaveOrUpdateInterceptor,
                          UserDeleteInterceptor userDeleteInterceptor) {
        this.saveOrUpdateInterceptor = userSaveOrUpdateInterceptor;
        this.deleteInterceptor = userDeleteInterceptor;
    }

    @Override
    public User queryByUserCode(String userCode) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getSysDeleteFlag, false);
        queryWrapper.eq(User::getUserCode, userCode);
        return this.baseMapper.selectOne(queryWrapper);
    }
}

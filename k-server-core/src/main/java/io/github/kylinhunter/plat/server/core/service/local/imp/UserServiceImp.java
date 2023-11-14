/*
 * Copyright (C) 2023 The k-commons Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kylinhunter.plat.server.core.service.local.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserVO;
import io.github.kylinhunter.plat.server.core.dao.mapper.UserMapper;
import io.github.kylinhunter.plat.server.core.service.local.UserService;
import io.github.kylinhunter.plat.server.core.service.local.interceptor.UserDeleteInterceptor;
import io.github.kylinhunter.plat.server.core.service.local.interceptor.UserSaveOrUpdateInterceptor;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * UserServiceImp 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2022-01-01
 */
@Service
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class UserServiceImp
    extends CommonServiceImpl<
    UserMapper, User, UserReqCreate, UserReqUpdate, UserResp, UserVO, UserReqQuery>
    implements UserService {

  public UserServiceImp(
      UserSaveOrUpdateInterceptor userSaveOrUpdateInterceptor,
      UserDeleteInterceptor userDeleteInterceptor) {
    this.saveOrUpdateInterceptor = userSaveOrUpdateInterceptor;
    this.deleteInterceptor = userDeleteInterceptor;
  }

  @Override
  public User findByUserName(String userName) {
    LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
    queryWrapper.eq(User::getSysDeleteFlag, false);
    queryWrapper.eq(User::getUserName, userName);
    return this.baseMapper.selectOne(queryWrapper);
  }
}

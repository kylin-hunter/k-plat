package com.kylinhunter.plat.core.service.local.imp;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kylinhunter.plat.api.auth.ReqLogin;
import com.kylinhunter.plat.api.auth.Token;
import com.kylinhunter.plat.api.module.core.bean.entity.User;
import com.kylinhunter.plat.commons.codec.PasswordUtil;
import com.kylinhunter.plat.core.dao.mapper.UserMapper;
import com.kylinhunter.plat.core.service.local.AuthService;
import com.kylinhunter.plat.web.auth.JWTService;
import com.kylinhunter.plat.web.exception.AuthException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-10 02:45
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImp implements AuthService {
    private final UserMapper userMapper;
    private final JWTService jwtService;

    @Override
    public String login(ReqLogin reqLogin) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getUserCode, reqLogin.getUserCode());

        User user = this.userMapper.selectOne(queryWrapper);
        if (user != null) {

            if (PasswordUtil.matches(reqLogin.getPassword(), user.getPassword())) {
                log.info("login user success {}={}", user.getUserCode(), user.getUserName());
                Token token = new Token();
                token.setUserId(user.getId());
                token.setType(user.getType());
                token.setUserCode(reqLogin.getUserCode());
                token.setUserName(user.getUserName());
                token.setAdmin(user.getType() == 1);
                return jwtService.create(token);

            } else {
                log.error("password error user={}", reqLogin.getUserCode());

            }
        } else {
            log.error("no user={}", reqLogin.getUserCode());
        }

        throw new AuthException("login error :" + reqLogin.getUserCode());
    }

    @Override
    public Token verify(String token) {
        return jwtService.verify(token);
    }

    @Override
    public String createTenantToken(String loginToken, String tenantId) {
        Token token = this.verify(loginToken);
        if (token.isAdmin()) {
            token.setTenantId(tenantId);
            return jwtService.create(token);
        } else {
            throw new AuthException("校验用户和租户关系失败" + token.getUserCode() + ":" + tenantId);
        }
    }

}

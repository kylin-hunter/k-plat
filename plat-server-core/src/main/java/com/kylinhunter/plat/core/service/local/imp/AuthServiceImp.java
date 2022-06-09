package com.kylinhunter.plat.core.service.local.imp;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kylinhunter.plat.api.module.core.bean.entity.User;
import com.kylinhunter.plat.commons.codec.PasswordUtil;
import com.kylinhunter.plat.core.dao.mapper.UserMapper;
import com.kylinhunter.plat.core.service.local.AuthService;
import com.kylinhunter.plat.web.auth.JWTService;
import com.kylinhunter.plat.web.auth.LoginForm;
import com.kylinhunter.plat.web.auth.Token;
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
    public String login(LoginForm loginForm) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getUserCode, loginForm.getUserCode());

        User user = this.userMapper.selectOne(queryWrapper);
        if (user != null) {

            if (PasswordUtil.matches(loginForm.getPassword(), user.getPassword())) {
                log.info("login user success {}={}", user.getUserCode(), user.getUserName());
                Token token = new Token();
                token.setType(user.getType());
                token.setUserCode(loginForm.getUserCode());
                token.setUserName(user.getUserName());
                return jwtService.create(token);

            } else {
                log.error("password error user={}", loginForm.getUserCode());

            }
        } else {
            log.error("no user={}", loginForm.getUserCode());
        }

        throw new AuthException("login error :" + loginForm.getUserCode());
    }

    @Override
    public Token verify(String token) {
        return jwtService.verify(token);
    }
}

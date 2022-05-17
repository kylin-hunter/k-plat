package com.kylinhunter.plat.web.auth;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kylinhunter.plat.web.exception.AuthException;
import com.kylinhunter.plat.web.exception.WebErrInfos;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-28 01:47
 **/
@Component
public class JWTService {

    private static final String USER_ID = "userId";
    private static final String USER_NAME = "userName";
    private static final String SUPER_USER = "superuser";
    private static final String ACCOUNT_TYPE = "accountType";
    private static final int EXPIRE_TIME = 60 * 60 * 24;
    private static final String SECRET = "cskb";

    public String create(TokenInfo tokenInfo) {
        try {

            Calendar expireTime = Calendar.getInstance();
            expireTime.add(Calendar.SECOND, EXPIRE_TIME);
            Date maxExpireTime = expireTime.getTime();
            Date requireExpireTime = tokenInfo.getExpireDate();
            if (requireExpireTime == null) {
                tokenInfo.setExpireDate(maxExpireTime);
            } else {
                if (requireExpireTime.after(maxExpireTime)) {
                    tokenInfo.setExpireDate(maxExpireTime);
                }
            }
            String token = JWT.create()
                    //                .withHeader(map) // 添加头部
                    .withClaim(USER_ID, tokenInfo.getUserId()) // 添加payload
                    .withClaim(USER_NAME, tokenInfo.getUserName())
                    .withClaim(SUPER_USER, tokenInfo.isSuperuser())
                    .withClaim(ACCOUNT_TYPE, tokenInfo.getAccountType())
                    .withExpiresAt(tokenInfo.getExpireDate()) // 设置过期时间
                    .sign(Algorithm.HMAC256(SECRET)); // 设置签名 密钥
            return token;
        } catch (AuthException e) {
            throw e;
        } catch (Exception e) {
            throw new AuthException(WebErrInfos.AUTH_TOKEN_CREATE_ERROR, e);

        }
    }

    public TokenInfo verify(String token) {
        try {
            if (StringUtils.isBlank(token)) {
                throw new AuthException(WebErrInfos.AUTH_TOKEN_VERIFY_NOT_FOUND);
            }
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            String userId = decodedJWT.getClaim(USER_ID).asString();
            String userName = decodedJWT.getClaim(USER_NAME).asString();
            boolean superUser = decodedJWT.getClaim(SUPER_USER).asBoolean();
            String accountType = decodedJWT.getClaim(ACCOUNT_TYPE).asString();
            Date date = decodedJWT.getExpiresAt();
            return new TokenInfo(userId, userName, superUser, accountType, date);
        } catch (AuthException e) {
            throw e;
        } catch (TokenExpiredException e) {
            throw new AuthException(WebErrInfos.AUTH_TOKEN_VERIFY_EXPIRED, e);
        } catch (JWTVerificationException e) {
            throw new AuthException(WebErrInfos.AUTH_TOKEN_VERIFY_INVALID, e);
        } catch (Exception e) {
            throw new AuthException(WebErrInfos.AUTH_TOKEN_VERIFY_ERROR, e);
        }
    }
}

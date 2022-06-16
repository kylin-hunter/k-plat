package com.kylinhunter.plat.web.auth;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kylinhunter.plat.api.auth.Token;
import com.kylinhunter.plat.commons.util.date.DateUtils;
import com.kylinhunter.plat.web.exception.AuthException;
import com.kylinhunter.plat.web.exception.WebErrInfoCustomizer;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 01:47
 **/
@Component
public class JWTService {

    private static final String USER_ID = "userId";
    private static final String TENANT_ID = "tenantId";
    private static final String USER_CODE = "userCode";
    private static final String USER_NAME = "userName";
    private static final String USER_TYPE = "userType";
    private static final String SECRET = "cskb";

    public String create(Token tokenInfo) {
        try {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime maxExpireTime = now.plus(1, ChronoUnit.DAYS);
            LocalDateTime requireExpireTime = tokenInfo.getExpireDate();
            if (requireExpireTime == null) {
                tokenInfo.setExpireDate(maxExpireTime);
            } else {
                if (requireExpireTime.isAfter(maxExpireTime)) {
                    tokenInfo.setExpireDate(maxExpireTime);
                }
            }
            return JWT.create()
                    //                .withHeader(map) // 添加头部
                    .withClaim(TENANT_ID, tokenInfo.getTenantId()) // 添加payload
                    .withClaim(USER_ID, tokenInfo.getUserId()) // 添加payload
                    .withClaim(USER_CODE, tokenInfo.getUserCode()) // 添加payload
                    .withClaim(USER_NAME, tokenInfo.getUserName())
                    .withClaim(USER_TYPE, tokenInfo.getUserType())
                    .withExpiresAt(DateUtils.toDate(tokenInfo.getExpireDate())) // 设置过期时间
                    .sign(Algorithm.HMAC256(SECRET));
        } catch (AuthException e) {
            throw e;
        } catch (Exception e) {
            throw new AuthException(WebErrInfoCustomizer.AUTH_TOKEN_CREATE_ERROR, e);

        }
    }

    public Token verify(String token) {
        try {
            if (StringUtils.isBlank(token)) {
                throw new AuthException(WebErrInfoCustomizer.AUTH_TOKEN_VERIFY_NOT_FOUND);
            }
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            String tenantId = decodedJWT.getClaim(TENANT_ID).asString();
            String userId = decodedJWT.getClaim(USER_ID).asString();
            String userCode = decodedJWT.getClaim(USER_CODE).asString();
            String userName = decodedJWT.getClaim(USER_NAME).asString();
            int userType = decodedJWT.getClaim(USER_TYPE).asInt();
            Date date = decodedJWT.getExpiresAt();
            return new Token(tenantId, userId, userCode, userName, userType, DateUtils.toLocalDateTime(date));
        } catch (AuthException e) {
            throw e;
        } catch (TokenExpiredException e) {
            throw new AuthException(WebErrInfoCustomizer.AUTH_TOKEN_VERIFY_EXPIRED, e);
        } catch (JWTVerificationException e) {
            throw new AuthException(WebErrInfoCustomizer.AUTH_TOKEN_VERIFY_INVALID, e);
        } catch (Exception e) {
            throw new AuthException(WebErrInfoCustomizer.AUTH_TOKEN_VERIFY_ERROR, e);
        }
    }
}

package com.kylinhunter.plat.web.request;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kylinhunter.plat.web.trace.CookieInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @description
 * @author BiJi'an
 * @date   2021/8/2
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class RequestContexService {

    private final HttpServletRequest request;

    /**
     * @return java.lang.String
     * @throws
     * @title getTraceId
     * @description
     * @author BiJi'an
     * @date 2021/8/1 3:46 上午
     */
    public String getTraceId() {
        return getHeader(RequestContext.HEADER_TRACE_ID);
    }

    /**
     * @return java.lang.String
     * @throws
     * @title getTraceId
     * @description
     * @author BiJi'an
     * @date 2021/8/1 3:46 上午
     */
    public String getAgentId() {
        String agentId = getHeader(RequestContext.HEADER_AGENT_ID);
        if (StringUtils.isEmpty(agentId)) {
            agentId = getParameter(RequestContext.PARAM_AGENT_ID);
        }
        return agentId;
    }

    /**
     * @return java.lang.String
     * @throws
     * @title getTenantId
     * @description
     * @author BiJi'an
     * @date 2021/8/1 3:46 上午
     */
    public String getToken() {
        // 获取请求头信息authorization信息
        final String authHeader = request.getHeader(RequestContext.AUTH_HEADER_KEY);
        log.info("## authHeader= {}", authHeader);
        if (!StringUtils.isBlank(authHeader) && authHeader.startsWith(RequestContext.TOKEN_PREFIX)) {
            String token = authHeader.substring(7);
            if (!StringUtils.isBlank(token)) {
                return token;
            }
        }
        return StringUtils.defaultString(request.getParameter(RequestContext.PARAM_TOKEN));

    }

    /**
     * 获取简单认证，不做任何处理
     * @return
     */
    public String getSimpleToken(){
        final String authHeader = request.getHeader(RequestContext.AUTH_HEADER_KEY);
        log.info("## authHeader= {}", authHeader);
        return StringUtils.defaultString(authHeader);
    }

    /**
     * @return boolean
     * @throws
     * @title isExplain
     * @description
     * @author BiJi'an
     * @date 2021/8/1 3:46 上午
     */
    public boolean isDebugMode() {
        return BooleanUtils.toBoolean(this.getParameter(RequestContext.PARAM_DEBUG));
    }

    /**
     * @param request
     * @param xForwardedFor
     * @param xRealIp
     * @return java.lang.String
     * @throws
     * @title getIP
     * @description
     * @author BiJi'an
     * @date 2021/8/1 3:46 上午
     */
    public String getIP(HttpServletRequest request, boolean xForwardedFor, boolean xRealIp) {
        if (xForwardedFor) {
            String forwardedIp = request.getHeader("X-Forwarded-For");
            if (!StringUtils.isEmpty(forwardedIp) && !"unknown".equalsIgnoreCase(forwardedIp)) {
                int index = forwardedIp.indexOf(",");
                if (index != -1) {
                    return forwardedIp.substring(0, index);
                } else {
                    return forwardedIp;
                }
            }
        }
        if (xRealIp) {
            String realIp = request.getHeader("X-Real-IP");
            if (!StringUtils.isEmpty(realIp)) {
                return realIp;
            }
        }

        return request.getRemoteAddr();
    }

    /**
     * @param name
     * @return java.lang.String
     * @throws
     * @title getHeader
     * @description
     * @author BiJi'an
     * @date 2021/8/1 3:46 上午
     */
    private String getHeader(String name) {
        return StringUtils.defaultString(request.getHeader(name));
    }

    /**
     * @param name
     * @return java.lang.String
     * @throws
     * @title getParameter
     * @description
     * @author BiJi'an
     * @date 2021/8/1 3:46 上午
     */
    private String getParameter(String name) {
        return StringUtils.defaultString(request.getParameter(name));
    }

    public List<CookieInfo> getCookieInfos() {
        List<CookieInfo> cookieInfos = Lists.newArrayList();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieInfos.add(new CookieInfo(cookie.getName(), cookie.getPath(), cookie.getValue()));
            }
        }
        return cookieInfos;
    }

    public Map<String, List<String>> getHeaders() {
        Map<String, List<String>> headers = Maps.newHashMap();
        final Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();

                Enumeration<String> values = request.getHeaders(name);
                if (values != null) {
                    while (values.hasMoreElements()) {
                        String value = values.nextElement();
                        headers.compute(name, (k, v) -> {
                            if (v == null) {
                                v = Lists.newArrayList();
                            }
                            v.add(value);
                            return v;
                        });
                    }
                }

            }
        }
        return headers;
    }

}

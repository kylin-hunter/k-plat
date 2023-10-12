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
package io.github.kylinhunter.plat.web.request;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.github.kylinhunter.plat.api.web.request.RequestConst;
import io.github.kylinhunter.plat.api.trace.CookieInfo;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author BiJi'an
 * @description
 * @date 2021/8/2
 */
@Slf4j
public class RequestUtils {

  /**
   * @return javax.servlet.http.HttpServletRequest
   * @title getRequest
   * @description getRequest
   * @author BiJi'an
   * @date 2023-10-13 01:22
   */

  public static HttpServletRequest getRequest() {
    ServletRequestAttributes requestAttributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    if (requestAttributes != null) {
      return requestAttributes.getRequest();
    }
    return null;
  }


  /**
   * @return boolean
   * @title isExplain
   * @description
   * @author BiJi'an
   * @date 2021/8/1 3:46 上午
   */
  public static boolean isDebugMode(HttpServletRequest request) {
    return BooleanUtils.toBoolean(getHeader(request, RequestConst.HEADER_DEBUG));
  }

  /**
   * @param request request
   * @param xForwardedFor xForwardedFor
   * @param xRealIp xRealIp
   * @return java.lang.String
   * @title getIP
   * @description
   * @author BiJi'an
   * @date 2021/8/1 3:46 上午
   */
  public static String getIP(HttpServletRequest request, boolean xForwardedFor, boolean xRealIp) {
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
   * @param name name
   * @return java.lang.String
   * @title getHeader
   * @description
   * @author BiJi'an
   * @date 2021/8/1 3:46 上午
   */
  private static String getHeader(HttpServletRequest request, String name) {
    return StringUtils.defaultString(request.getHeader(name));
  }

  /**
   * @param name name
   * @return java.lang.String
   * @title getParameter
   * @description
   * @author BiJi'an
   * @date 2021/8/1 3:46 上午
   */
  private static String getParameter(HttpServletRequest request, String name) {
    return StringUtils.defaultString(request.getParameter(name));
  }

  /**
   * @param request request
   * @return java.util.List<io.github.kylinhunter.plat.api.trace.CookieInfo>
   * @title getCookieInfos
   * @description getCookieInfos
   * @author BiJi'an
   * @date 2023-10-13 01:23
   */
  public static List<CookieInfo> getCookies(HttpServletRequest request) {
    List<CookieInfo> cookieInfos = Lists.newArrayList();
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        cookieInfos.add(new CookieInfo(cookie.getName(), cookie.getPath(), cookie.getValue()));
      }
    }
    return cookieInfos;
  }

  /**
   * @param request request
   * @return java.util.Map<java.lang.String, java.util.List < java.lang.String>>
   * @title getHeaders
   * @description getHeaders
   * @author BiJi'an
   * @date 2023-10-13 01:23
   */
  public static Map<String, List<String>> getHeaders(HttpServletRequest request) {
    Map<String, List<String>> headers = Maps.newHashMap();
    final Enumeration<String> headerNames = request.getHeaderNames();
    if (headerNames != null) {
      while (headerNames.hasMoreElements()) {
        String name = headerNames.nextElement();

        Enumeration<String> values = request.getHeaders(name);
        if (values != null) {
          while (values.hasMoreElements()) {
            String value = values.nextElement();
            headers.compute(
                name,
                (k, v) -> {
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

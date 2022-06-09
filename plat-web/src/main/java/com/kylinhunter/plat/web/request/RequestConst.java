package com.kylinhunter.plat.web.request;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-02 17:50
 **/
public class RequestConst {

    public static final String HEADER_TRACE_ID = "X-Trace-ID";   /*上游传来的traceId*/
    public static final String HEADER_TENANT_ID = "X-Tenant-ID";   /*上游传来的agentId*/
    public static final String AUTH_HEADER_KEY = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String PARAM_DEBUG = "debug"; /* 上游传来的 explain*/
    public static final String PARAM_TENANT_ID = "tenantId"; /* 上游传来的 explain*/
    public static final String PARAM_TOKEN = "token"; /* 上游传来的 token*/

}

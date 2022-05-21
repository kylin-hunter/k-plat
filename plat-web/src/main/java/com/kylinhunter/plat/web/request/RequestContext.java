package com.kylinhunter.plat.web.request;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-02 17:50
 **/
public class RequestContext {
//    public static final String HEADER_CURRENT_USERID = "x-current-user"; /*上游传来的 userId*/
    /*    public static final String HEADER_CURRENT_USERNAME = "x-current-user-name1"; 上游传来的 userName*/
    /*    public static final String HEADER_ACCOUNT_TYPE = "x-account-reqType";  上游传来的 账户类型*/
    /*    public static final String HEADER_AGENT_ID = "x-biz-id";  上游传来的 agent_id*/
    //    public static final String HEADER_AGENT_TOKEN = "x-agent-token"; /* 上游传来的 agent_id*/

    public static final String HEADER_TRACE_ID = "X-Trace-ID";   /*上游传来的traceId*/
    public static final String HEADER_AGENT_ID = "X-AGENT-ID";   /*上游传来的agentId*/
    public static final String AUTH_HEADER_KEY = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String PARAM_EXPLAIN = "explain"; /* 上游传来的 explain*/
    public static final String PARAM_DEBUG = "debug"; /* 上游传来的 explain*/
    public static final String PARAM_AGENT_ID = "tenantId"; /* 上游传来的 explain*/
    public static final String PARAM_TOKEN= "token"; /* 上游传来的 token*/

}

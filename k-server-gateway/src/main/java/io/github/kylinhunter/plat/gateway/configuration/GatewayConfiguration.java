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
package io.github.kylinhunter.plat.gateway.configuration;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

/**
 * @author BiJi'an
 * @description
 * @date 2023-09-15 00:49
 */
@Configuration
public class GatewayConfiguration {

  @PostConstruct
  public void init() {
    initCustomizedApis();
    initGatewayRules();
  }

  private void initCustomizedApis() {
    Set<ApiDefinition> definitions = new HashSet<>();
    ApiDefinition api1 =
        new ApiDefinition("k-core")
            .setPredicateItems(
                new HashSet<ApiPredicateItem>() {
                  {
                    add(
                        new ApiPathPredicateItem()
                            .setPattern("/k-plat/core/**")
                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
                  }
                });
    ApiDefinition api2 =
        new ApiDefinition("k-core-proxy")
            .setPredicateItems(
                new HashSet<ApiPredicateItem>() {
                  {
                    add(
                        new ApiPathPredicateItem()
                            .setPattern("/kplat/core-proxy/**")
                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
                  }
                });
    definitions.add(api1);
    definitions.add(api2);
    GatewayApiDefinitionManager.loadApiDefinitions(definitions);
  }

  private void initGatewayRules() {
    Set<GatewayFlowRule> rules = new HashSet<>();
    rules.add(new GatewayFlowRule("url-proxy-1").setCount(1).setIntervalSec(3));

    rules.add(new GatewayFlowRule("url-proxy-2").setCount(1).setIntervalSec(3));
    //    rules.add(new GatewayFlowRule("aliyun_route")
    //        .setCount(2)
    //        .setIntervalSec(2)
    //        .setBurst(2)
    //        .setParamItem(new GatewayParamFlowItem()
    //            .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_CLIENT_IP)
    //        )
    //    );
    //    rules.add(new GatewayFlowRule("httpbin_route")
    //        .setCount(10)
    //        .setIntervalSec(1)
    //        .setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER)
    //        .setMaxQueueingTimeoutMs(600)
    //        .setParamItem(new GatewayParamFlowItem()
    //            .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_HEADER)
    //            .setFieldName("X-Sentinel-Flag")
    //        )
    //    );
    //    rules.add(new GatewayFlowRule("httpbin_route")
    //        .setCount(1)
    //        .setIntervalSec(1)
    //        .setParamItem(new GatewayParamFlowItem()
    //            .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
    //            .setFieldName("pa")
    //        )
    //    );
    //    rules.add(new GatewayFlowRule("httpbin_route")
    //        .setCount(2)
    //        .setIntervalSec(30)
    //        .setParamItem(new GatewayParamFlowItem()
    //            .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
    //            .setFieldName("type")
    //            .setPattern("warn")
    //            .setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_CONTAINS)
    //        )
    //    );
    //
    //    rules.add(new GatewayFlowRule("some_customized_api")
    //        .setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME)
    //        .setCount(5)
    //        .setIntervalSec(1)
    //        .setParamItem(new GatewayParamFlowItem()
    //            .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
    //            .setFieldName("pn")
    //        )
    //    );
    GatewayRuleManager.loadRules(rules);
  }
}

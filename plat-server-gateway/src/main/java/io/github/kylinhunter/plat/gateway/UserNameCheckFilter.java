package io.github.kylinhunter.plat.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @author BiJi'an
 * @description
 * @date 2022-11-30 01:37
 **/

@Component
@Slf4j
public class UserNameCheckFilter implements Ordered, GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("AFilter前置逻辑");
        return chain.filter(exchange).then(Mono.fromRunnable(() ->
        {
            log.info("AFilter后置逻辑");
        }));
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 100;
    }
}
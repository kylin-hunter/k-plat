package io.github.kylinhunter.plat.gateway;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author BiJi'an
 * @description
 * @date 2022-11-30 01:59
 **/
@Component
public class ExtCheckRoutePredicateFactory extends AbstractRoutePredicateFactory<ExtCheckRoutePredicateFactory.Config> {
    public ExtCheckRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                ServerHttpRequest request = serverWebExchange.getRequest();
                MultiValueMap<String, String> queryParams = request.getQueryParams();

                String jifang = queryParams.getFirst("jifang");
                if (config.name.equals(jifang)) {
                    return true;
                }
                return false;
            }
        };
    }

    @Override
    public String name() {
        return "ExtCheck";
    }

    /**
     * 将签名 key 与 静态类Config中的属性进行绑定
     * 数组里面下标对应配置文件中配置
     *
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("name");
    }
    public static class Config {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
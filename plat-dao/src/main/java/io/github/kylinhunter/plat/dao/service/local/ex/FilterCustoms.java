package io.github.kylinhunter.plat.dao.service.local.ex;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-22 19:41
 **/
@Component
public class FilterCustoms {
    public static final Map<String, FilterCustom> DATA = Maps.newHashMap();

    @PostConstruct
    private void init() {

    }

    public FilterCustom getFilterCustom(String field) {
        return DATA.get(field);
    }

}

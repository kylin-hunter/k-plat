package com.kylinhunter.plat.dao.service.local;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.kylinhunter.plat.commons.util.name.NamePair;
import com.kylinhunter.plat.api.bean.filter.ReqFilters;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryPage;
import com.kylinhunter.plat.commons.exception.inner.ParamException;
import com.kylinhunter.plat.commons.util.name.NamePairUtils;
import com.kylinhunter.plat.dao.service.local.ex.FilterCustom;
import com.kylinhunter.plat.dao.service.local.ex.FilterCustoms;

import lombok.RequiredArgsConstructor;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-11 21:27
 **/
@Component
@RequiredArgsConstructor
public class FilterComponent {
    private static final Map<Class<?>, Set<String>> VALID_FIELDS = Maps.newHashMap();
    private static final ReqFilters EMPPTY_FILTERS = new ReqFilters();
    private final FilterCustoms filterCustoms;

    /**
     * @param cls
     * @param field
     * @return void
     * @throws
     * @title 添加合法的字段
     * @description
     * @author BiJi'an
     * @updateTime 2021/11/11 8:48 下午
     */
    public void addValidField(Class<?> cls, String field) {
        VALID_FIELDS.compute(cls, (k, v) -> {
            if (v == null) {
                v = Sets.newHashSet();
            }
            NamePair namePair = NamePairUtils.toNamePair(field);
            v.add(namePair.getUnderline());
            v.add(namePair.getCamel());
            return v;
        });

    }

    /**
     * @param reqQueryPage
     * @param filters
     * @return void
     * @throws
     * @title 校验排序字段
     * @description
     * @author BiJi'an
     * @updateTime 2021/11/11 8:57 下午
     */
    public static void checkValid(ReqQueryPage reqQueryPage, ReqFilters filters) {
        if (filters.size() > 0 && !filters.isInit()) {

            Set<String> fields = VALID_FIELDS.get(reqQueryPage.getClass());
            if (fields != null) {
                filters.forEach(filter -> {
                    if ( fields != null && !fields.contains(filter.getField())) {
                        throw new ParamException("invalid filter:" + filter.getField());
                    }
                });

            }
            filters.setInit(true);
        }
    }

    /**
     * @param reqQueryPage
     * @return java.util.List<com.kylinhunter.plat.api.bean.sort.ReqSort>
     * @throws
     * @title 解析filter
     * @description
     * @author BiJi'an
     * @updateTime 2021/11/11 3:45 下午
     */
    public ReqFilters getFilters(ReqQueryPage reqQueryPage) {
        ReqFilters filters = new ReqFilters(reqQueryPage.getFilters());
        if (filters.size() > 0) {
            checkValid(reqQueryPage, filters);
            return filters;
        }
        return EMPPTY_FILTERS;
    }

    /**
     * @param wrapper
     * @param reqQueryPage
     * @return void
     * @throws
     * @title 写filter到wrapper
     * @description
     */
    public <T> void writeToWrapper(QueryWrapper<T> wrapper, ReqQueryPage reqQueryPage) {
        ReqFilters filters = new ReqFilters(reqQueryPage.getFilters()) ;
        if (filters.size() > 0) {
            filters.forEach(filter -> {
                FilterCustom filterCustom = filterCustoms.getFilterCustom(filter.getField());
                if (filterCustom != null) { // 特殊的 filter 处理，例如 文档和faq的状态筛选 ，自定义filter还是尽量放到自己的微服务中
                    filterCustom.writeToWrapper(wrapper, filter);
                } else {
                    List<String> values = Arrays.asList(filter.getValue().split(","));
                    wrapper.in(filter.getColumn(), values);
                }

            });
        }
    }

}

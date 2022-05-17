package com.kylinhunter.plat.dao.service.local;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.kylinhunter.plat.api.bean.sort.ReqSorts;
import com.kylinhunter.plat.api.bean.entity.constants.SysCols;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryPage;
import com.kylinhunter.plat.api.bean.sort.Order;
import com.kylinhunter.plat.commons.exception.inner.ParamException;
import com.kylinhunter.plat.commons.util.name.NamePair;
import com.kylinhunter.plat.commons.util.name.NamePairUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-11 21:27
 **/
@Component
public class SortComponent {
    private static final Map<Class<?>, Set<String>> VALID_FIELDS = Maps.newHashMap();

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
     * @param sorts
     * @param clazz
     * @return void
     * @throws
     * @title 校验排序字段的合法性
     * @description
     * @author BiJi'an
     * @updateTime 2022/1/21 4:21 下午
     */
    private void check(ReqSorts sorts, Class<?> clazz) {
        Set<String> fields = VALID_FIELDS.get(clazz);
        sorts.forEach(sort -> {

            if (fields != null && !fields.contains(sort.getField())) {
                throw new ParamException("invalid sort:" + sort.getField());
            }
        });
    }

    public ReqSorts getSorts(ReqQueryPage reqQueryPage) {
        return this.getSorts(reqQueryPage, true);
    }

    /**
     * @param reqQueryPage
     * @return java.util.List<com.kylinhunter.plat.api.bean.sort.ReqSort>
     * @throws
     * @title 解析sort
     * @description
     * @author BiJi'an
     * @updateTime 2021/11/11 3:45 下午
     */
    public ReqSorts getSorts(ReqQueryPage reqQueryPage, boolean needCheck) {
        ReqSorts sorts = new ReqSorts(reqQueryPage.getSorts());
        if (!sorts.isInit()) {
            init(sorts, reqQueryPage, needCheck);
        }
        return sorts;
    }



    private void init(ReqSorts sorts, ReqQueryPage reqQueryPage, boolean needCheck) {


        sorts.forEach(sort -> {
            if (sort.getNamePair() == null) {
                sort.setNamePair(NamePairUtils.toNamePair(sort.getField()));
            }
        });
        if (needCheck) {
            check(sorts, reqQueryPage.getClass());
        }
        sorts.setInit(true);
    }

    /**
     * @param wrapper
     * @param reqQueryPage
     * @return void
     * @throws
     * @title 写sort到wrapper
     * @description
     * @author BiJi'an
     * @updateTime 2021/11/11 3:45 下午
     */
    public <T> void writeToWrapper(QueryWrapper<T> wrapper, ReqQueryPage reqQueryPage) {
        ReqSorts sorts = getSorts(reqQueryPage);
        if (sorts.size() > 0) {
            sorts.forEach(sort -> {
                if (Order.ASC.equalTo(sort.getOrder())) {
                    wrapper.orderByAsc(sort.getColumn());
                } else {
                    wrapper.orderByDesc(sort.getColumn());
                }
            });
        } else {
            wrapper.orderByDesc(SysCols.SYS_UPDATE_TIME);
        }
    }
}

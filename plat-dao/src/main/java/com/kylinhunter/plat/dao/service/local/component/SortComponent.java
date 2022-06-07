package com.kylinhunter.plat.dao.service.local.component;

import java.util.List;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kylinhunter.plat.api.bean.entity.constants.SysCols;
import com.kylinhunter.plat.api.bean.sort.Order;
import com.kylinhunter.plat.api.bean.sort.ReqSort;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryPage;
import com.kylinhunter.plat.commons.util.name.NamePair;
import com.kylinhunter.plat.commons.util.name.NamePairUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-11 21:27
 **/
@Component
public class SortComponent {

    /**
     * @param wrapper      wrapper
     * @param reqQueryPage reqQueryPage
     * @return void
     * @title 写sort到wrapper
     * @description
     * @author BiJi'an
     * @date 2021/11/11 3:45 下午
     */
    public <T> void sort(QueryWrapper<T> wrapper, ReqQueryPage reqQueryPage) {
        List<ReqSort> sorts = reqQueryPage.getSorts();
        if (sorts != null && sorts.size() > 0) {
            sorts.forEach(sort -> {
                NamePair namePair = NamePairUtils.toNamePair(sort.getField());
                String column = namePair.getSnake();
                if (Order.ASC.equalTo(sort.getOrder())) {
                    wrapper.orderByAsc(column);
                } else {
                    wrapper.orderByDesc(column);
                }
            });
        } else {
            wrapper.orderByDesc(SysCols.SYS_UPDATE_TIME);
        }
    }
}

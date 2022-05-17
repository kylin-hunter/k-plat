package com.kylinhunter.plat.dao.service.local.ex;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kylinhunter.plat.api.bean.filter.ReqFilter;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-22 19:41
 **/
public interface FilterCustom {

    <T> void writeToWrapper(QueryWrapper<T> wrapper, ReqFilter filter);
}

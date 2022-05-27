package com.kylinhunter.plat.dao.service.helper;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.vo.request.Req;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryPage;
import com.kylinhunter.plat.api.page.PageData;
import com.kylinhunter.plat.commons.exception.inner.biz.ex.DBException;
import com.kylinhunter.plat.commons.bean.BeanCopyUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-11 21:27
 **/
public class ServiceDataHelper {
    /**
     * @param reqQueryPage
     * @return com.kylinhunter.plat.api.page.PageData<Z>
     * @throws
     * @title 构造空的返回数据
     * @description
     * @author BiJi'an
     * @date 2021/11/10 11:13 上午
     */
    public static <Z, T> PageData<Z> emptyPageData(ReqQueryPage reqQueryPage) {
        PageData<Z> pageData = new PageData<>();
        if (reqQueryPage != null) {
            pageData.setPn(reqQueryPage.getPn());
            pageData.setPs(reqQueryPage.getPs());
        }
        return pageData;

    }

    public static <Z, T> PageData<Z> assemblePageData(IPage<T> page, Class<Z> beanClass) {
        try {
            PageData<Z> pageData = new PageData<>();
            if (page != null) {
                pageData.setPn(page.getCurrent());
                pageData.setPs(page.getSize());
                pageData.setPages(page.getPages());
                pageData.setTotal(page.getTotal());
                for (T r : page.getRecords()) {
                    if (beanClass != null) {
                        Z resp = beanClass.newInstance();
                        BeanCopyUtils.copyProperties(r, resp);
                        pageData.getBody().add(resp);

                    } else {
                        pageData.getBody().add((Z) r);
                    }

                }
            }
            return pageData;
        } catch (Exception e) {
            throw new DBException(" assemblePageData error", e);

        }

    }

    public static <Z, T> List<Z> assembleData(List<T> dbDatas, Class<Z> beanClass) {
        try {
            if (dbDatas != null) {
                List<Z> list = Lists.newArrayList();
                for (T dbData : dbDatas) {
                    if (beanClass != null) {
                        Z resp = beanClass.newInstance();
                        BeanCopyUtils.copyProperties(dbData, resp);
                        list.add(resp);
                    } else {
                        list.add((Z) dbData);
                    }
                }
                return list;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw new DBException(" assemblePageData error", e);

        }

    }

    /**
     * @param baseEntity
     * @param reqUpdate
     * @return void
     * @throws
     * @title 根据ReqUpdate 统一更新 Bean
     * @description
     * @author BiJi'an
     * @date 2021/10/25 7:45 下午
     */
    public static void setUpdateMsg(BaseEntity baseEntity, Req reqUpdate) {

        if (reqUpdate.getUserContext() != null && !StringUtils
                .isEmpty(reqUpdate.getUserContext().getCurrentUserId())) { // 防止
            baseEntity.setSysUpdateUserId(reqUpdate.getUserContext().getCurrentUserId());
            baseEntity.setSysUpdateUserName(reqUpdate.getUserContext().getCurrentUserName());
        }
        baseEntity.setSysUpdateTime(LocalDateTime.now());
    }

    /**
     * @param baseEntity
     * @param reqCreate
     * @return void
     * @throws
     * @title 根据ReqUpdate 统一更新 Bean
     * @description
     * @author BiJi'an
     * @date 2021/10/25 7:45 下午
     */
    public static void setCreateMsg(BaseEntity baseEntity, Req reqCreate) {
        if (reqCreate.getUserContext() != null) {

            baseEntity.setSysCreatedUserId(reqCreate.getUserContext().getCurrentUserId());
            baseEntity.setSysCreatedUserName(reqCreate.getUserContext().getCurrentUserName());
            baseEntity.setSysUpdateUserId(reqCreate.getUserContext().getCurrentUserId());
            baseEntity.setSysUpdateUserName(reqCreate.getUserContext().getCurrentUserName());
        }

        baseEntity.setSysUpdateTime(LocalDateTime.now());
    }



}

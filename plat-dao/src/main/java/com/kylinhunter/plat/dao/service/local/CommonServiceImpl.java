package com.kylinhunter.plat.dao.service.local;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.vo.VO;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryById;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryByIds;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryPage;
import com.kylinhunter.plat.api.bean.vo.response.batch.BatchResp;
import com.kylinhunter.plat.api.bean.vo.response.single.Resp;
import com.kylinhunter.plat.api.bean.vo.update.BatchReqUpdate;
import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import com.kylinhunter.plat.api.page.PageData;
import com.kylinhunter.plat.api.service.local.CommonService;
import com.kylinhunter.plat.commons.exception.ExceptionHelper;
import com.kylinhunter.plat.commons.exception.common.KRuntimeException;
import com.kylinhunter.plat.commons.exception.explain.ExceptionExplainer;
import com.kylinhunter.plat.commons.exception.info.ErrInfos;
import com.kylinhunter.plat.commons.exception.inner.biz.ex.DBException;
import com.kylinhunter.plat.dao.service.helper.ServiceDataHelper;

import lombok.NoArgsConstructor;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author biji'an
 * @since 2022-01-01
 */
@Transactional(rollbackFor = Exception.class)
@NoArgsConstructor
public abstract class CommonServiceImpl<M extends BaseMapper<T>, T extends BaseEntity, X extends ReqCreate,
        Y extends ReqUpdate,
        Z extends Resp, Q extends ReqQueryPage> extends ServiceImpl<M, T>
        implements CommonService<T, X, Y, Z, Q> {

    @Autowired
    private ExceptionExplainer exceptionExplainer;
    private PersistInterceptor<T, X, Y, Z, ? extends VO> persistInterceptor = new PersistInterceptor<>();
    private QueryInterceptor<T, Q, Z> queryInterceptor = new QueryInterceptor<>();

    public CommonServiceImpl(PersistInterceptor<T, X, Y, Z, ? extends VO> persistInterceptor) {
        this.persistInterceptor = persistInterceptor;
    }

    public CommonServiceImpl(QueryInterceptor<T, Q, Z> queryInterceptor) {
        this.queryInterceptor = queryInterceptor;
    }

    public CommonServiceImpl(PersistInterceptor<T, X, Y, Z, ? extends VO> persistInterceptor,
                             QueryInterceptor<T, Q, Z> queryInterceptor) {
        this.persistInterceptor = persistInterceptor;
        this.queryInterceptor = queryInterceptor;
    }

    protected Z save(X reqCreate, boolean newData) {
        try {
            T t = persistInterceptor.saveBefore(reqCreate);
            if (this.save(t)) {
                if (newData) {
                    return persistInterceptor.saveAfter(reqCreate, t);
                } else {
                    return null;
                }
            } else {
                throw new DBException("save db error");
            }
        } catch (Exception e) {
            throw exceptionExplainer.convert(e);
        }
    }

    @Override
    public Z save(X reqCreate) {
        return this.save(reqCreate, true);
    }

    @Override
    public boolean save(Collection<X> reqCreates) {
        try {
            reqCreates.forEach(reqCreate -> this.save(reqCreate, false));
            return true;
        } catch (Exception e) {
            throw exceptionExplainer.convert(e);
        }
    }

    @Override
    public Z queryById(ReqQueryById reqQueryById) {
        try {
            queryInterceptor.queryByIdBefore(reqQueryById);
            LambdaQueryWrapper<T> query = Wrappers.lambdaQuery();
            query.eq(T::getId, reqQueryById.getId());
            if (!reqQueryById.isWithLogicDelData()) {
                query.eq(T::getSysDeleteFlag, 0);
            }
            T entity = this.baseMapper.selectOne(query);
            return queryInterceptor.queryByIdAfter(reqQueryById, entity);
        } catch (Exception e) {
            throw exceptionExplainer.convert(e);
        }
    }

    @Override
    public List<Z> queryByIds(ReqQueryByIds reqQueryByIds) {
        try {

            this.queryInterceptor.queryByIdsBefore(reqQueryByIds);
            LambdaQueryWrapper<T> query = Wrappers.lambdaQuery();
            query.eq(T::getId, reqQueryByIds.getIds());
            if (!reqQueryByIds.isWithLogicDelData()) {
                query.eq(T::getSysDeleteFlag, 0);
            }
            List<T> beans = this.baseMapper.selectList(query);
            return this.queryInterceptor.queryByIdsAfter(reqQueryByIds, beans);

        } catch (Exception e) {
            throw exceptionExplainer.convert(e);
        }
    }

    @Override
    public boolean delete(ReqDelete reqDelete) {
        this.persistInterceptor.deleteBefore(reqDelete);
        boolean success;
        if (reqDelete.isPhysical()) {
            success = this.removeByIds(reqDelete.getIds());
        } else {
            List<T> datas = this.baseMapper.selectBatchIds(reqDelete.getIds());
            datas.forEach(data -> {
                data.setSysDeleteFlag(true);
                ServiceDataHelper.setDefaultUpdateMsg(data, reqDelete);
                this.baseMapper.updateById(data);
            });
            success = true;
        }
        return this.persistInterceptor.deleteAfter(reqDelete, success);

    }

    @Override
    public Z update(Y reqUpdate) {
        try {
            T t = this.getById(reqUpdate.getId());
            if (t == null) {
                throw new DBException(ErrInfos.DB_NO_EXIST, "no body for id =" + reqUpdate.getId());
            }
            t = persistInterceptor.updateBefore(reqUpdate, t);
            if (this.updateById(t)) {
                return persistInterceptor.updateAfter(reqUpdate, t);
            } else {
                throw new DBException("update db error");
            }
        } catch (KRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw exceptionExplainer.convert(e);
        }
    }

    @Override
    public BatchResp<Z> updateBatch(BatchReqUpdate<Y> batchReqUpdate) {

        BatchResp<Z> batchResp = new BatchResp<>();
        List<Y> data = batchReqUpdate.getBody();
        for (Y reqUpdate : data) {
            try {
                Z resp = this.update(reqUpdate);
                batchResp.addSingleResp(resp);
            } catch (Exception e) {
                log.error("update batch error", e);
                int code = ExceptionHelper.getErrCode(e);
                String msg = ExceptionHelper.getMessage(e);
                Z z = this.queryById(new ReqQueryById(reqUpdate.getId()));
                batchResp.addSingleResp(code, msg, z);
            }

        }
        return batchResp;
    }

    @Override
    public PageData<Z> query(Q reqQueryPage) {
        try {
            QueryWrapper<T> queryWrapper = queryInterceptor.queryBefore(reqQueryPage);
            Page<T> page = Page.of(reqQueryPage.getPn(), reqQueryPage.getPs());
            Page<T> entities = this.baseMapper.selectPage(page, queryWrapper);
            return queryInterceptor.queryAfter(reqQueryPage, entities);
        } catch (Exception e) {
            throw exceptionExplainer.convert(e);
        }
    }

}

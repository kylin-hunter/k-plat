package com.kylinhunter.plat.dao.service.local;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.entity.constants.SysCols;
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
import com.kylinhunter.plat.dao.service.local.interceptor.DeleteInterceptor;
import com.kylinhunter.plat.dao.service.local.interceptor.QueryInterceptor;
import com.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;

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
        Z extends Resp, V extends VO, Q extends ReqQueryPage> extends ServiceImpl<M, T>
        implements CommonService<T, X, Y, Z, V, Q> {
    protected Class<T> entityClass = currentEntityClass();
    protected Class<Z> respClass = currentRespClass();

    @Autowired
    private ExceptionExplainer exceptionExplainer;
    @Autowired
    private SaveOrUpdateInterceptor<T, X, Y, Z, V, Q> saveOrUpdateInterceptor;
    @Autowired
    private DeleteInterceptor<T, X, Y, Z, V, Q> deleteInterceptor;
    @Autowired
    private QueryInterceptor<T, X, Y, Z, V, Q> queryInterceptor;

    @SuppressWarnings("unchecked")
    protected Class<T> currentEntityClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), CommonServiceImpl.class, 1);
    }

    @SuppressWarnings("unchecked")
    protected Class<Z> currentRespClass() {
        return (Class<Z>) ReflectionKit.getSuperClassGenericType(this.getClass(), CommonServiceImpl.class, 4);
    }

    protected T createEntity() {
        try {
            return entityClass.newInstance();
        } catch (Exception e) {
            throw new DBException("crete EntityBean error", e);
        }
    }

    protected Z createResponse() {
        try {
            return respClass.newInstance();
        } catch (Exception e) {
            throw new DBException("getEntityBean error", e);
        }
    }

    protected Z save(X reqCreate, boolean newData) {
        try {
            T t = saveOrUpdateInterceptor.before(reqCreate, createEntity());
            if (this.save(t)) {
                if (newData) {
                    return saveOrUpdateInterceptor.after(reqCreate, t, createResponse());
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
    public Z update(Y reqUpdate) {
        try {
            T t = this.getById(reqUpdate.getId());
            if (t == null) {
                throw new DBException(ErrInfos.DB_NO_EXIST, "no data for id =" + reqUpdate.getId());
            }
            t = saveOrUpdateInterceptor.before(reqUpdate, t);
            if (this.updateById(t)) {
                return saveOrUpdateInterceptor.after(reqUpdate, t, createResponse());
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
    public boolean delete(ReqDelete reqDelete) {
        if (reqDelete.isPhysical()) {
            return this.removeByIds(reqDelete.getIds());
        } else {
            List<T> datas = this.baseMapper.selectBatchIds(reqDelete.getIds());
            datas.forEach(data -> {
                this.deleteInterceptor.before(reqDelete, data);
                this.baseMapper.updateById(data);
                this.deleteInterceptor.after(reqDelete, data);
            });
            return true;
        }

    }

    @Override
    public Z queryById(ReqQueryById reqQueryById) {
        try {
            queryInterceptor.before(reqQueryById);
            QueryWrapper<T> query = Wrappers.query();
            query.eq(SysCols.ID, reqQueryById.getId());
            if (!reqQueryById.isWithLogicDelData()) {
                query.eq(SysCols.SYS_DELETE_FLAG, "0");
            }
            T entity = this.baseMapper.selectOne(query);
            return queryInterceptor.after(reqQueryById, entity, createResponse());
        } catch (Exception e) {
            throw exceptionExplainer.convert(e);
        }
    }

    @Override
    public List<Z> queryByIds(ReqQueryByIds reqQueryByIds) {
        try {

            this.queryInterceptor.before(reqQueryByIds);
            LambdaQueryWrapper<T> query = Wrappers.lambdaQuery();
            query.eq(T::getId, reqQueryByIds.getIds());
            if (!reqQueryByIds.isWithLogicDelData()) {
                query.eq(T::getSysDeleteFlag, 0);
            }
            List<T> beans = this.baseMapper.selectList(query);
            return this.queryInterceptor.after(reqQueryByIds, beans, respClass);

        } catch (Exception e) {
            throw exceptionExplainer.convert(e);
        }
    }

    @Override
    public PageData<Z> query(Q reqQueryPage) {
        try {
            QueryWrapper<T> queryWrapper = queryInterceptor.query(reqQueryPage);
            Page<T> page = Page.of(reqQueryPage.getPn(), reqQueryPage.getPs());
            Page<T> entities = this.baseMapper.selectPage(page, queryWrapper);
            return queryInterceptor.after(reqQueryPage, entities, respClass);
        } catch (Exception e) {
            throw exceptionExplainer.convert(e);
        }
    }

}

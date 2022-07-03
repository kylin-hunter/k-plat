package com.kylinhunter.plat.dao.service.local;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kylinhunter.plat.api.auth.context.UserContextHandler;
import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.entity.constants.SysCols;
import com.kylinhunter.plat.api.bean.vo.VO;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import com.kylinhunter.plat.api.bean.vo.delete.ReqDeletes;
import com.kylinhunter.plat.api.bean.vo.query.ReqById;
import com.kylinhunter.plat.api.bean.vo.query.ReqByIds;
import com.kylinhunter.plat.api.bean.vo.query.ReqPage;
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
import com.kylinhunter.plat.dao.service.local.interceptor.QueryAccurateInterceptor;
import com.kylinhunter.plat.dao.service.local.interceptor.QueryComplexInterceptor;
import com.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public abstract class CommonServiceImpl<M extends BaseMapper<T>, T extends BaseEntity, X extends ReqCreate,
        Y extends ReqUpdate,
        Z extends Resp, V extends VO, Q extends ReqPage> extends ServiceImpl<M, T>
        implements CommonService<T, X, Y, Z, V, Q> {
    protected Class<T> entityClass = currentEntityClass();
    protected Class<Z> respClass = currentRespClass();

    @Autowired
    protected ApplicationContext applicationContext;
    @Autowired
    protected ExceptionExplainer exceptionExplainer;
    @Autowired
    protected UserContextHandler userContextHandler;

    protected SaveOrUpdateInterceptor<T, X, Y, Z, V, Q> saveOrUpdateInterceptor;

    protected DeleteInterceptor<T, X, Y, Z, V, Q> deleteInterceptor;

    protected QueryComplexInterceptor<T, X, Y, Z, V, Q> queryComplexInterceptor;

    protected QueryAccurateInterceptor<T, X, Y, Z, V, Q> queryAccurateInterceptor;

    protected boolean tenantSupported = true;

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
            T t = saveOrUpdateInterceptor.before(reqCreate, this.tenantSupported, createEntity());
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
            t = saveOrUpdateInterceptor.before(reqUpdate, this.tenantSupported, t);
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
                Z z = this.queryById(new ReqById(reqUpdate.getId()));
                batchResp.addSingleResp(code, msg, z);
            }

        }
        return batchResp;
    }

    @Override
    public boolean delete(ReqDelete reqDelete) {

        T data = this.baseMapper.selectById(reqDelete.getId());
        if (data != null) {
            this.deleteInterceptor.before(reqDelete, this.tenantSupported, data);
            if (reqDelete.isPhysical()) {
                this.baseMapper.deleteById(data.getId());
            } else {
                this.baseMapper.updateById(data);
            }
            this.deleteInterceptor.after(reqDelete, data);
        }
        return data != null;

    }

    @Override
    public boolean delete(ReqDeletes reqDeletes) {

        List<T> datas = this.baseMapper.selectBatchIds(reqDeletes.getIds());
        this.deleteInterceptor.before(reqDeletes, this.tenantSupported, datas);

        datas.forEach(data -> {
            if (reqDeletes.isPhysical()) {
                this.baseMapper.deleteById(data.getId());
            } else {
                this.baseMapper.updateById(data);
            }
        });
        this.deleteInterceptor.after(reqDeletes, datas);

        return datas.size() > 0;

    }

    @Override
    public Z queryById(ReqById reqById) {
        try {
            QueryWrapper<T> query = queryAccurateInterceptor.before(reqById, this.tenantSupported);
            query.eq(SysCols.ID, reqById.getId());
            T entity = this.baseMapper.selectOne(query);
            return queryAccurateInterceptor.after(reqById, entity, createResponse());
        } catch (Exception e) {
            throw exceptionExplainer.convert(e);
        }
    }

    @Override
    public List<Z> queryByIds(ReqByIds reqByIds) {
        try {

            QueryWrapper<T> query = this.queryAccurateInterceptor.before(reqByIds, this.tenantSupported);
            query.in(SysCols.ID, reqByIds.getIds());
            List<T> beans = this.baseMapper.selectList(query);
            return this.queryAccurateInterceptor.after(reqByIds, beans, respClass);

        } catch (Exception e) {
            throw exceptionExplainer.convert(e);
        }
    }

    @Override
    public T getById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public PageData<Z> query(Q reqQueryPage) {
        try {
            QueryWrapper<T> queryWrapper = queryComplexInterceptor.before(reqQueryPage, tenantSupported);
            Page<T> page = Page.of(reqQueryPage.getPn(), reqQueryPage.getPs());
            Page<T> entities = this.baseMapper.selectPage(page, queryWrapper);
            return queryComplexInterceptor.after(reqQueryPage, entities, respClass);
        } catch (Exception e) {
            throw exceptionExplainer.convert(e);
        }
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        if (this.saveOrUpdateInterceptor == null) {
            this.saveOrUpdateInterceptor = this.applicationContext.getBean(SaveOrUpdateInterceptor.class);
        }

        if (this.deleteInterceptor == null) {
            this.deleteInterceptor = this.applicationContext.getBean(DeleteInterceptor.class);
        }

        if (this.queryComplexInterceptor == null) {
            this.queryComplexInterceptor = this.applicationContext.getBean(QueryComplexInterceptor.class);
        }
        if (this.queryAccurateInterceptor == null) {
            this.queryAccurateInterceptor = this.applicationContext.getBean(QueryAccurateInterceptor.class);
        }

        if (this.getClass().getSimpleName().startsWith("Tenant")) {
            this.tenantSupported = true;
        } else {
            this.tenantSupported = false;
        }

    }

}

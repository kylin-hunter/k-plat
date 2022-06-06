package com.kylinhunter.plat.dao.service.local;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.entity.constants.SysCols;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import com.kylinhunter.plat.api.bean.vo.query.ReqQuery;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryById;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryByIds;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryPage;
import com.kylinhunter.plat.api.bean.vo.request.Req;
import com.kylinhunter.plat.api.bean.vo.response.batch.BatchResp;
import com.kylinhunter.plat.api.bean.vo.response.batch.BatchSingleResp;
import com.kylinhunter.plat.api.bean.vo.response.single.Resp;
import com.kylinhunter.plat.api.bean.vo.update.BatchReqUpdate;
import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import com.kylinhunter.plat.api.page.PageData;
import com.kylinhunter.plat.api.service.local.CommonService;
import com.kylinhunter.plat.api.service.local.SaveInterceptor;
import com.kylinhunter.plat.commons.bean.BeanCopyUtils;
import com.kylinhunter.plat.commons.exception.ExceptionHelper;
import com.kylinhunter.plat.commons.exception.common.KRuntimeException;
import com.kylinhunter.plat.commons.exception.info.ErrInfos;
import com.kylinhunter.plat.commons.exception.inner.biz.ex.DBException;
import com.kylinhunter.plat.dao.exception.DAOExceptionConverter;
import com.kylinhunter.plat.dao.service.helper.ServiceDataHelper;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author biji'an
 * @since 2022-01-01
 */
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@NoArgsConstructor
public abstract class CommonServiceImpl<M extends BaseMapper<T>, T extends BaseEntity, X extends ReqCreate,
        Y extends ReqUpdate,
        Z extends Resp, Q extends ReqQueryPage> extends ServiceImpl<M, T>
        implements CommonService<T, X, Y, Z, Q> {

    @Autowired
    private SortComponent sortComponent;
    @Autowired
    private FilterComponent filterComponent;

    private SaveInterceptor saveInterceptor;

    public CommonServiceImpl(SaveInterceptor saveInterceptor) {
        this.saveInterceptor = saveInterceptor;
    }

    @Override
    public Z save(X reqCreate) {
        try {
            T t = this.createSaveEntity(reqCreate);
            if (saveInterceptor != null) {
                saveInterceptor.before(reqCreate);
            }
            Z z;
            if (this.save((T) t)) {
                z = this.findById(new ReqQueryById(t.getId()));
            } else {
                throw new DBException("save db error");
            }

            if (saveInterceptor != null) {
                saveInterceptor.after(reqCreate);
            }
            return z;

        } catch (KRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw DAOExceptionConverter.convert(e);
        }
    }

    @Override
    public boolean save(Collection<X> reqCreates) {
        try {
            reqCreates.forEach(reqCreate -> {
                T t = this.createSaveEntity(reqCreate);
                if (!this.save((T) t)) {
                    throw new DBException("save db error");
                }
            });
            return true;
        } catch (KRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw DAOExceptionConverter.convert(e);
        }

    }

    private T createSaveEntity(X reqCreate) {
        checkRpcContext(reqCreate);
        T saveEntity = createEntityBean();
        BeanCopyUtils.copyProperties(reqCreate, saveEntity);
        ServiceDataHelper.setCreateMsg(saveEntity, reqCreate);
        return saveEntity;
    }

    @Override
    public Z findById(ReqQueryById commonVoFindById) {

        try {
            checkRpcContext(commonVoFindById);
            T docBase = this.getById(commonVoFindById.getId());
            if (docBase == null) {
                return null;
            }
            Z docDraftRespVo = createResponseBean();
            if (skip(commonVoFindById, docBase)) {
                return null;
            }
            BeanCopyUtils.copyProperties(docBase, docDraftRespVo);
            return docDraftRespVo;
        } catch (KRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw DAOExceptionConverter.convert(e);
        }
    }

    @Override
    public List<Z> findByIds(ReqQueryByIds reqQueryByIds) {
        try {
            checkRpcContext(reqQueryByIds);
            List<T> beans = this.baseMapper.selectBatchIds(reqQueryByIds.getIds());
            if (CollectionUtils.isEmpty(beans)) {
                return Collections.EMPTY_LIST;
            }
            return beans.stream().filter(bean -> {
                if (skip(reqQueryByIds, bean)) {
                    return false;
                }
                return true;
            }).map(bean -> {
                Z responseBean = createResponseBean();
                BeanCopyUtils.copyProperties(bean, responseBean);
                return responseBean;
            }).collect(Collectors.toList());

        } catch (KRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw DAOExceptionConverter.convert(e);
        }
    }

    /**
     * @param reqQuery
     * @param baseEntity
     * @return boolean
     * @throws
     * @title 支持逻辑删除，跳过非法数据
     * @description
     * @author BiJi'an
     * @date 2021/11/8 8:26 下午
     */
    protected boolean skip(ReqQuery reqQuery, BaseEntity baseEntity) {
        if (!reqQuery.isWithLogicDelData() && true == baseEntity.getSysDeleteFlag()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(ReqDelete reqDelete) {
        checkRpcContext(reqDelete);
        if (reqDelete.isPhysical()) {
            return this.removeByIds(reqDelete.getIds());
        } else {
            List<T> datas = this.baseMapper.selectBatchIds(reqDelete.getIds());
            datas.forEach(data -> {
                data.setSysDeleteFlag(true);
                ServiceDataHelper.setUpdateMsg(data, reqDelete);
                this.baseMapper.updateById(data);
            });
            return true;

        }

    }

    @Override
    public Z update(Y reqUpdate) {
        try {
            checkRpcContext(reqUpdate);

            if (saveInterceptor != null) {
                saveInterceptor.before(reqUpdate);
            }

            T t = this.getById(reqUpdate.getId());
            if (t == null) {
                throw new DBException(ErrInfos.DB_NO_EXIST, "no body for id =" + reqUpdate.getId());
            }
            BeanCopyUtils.copyProperties(reqUpdate, t);
            ServiceDataHelper.setUpdateMsg(t, reqUpdate);
            Z z;
            if (this.updateById(t)) {
                z = this.findById(new ReqQueryById(t.getId()));
            } else {
                throw new DBException("update db error");
            }

            if (saveInterceptor != null) {
                saveInterceptor.after(reqUpdate);
            }
            return z;
        } catch (KRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw DAOExceptionConverter.convert(e);
        }
    }

    @Override
    public BatchResp<Z> updateBatch(BatchReqUpdate<Y> batchReqUpdate) {

        BatchResp<Z> batchResp = new BatchResp<>();

        List<Y> data = batchReqUpdate.getBody();
        for (Y reqUpdate : data) {
            if (reqUpdate != null) {
                try {
                    Z resp = this.update(reqUpdate);
                    batchResp.addSingleResp(new BatchSingleResp<>(resp));
                } catch (Exception e) {
                    int code = ExceptionHelper.getErrCode(e);
                    String msg = ExceptionHelper.getMessage(e);
                    Z z = this.findById(new ReqQueryById(reqUpdate.getId()));
                    batchResp.addSingleResp(new BatchSingleResp<>(code, msg, z));
                }
            }
        }
        return batchResp;
    }

    @Override
    public PageData<Z> query(Q reqQueryPage) {
        try {

            return this.query(reqQueryPage, queryWrapper(reqQueryPage));
        } catch (KRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw DAOExceptionConverter.convert(e);
        }
    }

    protected QueryWrapper<T> queryWrapper(Q q) {
        QueryWrapper<T> wrapper = Wrappers.query();
        if (q.isWithTenant()) {
            wrapper.eq(SysCols.SYS_TENANT_ID, q.getTenantId());
        }
        if (!q.isWithLogicDelData()) {
            wrapper.eq(SysCols.SYS_DELETE_FLAG, false);
        }
        sortComponent.writeToWrapper(wrapper, q);
        filterComponent.writeToWrapper(wrapper, q);
        return wrapper;
    }

    protected PageData<Z> query(Q q, QueryWrapper<T> wrapper) throws IllegalAccessException, InvocationTargetException {
        Page<T> page = Page.of(q.getPn(), q.getPs());
        Page<T> docDraftPage = this.getBaseMapper().selectPage(page, wrapper);
        return assemblePageData(docDraftPage);
    }

    protected PageData<Z> assemblePageData(IPage<T> page) {
        PageData<Z> pageData = new PageData<>();
        pageData.setPn(page.getCurrent());
        pageData.setPs(page.getSize());
        pageData.setPages(page.getPages());
        pageData.setTotal(page.getTotal());
        for (T r : page.getRecords()) {
            Z docDraftRespVo = createResponseBean();
            BeanCopyUtils.copyProperties(r, docDraftRespVo);
            pageData.getBody().add(docDraftRespVo);
        }
        return pageData;
    }

    public T createEntityBean() {
        try {
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class modelClass = (Class) pt.getActualTypeArguments()[1];
            return entityClass.newInstance();
        } catch (Exception e) {
            throw new DBException("getEntityBean error", e);
        }
    }

    public Z createResponseBean() {
        try {
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class responseBeanClass = (Class) pt.getActualTypeArguments()[4];
            return (Z) responseBeanClass.newInstance();
        } catch (Exception e) {
            throw new DBException("getEntityBean error", e);
        }
    }

    private void checkRpcContext(Req req) {
        //        if (StringUtils.isEmpty(req.getCurrentUserId())) {
        //            req.setCurrentUserId(RemoteContextHolder.get().getUserId());
        //        }
        //
        //        if (StringUtils.isEmpty(req.getCurrentUserName())) {
        //            req.setCurrentUserName(RemoteContextHolder.get().getUserName());
        //        }
        //
        //        if (StringUtils.isEmpty(req.getTenantId())) {
        //            req.setTenantId(RemoteContextHolder.get().getTenantId());
        //        }
        //        if (StringUtils.isEmpty(req.getCurrentAgentId())) {
        //            req.setCurrentAgentId(RemoteContextHolder.get().getCurrentAgentId());
        //        }

    }

}

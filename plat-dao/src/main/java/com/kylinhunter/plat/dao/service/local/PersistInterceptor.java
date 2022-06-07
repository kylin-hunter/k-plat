package com.kylinhunter.plat.dao.service.local;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.vo.VO;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import com.kylinhunter.plat.api.bean.vo.response.single.Resp;
import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import com.kylinhunter.plat.commons.bean.BeanCopyUtils;
import com.kylinhunter.plat.commons.exception.inner.biz.ex.DBException;
import com.kylinhunter.plat.dao.service.helper.ServiceDataHelper;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 22:59
 **/
@Component
public class PersistInterceptor<T extends BaseEntity, C extends ReqCreate, U extends ReqUpdate,
        Z extends Resp, V extends VO> {

    protected Class<T> entityClass = currentModelClass();
    protected Class<Z> respClass = currentRespClass();
    private final String[] updateSkipProperties = new String[] {
            "id", "sysTenantId", "sysCreatedUserId", "sysCreatedUserName", "sysCreatedTime",
            "sysUpdateUserId", "sysUpdateUserName", "sysUpdateTime", "sysDeleteFlag", "sysOpLock"
    };

    public void saveOrUpdateBefore(V vo) {

    }

    public Z saveOrUpdateAfter(V vo, Z z) {
        return z;

    }

    @SuppressWarnings("unchecked")
    public T saveBefore(C c) {
        saveOrUpdateBefore((V) c);
        return createSaveEntity(c);
    }

    @SuppressWarnings("unchecked")
    public Z saveAfter(C c, T enity) {
        Z response = createResponseBean();
        BeanCopyUtils.copyProperties(enity, response);
        return saveOrUpdateAfter((V) c, response);
    }

    @SuppressWarnings("unchecked")
    public T updateBefore(U u, T entity) {
        saveOrUpdateBefore((V) u);
        BeanCopyUtils.copyProperties(u, entity, updateSkipProperties);
        ServiceDataHelper.setDefaultUpdateMsg(entity, u);
        return entity;
    }

    @SuppressWarnings("unchecked")
    public Z updateAfter(U u, T entity) {
        Z response = createResponseBean();
        BeanCopyUtils.copyProperties(entity, response);
        return saveOrUpdateAfter((V) u, response);

    }

    public void deleteBefore(ReqDelete reqDelete) {
    }

    public boolean deleteAfter(ReqDelete reqDelete, Boolean success) {
        return success;
    }

    private Z createResponseBean() {
        try {
            return respClass.newInstance();
        } catch (Exception e) {
            throw new DBException("getEntityBean error", e);
        }
    }

    @SuppressWarnings("unchecked")
    private Class<Z> currentRespClass() {
        return (Class<Z>) ReflectionKit.getSuperClassGenericType(this.getClass(), ServiceImpl.class, 3);
    }

    private T createSaveEntity(C reqCreate) {
        try {
            T saveEntity = entityClass.newInstance();
            BeanCopyUtils.copyProperties(reqCreate, saveEntity);
            ServiceDataHelper.setDefaultCreateMsg(saveEntity, reqCreate);
            return saveEntity;
        } catch (Exception e) {
            throw new DBException("crete EntityBean error", e);
        }
    }

    @SuppressWarnings("unchecked")
    private Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), ServiceImpl.class, 0);
    }
}

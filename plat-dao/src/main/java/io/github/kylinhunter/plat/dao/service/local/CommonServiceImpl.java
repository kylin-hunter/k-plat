/*
 * Copyright (C) 2023 The k-commons Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kylinhunter.plat.dao.service.local;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.kylinhunter.commons.exception.ExceptionConvertor;
import io.github.kylinhunter.commons.exception.ExceptionHelper;
import io.github.kylinhunter.commons.exception.common.KRuntimeException;
import io.github.kylinhunter.commons.exception.embed.biz.DBException;
import io.github.kylinhunter.commons.exception.info.ErrInfos;
import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
import io.github.kylinhunter.plat.api.bean.entity.constants.SysCols;
import io.github.kylinhunter.plat.api.bean.vo.VO;
import io.github.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.github.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import io.github.kylinhunter.plat.api.bean.vo.delete.ReqDeletes;
import io.github.kylinhunter.plat.api.bean.vo.query.ReqById;
import io.github.kylinhunter.plat.api.bean.vo.query.ReqByIds;
import io.github.kylinhunter.plat.api.bean.vo.query.ReqPage;
import io.github.kylinhunter.plat.api.bean.vo.response.batch.BatchResp;
import io.github.kylinhunter.plat.api.bean.vo.response.single.Resp;
import io.github.kylinhunter.plat.api.bean.vo.update.BatchReqUpdate;
import io.github.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import io.github.kylinhunter.plat.api.page.PageData;
import io.github.kylinhunter.plat.api.service.local.CommonService;
import io.github.kylinhunter.plat.api.trace.TraceHolder;
import io.github.kylinhunter.plat.dao.service.local.interceptor.DeleteInterceptor;
import io.github.kylinhunter.plat.dao.service.local.interceptor.FindByIdInterceptor;
import io.github.kylinhunter.plat.dao.service.local.interceptor.QueryInterceptor;
import io.github.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务实现类
 *
 * @author biji'an
 * @since 2022-01-01
 */
@Transactional(rollbackFor = Exception.class)
@NoArgsConstructor
@Slf4j
public abstract class CommonServiceImpl<
    M extends BaseMapper<T>,
    T extends BaseEntity,
    X extends ReqCreate,
    Y extends ReqUpdate,
    Z extends Resp,
    V extends VO,
    Q extends ReqPage>
    extends ServiceImpl<M, T> implements CommonService<T, X, Y, Z, V, Q> {

  protected Class<T> entityClass = currentEntityClass();
  protected Class<Z> respClass = currentRespClass();

  @Autowired
  protected ApplicationContext applicationContext;


  protected SaveOrUpdateInterceptor<T, X, Y, Z, V, Q> saveOrUpdateInterceptor;

  protected DeleteInterceptor<T, X, Y, Z, V, Q> deleteInterceptor;

  protected QueryInterceptor<T, X, Y, Z, V, Q> queryInterceptor;

  protected FindByIdInterceptor<T, X, Y, Z, V, Q> findByIdInterceptor;

  protected boolean tenantSupported = true;

  @SuppressWarnings("unchecked")
  protected Class<T> currentEntityClass() {
    return (Class<T>)
        ReflectionKit.getSuperClassGenericType(this.getClass(), CommonServiceImpl.class, 1);
  }

  @SuppressWarnings("unchecked")
  protected Class<Z> currentRespClass() {
    return (Class<Z>)
        ReflectionKit.getSuperClassGenericType(this.getClass(), CommonServiceImpl.class, 4);
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
      throw ExceptionConvertor.convert(e);
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
      throw ExceptionConvertor.convert(e);
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
      throw ExceptionConvertor.convert(e);
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
        Z z = this.findyById(new ReqById(reqUpdate.getId()));
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
      return true;
    }
    return false;
  }

  @Override
  public boolean delete(ReqDeletes reqDeletes) {

    List<T> datas = this.baseMapper.selectBatchIds(reqDeletes.getIds());
    this.deleteInterceptor.before(reqDeletes, this.tenantSupported, datas);

    datas.forEach(
        data -> {
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
  public Z findyById(ReqById reqById) {
    try {
      QueryWrapper<T> query = findByIdInterceptor.before(reqById, this.tenantSupported);
      query.eq(SysCols.ID, reqById.getId());
      T entity = this.baseMapper.selectOne(query);
      return findByIdInterceptor.after(reqById, entity, createResponse());
    } catch (Exception e) {
      throw ExceptionConvertor.convert(e);
    }
  }

  @Override
  public List<Z> findyByIds(ReqByIds reqByIds) {
    try {

      QueryWrapper<T> query = this.findByIdInterceptor.before(reqByIds, this.tenantSupported);
      query.in(SysCols.ID, reqByIds.getIds());
      List<T> beans = this.baseMapper.selectList(query);
      return this.findByIdInterceptor.after(reqByIds, beans, respClass);

    } catch (Exception e) {
      throw ExceptionConvertor.convert(e);
    }
  }

  @Override
  public PageData<Z> query(Q reqQueryPage) {
    try {
      QueryWrapper<T> queryWrapper = queryInterceptor.before(reqQueryPage, tenantSupported);
      Page<T> page = Page.of(reqQueryPage.getPn(), reqQueryPage.getPs());
      Page<T> entities = this.baseMapper.selectPage(page, queryWrapper);
      return queryInterceptor.after(reqQueryPage, entities, respClass);
    } catch (Exception e) {
      throw ExceptionConvertor.convert(e);
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

    if (this.queryInterceptor == null) {
      this.queryInterceptor = this.applicationContext.getBean(QueryInterceptor.class);
    }
    if (this.findByIdInterceptor == null) {
      this.findByIdInterceptor =
          this.applicationContext.getBean(FindByIdInterceptor.class);
    }

    Class<? extends CommonServiceImpl> clazz = this.getClass();
    if (clazz.getSimpleName().equals("TenantServiceImp")) {
      this.tenantSupported = false;
    } else {
      this.tenantSupported = clazz.getSimpleName().startsWith("Tenant");
    }
    log.info("{} 's tenantSupported={}", clazz.getSimpleName(), tenantSupported);
  }

  /**
   * @return java.lang.String
   * @title getTenanId
   * @description getTenanId
   * @author BiJi'an
   * @date 2023-10-18 15:51
   */
  public String getTenanId() {
    return TraceHolder.get().getUserContext().getTenantId();
  }
}

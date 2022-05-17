package com.kylinhunter.plat.dao.service.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.vo.update.BatchReqUpdate;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.response.single.Resp;
import com.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryById;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryByIds;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryPage;
import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import com.kylinhunter.plat.api.bean.vo.response.batch.BatchResp;
import com.kylinhunter.plat.api.page.PageData;
import com.kylinhunter.plat.api.service.local.CommonService;
import com.kylinhunter.plat.api.service.rpc.RpcCommonService;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-09 11:23
 **/
public class RpcCommonServiceImp<S extends CommonService<T, X, Y, Z, Q>, T extends BaseEntity,
        X extends ReqCreate,
        Y extends ReqUpdate, Z extends Resp, Q extends ReqQueryPage>
        implements RpcCommonService<S, X, Y, Z, Q> {

    @Autowired(required = false)
    protected S service;

    public S getService() {
        return service;
    }

    @Override
    public Z save(X reqCreate) {
        return service.save(reqCreate);
    }

    @Override
    public Z update(Y reqUpdate) {

        return service.update(reqUpdate);
    }

    public BatchResp<Z> updateBatch(BatchReqUpdate<Y> batchReqUpdate) {
        return service.updateBatch(batchReqUpdate);
    }

    @Override
    public Z findById(ReqQueryById commonVoFindById) {

        return service.findById(commonVoFindById);
    }

    @Override
    public boolean delete(ReqDelete reqDelete) {
        return service.delete(reqDelete);
    }

    @Override
    public PageData<Z> query(Q reqQueryPage) {
        return service.query(reqQueryPage);
    }

    public List<Z> findByIds(ReqQueryByIds commonVoFindByIds) {
        return service.findByIds(commonVoFindByIds);
    }
}

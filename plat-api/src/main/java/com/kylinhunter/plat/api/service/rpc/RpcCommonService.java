package com.kylinhunter.plat.api.service.rpc;

import com.kylinhunter.plat.api.bean.vo.query.ReqQueryById;
import com.kylinhunter.plat.api.bean.vo.update.BatchReqUpdate;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.response.single.Resp;
import com.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryPage;
import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import com.kylinhunter.plat.api.bean.vo.response.batch.BatchResp;
import com.kylinhunter.plat.api.page.PageData;
import com.kylinhunter.plat.api.service.local.CommonService;

/**
 * <p>
 * 通用RPC处理服务，必要的话可以继承修改
 * </p>
 *
 * @author biji'an
 * @since 2022-01-01
 */
public interface RpcCommonService<S extends CommonService, X extends ReqCreate,
        Y extends ReqUpdate, Z extends Resp, Q extends ReqQueryPage> {

    public Z save(X reqCreate);

    public Z update(Y reqUpdate);

    public BatchResp<Z> updateBatch(BatchReqUpdate<Y> batchReqUpdate);

    public Z findById(ReqQueryById commonVoFindById);

    public boolean delete(ReqDelete reqDelete);

    public PageData<Z> query(Q reqQueryPage);

}

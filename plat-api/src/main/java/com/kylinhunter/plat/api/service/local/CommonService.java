package com.kylinhunter.plat.api.service.local;

import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kylinhunter.plat.api.bean.entity.BaseEntity;
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

/**
 * CommonService
 *
 * @author biji'an
 * @since 2022-01-01
 */
public interface CommonService<T extends BaseEntity, X extends ReqCreate, Y extends ReqUpdate,
        Z extends Resp, V extends VO, Q extends ReqPage>
        extends IService<T> {

    Z save(X reqCreate);

    boolean save(Collection<X> reqCreate);

    Z update(Y reqUpdate);

    BatchResp<Z> updateBatch(BatchReqUpdate<Y> batchReqUpdate);

    boolean delete(ReqDelete reqDelete);

    boolean delete(ReqDeletes reqDeletes);

    Z queryById(ReqById reqById);

    List<Z> queryByIds(ReqByIds reqByIds);

    PageData<Z> query(Q reqQueryPage);

}

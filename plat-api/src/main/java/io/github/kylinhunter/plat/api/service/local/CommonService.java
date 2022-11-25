package io.github.kylinhunter.plat.api.service.local;

import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
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

    T getById(String id);

    List<Z> queryByIds(ReqByIds reqByIds);

    PageData<Z> query(Q reqQueryPage);

}

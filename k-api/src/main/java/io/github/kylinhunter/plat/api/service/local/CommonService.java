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
package io.github.kylinhunter.plat.api.service.local;

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
import java.util.Collection;
import java.util.List;

/**
 * CommonService
 *
 * @author biji'an
 * @since 2022-01-01
 */
public interface CommonService<
        T extends BaseEntity,
        X extends ReqCreate,
        Y extends ReqUpdate,
        Z extends Resp,
        V extends VO,
        Q extends ReqPage>
    extends IService<T> {

  Z save(X reqCreate);

  boolean save(Collection<X> reqCreate);

  Z update(Y reqUpdate);

  BatchResp<Z> updateBatch(BatchReqUpdate<Y> batchReqUpdate);

  boolean delete(ReqDelete reqDelete);

  boolean delete(ReqDeletes reqDeletes);

  Z findyById(ReqById reqById);

  List<Z> findyByIds(ReqByIds reqByIds);

  PageData<Z> query(Q reqQueryPage);
}

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
package io.github.kylinhunter.plat.search.service.local.imp;

import io.github.kylinhunter.plat.search.bean.index.User;
import io.github.kylinhunter.plat.search.dao.repository.UserRepository;
import io.github.kylinhunter.plat.search.service.local.UserService;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-10 01:27
 */
@Service
public class UserServiceImpl implements UserService {

  @Resource private UserRepository elasticRepository;
  @Resource private ElasticsearchRestTemplate elasticsearchTemplate;

  @Override
  public void deleteIndex(String index) {
    elasticsearchTemplate.delete(index);
  }

  @Override
  public void save(User docBean) {
    elasticRepository.save(docBean);
  }

  @Override
  public void saveAll(List<User> list) {
    elasticRepository.saveAll(list);
  }

  @Override
  public Iterator<User> findAll() {
    return elasticRepository.findAll().iterator();
  }
}

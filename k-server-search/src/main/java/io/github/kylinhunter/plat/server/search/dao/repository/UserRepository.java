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
package io.github.kylinhunter.plat.server.search.dao.repository;

import io.github.kylinhunter.plat.server.search.bean.index.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-10 01:25
 */
public interface UserRepository extends ElasticsearchRepository<User, String> {}

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
package io.github.kylinhunter.plat.core.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Mapper 接口
 *
 * @author biji'an
 * @since 2022-06-17
 */
@Mapper
@Repository
public interface TenantRoleMapper extends BaseMapper<TenantRole> {}

package io.github.kylinhunter.plat.search.dao.repository;

import io.github.kylinhunter.plat.search.bean.index.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-10 01:25
 */
public interface UserRepository extends ElasticsearchRepository<User, String> {

}
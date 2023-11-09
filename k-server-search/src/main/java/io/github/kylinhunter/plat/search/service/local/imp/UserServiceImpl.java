package io.github.kylinhunter.plat.search.service.local.imp;

import io.github.kylinhunter.plat.search.bean.index.User;
import io.github.kylinhunter.plat.search.service.local.UserService;
import io.github.kylinhunter.plat.search.dao.mapper.UserRepository;
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

  @Resource
  private UserRepository elasticRepository;
  @Resource
  private ElasticsearchRestTemplate elasticsearchTemplate;

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
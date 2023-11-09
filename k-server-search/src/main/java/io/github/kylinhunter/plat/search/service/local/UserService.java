package io.github.kylinhunter.plat.search.service.local;

import io.github.kylinhunter.plat.search.bean.index.User;
import java.util.Iterator;
import java.util.List;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-10 01:26
 */
public interface UserService {

  void deleteIndex(String index);

  void save(User docBean);

  void saveAll(List<User> list);

  Iterator<User> findAll();
}
package io.github.kylinhunter.plat.search.controller;

import io.github.kylinhunter.plat.search.bean.index.User;
import io.github.kylinhunter.plat.search.service.local.UserService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-10 01:32
 */
@RestController
@RequestMapping("/employeeInfo")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping("/batchSave")
  public String init() throws Exception {
    List<User> list = new ArrayList<>();
    IntStream.range(0, 100).forEach(i -> {
      User user = new User();
      user.setId("id" + i);
      user.setRealName("realName" + i);
      user.setUserName("userName" + i);
      user.setNickName("nickName" + i);
      user.setSource("source" + i);
      user.setDescription("description" + i);
      user.setSysCreatedUserId("user" + i);
      user.setSysCreatedUserName("userName" + i);
//      user.setSysUpdateTime(LocalDateTime.now());
      user.setSysUpdateUserId("user" + i);
      user.setSysUpdateUserName("userName" + i);
//      user.setSysUpdateTime(LocalDateTime.now());
      user.setSysTenantId("tenant" + i);
      list.add(user);
    });
    userService.saveAll(list);
    return "success -> " + list.size();
  }

  @GetMapping("/listAll")
  public Iterator<User> all() {
    return userService.findAll();
  }

}
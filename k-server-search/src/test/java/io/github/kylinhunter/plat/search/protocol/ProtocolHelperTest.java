package io.github.kylinhunter.plat.search.protocol;

import io.github.kylinhunter.plat.server.search.bean.index.User;
import io.github.kylinhunter.plat.server.search.protocol.ProtocolHelper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProtocolHelperTest {

  @Test
  void test() {
    List<User> users1 = new ArrayList<>();
    IntStream.range(0, 100).forEach(i -> {
      User user = new User();
      user.setId("id" + i);
      user.setSysCreatedUserId("user" + i);
      user.setSysCreatedUserName("userName" + i);
      user.setSysUpdateTime(LocalDateTime.now());
      user.setSysUpdateUserId("user" + i);
      user.setSysUpdateUserName("userName" + i);
      user.setSysUpdateTime(LocalDateTime.now());
      user.setSysTenantId("tenant" + i);
      user.setRealName("realName" + i);
      user.setUserName("userName" + i);
      user.setNickName("nickName" + i);
      user.setSource("source" + i);
      user.setDescription("description" + i);
      user.setType(i);
      user.setSource("source" + i);
      users1.add(user);
    });
    String s1 = ProtocolHelper.toString(users1);
    System.out.println(s1);
    List<User> users2 = ProtocolHelper.toIndex(s1, User.class);
    String s2 = ProtocolHelper.toString(users2);
    System.out.println(s2);
    Assertions.assertEquals(s1, s2);
  }
}
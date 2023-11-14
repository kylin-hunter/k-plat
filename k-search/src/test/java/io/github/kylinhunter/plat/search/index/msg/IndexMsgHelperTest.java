package io.github.kylinhunter.plat.search.index.msg;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IndexMsgHelperTest {

  @Test
  void test() {
    List<TestUser> users1 = new ArrayList<>();
    IntStream.range(0, 100).forEach(i -> {
      TestUser user = new TestUser();
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
    String s1 = IndexMsgHelper.toString(users1);
    System.out.println(s1);
    List<TestUser> users2 = IndexMsgHelper.toBeans(s1, TestUser.class);
    String s2 = IndexMsgHelper.toString(users2);
    System.out.println(s2);
    Assertions.assertEquals(s1, s2);

    TestUser user0 = users1.get(0);
    String s3 = IndexMsgHelper.toString(user0);
    System.out.println(s3);
    TestUser user00 = IndexMsgHelper.toBean(s3, TestUser.class);
    String s4 = IndexMsgHelper.toString(user00);
    System.out.println(s4);
    Assertions.assertEquals(s3, s4);

  }
}
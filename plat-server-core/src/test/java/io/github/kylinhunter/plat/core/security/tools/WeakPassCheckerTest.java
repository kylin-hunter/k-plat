package io.github.kylinhunter.plat.core.security.tools;

import static org.junit.jupiter.api.Assertions.*;

import io.github.kylinhunter.plat.core.security.password.WeakPassChecker;
import io.github.kylinhunter.plat.core.security.password.WeakPassOption;
import org.junit.jupiter.api.Test;

class WeakPassCheckerTest {

  @Test
  void check() {
    WeakPassOption weakPassOption = WeakPassOption.builder().build();
    WeakPassChecker weakPassChecker = new WeakPassChecker(weakPassOption);
    assertEquals(true, weakPassChecker.isWeak(""));//空不行
    assertEquals(true, weakPassChecker.isWeak("1"));//          有数字但是长度不够，没有小写字母、没有大写字母，没有特殊字符

    assertEquals(true, weakPassChecker.isWeak("13579246"));//   有数字长度够了，没有小写字母、没有大写字母，没有特殊字符
    assertEquals(true, weakPassChecker.isWeak("a13579246"));//  有数字长度够了，有小写字母，没有大写字母，没有特殊字符
    assertEquals(true, weakPassChecker.isWeak("a13579246A"));// 有数字长度够了，有小写字母，有大写字母 、没有特殊字符
    assertEquals(false, weakPassChecker.isWeak("a13579246A#"));// 有数字长度够了，有小写字母，有大写字母 、有特殊字符

    assertEquals(true,
        weakPassChecker.isWeak("a13579246A#bijianlxp"));// 有数字长度够了，有小写字母，有大写字母 、有特殊字符，但是超长了

    assertEquals(false, weakPassChecker.isWeak("a13579246A#aa"));// 有连续相同的3个字符，是弱密码
    assertEquals(true, weakPassChecker.isWeak("a13579246A#aaa"));// 有连续相同的3个字符，是弱密码
    assertEquals(true, weakPassChecker.isWeak("a13579246A#aaaa"));// 有连续相同的3个字符，是弱密码

    assertEquals(false, weakPassChecker.isWeak("a13579246A#11"));// 有连续相同的3个数字，是弱密码
    assertEquals(true, weakPassChecker.isWeak("a13579246A#111"));// 有连续相同的3个数字，是弱密码
    assertEquals(true, weakPassChecker.isWeak("a13579246A#1111"));// 有连续相同的3个数字，是弱密码

    assertEquals(false, weakPassChecker.isWeak("a13579246A#12"));// 有连续升序的3个数字，是弱密码
    assertEquals(true, weakPassChecker.isWeak("a13579246A#123"));// 有连续升序的3个数字，是弱密码
    assertEquals(true, weakPassChecker.isWeak("a13579246A#1234"));// 有连续升序的3个数字，是弱密码

    assertEquals(false, weakPassChecker.isWeak("a13579246A#21"));// 有连续降序的3个数字，是弱密码
    assertEquals(true, weakPassChecker.isWeak("a13579246A#321"));// 有连续降序的3个数字，是弱密码
    assertEquals(true, weakPassChecker.isWeak("a13579246A#3210"));// 有连续降序的3个数字，是弱密码

    assertEquals(false, weakPassChecker.isWeak("a13579246A#ab"));// 有连续升序的3个字母，是弱密码
    assertEquals(true, weakPassChecker.isWeak("a13579246A#abc"));// 有连续升序的3个字母，是弱密码
    assertEquals(true, weakPassChecker.isWeak("a13579246A#abcd"));// 有连续升序的3个字母，是弱密码

    assertEquals(false, weakPassChecker.isWeak("a13579246A#dc"));// 有连续降序的3个字母，是弱密码
    assertEquals(true, weakPassChecker.isWeak("a13579246A#dcb"));// 有连续降序的3个字母，是弱密码
    assertEquals(true, weakPassChecker.isWeak("a13579246A#dcba"));// 有连续降序的3个字母，是弱密码

  }
}
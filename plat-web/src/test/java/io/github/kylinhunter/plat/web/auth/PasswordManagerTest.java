package io.github.kylinhunter.plat.web.auth;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class PasswordManagerTest {

  @Test
  void encode() {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    WeakPasswordChecker weakPasswordChecker = new WeakPasswordChecker();
    PasswordManager passwordManager = new PasswordManager(passwordEncoder, weakPasswordChecker);

    String text = "bijian";

    String encode1 = passwordManager.encode(text);
    System.out.println("encode1:" + encode1);

    String encode2 = passwordManager.encode(text);
    System.out.println("encode2:" + encode2);

    assertTrue(passwordManager.matches(text, encode1));
    assertTrue(passwordManager.matches(text, encode2));
  }
}
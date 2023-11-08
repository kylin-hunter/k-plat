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
package io.github.kylinhunter.plat.web.auth;

import io.github.kylinhunter.commons.tools.WeakPasswordChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-07 01:14
 */
@RequiredArgsConstructor
public class PasswordManager {

  private final PasswordEncoder passwordEncoder;
  private final WeakPasswordChecker weakPasswordChecker;

  /**
   * @param pass pass
   * @return java.lang.String
   * @title encode
   * @description encode
   * @author BiJi'an
   * @date 2023-10-17 15:36
   */
  public String encode(String pass) {
    return passwordEncoder.encode(pass);
  }

  /**
   * @param rawPassword rawPassword
   * @param encodedPassword encodedPassword
   * @return boolean
   * @throws
   * @title matches
   * @description matches
   * @author BiJi'an
   * @date 2023-10-17 15:36
   */
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }

  /***
   * @title isWeak
   * @description isWeak
   * @author BiJi'an
   * @param pass pass
   * @date 2023-10-17 15:39
   * @return boolean
   */

  public boolean isWeak(String pass) {
    return weakPasswordChecker.check(pass);
  }
}

package io.github.kylinhunter.plat.api.auth;

import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-15 02:00
 */
@Getter
@Setter
@NoArgsConstructor
public class TokenEx {

  private Set<String> pemCodes;

  public TokenEx(Set<String> pemCodes) {
    this.pemCodes = pemCodes;
  }


}
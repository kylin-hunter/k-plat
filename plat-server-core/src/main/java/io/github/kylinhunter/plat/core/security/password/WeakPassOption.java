package io.github.kylinhunter.plat.core.security.password;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-17 00:26
 */

@Builder
@Data
public class WeakPassOption {

  @Default
  private int minLength = 6;
  @Default
  private int maxLength = 16;
  @Default
  int minDuplicateChars = 3;
  @Default
  int minContinuousChars = 3;
}
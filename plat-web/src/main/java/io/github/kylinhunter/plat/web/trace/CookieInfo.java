package io.github.kylinhunter.plat.web.trace;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-01 19:25
 **/
@Data
@AllArgsConstructor
public class CookieInfo {
    private String name;
    private String path;
    private String value;
}

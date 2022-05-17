package com.kylinhunter.plat.commons.io;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date  2022-01-01 02:15
 **/
@Data
@AllArgsConstructor
public class PathInfo {
    private PathType pathType;
    private String path;
}

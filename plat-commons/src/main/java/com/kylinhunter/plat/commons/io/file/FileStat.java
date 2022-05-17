package com.kylinhunter.plat.commons.io.file;

import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022-05-18 01:31
 **/
@Data
public class FileStat {
    private int lineNum;

    public void addLineNum() {
        this.lineNum++;
    }
}

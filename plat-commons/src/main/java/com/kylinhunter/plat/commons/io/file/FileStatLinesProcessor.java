package com.kylinhunter.plat.commons.io.file;

/**
 * @author BiJi'an
 * @description
 * @date 2022-05-18 01:31
 **/
public class FileStatLinesProcessor extends DefaultLinesProcessor<FileStat> {
    private FileStat fileStat = new FileStat();

    @Override
    public void process(String line) {
        fileStat.addLineNum();
    }

    @Override
    FileStat getResult() {
        return fileStat;
    }
}

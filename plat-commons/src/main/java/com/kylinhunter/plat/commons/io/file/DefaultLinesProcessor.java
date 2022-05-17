package com.kylinhunter.plat.commons.io.file;

/**
 * @author BiJi'an
 * @description default line processor
 * @date 2022/1/1
 **/
public abstract class DefaultLinesProcessor<T> implements LinesProcessor {

    abstract T getResult();
}

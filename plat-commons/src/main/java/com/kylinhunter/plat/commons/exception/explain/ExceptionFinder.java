package com.kylinhunter.plat.commons.exception.explain;

import java.util.Set;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 01:46
 **/
public class ExceptionFinder {

    /**
     * @param e
     * @param acceptSubClass
     * @param cls
     * @return T
     * @throws
     * @title 查找指定类型的异常
     * @description
     * @author BiJi'an
     * @updateTime 2022/01/01 5:14 下午
     */
    @SuppressWarnings("unchecked")
    public static <T extends Throwable> T find(Throwable e, boolean acceptSubClass, Class<T> cls) {

        if (cls != null) {
            while (e != null) {
                if (e.getClass().equals(cls)) {
                    return (T) e;
                } else if (acceptSubClass && cls.isAssignableFrom(e.getClass())) {
                    return (T) e;
                }
                e = e.getCause();
            }
        }
        return null;
    }

    /**
     * @param e
     * @param acceptSubClass
     * @param samples
     * @return com.kylinhunter.plat.commons.exception.ExceptionHelper.ExceptionFind
     * @throws
     * @title 找到第一个需要的异常
     * @description
     * @author BiJi'an
     * @updateTime 2022/01/01 5:20 下午
     */
    public static ExceptionFind find(Throwable e, boolean acceptSubClass, Set<Class<? extends Throwable>> samples) {

        if (samples != null) {
            while (e != null) {
                for (Class<? extends Throwable> sample : samples) {
                    if (e.getClass().equals(sample)) {
                        return new ExceptionFind(e, sample);
                    }

                    if (acceptSubClass && sample.isAssignableFrom(e.getClass())) {
                        return new ExceptionFind(e, sample);
                    }
                }

                e = e.getCause();
            }
        }

        return null;

    }
}

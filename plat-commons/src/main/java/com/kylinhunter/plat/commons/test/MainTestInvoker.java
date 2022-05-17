package com.kylinhunter.plat.commons.test;

/**
 * @author BiJi'an
 * @description 不抛出异常调用main，测试用的
 * @date 2022-01-01 15:06
 **/
public class MainTestInvoker {
    public static <T> void invoke(Invoker invoker) {
        try {
            invoker.invoke();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface Invoker {
        void invoke() throws Exception;

    }
}

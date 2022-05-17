package com.kylinhunter.plat.web.log;

public class ReturnValueHolder {
    private static ThreadLocal<Object> returnValue = new ThreadLocal<>();

    public static Object getValue() {
        return returnValue.get();
    }

    public static void setValue(Object value) {
        returnValue.set(value);
    }

    public static void remove() {
        returnValue.remove();
    }
}

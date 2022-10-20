package com.kylinhunter.plat.study.mat;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author BiJi'an
 * @description
 * @date 2022-08-10 00:57
 **/
public class ObjectsMAT {

    public static void main(String[] args) {
        new Thread(() -> {
            Map<String, A> map = Maps.newHashMap();

            IntStream.range(0, 10).forEach((i) -> {
                byte[] b = new byte[1024 * 1024];
                String str = new String(b).replace('\0', (char) i);

                A a = new A();
                a.b.c.list.add(str);

                map.put(i + "", a);
            });
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "king-thread").start();
        ;
    }

    static class A {
        public B b = new B();

    }

    static class B {
        public C c = new C();

    }

    static class C {
        public List<String> list = Lists.newArrayList();
    }
}


package com.kylinhunter.plat.study.mat;

/**
 * @author BiJi'an
 * @description
 * @date 2022-08-10 00:37
 **/
public class MATRef {

    public static void main(String[] args) throws InterruptedException {

        A a = new A();
        B b = new B();

        Thread.sleep(Integer.MAX_VALUE);
    }

}

class A {
    private C c1 = C.getInstance();

}

class B {
    private C c2 = C.getInstance();

}

class C {
    private static C myC = new C();

    public static C getInstance() {
        return myC;
    }

    private D d = new D();
    private E e = new E();

}

class D {

}

class E {

}
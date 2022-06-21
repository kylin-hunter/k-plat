package com.kylinhunter.plat.commons.codec;

import org.junit.jupiter.api.Test;

class MD5UtilTest {

    @Test
    public void testMd5() {
        String text = "bijian";
        String md5 = MD5Util.md5(text);
        System.out.println(md5);
        md5 = MD5Util.md5(text);
        System.out.println(md5); // 可以破解的 ，解密网站 https://www.cmd5.com/
    }

    @Test
    void testMd5WithSalt() {
        String text = "bijian";
        String salt = "$1$salt";
        System.out.println("salt:" + salt);
        String md5 = MD5Util.md5(text, salt);
        System.out.println(md5);
        md5 = MD5Util.md5(text, salt);
        System.out.println(md5); //

    }
    
}
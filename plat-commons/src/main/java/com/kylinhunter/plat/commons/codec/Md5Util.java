package com.kylinhunter.plat.commons.codec;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-07 00:47
 **/
public class Md5Util {

    public static String md5(String text) {
        String encode = DigestUtils.md5Hex(text);
        return encode;
    }

    public static String md5(String text, String salt) {
        String encode = Md5Crypt.md5Crypt(text.getBytes(StandardCharsets.UTF_8), salt);
        return encode;
    }
}

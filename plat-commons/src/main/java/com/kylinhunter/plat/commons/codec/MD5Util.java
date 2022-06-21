package com.kylinhunter.plat.commons.codec;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-07 00:47
 **/
public class MD5Util {

    public static String md5(String text) {
        return DigestUtils.md5Hex(text.getBytes(StandardCharsets.UTF_8));
    }

    public static String md5(String text, String salt) {
        return Md5Crypt.md5Crypt(text.getBytes(StandardCharsets.UTF_8), salt);
    }
}

package com.kylinhunter.plat.commons.codec;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-21 00:20
 **/
public class SecretKeyUtil {

    public static String keyString(SecretKey key) {
        byte[] bytes = key.getEncoded();
        // log.info("真实的秘钥：" + Arrays.toString(bytes));
        byte[] base64Bytes = Base64.getEncoder().encode(bytes);
        // log.info("秘钥(base64加密之后）：" + Arrays.toString(base64Bytes));
        String keyStr = new String(base64Bytes, StandardCharsets.UTF_8);
        // log.info("传播秘钥：" + keyStr);
        return keyStr;

    }

    public static SecretKey restoreKey(String keyStr) {
        // log.info("收到传播秘钥：" + keyStr);
        byte[] keyBytes = keyStr.getBytes(StandardCharsets.UTF_8);
        // log.info("还原秘钥的字节（utf-8）" + Arrays.toString(keyBytes));
        byte[] keyRaw = Base64.getDecoder().decode(keyBytes);
        // log.info("还原真实秘钥（base64解密）：：" + Arrays.toString(keyRaw));

        return new SecretKeySpec(keyRaw, "AES");

    }
}

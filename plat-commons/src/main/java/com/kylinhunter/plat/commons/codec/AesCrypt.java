package com.kylinhunter.plat.commons.codec;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.kylinhunter.plat.commons.exception.inner.GeneralException;
import com.kylinhunter.plat.commons.exception.inner.InitException;

/**
 * AES算法
 *
 * @author bijian
 */
// @Slf4j
public class AesCrypt  {
    private SecretKey defaultKey;
    private ThreadLocal<Cipher> defaultEnCipher;
    private ThreadLocal<Cipher> defaultDeCipher;
    private ThreadLocal<Cipher> enCipher;
    private ThreadLocal<Cipher> deCipher;
    private static final String DEFAULT_SEED = "kplat";

    private static final AesCrypt singletin = new AesCrypt();

    public static AesCrypt getInstance() {
        return singletin;
    }

    private AesCrypt() {
        this.init(this.generateKey());
    }

    public AesCrypt(String defaultKey) {
        this.init(this.restoreKey(defaultKey));
    }

    private void init(SecretKey defaultKey) {
        this.defaultKey = defaultKey;
        this.defaultEnCipher = this.initCipher(Cipher.ENCRYPT_MODE, defaultKey);
        this.defaultDeCipher = this.initCipher(Cipher.DECRYPT_MODE, defaultKey);
        this.enCipher = this.initCipher(Cipher.ENCRYPT_MODE, null);
        this.deCipher = this.initCipher(Cipher.DECRYPT_MODE, null);

    }

    private ThreadLocal<Cipher> initCipher(int mode, SecretKey key) {
        return ThreadLocal.withInitial(() -> {

            try {
                Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding"); // 创建密码器
                if (key != null) {
                    cipher.init(mode, key);// 初始化为加密模式的密码器
                }
                return cipher;
            } catch (Exception e) {
                throw new InitException("init AesCrypt  error", e);
            }
        });
    }

    public Cipher getEnCipher(SecretKey key) {

        try {
            Cipher cipher = this.enCipher.get();
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器
            return cipher;
        } catch (InvalidKeyException e) {
            throw new InitException("getEnCipher error", e);
        }

    }

    public Cipher getDeCipher(SecretKey key) {
        try {
            Cipher cipher = this.deCipher.get();
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
            return cipher;
        } catch (InvalidKeyException e) {
            throw new InitException("getDeCipher error", e);
        }
    }

    
    public SecretKey getDefaultKey() {
        return defaultKey;
    }

    
    public SecretKey generateKey() {
        return generateKey(DEFAULT_SEED);
    }

    
    public SecretKey generateKey(String seed) {
        // TODO Auto-generated method stub

        try {
            // 1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            // 2.根据ecnodeRules规则初始化密钥生成器
            // 生成一个128位的随机源,根据传入的字节数组
            // keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            //            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            SecureRandom random = new SecureRandom(seed.getBytes());
            //            random.setSeed(seed.getBytes());
            keygen.init(128, random);// 利用用户密码作为随机数初始化出

            // 3.产生原始对称密钥
            SecretKey original_key = keygen.generateKey();
            // 4.获得原始对称密钥的字节数组
            byte[] keyRaw = original_key.getEncoded();
            return new SecretKeySpec(keyRaw, "AES");// 转换为AES专用密钥

        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new GeneralException("generateKey error", e);
        }
    }

    
    public SecretKey restoreKey(String key) {
        return SecretKeyUtil.restoreKey(key);
    }

    
    public String stringKey(SecretKey key) {
        return SecretKeyUtil.keyString(key);
    }

    
    public String encrypt(String text) {
        return encrypt(text, this.defaultEnCipher.get());
    }

    
    public String encrypt(String text, String keyStr) {
        return encrypt(text, restoreKey(keyStr));
    }

    
    public String encrypt(String text, SecretKey key) {
        // TODO Auto-generated method stub

        return encrypt(text, this.getEnCipher(key));

    }

    private String encrypt(String text, Cipher cipher) {
        // TODO Auto-generated method stub

        try {
            // log.info("明文：" + text);
            byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);
            // log.info("明文的原始字节（utf-8）：" + Arrays.toString(textBytes));
            int length = textBytes.length;
            // 计算需填充长度
            int blockSize = cipher.getBlockSize();

            if (length % blockSize != 0) {
                length = length + (blockSize - (length % blockSize));
            }
            byte[] plaintext = new byte[length];
            // 填充
            System.arraycopy(textBytes, 0, plaintext, 0, textBytes.length);

            byte[] decrptText = cipher.doFinal(plaintext);// 加密
            // log.info("密文字节：" + Arrays.toString(decrptText));
            byte[] base64Bytes = Base64.getEncoder().encode(decrptText);
            // log.info("密字节(base64之后）：" + Arrays.toString(base64Bytes));
            // log.info("加密的最终结果：" + result);

            return new String(base64Bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new GeneralException("encrypt error", e);

        }

    }

    
    public String decrypt(String text) {
        return decrypt(text, this.defaultDeCipher.get());
    }

    
    public String decrypt(String text, String keyStr) {
        return decrypt(text, restoreKey(keyStr));
    }

    
    public String decrypt(String decryptText, SecretKey key) {
        // TODO Auto-generated method stub
        // log.info("密文：" + decryptText);
        return decrypt(decryptText, this.getDeCipher(key));
    }

    private String decrypt(String decryptText, Cipher cipher) {
        // TODO Auto-generated method stub

        try {

            byte[] decryptTextBytes = decryptText.getBytes(StandardCharsets.UTF_8);
            // log.info("密文用UTF8进行还原（utf-8）：" + Arrays.toString(decryptTextBytes));

            byte[] decryptTextRaw = Base64.getDecoder().decode(decryptTextBytes);
            // log.info("密文用base64还原：" + Arrays.toString(decryptTextRaw));

            byte[] encryptTextBytes = cipher.doFinal(decryptTextRaw);// 加密

            // log.info("明文字节：" + Arrays.toString(encryptTextBytes));

            String result = new String(encryptTextBytes, StandardCharsets.UTF_8);
            // log.info("解密的最终明文（UTF-8编码)：" + result);

            return result.trim();
        } catch (Exception e) {
            throw new GeneralException("encrypt error:" + e.getMessage(), e);

        }

    }

}

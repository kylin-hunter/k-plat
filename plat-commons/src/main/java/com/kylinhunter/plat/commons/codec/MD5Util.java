package com.kylinhunter.plat.commons.codec;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.io.FilenameUtils;

import com.kylinhunter.plat.commons.exception.inner.KIOException;

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

    public static String md5(byte[] bytes) {
        return DigestUtils.md5Hex(bytes);
    }

    public static String md5(InputStream inputStream) throws NoSuchAlgorithmException, IOException {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte buffer[] = new byte[10240];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            return Hex.encodeHexString(digest.digest());
        } catch (Exception e) {
            throw new KIOException("md5 error ", e);
        }

    }

    public static String md5(File file) {
        if (!file.isFile()) {
            throw new KIOException("not file " + file.getAbsolutePath());
        }

        try (FileInputStream in = new FileInputStream(file)) {
            return md5(in);
        } catch (Exception e) {
            throw new KIOException("md5 error ", e);

        }

    }
}

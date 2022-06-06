package com.kylinhunter.plat.commons.codec;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-07 01:14
 **/
public class PasswordUtil {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encode(String pass) {
        return bCryptPasswordEncoder.encode(pass);
    }

    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}

package com.zju.nft.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;

@Slf4j
public class PasswordUtil {
    public static String getRandSalt(){
        Random random = new Random();
        char[] chars = new char[10];

        for (int i = 0; i < 10; i++){
            chars[i] = (char)('a' + random.nextInt(26));
        }

        return new String(chars);
    }

    public static String passwordHash(String password, String salt){
        String value = password + salt;
        byte[] b = value.getBytes();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] result = md.digest(b);
            return Hex.encodeHexString(result);
        }catch (Exception e){
            log.info("password hash error");
            return null;
        }
    }
}

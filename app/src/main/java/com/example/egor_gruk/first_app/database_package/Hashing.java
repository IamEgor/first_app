package com.example.egor_gruk.first_app.database_package;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Егор on 04.12.2014.
 */
public class Hashing {
    public static String md5(String plainText) throws NoSuchAlgorithmException {
        return md5(null, plainText);
    }

    public static String md5(String salt, String plainText)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        if (salt != null) {
            md.update(salt.getBytes());
        }
        md.update(plainText.getBytes());

        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return sb.toString();
    }

}

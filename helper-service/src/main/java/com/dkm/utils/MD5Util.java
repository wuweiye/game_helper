package com.dkm.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class MD5Util {
    private static final String encryModel="MD5";


    public static String encrypt(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance(encryModel);
            md.update(str.getBytes());
            StringBuffer sb = new StringBuffer();
            byte[] bytes = md.digest();
            for (int i = 0; i < bytes.length; i++) {
                int b = bytes[i] & 0xFF;
                if (b < 0x10) {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(b));
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static String encrypt(Map<String,String> map) {
        try {
            MessageDigest digest = MessageDigest.getInstance(encryModel);
            for(String str : map.keySet()){
                digest.update(str.getBytes());
            }

            BigInteger bigInteger = new BigInteger(1,digest.digest());
            return bigInteger.toString(16);
        } catch (Exception e) {
            return null;
        }
    }

    public static String encrypt(String... strings) {

        try {
            MessageDigest digest = MessageDigest.getInstance(encryModel);
            for(String str :strings){
                digest.update(str.getBytes());
            }
            BigInteger bigInteger = new BigInteger(1,digest.digest());
            return bigInteger.toString(16);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


}

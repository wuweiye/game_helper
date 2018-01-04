package com.dkm.utils;

import com.dkm.base.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

public class FileUtils {

    public static Long start ,end;

    private final static byte[] buffer = new byte[1024 * 8];

    /**
     * 计算文件的(MD5 SHA-1)
     * 默认MD5
     * @param file
     * @return
     */
    public static String getFileDigest(File file,String... args){


        if(!file.isFile()){
            Constants.sys("找不到对应文件");
            return null;
        }
        String algorithm = null;
        if(args.length != 0){
            algorithm = args[0];
        }else {
            algorithm = "MD5";
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        int len;
        try {

            digest = MessageDigest.getInstance(algorithm);
            in = new FileInputStream(file);
            while ((len = in.read(buffer))!= -1){
                digest.update(buffer,0,len);
            }
            BigInteger bigInteger = new BigInteger(1,digest.digest());
            return bigInteger.toString(16);


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        return null;
    }


    public static String getInputStreamDigest(InputStream in, String... args){



        start = System.currentTimeMillis();
        Constants.sys("\n\n\nTest 开始" + Constants.wholeDateFormat.format(new Date()));
        MessageDigest digest = null;

        int len;

        String algorithm = null;
        if(args.length != 0){
            algorithm = args[0];
        }else {
            algorithm = "MD5";
        }

        try {
            digest = MessageDigest.getInstance(algorithm);
            while ((len =  in.read(buffer)) != -1){
                digest.update(buffer,0,len);
            }

            BigInteger bigInteger = new BigInteger(1,digest.digest());
            Constants.sys(bigInteger.toString(16));

            end = System.currentTimeMillis() - start;
            Constants.sys("OK" + Constants.wholeDateFormat.format(new Date()));
            Constants.sys("此次运行一共用时"+ end +"毫秒\n\n\n");
            return bigInteger.toString(16);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis() - start;
        Constants.sys("ERROR" + Constants.wholeDateFormat.format(new Date()));
        Constants.sys("此次运行一共用时"+ end +"毫秒\n\n\n");

        return null;
    }




}

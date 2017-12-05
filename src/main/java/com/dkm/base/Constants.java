package com.dkm.base;

import java.text.SimpleDateFormat;

/**
 * 常用方法
 */
public class Constants {

    public  static  final SimpleDateFormat wholeDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public  static  final SimpleDateFormat yearAndDayDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public  static  final SimpleDateFormat hourAndMinuteFormat = new SimpleDateFormat("HH:mm:ss");



    public static void sys(String value){
        System.out.println(value);
    }







}

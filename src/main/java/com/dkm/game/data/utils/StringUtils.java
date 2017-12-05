package com.dkm.game.data.utils;

public class StringUtils {


    public static boolean isEmpty(String value){

        if(value == null || value == " "){
            return true;
        }

        return false;
    }
}

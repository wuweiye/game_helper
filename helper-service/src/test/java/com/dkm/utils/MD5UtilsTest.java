package com.dkm.utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MD5UtilsTest {



    @Test
    public void MapToMd5Test(){

        Map<String,String> map = new HashMap<String, String>();

        map.put("one","one");
        map.put("two","two");

        //5b9164ad6f496d9dee12ec7634ce253f
        String md5 = MD5Util.encrypt(map);
        System.out.println(md5);
    }


    @Test
    public void StringsToMd5(){

        String md5 = MD5Util.encrypt("one","two");
        System.out.println(md5);

    }



}

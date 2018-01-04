package com.dkm.utils;


import com.dkm.annotation.timer.Timer;

import java.lang.reflect.Method;

public class TimeUtils {

    public static void getTime(){

        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        System.out.println("className :" + className);


        try {
            Class c  = Class.forName(className);
            Object obj = c.newInstance();
            Method[] methods = c.getDeclaredMethods();
            for(Method method : methods){
                if(method.isAnnotationPresent(Timer.class)){
                    method.setAccessible(true);
                    long start = System.currentTimeMillis();
                    method.invoke(obj);
                    long end = System.currentTimeMillis();
                    System.out.println(method.getName()+" 执行时间" +String.valueOf(end - start));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.dkm.helper.test;

public class Test {




    public static void test(String arga1,String... args){
        System.out.println(arga1);

        System.out.println(args.length);
        for(String s : args) {
            System.out.println(s);
        }
    }




   /* public static void main(String[] args){

        test("test");
    }*/
}

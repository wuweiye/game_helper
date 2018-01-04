package com.dkm.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class Test2Service {

    public static Logger log = Logger.getLogger(Test2Service.class);


    public void test(){
        log.info("test");
    }
}

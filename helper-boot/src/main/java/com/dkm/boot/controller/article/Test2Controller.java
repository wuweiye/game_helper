package com.dkm.boot.controller.article;

import com.dkm.service.Test2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "helper/")
@RestController
public class Test2Controller {


    @Autowired
    Test2Service testService;

    @RequestMapping("test")
    public String test(){


        testService.test();

        return  "success";
    }

}

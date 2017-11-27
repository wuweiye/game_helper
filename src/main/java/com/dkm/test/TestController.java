package com.dkm.test;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {

    private TestService testService;

    @RequestMapping("listener")
    public String listener(){

        testService.onTestEvent("test");

        return "success";
    }
}

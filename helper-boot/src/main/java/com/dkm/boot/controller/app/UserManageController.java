package com.dkm.boot.controller.app;

import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/manage")
public class UserManageController {

    @Autowired
    UserService userService;

    @RequestMapping("login")
    public ResponseEntity<BaseResp> login(@RequestParam(required = false,name = "name")String name){

        BaseResp baseResp = new BaseResp();

        return new ResponseEntity<BaseResp>(baseResp, HttpStatus.OK);
    }

    @RequestMapping("register")
    public ResponseEntity<BaseResp> register(@RequestParam("name")String name, @RequestParam("pwd")String pwd){



        BaseResp baseResp = new BaseResp();

        return new ResponseEntity<BaseResp>(baseResp, HttpStatus.OK);
    }


    @RequestMapping("update")
    public ResponseEntity<BaseResp> update(){

        BaseResp baseResp = new BaseResp();

        return new ResponseEntity<BaseResp>(baseResp, HttpStatus.OK);
    }




}

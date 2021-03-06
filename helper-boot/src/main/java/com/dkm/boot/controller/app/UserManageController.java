package com.dkm.boot.controller.app;

import com.dkm.basic.component.ext.web.BaseController;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.resp.user.LoginResp;
import com.dkm.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/user/manage")
public class UserManageController extends BaseController {

    @Autowired
    UserService userService;

    @RequestMapping("login")
    public ResponseEntity<PageResp<LoginResp>> login(@RequestParam(required = false,name = "name")String name ,
                                          @RequestParam(required = false,name = "pwd")String pwd){

        BaseResp baseResp = new BaseResp();

        PageResp<LoginResp> rep = userService.login(name,pwd,super.request.getRemoteAddr() + ":" + super.request.getRemotePort());

        return new ResponseEntity<PageResp<LoginResp>>(rep, HttpStatus.OK);
    }

    @RequestMapping("register")
    public ResponseEntity<BaseResp> register(@RequestParam("name")String name, @RequestParam("pwd")String pwd){



        BaseResp baseResp = userService.register(name, pwd, super.request.getRemoteAddr() + ":" + super.request.getRemotePort());

        return new ResponseEntity<BaseResp>(baseResp, HttpStatus.OK);
    }


    @RequestMapping("update")
    public ResponseEntity<BaseResp> update(){

        BaseResp baseResp = new BaseResp();

        return new ResponseEntity<BaseResp>(baseResp, HttpStatus.OK);
    }




}

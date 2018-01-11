package com.dkm.boot.controller.user;

import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.model.user.User;
import com.dkm.service.user.UserOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/login")
public class LoginController {

    @Autowired
    UserOneService userOneService;

    public ResponseEntity<String> login(@RequestParam("name")String name, @RequestParam("pwd") String pwd){


        User user = new User();
        user.setPassword(pwd);
        user.setUsername(name);
        user.setSalt("123456");

        userOneService.createUser(user);



        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }
}

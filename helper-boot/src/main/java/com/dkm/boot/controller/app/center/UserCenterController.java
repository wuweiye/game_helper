package com.dkm.boot.controller.app.center;

import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.resp.user.LoginResp;
import com.dkm.resp.user.UserInfoResp;
import com.dkm.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/center/user/")
public class UserCenterController {

    @Autowired
    UserService userService;



    @RequestMapping("info")
    public ResponseEntity<PageResp<UserInfoResp>> getUserInfo(@RequestParam(name = "userId")String userId){

        Long id = Long.parseLong(userId);

        PageResp<UserInfoResp> rep = userService.getInfo(id);

        return new ResponseEntity<PageResp<UserInfoResp>>(rep, HttpStatus.OK);
    }

}

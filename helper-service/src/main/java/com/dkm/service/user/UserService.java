package com.dkm.service.user;

import com.dkm.base.Constants;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.dao.user.UserDao;
import com.dkm.model.user.UserEntity;
import com.dkm.resp.user.LoginResp;
import com.dkm.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserDao userDao;


    public void save(UserEntity entity){
        userDao.saveAndFlush(entity);
    }



    public void  getInfo(Long userId){

        UserEntity userEntity = userDao.getOne(userId);
    }



    public LoginResp login(String name, String pwd, String ip){

        UserEntity entity =  userDao.findByUserNameAndPassword(name, pwd);
        LoginResp loginResp = new LoginResp();

       /* Map<String,String > map = new HashMap<String, String>();
        map.put("userName",entity.getUserName());
        map.put("key", Constants.wholeDateFormat.format(new Date()));*/

        if(entity == null){

            loginResp.setResultCode(-1);
            loginResp.setResultMessage("登录失败");

        }else {

            Date date = new Date();
            //保存登陆记录
            entity.setLastLoginTime(date);
            entity.setLastLoginId(ip);
            save(entity);

            String time = String.valueOf(date.getTime()/1000);

            loginResp.setResultCode(0);
            loginResp.setTime(time);
            loginResp.setUserId(entity.getId()+"");
            loginResp.setKey(MD5Util.encrypt(time, entity.getId().toString() , "MD5"));


        }

        return  loginResp;

    }

    public BaseResp register(String name, String pwd, String ip){

        UserEntity userEntity = userDao.findByUserName(name);

        if(userEntity != null){
            return new BaseResp(-1, "用户名已存在");
        }

        userEntity = new UserEntity();
        userEntity.setRegIp(ip);
        userEntity.setLastLoginTime(new Date());
        userEntity.setPassword(pwd);
        userEntity.setUserName(name);
        save(userEntity);

        return new BaseResp(0,"register success");
    }



}

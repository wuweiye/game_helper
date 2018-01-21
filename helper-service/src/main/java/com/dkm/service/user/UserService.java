package com.dkm.service.user;

import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.basic.component.ext.web.PageResp;
import com.dkm.dao.user.UserDao;
import com.dkm.model.user.UserEntity;
import com.dkm.resp.user.LoginResp;
import com.dkm.resp.user.UserInfoResp;
import com.dkm.utils.MD5Util;
import com.dkm.utils.redis.StrRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RedisTemplate<String, String> redis;


    public void save(UserEntity entity){
        userDao.saveAndFlush(entity);
    }



    public PageResp<UserInfoResp> getInfo(Long userId){

        PageResp<UserInfoResp> resp = new PageResp<UserInfoResp>();

        List<UserInfoResp> list = new ArrayList<UserInfoResp>();

        UserInfoResp userInfoResp = new UserInfoResp();

        UserEntity userEntity = userDao.getOne(userId);
        if(userEntity != null){

            resp.setTotal(1);
            userInfoResp.setName(userEntity.getUserName());

        }

        list.add(userInfoResp);
        resp.setRows(list);


        return resp;


    }



    public PageResp<LoginResp> login(String name, String pwd, String ip){

        UserEntity entity =  userDao.findByUserNameAndPassword(name, pwd);
        LoginResp loginResp = new LoginResp();

        PageResp<LoginResp> resp = new PageResp<LoginResp>();

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
            String key = MD5Util.encrypt(time, entity.getId().toString() , "MD5");
            loginResp.setKey(key);

            StrRedisUtil.set(redis, key, entity.getId());


        }

        List<LoginResp> list = new ArrayList();
        list.add(loginResp);
        resp.setRows(list);
        resp.setTotal(1l);
        return  resp;

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

package com.dkm.service.user;

import com.dkm.base.Constants;
import com.dkm.basic.component.ext.web.BaseResp;
import com.dkm.dao.user.UserDao;
import com.dkm.model.user.UserEntity;
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


    public BaseResp login(String name,String pwd){

        UserEntity entity =  userDao.findByUserNameAndPassword(name, pwd);
        BaseResp baseResp = null;
        if(entity == null){

            return new BaseResp(-1,"登录失败");
        }


        Map<String,String > map = new HashMap<String, String>();
        map.put("userName",entity.getUserName());
        map.put("key", Constants.wholeDateFormat.format(new Date()));

        





        return  baseResp;

    }



}

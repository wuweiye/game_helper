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


public interface UserService {




    public void save(UserEntity entity);


    public PageResp<UserInfoResp> getInfo(Long userId);


    public PageResp<LoginResp> login(String name, String pwd, String ip);

    public BaseResp register(String name, String pwd, String ip);


    public UserEntity getUser(Long id);


}

package com.dkm.base;


import com.dkm.game.user.dao.UserDao;
import com.dkm.game.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseService {

    @Autowired
    UserDao userDao;



    protected UserEntity getUser(String id){

        return userDao.getOne(id);
    }
}

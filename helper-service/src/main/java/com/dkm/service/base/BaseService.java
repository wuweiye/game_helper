package com.dkm.service.base;



import com.dkm.dao.user.UserDao;
import com.dkm.model.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseService {

    @Autowired
    UserDao userDao;

    protected UserEntity getUser(Long id){

        return userDao.getOne(id);
    }
}

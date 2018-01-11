package com.dkm.service.user;

import com.dkm.dao.user.UserOneDao;
import com.dkm.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOneServiceImpl implements UserOneService{

    @Autowired
    private UserOneDao userDao;
    @Autowired
    private PasswordHelper passwordHelper;

    public User createUser(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        return userDao.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.save(user);
    }

    @Override
    public void deleteUser(Long userId) {

        userDao.delete(userId);
    }

    public void changePassword(Long userId, String newPassword) {
        User user =userDao.findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.save(user);
    }

    @Override
    public User findOne(Long userId) {
        return userDao.findOne(userId);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findByUsername(String username){

      return userDao.findByUsername(username);
    }
}

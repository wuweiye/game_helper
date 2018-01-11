package com.dkm.service.user;

import com.dkm.model.user.User;

import java.util.List;

public interface UserOneService {


    public User createUser(User user);
    public User updateUser(User user);
    public void deleteUser(Long userId);

    public void changePassword(Long userId, String newPassword);

    User findOne(Long userId);
    List<User> findAll();
    public User findByUsername(String username);



}

package com.dkm.dao.user;


import com.dkm.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, Long > {

    UserEntity findByUserNameAndPassword(String name, String pwd);

}

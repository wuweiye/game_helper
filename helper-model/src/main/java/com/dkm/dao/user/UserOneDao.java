package com.dkm.dao.user;


import com.dkm.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOneDao extends JpaRepository<User, Long > {


    User findByUsername(String name);
}

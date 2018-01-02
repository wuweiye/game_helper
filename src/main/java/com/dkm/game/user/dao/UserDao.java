package com.dkm.game.user.dao;

import com.dkm.game.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, Long > {
}

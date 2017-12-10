package com.dkm.game.message.dao;

import com.dkm.game.message.entity.GameReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameReplyRepository extends JpaRepository<GameReplyEntity, String> ,JpaSpecificationExecutor<GameReplyEntity> {

}

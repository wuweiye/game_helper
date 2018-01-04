package com.dkm.dao.message;


import com.dkm.model.message.GameReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameReplyRepository extends JpaRepository<GameReplyEntity, String> ,JpaSpecificationExecutor<GameReplyEntity> {

}

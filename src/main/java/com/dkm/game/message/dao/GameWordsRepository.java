package com.dkm.game.message.dao;

import com.dkm.game.message.entity.GameWordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameWordsRepository extends JpaRepository<GameWordsEntity, String> ,JpaSpecificationExecutor<GameWordsEntity> {

}

package com.dkm.game.data.dao;

import com.dkm.game.data.entity.GameWordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameWordsRepository extends JpaRepository<GameWordsEntity, String> ,JpaSpecificationExecutor<GameWordsEntity> {

}

package com.dkm.game.data.dao;

import com.dkm.game.data.entity.GameDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameDataRepository extends JpaRepository<GameDataEntity, String> ,JpaSpecificationExecutor<GameDataEntity> {

    

}

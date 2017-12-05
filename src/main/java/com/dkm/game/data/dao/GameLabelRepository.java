package com.dkm.game.data.dao;

import com.dkm.game.data.entity.GameLabelEntity;
import com.dkm.game.data.entity.GameLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameLabelRepository extends JpaRepository<GameLabelEntity, String> ,JpaSpecificationExecutor<GameLabelEntity> {


    public GameLabelEntity findByGidAndLid(String gid,String lid);
}

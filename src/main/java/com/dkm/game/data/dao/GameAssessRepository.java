package com.dkm.game.data.dao;

import com.dkm.game.data.entity.GameAssessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameAssessRepository extends JpaRepository<GameAssessEntity, Long> ,JpaSpecificationExecutor<GameAssessEntity> {


    GameAssessEntity findByGid(Long gid);
}

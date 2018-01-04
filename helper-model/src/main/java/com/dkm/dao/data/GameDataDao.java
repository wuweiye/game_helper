package com.dkm.dao.data;


import com.dkm.model.data.GameDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameDataDao extends JpaRepository<GameDataEntity, Long> ,JpaSpecificationExecutor<GameDataEntity> {


    GameDataEntity findByGid(Long gid);
}

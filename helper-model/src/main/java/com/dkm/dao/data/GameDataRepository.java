package com.dkm.dao.data;


import com.dkm.model.data.GameDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameDataRepository extends JpaRepository<GameDataEntity, Long> ,JpaSpecificationExecutor<GameDataEntity> {


    GameDataEntity findByGid(Long gid);
}

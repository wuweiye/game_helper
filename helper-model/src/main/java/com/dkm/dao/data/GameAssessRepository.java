package com.dkm.dao.data;


import com.dkm.model.data.GameAssessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameAssessRepository extends JpaRepository<GameAssessEntity, Long> ,JpaSpecificationExecutor<GameAssessEntity> {


    GameAssessEntity findByGid(Long gid);
}

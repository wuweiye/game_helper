package com.dkm.dao.data;


import com.dkm.model.data.GameItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameItemsDao extends JpaRepository<GameItemsEntity, Long> ,JpaSpecificationExecutor<GameItemsEntity> {


}

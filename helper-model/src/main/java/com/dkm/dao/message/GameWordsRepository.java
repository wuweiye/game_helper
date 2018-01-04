package com.dkm.dao.message;

import com.dkm.model.message.GameWordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameWordsRepository extends JpaRepository<GameWordsEntity, String> ,JpaSpecificationExecutor<GameWordsEntity> {

}

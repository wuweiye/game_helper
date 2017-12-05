package com.dkm.game.data.dao;

import com.dkm.game.data.entity.GameArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameArticleRepository extends JpaRepository<GameArticleEntity, String> ,JpaSpecificationExecutor<GameArticleEntity> {


}

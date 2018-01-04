package com.dkm.dao.game;

import com.dkm.model.game.GameArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameArticleDao extends JpaRepository<GameArticleEntity, Long> ,JpaSpecificationExecutor<GameArticleEntity> {


}

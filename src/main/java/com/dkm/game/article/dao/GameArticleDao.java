package com.dkm.game.article.dao;

import com.dkm.game.article.entity.GameArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameArticleDao extends JpaRepository<GameArticleEntity, String> ,JpaSpecificationExecutor<GameArticleEntity> {


}

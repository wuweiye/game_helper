package com.dkm.game.data.dao;

import com.dkm.game.data.entity.GameLibrary;
import com.dkm.game.data.entity.LabelLibraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LabelLibraryRepository extends JpaRepository<LabelLibraryEntity, String> ,JpaSpecificationExecutor<LabelLibraryEntity> {
    public LabelLibraryEntity findByName(String name);


    /*public GameLibrary findByName(String name);*/
}

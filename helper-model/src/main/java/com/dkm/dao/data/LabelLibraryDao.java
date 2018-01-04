package com.dkm.dao.data;

import com.dkm.model.data.LabelLibraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LabelLibraryDao extends JpaRepository<LabelLibraryEntity, Long> ,JpaSpecificationExecutor<LabelLibraryEntity> {
    public LabelLibraryEntity findByName(String name);


    /*public GameLibrary findByName(String name);*/
}

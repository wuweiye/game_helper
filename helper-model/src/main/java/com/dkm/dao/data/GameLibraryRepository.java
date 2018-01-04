package com.dkm.dao.data;


import com.dkm.model.data.GameLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GameLibraryRepository extends JpaRepository<GameLibrary, Long> ,JpaSpecificationExecutor<GameLibrary> {


    public GameLibrary findByName(String name);


    public List<GameLibrary> findByStatus(String name);
}

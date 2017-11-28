package com.dkm.game.data.dao;

import com.dkm.game.data.entity.GameLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GameLibraryRepository extends JpaRepository<GameLibrary, String> ,JpaSpecificationExecutor<GameLibrary> {


    public List<GameLibrary> findByName(String name);
}

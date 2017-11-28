package com.dkm.game.data.service;

import com.dkm.game.data.dao.GameLibraryRepository;
import com.dkm.game.data.entity.GameLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataManageService {

    @Autowired
    GameLibraryRepository gameLibraryRepository;

    public void save(GameLibrary gameLibrary){

        gameLibraryRepository.saveAndFlush(gameLibrary);
    }


    public  void update(GameLibrary gameLibrary){
        GameLibrary gameLibrary1 = gameLibraryRepository.findOne(gameLibrary.getGId());
    }

    public void print(){

        List<GameLibrary> gameLibraries = gameLibraryRepository.findByName("test");
        System.out.println(gameLibraries.size());

    }

}

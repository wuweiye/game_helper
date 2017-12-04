package com.dkm.game.data.service;

import com.dkm.game.data.dao.GameLabelRepository;
import com.dkm.game.data.dao.LabelLibraryRepository;
import com.dkm.game.data.entity.GameLabelEntity;
import com.dkm.game.data.entity.LabelLibraryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelLibraryService {

    @Autowired
    GameLabelRepository gameLabelRepository;

    @Autowired
    LabelLibraryRepository labelLibraryRepository;


    public void save(){
        try {
            GameLabelEntity gameLabelEntity = new GameLabelEntity();
            gameLabelRepository.save(gameLabelEntity);
        } catch (Exception e) {
            System.out.println("11111");
        }

        try {
            LabelLibraryEntity labelLibraryEntity = new LabelLibraryEntity();
            labelLibraryRepository.save(labelLibraryEntity);
        } catch (Exception e) {
            System.out.println("22222");
        }

    }
}

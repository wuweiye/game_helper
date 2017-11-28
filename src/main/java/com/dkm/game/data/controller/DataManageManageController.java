package com.dkm.game.data.controller;

import com.dkm.game.data.entity.GameLibrary;
import com.dkm.game.data.service.DataManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RequestMapping(value = "/data/manage", produces = "application/json;charset=utf-8")
@RestController
public class DataManageManageController {

    @Autowired
    DataManageService dataManageService;



    @RequestMapping("/save")
    public void save(){
        GameLibrary gameLibrary = new GameLibrary();
        dataManageService.save(gameLibrary);
    }

    @RequestMapping("/print")
    public void print(){
        dataManageService.print();
    }

}

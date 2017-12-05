package com.dkm.game.test.service;

import com.dkm.base.Constants;
import com.dkm.game.data.entity.GameLibrary;
import com.dkm.game.data.service.DataManageService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataManageServiceTest {

    @Autowired
    DataManageService dataManageService;

    @Before
    public void setUp() throws Exception {
        Constants.sys("Test 开始");
    }

    @After
    public void tearDown() throws Exception {
        Constants.sys("Test Over");
    }

    @Test
    public void save() throws Exception {

        GameLibrary gameLibrary = new GameLibrary();
        gameLibrary.setName("测试save 2");
        dataManageService.save(gameLibrary);

    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void gameLibraryQuery() throws Exception {
    }

    @Test
    public void addGameLibrary() throws Exception {
    }


}

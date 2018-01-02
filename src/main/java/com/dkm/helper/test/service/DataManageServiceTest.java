package com.dkm.helper.test.service;

import com.dkm.base.Constants;
import com.dkm.game.data.entity.GameLibrary;
import com.dkm.game.data.params.GameLibraryParams;
import com.dkm.game.data.req.GameLibraryReq;
import com.dkm.game.data.service.DataManageService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataManageServiceTest {

    @Autowired
    DataManageService dataManageService;

    public static Long start ,end;



    @Before
    public void setUp() throws Exception {
        start = System.currentTimeMillis();
        Constants.sys("\n\n\nTest 开始" + Constants.wholeDateFormat.format(new Date()));

    }

    @After
    public void tearDown() throws Exception {
        end = System.currentTimeMillis() - start;
        Constants.sys("Test 结束" + Constants.wholeDateFormat.format(new Date()));
        Constants.sys("此次运行一共用时"+ end +"毫秒\n\n\n");
    }

    @Test
    public void save() throws Exception {

        GameLibrary gameLibrary = new GameLibrary();
        gameLibrary.setName("测试save 2");
        dataManageService.save(gameLibrary);

    }


    @Test
    public void gameLibraryQuery() throws Exception {
    }

    @Test
    public void addGameLibrary() throws Exception {

        GameLibraryParams req = new GameLibraryParams();
        req.setName("123456");
        req.setStatus("true");

        dataManageService.addGameLibrary(req,null);

    }


}

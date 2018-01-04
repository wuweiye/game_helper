package com.dkm.myenum;


import com.dkm.dao.data.GameLibraryDao;
import com.dkm.model.data.GameLibrary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TwoTest {

    @Autowired
    GameLibraryDao gameLibraryDao;


    @Test
    public void test(){

        GameLibrary gameLibrary = new GameLibrary();
        gameLibrary.setName("12354343");
        gameLibraryDao.saveAndFlush(gameLibrary);

    }

}

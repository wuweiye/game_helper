package com.dkm;

import com.dkm.base.Constants;
import com.dkm.service.data.DataManageService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TestModel {

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
    public void test() throws Exception {



    }




}

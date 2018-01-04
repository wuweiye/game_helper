package com.dkm.myenum;


import com.dkm.base.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/*@RunWith(SpringJUnit4ClassRunner.class)*/
@RunWith(Parameterized.class)
public class EnumTest {

    private String name;
    private boolean result;

    /**
     * 该构造方法的参数与下面@Parameters注解的方法中的Object数组中值的顺序对应
     * @param name
     * @param result
     */
    public EnumTest(String name, boolean result) {
        super();
        this.name = name;
        this.result = result;
    }

    @Test
    public void test() {

        Constants.sys(GameEnum.Status.INVALID.getValue());


    }



    @Parameterized.Parameters
    public static Collection<?> data(){
        // Object 数组中值的顺序注意要和上面的构造方法ParameterTest的参数对应
        return Arrays.asList(new Object[][]{
                {"小明2", true},
                {"坏", false},
                {"莉莉", false},
        });
    }
}

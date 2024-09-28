package com.example.Main;
import org.junit.Assert;
import org.junit.Test;

import com.example.checkAns;
public class checkAnsTest {
    @Test
    public void test1() { //测试加法
        Assert.assertEquals("加法结果有误","3",checkAns.calculate("1","2","＋"));
    }

    @Test
    public void test2() { //测试除法
        Assert.assertEquals("除法结果有误","2",checkAns.calculate("4","2","÷"));
    }

    @Test
    public void test3() { //测试减法
        Assert.assertEquals("减法结果有误","3",checkAns.calculate("5","2","－"));
    }

    @Test
    public void test4() { //测试乘法
        Assert.assertEquals("乘法结果有误","2",checkAns.calculate("1","2","×"));
    }

    @Test
    public void test5() { //测试分数化简
        Assert.assertEquals("分数化简结果有误","2'1/2",checkAns.greatFraction(2, 2, 4));
    }
}

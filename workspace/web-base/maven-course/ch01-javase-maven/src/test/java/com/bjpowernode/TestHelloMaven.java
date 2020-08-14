package com.bjpowernode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author Panda
 * @create 2020/8/11 15:57
 */
public class TestHelloMaven {

    @Test
    public void testAdd(){
        System.out.println("========Test——Add========");
        HelloMaven hello = new HelloMaven();
        int res = hello.add(10,20);
        //判断结果是否正确
        Assert.assertEquals(30,res);
    }
}

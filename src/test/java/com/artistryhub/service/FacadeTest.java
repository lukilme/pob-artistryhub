package com.artistryhub.service;


import org.junit.Assert;
import org.junit.Test;

public class FacadeTest {

    @Test
    public void somaTest(){
        Facade facade = new Facade();
        Assert.assertEquals(facade.soma(2,2),4);
    }
    
    @Test
    public void multiplicarTest(){
        Facade facade = new Facade();
        Assert.assertEquals(facade.multiplicar(2,5),10);
        Assert.assertEquals(facade.multiplicar(6,5),30);
        Assert.assertEquals(facade.multiplicar(4,4),16);
    }
}
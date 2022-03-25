package com.hfad.appfinal;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfirmedPlayerModelTest {

    //create confirmed player model
    ConfirmedPlayerModel pl=new ConfirmedPlayerModel("mark");


    //test getters and setters
    @Test
    public void getName() {

        assertEquals(pl.getName(),"mark");

    }

    //test constructor
    @Test
    public void testConst() {

        assertNotNull(pl);

    }

}
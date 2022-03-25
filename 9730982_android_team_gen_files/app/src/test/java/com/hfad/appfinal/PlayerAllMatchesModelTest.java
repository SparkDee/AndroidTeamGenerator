package com.hfad.appfinal;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerAllMatchesModelTest {

    //create player all matches model object
    PlayerAllMatchesModel am=new PlayerAllMatchesModel("01-01-2001","09:00:00","ulidia");


    //test getters and setters
    @Test
    public void getDate() {
        assertEquals("01-01-2001",am.getDate());
    }

    @Test
    public void getTime() {
        assertEquals("09:00:00",am.getTime());
    }

    @Test
    public void getVenue() {
        assertEquals("ulidia",am.getVenue());
    }

    //test const
    @Test
    public void testConst() {
        assertNotNull(am);
    }


}
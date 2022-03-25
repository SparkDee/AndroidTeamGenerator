package com.hfad.appfinal;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatchModelTest {

    //match object created
    MatchModel m= new MatchModel("1","2","3","12-01-01","09:00:00","ulidia");


    //test cases to test getters and setters

    @Test
    public void getMatchId() {

        assertEquals("1",m.getMatchId());
    }



    @Test
    public void getAdminId() {
        assertEquals("2",m.getAdminId());
    }



    @Test
    public void getTeamId() {
        assertEquals("3",m.getTeamId());
    }



    @Test
    public void getDate() {
        assertEquals("12-01-01",m.getDate());
    }



    @Test
    public void getTime() {
        assertEquals("09:00:00",m.getTime());
    }



    @Test
    public void getVenue() {
        assertEquals("ulidia",m.getVenue());
    }


    //test const
    @Test
    public void testConst(){
        assertNotNull(m);
    }


}
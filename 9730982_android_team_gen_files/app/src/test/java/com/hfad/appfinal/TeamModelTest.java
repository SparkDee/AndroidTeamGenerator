package com.hfad.appfinal;

import org.junit.Test;

import static org.junit.Assert.*;

public class TeamModelTest {

    TeamModel tm=new TeamModel("1","2","monday");

    @Test
    public void getTeamId() {
        assertEquals("1",tm.getTeamId());
    }

    @Test
    public void getAdminId() {
        assertEquals("2",tm.getAdminId());
    }

    @Test
    public void getTeamName() {
        assertEquals("monday",tm.getTeamName());
    }


    //test constructor
    @Test
    public void testConst() {
        assertNotNull(tm);
    }

}
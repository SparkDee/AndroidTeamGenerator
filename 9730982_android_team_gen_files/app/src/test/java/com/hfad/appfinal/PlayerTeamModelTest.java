package com.hfad.appfinal;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTeamModelTest {
    PlayerTeamModel pt= new PlayerTeamModel("monday","2","1");

    @Test
    public void getTeamId() {
        assertEquals("2",pt.getTeamId());
    }

    @Test
    public void getPlayerId() {
        assertEquals("1",pt.getPlayerId());
    }

    @Test
    public void getTeamName() {
        assertEquals("monday",pt.getTeamName());
    }
}
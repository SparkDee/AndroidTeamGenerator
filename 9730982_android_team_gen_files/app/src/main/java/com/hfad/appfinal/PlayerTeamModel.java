package com.hfad.appfinal;

public class PlayerTeamModel {

    private String teamId;
    private String playerId;
    private String teamName;


    public PlayerTeamModel(String teamName, String teamId, String playerId) {
        this.teamName = teamName;
        this.teamId = teamId;
        this.playerId = playerId;




    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}

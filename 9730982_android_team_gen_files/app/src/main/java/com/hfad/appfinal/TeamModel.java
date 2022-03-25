package com.hfad.appfinal;

import android.content.Context;

public class TeamModel {

    private String teamId;
    private String adminId;
    private String teamName;

    public TeamModel(String teamId, String adminId, String teamName) {
        this.teamId = teamId;
        this.adminId = adminId;
        this.teamName = teamName;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

}

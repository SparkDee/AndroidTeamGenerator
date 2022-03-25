package com.hfad.appfinal;

public class MatchModel {


    //vars
    private String matchId;
    private String adminId;
    private String teamId;
    private String date;
    private String time;
    private String venue;


    //constructor
    public MatchModel(String matchId, String adminId, String teamId, String date, String time, String venue) {
        this.matchId = matchId;
        this.adminId = adminId;
        this.teamId = teamId;
        this.date = date;
        this.time = time;
        this.venue = venue;
    }


    //getters and setters
    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}

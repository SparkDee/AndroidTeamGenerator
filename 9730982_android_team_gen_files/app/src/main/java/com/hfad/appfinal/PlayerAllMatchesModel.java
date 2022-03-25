package com.hfad.appfinal;

public class PlayerAllMatchesModel {

    private String date;
    private String time;
    private String venue;

    public PlayerAllMatchesModel(String date, String time, String venue) {
        this.date = date;
        this.time = time;
        this.venue = venue;
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

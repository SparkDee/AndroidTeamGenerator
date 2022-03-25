package com.hfad.appfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    //shared pref file name
    private static final String PREF_NAME = "LOGIN";
    //shared pref mode
    private static final String LOGIN = "IS_LOGIN";

    //shared pref keys for creator
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";
    public static final String ADMIN_ID = "ADMIN_ID";
    //public static final String TEAM_ID="TEAM_ID";

    //shared pref keys for player
    public static final String PLAYER_NAME = "PLAYER_NAME";
    public static final String PLAYER_EMAIL = "PLAYER_EMAIL";
    public static final String PLAYER_ID = "PLAYER_ID";
    //public static final String PLAYER_TEAM_ID="PLAYER_TEAM_ID";


    //constructor
    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    //create creator session
    public void createSession(String name, String email, String admin_id) {
        editor.putBoolean(LOGIN, true);
        editor.putString(NAME, name);
        editor.putString(EMAIL, email);
        editor.putString(ADMIN_ID, admin_id);
        //editor.putString(TEAM_ID,team_id);
        //editor.commit();
        editor.apply();

    }

    //create player session
    public void createPlayerSession(String name, String email, String player_id) {
        editor.putBoolean(LOGIN, true);
        editor.putString(PLAYER_NAME, name);
        editor.putString(PLAYER_EMAIL, email);
        editor.putString(PLAYER_ID, player_id);
        //editor.putString(PLAYER_TEAM_ID,player_team_id);
        //editor.commit();
        editor.apply();

    }


    public boolean isLoggin() {
        return sharedPreferences.getBoolean(LOGIN, false);

    }

    //check if creator logged in
    public void checkLogin() {

        if (!this.isLoggin()) {
            Intent i = new Intent(context, AdminLogin.class);

            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

        }
    }

    //check if player logged in
    public void checkPlayerLogin() {

        if (!this.isLoggin()) {
            Intent i = new Intent(context, PlayerLogin.class);

            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

        }
    }

    //hashmap to store creator details
    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(ADMIN_ID, sharedPreferences.getString(ADMIN_ID, null));
        //user.put(TEAM_ID,sharedPreferences.getString(TEAM_ID,null));
        return user;
    }

    //hashmap to store player details
    public HashMap<String, String> getPlayerDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(PLAYER_NAME, sharedPreferences.getString(PLAYER_NAME, null));
        user.put(PLAYER_EMAIL, sharedPreferences.getString(PLAYER_EMAIL, null));
        user.put(PLAYER_ID, sharedPreferences.getString(PLAYER_ID, null));
        //user.put(PLAYER_TEAM_ID,sharedPreferences.getString(PLAYER_TEAM_ID,null));
        return user;
    }


    //logout function
    public void logout() {
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }


}

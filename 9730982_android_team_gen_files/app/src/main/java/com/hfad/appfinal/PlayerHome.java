package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;

public class PlayerHome extends AppCompatActivity {


    private Button btn_player_details,btn_player_team,btn_all_match_player_complete;

    private TextView player_logged_in_as;

    private ImageButton btnLogout,btnHome;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_home);

        sessionManager=new SessionManager(this);
        sessionManager.checkPlayerLogin();




        btn_player_details=findViewById(R.id.btn_details_player);
        btn_player_team=findViewById(R.id.btn_teams_player);
        btn_all_match_player_complete=findViewById(R.id.btn_all_match_player_complete);

        player_logged_in_as=findViewById(R.id.player_logged_in_as);

        HashMap<String,String> user=sessionManager.getPlayerDetail();

        String mName=user.get(sessionManager.PLAYER_NAME);
        player_logged_in_as.setText(mName);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerHome.this,PlayerHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });


        btn_all_match_player_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerHome.this,PlayerViewAllMatches.class));
            }
        });


        btn_player_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerHome.this,PlayerTeamOptions.class));
            }
        });




        btn_player_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerHome.this,PlayerDetails.class));
            }
        });

        /*btn_player_matches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerHome.this,PlayerMatches.class));
            }
        });*/



    }
}
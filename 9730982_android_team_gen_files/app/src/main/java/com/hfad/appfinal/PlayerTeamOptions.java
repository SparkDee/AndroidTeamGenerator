package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PlayerTeamOptions extends AppCompatActivity {

    private Button btnJoinTeam,btnViewTeams;
    SessionManager sessionManager;
    private ImageButton btnLogout,btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_team_options);

        sessionManager = new SessionManager(this);
        sessionManager.checkPlayerLogin();


        btnJoinTeam=findViewById(R.id.btn_team_join_player);
        btnViewTeams=findViewById(R.id.btn_view_team_player);


        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerTeamOptions.this,PlayerHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });

        btnViewTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerTeamOptions.this, PlayerViewTeams.class));

            }
        });


        btnJoinTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerTeamOptions.this, PlayerJoinTeam.class));
            }
        });



    }
}
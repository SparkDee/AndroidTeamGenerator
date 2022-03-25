package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ActivityTeamDetailsPlayer extends AppCompatActivity {


    //set vars
    private TextView textViewName, textViewId;
    private Button btnAvailableMatches, btnConfirmedMatches;
    private ImageButton btnLogout,btnHome;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details_player);

        //create session manager and get user detail
        sessionManager = new SessionManager(this);
        sessionManager.checkPlayerLogin();


        //xml
        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);
        textViewName = findViewById(R.id.detail_name);
        textViewId = findViewById(R.id.detail_id);
        btnAvailableMatches = findViewById(R.id.btnAvailableMatchesPlayer);
        btnConfirmedMatches = findViewById(R.id.btnConfirmedMatchesPlayer);



        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityTeamDetailsPlayer.this,PlayerHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });


        btnAvailableMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTeamDetailsPlayer.this, PlayerMatches.class);
                String teamId = textViewId.getText().toString();
                intent.putExtra("team_id", teamId);
                String teamName=textViewName.getText().toString();
                intent.putExtra("team_name",teamName);
                startActivity(intent);
            }
        });

        btnConfirmedMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivityTeamDetailsPlayer.this, PlayerViewConfirmedMatches.class);
                String teamId = textViewId.getText().toString();
                intent.putExtra("team_id", teamId);
                String teamName=textViewName.getText().toString();
                intent.putExtra("team_name",teamName);

                startActivity(intent);


            }
        });

        //get team name data
        String name = getIntent().getExtras().getString("team_name");
        String id = getIntent().getExtras().getString("team_id");
        textViewName.setText(name);
        textViewId.setText(id);


    }
}
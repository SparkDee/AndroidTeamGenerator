package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ActivityTeamDetails extends AppCompatActivity {


    //set vars
    private TextView textViewName,textViewId;

    private Button btnCreateMatch,btnViewMatches;

    private ImageButton btnLogout,btnHome;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        //create session manager and get user detail
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        //xml
        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);
        textViewName=findViewById(R.id.detail_name);
        textViewId=findViewById(R.id.detail_id);
        btnCreateMatch=findViewById(R.id.btnCreateMatchTeamDetails);
        btnViewMatches=findViewById(R.id.btnViewMatchesTeamDetails);


        //set button onclick listeners
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityTeamDetails.this,AdminHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });


        btnCreateMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //startActivity(new Intent(ActivityTeamDetails.this,AdminCreateMatch.class));
                Intent intent=new Intent(ActivityTeamDetails.this,AdminCreateMatch.class);
                String castId=textViewId.getText().toString();
                intent.putExtra("cast_id",castId);

                startActivity(intent);

            }
        });

        btnViewMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (ActivityTeamDetails.this,AdminViewMatches.class);
                String teamId=textViewId.getText().toString();
                intent.putExtra("team_id",teamId);
                String name=textViewName.getText().toString();
                intent.putExtra("team_name",name);
                startActivity(intent);
            }
        });


        //get team name data
        String name=getIntent().getExtras().getString("team_name");
        String id=getIntent().getExtras().getString("team_id");
        textViewName.setText(name);
        textViewId.setText(id);






    }
}
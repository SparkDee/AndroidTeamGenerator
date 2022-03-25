package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminTeamOptions extends AppCompatActivity {

    private Button btnCreateTeam,btnViewTeam;
    SessionManager sessionManager;
    private ImageButton btnLogout,btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_team_options);

        sessionManager=new SessionManager(this);
        //check creator login
        sessionManager.checkLogin();


        btnCreateTeam=findViewById(R.id.btn_team_create_team);
        //btnDeleteTeam=findViewById(R.id.btn_team_delete_team);
        btnViewTeam=findViewById(R.id.btn_team_view_teams);
        //btnUpdateTeam=findViewById(R.id.btn_team_update_team);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminTeamOptions.this,AdminHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });

        btnCreateTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AdminTeamOptions.this, AdminCreateTeam.class));

            }
        });

        /*btnDeleteTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminTeamOptions.this, AdminDeleteTeam.class));
            }
        });*/

        /*btnUpdateTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminTeamOptions.this, AdminUpdateTeam.class));
            }
        });*/

        btnViewTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminTeamOptions.this, AdminViewTeam.class));
            }
        });













    }
}
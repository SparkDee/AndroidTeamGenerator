package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;

public class AdminHome extends AppCompatActivity {

    private Button btnDetails,btnTeamOptions;
    private ImageButton btnLogout,btnHome;
    private TextView adminLoggedInAs;

    SessionManager sessionManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);



        sessionManager=new SessionManager(this);
        sessionManager.checkLogin();

        //btnLogout=findViewById(R.id.btn_admin_home_logout);
        //btnMatch=findViewById(R.id.btn_match_options_admin);
        btnDetails=findViewById(R.id.btn_admin_details);
        btnTeamOptions=findViewById(R.id.btn_admin_team_options);


        adminLoggedInAs=findViewById(R.id.admin_logged_in_as);

        HashMap<String,String>user=sessionManager.getUserDetail();
        String mName=user.get(sessionManager.NAME);
        String mUserID=user.get(sessionManager.ADMIN_ID);


        adminLoggedInAs.setText(mName);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);




        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this,AdminHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });


        /*btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sessionManager.logout();
            }
        });*/



        /*btnMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AdminHome.this,AdminMatchOptions.class));

            }
        });*/


        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this,AdminDetails.class));
            }
        });

        btnTeamOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, AdminTeamOptions.class));
            }
        });

    }
}
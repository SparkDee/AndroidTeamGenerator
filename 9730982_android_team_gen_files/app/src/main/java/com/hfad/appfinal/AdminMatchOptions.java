package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminMatchOptions extends AppCompatActivity {

    private Button btnCreateMatch, btnDeleteMatch,btnViewMatches,btnUpdateMatch;
    SessionManager sessionManager;
    private ImageButton btnLogout,btnHome;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_match_options);


        sessionManager=new SessionManager(this);
        //check creator login
        sessionManager.checkLogin();


        btnCreateMatch=findViewById(R.id.btn_create_match);
        btnDeleteMatch=findViewById(R.id.btn_delete_match);
        btnViewMatches=findViewById(R.id.btn_view_matches);
        btnUpdateMatch=findViewById(R.id.btn_update_match);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);




        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMatchOptions.this,AdminHome.class));
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
                startActivity(new Intent(AdminMatchOptions.this, AdminCreateMatch.class));
            }
        });

        btnDeleteMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMatchOptions.this, AdminDeleteMatch.class));
            }
        });

        btnViewMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMatchOptions.this,AdminViewMatches.class));
            }
        });


        btnUpdateMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMatchOptions.this, AdminUpdateMatch.class));
            }
        });







    }
}
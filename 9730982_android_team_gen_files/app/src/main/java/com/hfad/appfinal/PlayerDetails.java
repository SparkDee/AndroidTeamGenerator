package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PlayerDetails extends AppCompatActivity {

    private Button btnCurrent,btnDelete,btnUpdate;
    private ImageButton btnLogout,btnHome;
    SessionManager sessionManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);

        sessionManager=new SessionManager(this);
        sessionManager.checkPlayerLogin(); //checkLogin();

        btnCurrent=findViewById(R.id.btn_player_current_details);
        btnDelete=findViewById(R.id.btn_player_delete_details);
        btnUpdate=findViewById(R.id.btn_player_update_details);


        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerDetails.this,PlayerHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });



        btnCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerDetails.this,PlayerCurrentDetails.class));

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerDetails.this,PlayerDeleteDetails.class));

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerDetails.this,PlayerUpdateDetails.class));

            }
        });
    }
}
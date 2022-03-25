package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PlayerLand extends AppCompatActivity {


    private ImageButton playerRegisterNew;
    private ImageButton playerLogin;

    private ImageButton btnLogout,btnHome;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_land);

        sessionManager = new SessionManager(this);



        playerRegisterNew=findViewById(R.id.player_register_button);
        playerLogin=findViewById(R.id.player_login_button);


        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerLand.this,PlayerLand.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });

        playerRegisterNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerLand.this,PlayerCreate.class));
            }
        });

        playerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerLand.this,PlayerLogin.class));
            }
        });


    }
}
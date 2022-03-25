package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AdminLand extends AppCompatActivity {

    private ImageButton admin_register_new;
    private ImageButton admin_login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_land);

        admin_register_new=findViewById(R.id.admin_register_button);
        admin_login=findViewById(R.id.admin_login_button);


        admin_register_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLand.this,AdminCreate.class));
            }
        });


        admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLand.this,AdminLogin.class));
            }
        });




    }
}
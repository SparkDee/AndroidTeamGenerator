package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class StartPage extends AppCompatActivity {

    private ImageButton btnPool,btnFooty,btnBasketBall;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        btnBasketBall=findViewById(R.id.choose_basketball_button);
        btnFooty=findViewById(R.id.choose_football_button);
        btnPool=findViewById(R.id.choose_pool_button);


        btnPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartPage.this,MainActivity.class));
            }
        });

        btnFooty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartPage.this,MainActivity.class));

            }
        });

        btnBasketBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartPage.this,MainActivity.class));

            }
        });


    }
}
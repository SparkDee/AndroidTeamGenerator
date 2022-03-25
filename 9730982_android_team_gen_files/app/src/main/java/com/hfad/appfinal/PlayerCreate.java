package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PlayerCreate extends AppCompatActivity {

    private EditText textName,textEmail, textPassword,textTeam;
    private Button buttonCreatePlayer;
    private ImageButton btnLogout,btnHome;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_create);
        sessionManager = new SessionManager(this);

        //ref ids from XML
        textName=findViewById(R.id.player_name_create);
        textEmail=findViewById(R.id.player_email_create);
        textPassword=findViewById(R.id.player_password_create);
        //textTeam=findViewById(R.id.player_team_create);

        //button
        buttonCreatePlayer=findViewById(R.id.btn_player_insert);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);




        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerCreate.this,PlayerCreate.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });


        buttonCreatePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

    }



    private void insertData() {
        //vars to get data from inputs
        String name=textName.getText().toString();
        String email=textEmail.getText().toString();
        String password=textPassword.getText().toString();
        //String team=textTeam.getText().toString();

        if (name.isEmpty()) {
            textName.setError("Enter Name");
        } else if (email.isEmpty()) {
            textEmail.setError("Enter Email");
        } else if (password.isEmpty()) {
            textEmail.setError("Enter Password");
        }else{
            //set insert URL
            String insert_url="http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_insert_data_player.php";
            //connect to DB using String Request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, insert_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        if (success.equals("1")) {
                            Toast.makeText(PlayerCreate.this, "Player Inserted", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(PlayerCreate.this,PlayerLogin.class);
                            startActivity(intent);


                        }else{

                            Toast.makeText(PlayerCreate.this, "User Already Registered", Toast.LENGTH_LONG).show();
                            }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //if error
                            Toast.makeText(PlayerCreate.this, "Error Inserting Data "+error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                // params to send to db



                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    params.put("name", name);
                    params.put("email",email);
                    params.put("password", password);



                    return params;
                }
            };

            //request queue to use volley, communicate to backend
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }
    }

}







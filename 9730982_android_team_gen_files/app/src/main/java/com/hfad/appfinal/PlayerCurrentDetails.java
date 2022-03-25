package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PlayerCurrentDetails extends AppCompatActivity {

    private TextView playerNameTxt,playerEmailTxt;

    private Button updateDetails;

    private ImageButton btnLogout,btnHome;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_current_details);

        sessionManager = new SessionManager(this);
        sessionManager.checkPlayerLogin(); //checkLogin();

        HashMap<String, String> user = sessionManager.getPlayerDetail();

        String id = user.get(sessionManager.PLAYER_ID);
        getPlayerDetails(id);

        playerNameTxt=findViewById(R.id.player_name_current);
        playerEmailTxt=findViewById(R.id.player_email_current);
        updateDetails = findViewById(R.id.btn_update_details_player_current);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerCurrentDetails.this,PlayerHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });


        updateDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerCurrentDetails.this, PlayerUpdateDetails.class));
            }
        });

    }
        public void getPlayerDetails(String id){

            String post_id=id;

            String url="http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_display_all_player_details.php";
            RequestQueue requestQueue;
            requestQueue= Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        JSONArray jsonArray = jsonObject.getJSONArray("details");
                        if (success.equals("1")) {
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject object = jsonArray.getJSONObject(i);

                                String name = object.getString("name").trim();
                                String email = object.getString("email").trim();



                                playerNameTxt.setText("Name: "+name);
                                playerEmailTxt.setText("Email: "+email);



                            /*Intent intent = new Intent(AdminLogin.this, AdminHome.class);
                            startActivity(intent);
                            finish();*/

                            }
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(PlayerCurrentDetails.this, "No Record Found. Check Details", Toast.LENGTH_LONG).show();
                    }

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(PlayerCurrentDetails.this, "LINE103 "+error.toString(), Toast.LENGTH_LONG).show();
                        }

                    })

            {


                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<>();
                    params.put("player_id",post_id);

                    return params;
                }
            };


            requestQueue.add(stringRequest);

        }
    }






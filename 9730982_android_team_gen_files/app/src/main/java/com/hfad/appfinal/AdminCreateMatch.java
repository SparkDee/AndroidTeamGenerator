package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminCreateMatch extends AppCompatActivity {


    //set vars
    SessionManager sessionManager;

    private Button btnCreateMatch;

    private EditText textMatchDate, textMatchTime, textMatchVenue;
    private String textTeamID;
    private String textAdminID;

    private ImageButton btnLogout,btnHome;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_match);

        //create session manager and get user details
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        textAdminID = user.get(sessionManager.ADMIN_ID);

        //xml
        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);
        btnCreateMatch = findViewById(R.id.btn_admin_create_match);
        textMatchDate = findViewById(R.id.admin_create_match_date);
        textMatchTime = findViewById(R.id.admin_create_match_time);
        textMatchVenue = findViewById(R.id.admin_create_match_venue);


       textTeamID=getIntent().getExtras().getString("cast_id");

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminCreateMatch.this,AdminHome.class));
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

                insertMatchData();

            }
        });


    }

    //method to insert match data
    private void insertMatchData() {


        String adminID=textAdminID;
        String teamId=textTeamID;


        String matchDate = textMatchDate.getText().toString();
        String matchTime = textMatchTime.getText().toString();
        String matchVenue = textMatchVenue.getText().toString();

        if (matchDate.isEmpty()) {
            textMatchDate.setError("Enter Date");
        } else if (matchTime.isEmpty()) {
            textMatchTime.setError("Enter Time");
        } else if (matchVenue.isEmpty()) {
            textMatchVenue.setError("Enter Venue");
        } else {

            //set insert URL
            String insert_url = "http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_insert_match.php";


            //connect to DB using String Request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, insert_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        if (success.equals("1")) {
                            Toast.makeText(AdminCreateMatch.this, "Match Created", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(AdminCreateMatch.this, AdminHome.class);
                            startActivity(intent);


                        }else if(success.equals("0")){
                            Toast.makeText(AdminCreateMatch.this, "Invalid date/time. Please check input format", Toast.LENGTH_LONG).show();

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
                            Toast.makeText(AdminCreateMatch.this, "Error Inserting Data " + error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                // params to send to db


                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    params.put("admin_id", adminID);
                    params.put("date", matchDate);
                    params.put("time", matchTime);
                    params.put("venue", matchVenue);
                    params.put("team_id", teamId);


                    return params;
                }
            };

            //request queue to use volley, communicate to backend
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }
    }
}













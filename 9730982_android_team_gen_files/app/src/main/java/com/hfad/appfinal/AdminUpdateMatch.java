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

public class AdminUpdateMatch extends AppCompatActivity {

    SessionManager sessionManager;

    private Button btnUpdateMatch;
    private ImageButton btnLogout,btnHome;
    private EditText textMatchDate, textMatchTime, textMatchVenue;
    private String textTeamID,textMatchID;
    private String textAdminID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_match);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();


        btnUpdateMatch = findViewById(R.id.btn_admin_update_match);

        textMatchDate = findViewById(R.id.admin_edit_match_date);
        textMatchTime = findViewById(R.id.admin_edit_match_time);
        textMatchVenue = findViewById(R.id.admin_edit_match_venue);

        String matchId=getIntent().getExtras().getString("match_id");
        String teamId=getIntent().getExtras().getString("team_id");
        String date=getIntent().getExtras().getString("date");
        String time=getIntent().getExtras().getString("time");
        String venue=getIntent().getExtras().getString("venue");

        textMatchID=matchId;
        textMatchDate.setText(date);
        textMatchTime.setText(time);
        textMatchVenue.setText(venue);
        textTeamID=teamId;
        textAdminID = user.get(sessionManager.ADMIN_ID);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);




        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminUpdateMatch.this,AdminHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });


        btnUpdateMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateMatch();
            }
        });


    }

    private void updateMatch() {


        String adminIdCurrent = textAdminID;
        String matchTime=textMatchTime.getText().toString();
        String matchDate=textMatchDate.getText().toString();
        String matchVenue=textMatchVenue.getText().toString();
        String matchTeamId=textTeamID;
        String matchID=textMatchID;


        if (matchTime.isEmpty()) {
            textMatchTime.setError("Cannot be Empty");
        } else if (matchVenue.isEmpty()) {
            textMatchVenue.setError("Cannot be Empty");
        } else if(matchDate.isEmpty()) {
            textMatchDate.setError("Cannot be Empty");
        }else{



            String update_url = "http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_update_match.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, update_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        if (success.equals("1")) {
                            Toast.makeText(AdminUpdateMatch.this, "Match Updated", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(AdminUpdateMatch.this, AdminHome.class);
                            startActivity(intent);


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
                            Toast.makeText(AdminUpdateMatch.this, "Error Updating Data " + error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                // params to send to db


                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    params.put("team_id", matchTeamId);
                    params.put("date", matchDate);
                    params.put("time", matchTime);
                    params.put("venue", matchVenue);
                    params.put("match_id",matchID);
                    params.put("admin_id", adminIdCurrent);


                    return params;
                }
            };

            //request queue to use volley, communicate to backend
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }
    }


}
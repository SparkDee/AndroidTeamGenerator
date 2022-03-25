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

public class ActivityPlayerConfirmedMatchDetails extends AppCompatActivity {


    //set vars
    private Button btnLeaveMatch;
    private TextView textMatchId, textMatchDate, textMatchTime, textMatchVenue;
    SessionManager sessionManager;
    private String playerID;
    private String match;
    private ImageButton btnLogout,btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_confirmed_match_details);

        //create session manager
        sessionManager = new SessionManager(this);
        sessionManager.checkPlayerLogin();

        //get player details from session manager
        HashMap<String, String> user = sessionManager.getPlayerDetail();
        playerID = user.get(sessionManager.PLAYER_ID);

        //xml
        btnLeaveMatch=findViewById(R.id.btn_player_leave_confirmed_match);
        textMatchId = findViewById(R.id.match_details_player_confirmed_id);
        textMatchDate = findViewById(R.id.match_details_player_confirmed_date);
        textMatchTime = findViewById(R.id.match_details_player_confirmed_time);
        textMatchVenue = findViewById(R.id.match_details_player_confirmed_venue);
        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityPlayerConfirmedMatchDetails.this,PlayerHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });






        btnLeaveMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaveMatch();
            }
        });


        String teamId = getIntent().getExtras().getString("team_id");
        String matchId=getIntent().getExtras().getString("match_id");
        String date = getIntent().getExtras().getString("date");
        String time = getIntent().getExtras().getString("time");
        String venue = getIntent().getExtras().getString("venue");

        match=matchId;


        //set data to textview
        textMatchId.setText(matchId);
        textMatchDate.setText(date);
        textMatchTime.setText(time);
        textMatchVenue.setText(venue);


    }

    //method to leave match
    private void leaveMatch() {

        String player_id = playerID;
        String match_id = match;
        String delete_url = "http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_player_leave_confirmed_match.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, delete_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")) {
                        Toast.makeText(ActivityPlayerConfirmedMatchDetails.this, "Removed From Match", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(ActivityPlayerConfirmedMatchDetails.this,PlayerHome.class);
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
                        Toast.makeText(ActivityPlayerConfirmedMatchDetails.this, "Error Deleting Account "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            // params to send to db


            //this is what is sent
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("player_id",player_id);
                params.put("match_id",match_id);


                return params;
            }
        };

        //request queue to use volley, communicate to backend
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}

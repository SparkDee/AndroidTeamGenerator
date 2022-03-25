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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivityPlayerMatchDetails extends AppCompatActivity {


    //set vars
    private TextView textMatchId, textMatchDate, textMatchTime, textMatchVenue;

    private Button btnJoinMatch;

    private String playerID;

    private String matchID;

    private ImageButton btnLogout,btnHome;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_match_details);

        //create session manager
        sessionManager = new SessionManager(this);
        sessionManager.checkPlayerLogin();

        //get player details from session manager
        HashMap<String, String> user = sessionManager.getPlayerDetail();
        playerID = user.get(sessionManager.PLAYER_ID);


        //xml
        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);
        textMatchId = findViewById(R.id.player_match_details_id);
        textMatchDate = findViewById(R.id.player_match_details_date);
        textMatchTime = findViewById(R.id.player_match_details_time);
        textMatchVenue = findViewById(R.id.player_match_details_venue);
        btnJoinMatch = findViewById(R.id.btnJoinMatchPlayer);


        //set onclick listeners for buttons
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityPlayerMatchDetails.this,PlayerHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });

        btnJoinMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                joinMatch();

            }
        });

        String id = getIntent().getExtras().getString("team_id");
        String date = getIntent().getExtras().getString("date");
        String time = getIntent().getExtras().getString("time");
        String venue = getIntent().getExtras().getString("venue");
        String match = getIntent().getExtras().getString("match_id");


        //set text views
        textMatchId.setText(id);
        textMatchDate.setText(date);
        textMatchTime.setText(time);
        textMatchVenue.setText(venue);

        matchID = match;

    }


    //join match method
    private void joinMatch() {
        String player = playerID;
        String match = matchID;



        String url = "http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/check_match_duplicate.php";

        //connect to DB using String Request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    switch (success) {
                        case "1":
                            Toast.makeText(ActivityPlayerMatchDetails.this, "Player Already Confirmed For Match. Please Join Another", Toast.LENGTH_LONG).show();
                            Intent intentDuplicate = new Intent(ActivityPlayerMatchDetails.this, PlayerHome.class);
                            startActivity(intentDuplicate);
                            break;
                        case "2":
                            Toast.makeText(ActivityPlayerMatchDetails.this, "Match Full. Please Check Back Regularly", Toast.LENGTH_LONG).show();
                            Intent intentMatchFull = new Intent(ActivityPlayerMatchDetails.this, PlayerHome.class);
                            break;
                        case "3":
                            Toast.makeText(ActivityPlayerMatchDetails.this, "Player Added", Toast.LENGTH_LONG).show();
                            Intent intentAdded = new Intent(ActivityPlayerMatchDetails.this, PlayerTeamOptions.class);
                            startActivity(intentAdded);
                            break;
                        default:
                            Toast.makeText(ActivityPlayerMatchDetails.this, "Error", Toast.LENGTH_LONG).show();
                            break;
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
                        Toast.makeText(ActivityPlayerMatchDetails.this, "Server Issue " + error.toString(), Toast.LENGTH_LONG).show();
                        //intent to reserve list
                    }
                }) {
            // params to send to db


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("player_id", player);
                params.put("match_id", match);

                return params;
            }
        };

        //request queue to use volley, communicate to backend
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}

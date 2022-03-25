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

public class ActivityMatchDetails extends AppCompatActivity {

    //set vars
    private Button btnEdit, btnDelete, btnConfirmedPlayers;
    private TextView textMatchId, textMatchDate, textMatchTime, textMatchVenue;
    private String match;
    private ImageButton btnLogout,btnHome;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        //create session manager
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        //user detail from session manager
        HashMap<String, String> user = sessionManager.getUserDetail();
        String adminId = user.get(sessionManager.ADMIN_ID);



        //xml
        textMatchId = findViewById(R.id.match_details_id);
        textMatchDate = findViewById(R.id.match_details_date);
        textMatchTime = findViewById(R.id.match_details_time);
        textMatchVenue = findViewById(R.id.match_details_venue);
        btnDelete = findViewById(R.id.btnDeleteMatchDetails);
        btnEdit = findViewById(R.id.btnEditMatchDetails);
        btnConfirmedPlayers=findViewById(R.id.btnViewConfirmedPlayersMatch);





        //get intents
        String id = getIntent().getExtras().getString("team_id");
        String date = getIntent().getExtras().getString("date");
        String time = getIntent().getExtras().getString("time");
        String venue = getIntent().getExtras().getString("venue");
        match = getIntent().getExtras().getString("match_id");

        //set to textview
        textMatchId.setText(id);
        textMatchDate.setText(date);
        textMatchTime.setText(time);
        textMatchVenue.setText(venue);

        //xml buttons
        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);




        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityMatchDetails.this,AdminHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });





    //send intent data to view confirmed players activity
        btnConfirmedPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivityMatchDetails.this, AdminViewConfirmedPlayers.class);
                intent.putExtra("match_id", match);
                intent.putExtra("date", date);
                intent.putExtra("time", time);
                intent.putExtra("venue", venue);
                startActivity(intent);

            }
        });




        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivityMatchDetails.this, AdminUpdateMatch.class);
                intent.putExtra("match_id", match);
                intent.putExtra("admin_id", adminId);
                intent.putExtra("team_id", id);
                intent.putExtra("date", date);
                intent.putExtra("time", time);
                intent.putExtra("venue", venue);


                startActivity(intent);


            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteMatch();
            }
        });


    }

    //delete match method
    private void deleteMatch() {
        String matchId = match;

        String delete_url = "http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_delete_match_details.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, delete_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")) {
                        Toast.makeText(ActivityMatchDetails.this, "Match Deleted", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ActivityMatchDetails.this, AdminTeamOptions.class);
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
                        Toast.makeText(ActivityMatchDetails.this, "Error Deleting Match " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            // params to send to db


            //this is what is sent
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("match_id", matchId);


                return params;
            }
        };

        //request queue to use volley, communicate to backend
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}




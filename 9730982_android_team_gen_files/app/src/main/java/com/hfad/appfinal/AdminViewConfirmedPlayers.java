package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminViewConfirmedPlayers extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ConfirmedPlayerAdapter adapter;
    private List<ConfirmedPlayerModel> playerList;
    private RequestQueue requestQueue;
    //private SwipeRefreshLayout swipeRefreshLayout;
    private TextView textDate, textTime, textVenue, textPlayersConfirmed;
    private int number;
    private ImageButton btnLogout,btnHome;
    SessionManager sessionManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_confirmed_players);


        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        String matchId = getIntent().getExtras().getString("match_id");
        String date = getIntent().getExtras().getString("date");
        String time = getIntent().getExtras().getString("time");
        String venue = getIntent().getExtras().getString("venue");

        textDate = findViewById(R.id.confirmed_player_date);
        textTime = findViewById(R.id.confirmed_player_time);
        textVenue = findViewById(R.id.confirmed_player_venue);
        textPlayersConfirmed = findViewById(R.id.confirmed_player_number);

        textDate.setText(date);
        textTime.setText(time);
        textVenue.setText(venue);


        mRecyclerView = findViewById(R.id.my_recycler_view_admin_confirmed_players);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //swipeRefreshLayout=findViewById(R.id.swipe_refresher);

        playerList = new ArrayList<>();
        adapter = new ConfirmedPlayerAdapter(this, playerList);
        mRecyclerView.setAdapter(adapter);


        requestQueue = Volley.newRequestQueue(this);

        retrieve_confirmed_players(matchId);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);




        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminViewConfirmedPlayers.this,AdminHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });


    }


    private void retrieve_confirmed_players(String matchId) {
        String match = matchId;

        String get_all_users_url = "http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_admin_show_confirmed_match_players.php";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, get_all_users_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("details");
                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);

                            String player = object.getString("name").trim();


                            ConfirmedPlayerModel players = new ConfirmedPlayerModel(player);

                            playerList.add(players);

                            adapter.notifyDataSetChanged();


                        }

                        number = playerList.size();
                        textPlayersConfirmed.setText(String.valueOf("Players Confirmed: " + number));


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(AdminViewConfirmedPlayers.this, "No Record Found. Check Details", Toast.LENGTH_LONG).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminViewConfirmedPlayers.this, "LINE103 " + error.toString(), Toast.LENGTH_LONG).show();
                    }

                }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("match_id", match);


                return params;
            }
        };


        requestQueue.add(stringRequest);

    }
}


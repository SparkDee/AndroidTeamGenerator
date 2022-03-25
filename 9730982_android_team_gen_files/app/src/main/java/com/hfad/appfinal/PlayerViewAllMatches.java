package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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

public class PlayerViewAllMatches extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PlayerAllMatchesAdapter adapter;
    private List<PlayerAllMatchesModel> playerList;
    private RequestQueue requestQueue;
    SessionManager sessionManager;
    private String playerID;
    private ImageButton btnLogout,btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_view_all_matches);

        sessionManager = new SessionManager(this);
        sessionManager.checkPlayerLogin();

        HashMap<String, String> user = sessionManager.getPlayerDetail();
        playerID = user.get(sessionManager.PLAYER_ID);


        mRecyclerView = findViewById(R.id.my_recycler_view_all_player_matches);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        playerList = new ArrayList<>();
        adapter = new PlayerAllMatchesAdapter(this, playerList);
        mRecyclerView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);

        retrieve_all_matches(playerID);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerViewAllMatches.this,PlayerHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });


    }

    private void retrieve_all_matches(String playerID) {

        String player=playerID;

        String get_all_matches_url = "http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_display_full_player_match_list.php";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, get_all_matches_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("details");
                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);

                            String date = object.getString("date").trim();
                            String time = object.getString("time").trim();
                            String venue = object.getString("venue").trim();


                            //System.out.println(player);

                            PlayerAllMatchesModel matches=new PlayerAllMatchesModel(date,time,venue);

                            playerList.add(matches);

                            adapter.notifyDataSetChanged();


                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(PlayerViewAllMatches.this, "No Record Found. Check Details", Toast.LENGTH_LONG).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PlayerViewAllMatches.this, "LINE103 " + error.toString(), Toast.LENGTH_LONG).show();
                    }

                }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("player_id", player);


                return params;
            }
        };


        requestQueue.add(stringRequest);

    }
}





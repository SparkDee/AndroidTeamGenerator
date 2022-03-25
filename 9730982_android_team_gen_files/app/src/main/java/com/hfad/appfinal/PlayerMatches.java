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

public class PlayerMatches extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private PlayerMatchAdapter adapter;
    private List<PlayerMatchModel> matchModelList;
    private RequestQueue requestQueue;
    private TextView textTeamName;
    //private SwipeRefreshLayout swipeRefreshLayout;
    SessionManager sessionManager;
    private ImageButton btnLogout,btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_matches);


        sessionManager = new SessionManager(this);
        sessionManager.checkPlayerLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        //String id=user.get(sessionManager.ADMIN_ID);

        mRecyclerView=findViewById(R.id.my_recycler_view_player_matches);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //swipeRefreshLayout=findViewById(R.id.swipe_refresher);

        matchModelList=new ArrayList<>();
        adapter=new PlayerMatchAdapter(this,matchModelList);
        mRecyclerView.setAdapter(adapter);

        requestQueue= Volley.newRequestQueue(this);

        textTeamName=findViewById(R.id.text_current_team);


        String id=getIntent().getExtras().getString("team_id");
        String name=getIntent().getExtras().getString("team_name");

        textTeamName=findViewById(R.id.text_current_team);
        textTeamName.setText(name);


        retrieve_team_matches(id);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerMatches.this,PlayerHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });



    }

    private void retrieve_team_matches(String id) {



        String team_id=id;

        String get_all_team_matches_url="http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_admin_show_team_matches.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, get_all_team_matches_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("details");
                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);

                            String match_id = object.getString("match_id").trim();
                            String admin_id = object.getString("admin_id").trim();
                            String team_id = object.getString("team_id").trim();
                            String date = object.getString("date").trim();
                            String time = object.getString("time").trim();
                            String venue = object.getString("venue").trim();

                            //System.out.println("TEAM ID IN GET ALL TEAMS"+team_id);

                            PlayerMatchModel matchModel=new PlayerMatchModel(match_id,admin_id,team_id,date,time,venue);

                            matchModelList.add(matchModel);

                            adapter.notifyDataSetChanged();


                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(PlayerMatches.this, "No Record Found. Check Details", Toast.LENGTH_LONG).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PlayerMatches.this, "LINE103 "+error.toString(), Toast.LENGTH_LONG).show();
                    }

                })

        {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("team_id",team_id);

                return params;
            }
        };


        requestQueue.add(stringRequest);

    }
}


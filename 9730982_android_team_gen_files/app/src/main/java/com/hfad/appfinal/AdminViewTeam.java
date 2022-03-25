package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

public class AdminViewTeam extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TeamAdapter adapter;
    private List<TeamModel> teamModelList;
    private RequestQueue requestQueue;
    //private SwipeRefreshLayout swipeRefreshLayout;
    SessionManager sessionManager;
    private ImageButton btnLogout,btnHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_team);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        String id = user.get(sessionManager.ADMIN_ID);


        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //swipeRefreshLayout=findViewById(R.id.swipe_refresher);

        teamModelList = new ArrayList<>();
        adapter = new TeamAdapter(this, teamModelList);
        mRecyclerView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);

        retrieve_all_teams(id);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);




        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminViewTeam.this,AdminHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });


    }

    private void retrieve_all_teams(String id) {

        String post_id = id;
        String get_all_users_url = "http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_display_admin_teams.php";

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

                            String team_name = object.getString("team_name").trim();
                            String team_id = object.getString("team_id").trim();
                            String admin_id = object.getString("admin_id").trim();


                            TeamModel teamModel = new TeamModel(team_id, admin_id, team_name);

                            teamModelList.add(teamModel);

                            adapter.notifyDataSetChanged();


                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(AdminViewTeam.this, "No Record Found. Check Details", Toast.LENGTH_LONG).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminViewTeam.this, "LINE103 " + error.toString(), Toast.LENGTH_LONG).show();
                    }

                }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("admin_id", post_id);

                return params;
            }
        };


        requestQueue.add(stringRequest);

    }
}





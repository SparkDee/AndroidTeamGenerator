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

public class PlayerJoinTeam extends AppCompatActivity {

    private Button btnJoinTeam;
    private EditText textTeamId;
    private String id;
    private ImageButton btnLogout,btnHome;

    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_join_team);

        btnJoinTeam=findViewById(R.id.btn_team_join_player_code);
        textTeamId=findViewById(R.id.player_join_team_code);

        sessionManager = new SessionManager(this);
        sessionManager.checkPlayerLogin();
        HashMap<String, String> user = sessionManager.getPlayerDetail();
        id = user.get(sessionManager.PLAYER_ID);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerJoinTeam.this,PlayerHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });





        btnJoinTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertTeam();
            }
        });



    }

    private void insertTeam() {

    String insert_url="http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_insert_player_team_data.php";
    String playerId=id;
    String teamId=textTeamId.getText().toString();

        //connect to DB using String Request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, insert_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")) {
                        Toast.makeText(PlayerJoinTeam.this, "Team Joined", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(PlayerJoinTeam.this,PlayerTeamOptions.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(PlayerJoinTeam.this, "Team Already Joined", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(PlayerJoinTeam.this,PlayerTeamOptions.class);
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
                        Toast.makeText(PlayerJoinTeam.this, "Error Inserting Data "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            // params to send to db



            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("player_id", playerId);
                params.put("team_id",teamId);


                return params;
            }
        };

        //request queue to use volley, communicate to backend
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}


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

public class AdminCreateTeam extends AppCompatActivity {

    //set vars
    private Button btnCreateTeam;
    private EditText textTeamName;
    private String textAdminId;
    private ImageButton btnLogout,btnHome;
    SessionManager sessionManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_team);


        //create session manager and get user detail
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();



        //xml
        btnCreateTeam=findViewById(R.id.btn_admin_create_team);

        textTeamName=findViewById(R.id.admin_create_team_name);
        textAdminId=user.get(sessionManager.ADMIN_ID);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);




        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminCreateTeam.this,AdminHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });



        btnCreateTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertNewTeamData();
            }
        });



    }

    //insert new team data

    private void insertNewTeamData() {

        String adminID = textAdminId;
        String teamName=textTeamName.getText().toString();

        if(teamName.isEmpty()){
            textTeamName.setError("Enter Team Name");
        }else{
            String insert_url ="http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_insert_team.php";
            //connect to DB using String Request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, insert_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        if (success.equals("1")) {
                            Toast.makeText(AdminCreateTeam.this, "Team Created", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(AdminCreateTeam.this,AdminHome.class);
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
                            Toast.makeText(AdminCreateTeam.this, "Error Inserting Data "+error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                // params to send to db



                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    params.put("admin_id", adminID);
                    params.put("team_name",teamName);



                    return params;
                }
            };

            //request queue to use volley, communicate to backend
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }
    }

}



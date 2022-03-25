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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PlayerUpdateDetails extends AppCompatActivity {


    private EditText amendPlayerName, amendPlayerEmail;

    private String playerIdDetail;
    private ImageButton btnLogout,btnHome;
    private Button btnUpdateDetails;

    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_update_details);

        sessionManager = new SessionManager(this);
        sessionManager.checkPlayerLogin();

        amendPlayerName = findViewById(R.id.player_name_amend);
        amendPlayerEmail = findViewById(R.id.player_email_amend);



        HashMap<String, String> user = sessionManager.getPlayerDetail();

       // String pName = user.get(sessionManager.PLAYER_NAME);
        // pEmail =user.get(sessionManager.PLAYER_EMAIL);

        //amendPlayerName.setText(pName);
        //amendPlayerEmail.setText(pEmail);

        playerIdDetail=user.get(sessionManager.PLAYER_ID);
        getPlayerDetails(playerIdDetail);

        btnUpdateDetails = findViewById(R.id.btn_update_details_player_update);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerUpdateDetails.this,PlayerHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });

        btnUpdateDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updatePlayerData();
            }
        });

    }

    private void updatePlayerData() {

        String name = amendPlayerName.getText().toString();
        String email = amendPlayerEmail.getText().toString();
        String playerIdCurrent=playerIdDetail;

        if (name.isEmpty()) {
            amendPlayerEmail.setError("Cannot be Empty");
        } else if (email.isEmpty()) {
            amendPlayerEmail.setError("Cannot be Empty");
        } else {

            String update_url = "http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_update_player_data.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, update_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        if (success.equals("1")) {
                            Toast.makeText(PlayerUpdateDetails.this, "Player Updated", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(PlayerUpdateDetails.this,PlayerDetails.class);
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
                            Toast.makeText(PlayerUpdateDetails.this, "Error Updating Data "+error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                // params to send to db



                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    params.put("name", name);
                    params.put("email",email);
                    params.put("player_id",playerIdCurrent);



                    return params;
                }
            };

            //request queue to use volley, communicate to backend
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }
    }

    public void getPlayerDetails(String playerIdDetail){

        String post_id=playerIdDetail;



        String url="http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_display_all_player_details.php";
        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("details");
                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);

                            String name = object.getString("name").trim();
                            String email = object.getString("email").trim();



                            amendPlayerName.setText(name);
                            amendPlayerEmail.setText(email);




                            /*Intent intent = new Intent(AdminLogin.this, AdminHome.class);
                            startActivity(intent);
                            finish();*/

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(PlayerUpdateDetails.this, "No Record Found. Check Details", Toast.LENGTH_LONG).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PlayerUpdateDetails.this, "Server Issue "+error.toString(), Toast.LENGTH_LONG).show();
                    }

                })

        {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("player_id",post_id);

                return params;
            }
        };


        requestQueue.add(stringRequest);

    }
}







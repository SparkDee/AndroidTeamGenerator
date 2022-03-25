package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminCurrentDetails extends AppCompatActivity {


    //set vars
    private TextView adminNameTxt,adminEmailTxt;
    private Button updateDetails;
    private ImageButton btnLogout,btnHome;
    SessionManager sessionManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_current_details);

        //create session manager and view user detail
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();


        HashMap<String, String> user = sessionManager.getUserDetail();
        String id=user.get(sessionManager.ADMIN_ID);

        getAdminDetails(id);


        adminEmailTxt = findViewById(R.id.admin_email_current);
        adminNameTxt = findViewById(R.id.admin_name_current);
        updateDetails = findViewById(R.id.btn_update_details_admin_current);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);




        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminCurrentDetails.this,AdminHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });



        updateDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminCurrentDetails.this, AdminUpdateDetails.class));
            }
        });

    }

    //get admin details method
    public void getAdminDetails(String id){

        String post_id=id;



        String url="http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_display_all_admin_details.php";
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



                            adminNameTxt.setText("Name: "+name);
                            adminEmailTxt.setText("Email: "+email);



                            /*Intent intent = new Intent(AdminLogin.this, AdminHome.class);
                            startActivity(intent);
                            finish();*/

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(AdminCurrentDetails.this, "No Record Found. Check Details", Toast.LENGTH_LONG).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminCurrentDetails.this, "LINE103 "+error.toString(), Toast.LENGTH_LONG).show();
                    }

                })

        {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("admin_id",post_id);

                return params;
            }
        };


        requestQueue.add(stringRequest);

    }
}



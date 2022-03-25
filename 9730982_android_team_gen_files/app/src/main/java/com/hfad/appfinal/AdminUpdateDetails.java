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

public class AdminUpdateDetails extends AppCompatActivity {

    private EditText amendAdminName, amendAdminEmail;

    private String id;

    private Button btnUpdateDetails;
    private ImageButton btnLogout,btnHome;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_details);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        amendAdminName = findViewById(R.id.admin_name_amend);
        amendAdminEmail = findViewById(R.id.admin_email_amend);

        HashMap<String, String> user = sessionManager.getUserDetail();



        id=user.get(sessionManager.ADMIN_ID);

        getAdminDetails(id);

        btnUpdateDetails = findViewById(R.id.btn_update_details_admin_update);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);




        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminUpdateDetails.this,AdminHome.class));
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

                updateAdminData();
            }
        });

    }



    private void updateAdminData() {


        String name = amendAdminName.getText().toString();
        String email = amendAdminEmail.getText().toString();
        String adminIdCurrent=id;

        if (name.isEmpty()) {
            amendAdminEmail.setError("Cannot be Empty");
        } else if (email.isEmpty()) {
            amendAdminEmail.setError("Cannot be Empty");
        } else {

            String update_url = "http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_update_admin_data.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, update_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        if (success.equals("1")) {
                            Toast.makeText(AdminUpdateDetails.this, "Creator Updated", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(AdminUpdateDetails.this,AdminDetails.class);
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
                            Toast.makeText(AdminUpdateDetails.this, "Error Updating Data "+error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                // params to send to db



                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    params.put("name", name);
                    params.put("email",email);
                    params.put("admin_id",adminIdCurrent);



                    return params;
                }
            };

            //request queue to use volley, communicate to backend
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }
    }

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



                            amendAdminName.setText(name);
                            amendAdminEmail.setText(email);




                            /*Intent intent = new Intent(AdminLogin.this, AdminHome.class);
                            startActivity(intent);
                            finish();*/

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(AdminUpdateDetails.this, "No Record Found. Check Details", Toast.LENGTH_LONG).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminUpdateDetails.this, "Server Issue "+error.toString(), Toast.LENGTH_LONG).show();
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











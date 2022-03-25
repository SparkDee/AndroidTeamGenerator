package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class AdminDeleteTeam extends AppCompatActivity {

    private Button btnReturnHome,btnDeleteTeam;
    private ImageButton btnLogout,btnHome;
    String adminId;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_team);

        sessionManager=new SessionManager(this);
        sessionManager.checkLogin();


        HashMap<String,String> user=sessionManager.getUserDetail();

        btnDeleteTeam=findViewById(R.id.btn_admin_delete_team);
        btnReturnHome=findViewById(R.id.btn_admin_return_home_delete_team);

        adminId=user.get(sessionManager.ADMIN_ID);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);




        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDeleteTeam.this,AdminHome.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });


        btnReturnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDeleteTeam.this,AdminHome.class));
            }
        });

        btnDeleteTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAdminAccount();
            }
        });

    }



    private void deleteAdminAccount() {

        String adminIdDelete=adminId;
        String delete_url="http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_team_delete.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, delete_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")) {
                        Toast.makeText(AdminDeleteTeam.this, "Team Deleted", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(AdminDeleteTeam.this,AdminTeamOptions.class);
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
                        Toast.makeText(AdminDeleteTeam.this, "Error Deleting Account "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            // params to send to db


            //this is what is sent
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("admin_id",adminIdDelete);



                return params;
            }
        };

        //request queue to use volley, communicate to backend
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}

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

public class PlayerDeleteDetails extends AppCompatActivity {


    private Button btnReturnHome,btnDeleteAccount;
    String playerId;
    SessionManager sessionManager;
    private ImageButton btnLogout,btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_delete_details);

        sessionManager=new SessionManager(this);
        sessionManager.checkPlayerLogin();



        HashMap<String,String> user=sessionManager.getPlayerDetail();

        btnDeleteAccount=findViewById(R.id.btn_player_delete_account);
        btnReturnHome=findViewById(R.id.btn_player_return_home_delete);

        playerId=user.get(sessionManager.PLAYER_ID);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerDeleteDetails.this,PlayerHome.class));
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
                startActivity(new Intent(PlayerDeleteDetails.this,PlayerHome.class));
            }
        });



        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePlayerAccount();
            }
        });


    }

    private void deletePlayerAccount() {

        String playerIdDelete=playerId;
        String delete_url="http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_player_delete.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, delete_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")) {
                        Toast.makeText(PlayerDeleteDetails.this, "Account Deleted", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(PlayerDeleteDetails.this, MainActivity.class);
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
                        Toast.makeText(PlayerDeleteDetails.this, "Error Deleting Account "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            // params to send to db


            //this is what is sent
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("player_id",playerIdDelete);



                return params;
            }
        };

        //request queue to use volley, communicate to backend
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}


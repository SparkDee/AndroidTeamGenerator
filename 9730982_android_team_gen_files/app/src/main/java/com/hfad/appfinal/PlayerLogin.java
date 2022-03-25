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

public class PlayerLogin extends AppCompatActivity {

    private ImageButton btnLogout,btnHome;
    private EditText email,password;
    private Button btnLogin;
    private static String URL_LOGIN="http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_player_login.php";

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_login);

        sessionManager=new SessionManager(this);

        email=findViewById(R.id.player_login_email);
        password=findViewById(R.id.player_login_password);
        btnLogin=findViewById(R.id.btn_player_login);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayerLogin.this,PlayerLogin.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mEmail=email.getText().toString().trim();
                String mPass=password.getText().toString().trim();

                if(!mEmail.isEmpty()||!mPass.isEmpty()){
                    Login(mEmail,mPass);
                }else{
                    email.setError("Please Insert Email");
                    password.setError("Please Insert Password");
                }

            }
        });


    }

    private void Login(final String email, final String password) {



        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            // System.out.println(jsonArray);
                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String player_name = object.getString("name").trim();
                                    String player_email = object.getString("email").trim();
                                    String player_id = object.getString("player_id").trim();
                                    //String player_team_id=object.getString("team_id").trim();
                                    //System.out.println(object);

                                    //call session
                                    sessionManager.createPlayerSession(player_name,player_email,player_id);


                                    Intent intent = new Intent(PlayerLogin.this, PlayerHome.class);
                                    startActivity(intent);

                                }
                            }






                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PlayerLogin.this, "Error Inserting Data. Check Details", Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PlayerLogin.this, "LINE103 "+error.toString(), Toast.LENGTH_LONG).show();
                    }

                })

        {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}





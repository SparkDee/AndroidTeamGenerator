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

public class AdminLogin extends AppCompatActivity {


    private EditText email,password;
    private Button btn_login;
    private static String URL_LOGIN="http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_admin_login.php";
    SessionManager sessionManager;
    private ImageButton btnLogout,btnHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        sessionManager=new SessionManager(this);

        email=findViewById(R.id.admin_email_login);
        password=findViewById(R.id.admin_password_login);
        btn_login=findViewById(R.id.btn_admin_login);

        btnLogout=findViewById(R.id.logout_icon);
        btnHome=findViewById(R.id.home_icon);




        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLogin.this,AdminLogin.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });




        btn_login.setOnClickListener(new View.OnClickListener() {
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
                            //System.out.println(jsonArray);
                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String name = object.getString("name").trim();
                                    String email = object.getString("email").trim();
                                    String admin_id = object.getString("admin_id").trim();
                                    //String team_id=object.getString("team_id").trim();

                                    //System.out.println(object);

                                    //call session
                                    sessionManager.createSession(name,email,admin_id);  //put team_id back in


                                    Intent intent = new Intent(AdminLogin.this, AdminHome.class);
                                    startActivity(intent);
                                    finish();

                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AdminLogin.this, "No Record Found. Check Details", Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminLogin.this, "LINE103 "+error.toString(), Toast.LENGTH_LONG).show();
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




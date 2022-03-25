package com.hfad.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class AdminCreate extends AppCompatActivity {

    //set vars
    private EditText textName,textEmail, textPassword,textTeam;
    private Button buttonCreatePlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create);


        //ref ids from XML
        textName=findViewById(R.id.admin_create_name);
        textEmail=findViewById(R.id.admin_create_email);
        textPassword=findViewById(R.id.admin_create_password);
        //textTeam=findViewById(R.id.admin_create_team);

        //button
        buttonCreatePlayer=findViewById(R.id.btn_admin_insert);

        //set onclick listener
        buttonCreatePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();
            }
        });
    }

    //insert data method
    private void insertData() {
        //vars to get data from inputs
        String name=textName.getText().toString();
        String email=textEmail.getText().toString();
        String password=textPassword.getText().toString();
        //String team=textTeam.getText().toString();


        if (name.isEmpty()) {
            textName.setError("Enter Name");
        } else if (email.isEmpty()) {
            textEmail.setError("Enter Email");
        } else if (password.isEmpty()) {
            textEmail.setError("Enter Password");
        }else{
            //set insert URL
            String insert_url="http://mdavidson04.lampt.eeecs.qub.ac.uk/app_final/db_insert_data_admin.php";



            //connect to DB using String Request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, insert_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        if (success.equals("1")) {
                            Toast.makeText(AdminCreate.this, "Creator Inserted", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(AdminCreate.this,AdminLogin.class);
                            startActivity(intent);

                        }else{

                            Toast.makeText(AdminCreate.this, "User Already Registered", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(AdminCreate.this, "Error Inserting Data "+error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                // params to send to db



                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    params.put("name", name);
                    params.put("email",email);
                    params.put("password", password);
                    //params.put("team_id", team);


                    return params;
                }
            };

            //request queue to use volley, communicate to backend
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }
    }

}

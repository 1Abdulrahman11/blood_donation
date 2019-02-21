package com.example.bloodbank10;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginH extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_h);


        final EditText etEmail = (EditText) findViewById(R.id.email);
        final EditText etPass = (EditText) findViewById(R.id.pass);
        Button login = (Button) findViewById(R.id.login);




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etEmail.getText().toString();
                final String password = etPass.getText().toString();

                Response.Listener<String> responseLesener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success){
                                String name = jsonObject.getString("name");
                                String age = jsonObject.getString("location");

                                Intent intent = new Intent(LoginH.this, hospitalArea.class);
                                intent.putExtra("username",username);
                                intent.putExtra("password",password);
                                intent.putExtra("name",name);
                                intent.putExtra("location",age);
                                LoginH.this.startActivity(intent);
                                finish();


                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginH.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                LoginRequest loginRequest= new LoginRequest(username,password,responseLesener,1);
                RequestQueue queue = Volley.newRequestQueue(LoginH.this);
                queue.add(loginRequest);
            }
        });

    }
}

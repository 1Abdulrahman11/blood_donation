package com.example.bloodbank10;

import android.content.Intent;
import android.app.AlertDialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText etEmail = (EditText) findViewById(R.id.email);
        final EditText etPass = (EditText) findViewById(R.id.pass);
        Button login = (Button) findViewById(R.id.login);
        Button signup = (Button) findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inSignup = new Intent(Login.this, SignUp.class);
                Login.this.startActivity(inSignup);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etEmail.getText().toString();
                final String password = etPass.getText().toString();

                Response.Listener<String> responseLesener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success){
                                String name = jsonObject.getString("name");
                                String age = jsonObject.getString("age");
                                String blood = jsonObject.getString("blood");

                                Intent intent = new Intent(Login.this, DonorArea.class);
                                intent.putExtra("email",email);
                                intent.putExtra("password",password);
                                intent.putExtra("name",name);
                                intent.putExtra("age",age);
                                intent.putExtra("blood",blood);
                                Login.this.startActivity(intent);
                                finish();


                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
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


                LoginRequest loginRequest= new LoginRequest(email,password,responseLesener);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(loginRequest);
            }
        });

    }
}

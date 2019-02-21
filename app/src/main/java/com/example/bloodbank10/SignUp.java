package com.example.bloodbank10;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.RadioAccessSpecifier;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    private EditText name,email,age,pass,cpass,weight;
    private Button signup;
    private Button login;
    private Spinner blood;
    private RadioGroup Ggender;
    private RadioButton Bgender;

    private final String URL_REGIST = "http://10.200.214.84/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Ggender = (RadioGroup) findViewById(R.id.radioGroup);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        age = (EditText) findViewById(R.id.age);
        pass = (EditText) findViewById(R.id.pass);
        cpass = (EditText) findViewById(R.id.cpass);
        signup = (Button) findViewById(R.id.signup);
        login = (Button) findViewById(R.id.login);
        blood = (Spinner) findViewById(R.id.blood);
        weight = (EditText) findViewById(R.id.weight);




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inLogin = new Intent(SignUp.this, Login.class);
                SignUp.this.startActivity(inLogin);
                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Regist();
                regist2();

            }
        });
    }


        private void Regist(){
       // signup.setVisibility(View.GONE);
        final String name = this.name.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String pass = this.pass.getText().toString().trim();
        final String age = this.age.getText().toString().trim();
        final String blood = this.blood.getSelectedItem().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            Log.i("tagconvertstr", "[*******"+response+"*********]");
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if(success.equals("1")){
                                Toast.makeText(SignUp.this, "Register success", Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SignUp.this, "Register fail "+e.toString(), Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUp.this, "Register fail "+error.toString(), Toast.LENGTH_LONG).show();



                    }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("name",name);
                parms.put("email",email);
                parms.put("password",pass);
                parms.put("age",age);
                parms.put("blood",blood);


                return super.getParams();
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

        private void regist2(){

            final String name = this.name.getText().toString().trim();
            final String email = this.email.getText().toString().trim();
            final String pass = this.pass.getText().toString().trim();
            final String cpass = this.cpass.getText().toString().trim();
            final String age = this.age.getText().toString().trim();
            final String weight = this.weight.getText().toString().trim();
            final String blood = this.blood.getSelectedItem().toString().trim();
            int selectedR = Ggender.getCheckedRadioButtonId();
            Bgender = (RadioButton) findViewById(selectedR);
            final String gender = Bgender.getText().toString();
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (!pass.equals(cpass)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                        builder.setMessage("the password dose not match ")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();

                    }

                    else{
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this, DonorArea.class);
                            SignUp.this.startActivity(intent);
                            finish();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                            builder.setMessage("Register Failed!.")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                }
            };

            RegisterRequest registerRequest = new RegisterRequest(name, email, pass, age,blood,gender,weight, responseListener);
            RequestQueue queue = Volley.newRequestQueue(SignUp.this);
            queue.add(registerRequest);
        }



}

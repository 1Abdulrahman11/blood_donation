package com.example.bloodbank10;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://10.200.214.84/register.php";
  //  private static final String REGISTER_REQUEST_URL = "http://1boom1.thefreecpanel.com/register.php";

    private Map<String, String> params;

    public RegisterRequest(String name, String email, String password,String age, String blood,String gender,String weight, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
        params.put("age", age + "");
        params.put("blood", blood);
        params.put("gender",gender);
        params.put("weight",weight);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
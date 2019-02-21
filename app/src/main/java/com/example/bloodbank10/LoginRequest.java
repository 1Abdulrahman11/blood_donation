package com.example.bloodbank10;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://10.200.214.84/login.php";
    private static final String LOGIN_REQUEST_URL_h = "http://10.200.214.84/login_h.php";
 //   private static final String LOGIN_REQUEST_URL = "http://1boom1.thefreecpanel.com/login.php";
  //  private static final String LOGIN_REQUEST_URL_h = "http://1boom1.thefreecpanel.com/login_h.php";


    private Map<String, String> params;

    public LoginRequest( String email, String password, Response.Listener<String> listener ) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
    }

    public LoginRequest( String email, String password, Response.Listener<String> listener ,int type) {
        super(Request.Method.POST, LOGIN_REQUEST_URL_h, listener, null);
        params = new HashMap<>();
        params.put("username", email);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}

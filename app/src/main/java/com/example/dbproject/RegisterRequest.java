package com.example.dbproject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://sunny819819.dothome.co.kr/Register.php";
    private Map<String, String> map;

    public RegisterRequest(String userName, String PhoneNum, String nickName, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userName", userName);
        map.put("PhoneNum", PhoneNum);
        map.put("nickName", nickName);
    }

    @Override
    protected Map<String, String> getParams() {
        return map;
    }
}
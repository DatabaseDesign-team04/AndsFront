package com.example.dbproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OrderRequest extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://sunny819819.dothome.ac.kr/Order.php";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public OrderRequest(String dateText, int editEa, int Price, String destination, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("create_at", dateText);
        map.put("orderAllCount", editEa + " ");
        map.put("destination", destination + " ");
        map.put("orderAllPrice", Price + " ");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
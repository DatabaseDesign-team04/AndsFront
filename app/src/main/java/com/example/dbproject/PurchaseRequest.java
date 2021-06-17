package com.example.dbproject;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PurchaseRequest extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://sunny819819.dothome.co.kr/Order.php";
    private Map<String, String> map;

    public PurchaseRequest(String receiverName, String receiverNum, String destination,
                           String prodName,int prodCount, int totalPrice, boolean couponUse, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("receiverName", receiverName);
        map.put("receiverNum", receiverNum);
        map.put("destination", destination);
        map.put("prodName", prodName);
        map.put("prodCount", prodCount+"");
        map.put("totalPrice", totalPrice+"");
        map.put("couponUse", couponUse+"");

    }

    @Override
    protected Map<String, String> getParams() {
        return map;
    }
}
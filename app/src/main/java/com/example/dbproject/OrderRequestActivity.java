package com.example.dbproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderRequestActivity extends AppCompatActivity {

    private TextView et_dateText, et_editEa, et_Price, et_destination;
    private Button btn_Order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //아이디 값 찾아주기
        et_dateText = findViewById(R.id.dateText);
        et_editEa = findViewById(R.id.editEa);
        et_Price = findViewById(R.id.Price);
        et_destination = findViewById(R.id.destination);

        // 구매하기 버튼 클릭 시 수행
        btn_Order = findViewById(R.id.btn_purchase);
        btn_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String dateText = et_dateText.getText().toString();
                int editEa = Integer.parseInt(et_editEa.getText().toString());
                int Price = Integer.parseInt(et_Price.getText().toString());
                String destination = et_destination.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 회원등록에 성공한 경우
                                Toast.makeText(getApplicationContext(), "회원 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(OrderRequestActivity.this, OrderRequest.class);
                                startActivity(intent);
                            } else { // 회원등록에 실패한 경우
                                Toast.makeText(getApplicationContext(), "회원 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                // 서버로 Volley를 이용해서 요청을 함.

                OrderRequest orderRequest = new OrderRequest(dateText, editEa, Price, destination, responseListener);
                RequestQueue queue = Volley.newRequestQueue(OrderRequestActivity.this);
                queue.add(orderRequest);

            }
        });

        // 쇼핑계속
        Button shopping_btn = (Button) findViewById(R.id.shopping_btn);
        shopping_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                startActivity(intent);
            }
        });
    }

}
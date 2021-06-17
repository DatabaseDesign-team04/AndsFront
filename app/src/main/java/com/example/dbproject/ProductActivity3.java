package com.example.dbproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

// MainActivity는 상품 소개 창
public class ProductActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button purchase_btn = (Button) findViewById(R.id.purshase_btn);
        purchase_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PurchaseActivity.class);
                startActivity(intent);
            }
        });

        // 장바구니 창은 일단 계획한게 아니었어서 보류
        Button cart_btn = (Button) findViewById(R.id.cart_btn);
        cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });
    }
}
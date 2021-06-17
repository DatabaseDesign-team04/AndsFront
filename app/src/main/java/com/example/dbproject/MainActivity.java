package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_login;
    private Button btn_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = findViewById(R.id.btn_login);

        // 로그인인 버튼을 클릭시 수행
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);//액티비티 이동
            }
        });

        btn_product = findViewById(R.id.btn_product8);
        btn_product.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                        startActivity(intent);
                    }
                }
        );

        Button btn_product1 = (Button) findViewById(R.id.btn_product1);
        btn_product1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity2.class);
                startActivity(intent);
            }
        });

        Button btn_product2 = (Button) findViewById(R.id.btn_product2);
        btn_product2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity3.class);
                startActivity(intent);
            }
        });
    }
}
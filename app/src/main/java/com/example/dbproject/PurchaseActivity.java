package com.example.dbproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PurchaseActivity extends AppCompatActivity {
    TextView productSum;
    TextView deliverPrice;
    TextView totalPrice;
    TextView cuponContext;
    int TotalProductPrice = 2500;
    int TotalPurchasePrice = 0, deliver = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        productSum = (TextView) findViewById(R.id.totalProduct);
        deliverPrice = (TextView) findViewById(R.id.deliverPrice);
        totalPrice = (TextView) findViewById(R.id.Price);
        cuponContext = (TextView) findViewById(R.id.cuponContext);

        deliverPrice.setText(deliver + "원"); // 배송 금액 출력 : 2500원으로 고정
        productSum.setText(TotalProductPrice + "원"); // 총 상품 금액

        final int[] selectedItem = {0};

        Button cupon_btn = (Button) findViewById(R.id.cupon_btn);
        cupon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] cupons = new String[]{"신규 회원용 상품 10% 할인 쿠폰"};
                final double[] discounts = {0.1}; // 10% 할인
                AlertDialog.Builder dialouge = new AlertDialog.Builder(PurchaseActivity.this);
                dialouge.setTitle("쿠폰을 고르세요.")
                        .setSingleChoiceItems(cupons, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                selectedItem[0] = i;
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(PurchaseActivity.this
                                , cupons[selectedItem[0]]
                                , Toast.LENGTH_SHORT).show();
                                cuponContext.setText(cupons[selectedItem[0]]);
                                TotalPurchasePrice = (int) ((TotalProductPrice + deliver) - TotalProductPrice* discounts[selectedItem[0]]);
                                totalPrice.setText(TotalPurchasePrice + "원");
                            }
                        })
                        .setNeutralButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(PurchaseActivity.this
                                , "쿠폰 사용을 취소하였습니다."
                                , Toast.LENGTH_LONG).show();
                            }
                        });
                dialouge.create();
                dialouge.show();
            }
        });

        TotalPurchasePrice = TotalProductPrice + deliver;
        totalPrice.setText(TotalPurchasePrice + "원"); // 총 결제 금액

        Button purchase_btn = (Button) findViewById(R.id.btn_purchase);
        purchase_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                startActivity(intent);
            }
        });

    }
}
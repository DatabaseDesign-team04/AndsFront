package com.example.dbproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class PurchaseActivity extends AppCompatActivity {
    TextView productSum;
    TextView deliverPrice;
    TextView totalPrice;
    TextView cuponContext , prodName;

    int TotalProductPrice = 2500;
    int TotalPurchasePrice = 0, deliver = 2500;

    private EditText deliverName, deliverNum, deliverLocation, et_prodCount;
    private Button purchase_btn, coupon_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);


        //edit txt에 입력한 값 찾아주기
        deliverName = findViewById(R.id.deliverName);
        deliverNum = findViewById(R.id.deliverNumber);
        deliverLocation = findViewById(R.id.deliverLocation);
        et_prodCount = findViewById(R.id.editEa);

        //결제 금액 관련 변수 선언
        productSum = (TextView) findViewById(R.id.totalProduct);
        deliverPrice = (TextView) findViewById(R.id.deliverPrice);
        totalPrice = (TextView) findViewById(R.id.Price);
        cuponContext = (TextView) findViewById(R.id.cuponContext);
        prodName= findViewById(R.id.productName);

        final boolean[] couponUse = {true}; //쿠폰 사용 여부

        deliverPrice.setText(deliver + "원"); // 배송 금액 출력 : 2500원으로 고정
        productSum.setText(TotalProductPrice + "원"); // 총 상품 금액

        final int[] selectedItem = {0};

        coupon_btn = findViewById(R.id.cupon_btn);
        coupon_btn.setOnClickListener(new View.OnClickListener() {
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
                                couponUse[0] =false;
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

        //결제 버튼 클릭시 수행
        purchase_btn = findViewById(R.id.purchase_btn);
        purchase_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String receiverName = deliverName.getText().toString();
                String receiverNum = deliverNum.getText().toString();
                String destination = deliverLocation.getText().toString();
                String productName= prodName.getText().toString();
                int prodCount = Integer.parseInt(et_prodCount.getText().toString());
                int TotalPrice= Integer.parseInt(totalPrice.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 주문에 성공한 경우
                                Intent intent = new Intent(PurchaseActivity.this, OrderActivity.class);
                                startActivity(intent);
                            } else { // 주문에 실패한 경우
                                Toast.makeText(getApplicationContext(), "주문에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                // 서버로 Volley를 이용해서 요청을 함.
                PurchaseRequest purchaseRequest = new PurchaseRequest(receiverName,receiverNum,destination,productName, prodCount, TotalPrice,couponUse[0], responseListener);
                RequestQueue queue = Volley.newRequestQueue(PurchaseActivity.this);
                queue.add(purchaseRequest);

            }
        });

    }
}
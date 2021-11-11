package com.example.jangbogo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {
    private TextView txtOrderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successpayment);

        txtOrderList = findViewById(R.id.txtOrderList);

        txtOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //order액티비티로 넘어가기
            }
        });
    }
}

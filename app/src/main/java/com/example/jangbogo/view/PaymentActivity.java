package com.example.jangbogo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jangbogo.R;
import com.example.jangbogo.view.MapActivity;
import com.example.jangbogo.view.OrderActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PaymentActivity extends AppCompatActivity {
    private TextView txtOrderList;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successpayment);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        txtOrderList = findViewById(R.id.txtOrderList);

        txtOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                startActivity(intent);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mapPage :
                        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.orderPage :
                        Intent intent1 = new Intent(getApplicationContext(), OrderActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                }
                return true;
            }
        });
    }
}

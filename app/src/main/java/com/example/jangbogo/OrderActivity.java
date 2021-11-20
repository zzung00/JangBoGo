package com.example.jangbogo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private ImageButton btnMap;
    private List<Order> orders = new ArrayList<>();
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private MarketActivity marketActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);



        marketActivity = (MarketActivity) MarketActivity.activity;
        btnMap = (FloatingActionButton) findViewById(R.id.btnMap);
        orderAdapter = new OrderAdapter(orders);
        recyclerView = (RecyclerView)findViewById(R.id.orderRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(orderAdapter);


        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marketActivity.finish();
                finish();
            }
        });

    }
}

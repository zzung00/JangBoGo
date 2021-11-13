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
    private Market market;
    private List<OrderItem> orderItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        btnMap = (FloatingActionButton) findViewById(R.id.btnMap);
        //market = getIntent().getParcelableExtra("market");
        //orderItems = getIntent().getParcelableArrayListExtra("orderItems");
        orderAdapter = new OrderAdapter(orderItems);
        recyclerView = (RecyclerView)findViewById(R.id.orderRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(orderAdapter);


        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

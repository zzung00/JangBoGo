package com.example.jangbogo.view;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jangbogo.R;
import com.example.jangbogo.adapter.OrderAdapter;
import com.example.jangbogo.model.Order;
import com.example.jangbogo.service.JangBoGoAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    private ImageButton btnMap;
    private List<Order> orders = new ArrayList<>();
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private MarketActivity marketActivity;

    @RequiresApi(api = Build.VERSION_CODES.O)
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

        Call<List<Order>> call = JangBoGoAPI.getInstance().getService().getAllOrders();
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    List<Order> res = response.body();
                    orderAdapter.setOrders(res);
                    orderAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

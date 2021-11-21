package com.example.jangbogo.view;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jangbogo.R;
import com.example.jangbogo.adapter.OrderItemAdapter;
import com.example.jangbogo.model.Order;
import com.example.jangbogo.model.OrderItem;
import com.example.jangbogo.service.JangBoGoAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private List<OrderItem> orderItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private OrderItemAdapter orderItemAdapter;
    private Order order;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        order = getIntent().getParcelableExtra("order");
        btnBack = (FloatingActionButton) findViewById(R.id.btnBackInOrder);
        orderItemAdapter = new OrderItemAdapter(orderItems);
        recyclerView = (RecyclerView) findViewById(R.id.orderDetailRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(orderItemAdapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Call<List<OrderItem>> call = JangBoGoAPI.getInstance().getService().loadOrderItem(order.getId());
        call.enqueue(new Callback<List<OrderItem>>() {
            @Override
            public void onResponse(Call<List<OrderItem>> call, Response<List<OrderItem>> response) {
                if (response.isSuccessful()) {
                    List<OrderItem> res = response.body();
                    orderItemAdapter.setOrderItems(res);
                    orderItemAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<OrderItem>> call, Throwable t) {

            }
        });

    }
}

package com.example.jangbogo;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarketActivity extends AppCompatActivity implements  View.OnClickListener {
    private TextView marketName;
    private TextView item1;
    private TextView item2;
    private TextView item3;
    private TextView select;
    private ImageButton btnCart;
    private ImageButton btnBack;
    private ColorStateList def;
    private List<Stock> stocks = new ArrayList<>();
    private Retrofit retrofit;
    private JangBoGoService service;
    private RecyclerView recyclerView;
    private StockAdapter stockAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.2/").addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(JangBoGoService.class);
        Call<List<Stock>> call = service.loadAllStockByMarketId();
        call.enqueue(new Callback<List<Stock>>() {
            @Override
            public void onResponse(Call<List<Stock>> call, Response<List<Stock>> response) {
                if (response.isSuccessful()) {
                    List<Stock> res = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Stock>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        stocks.add(new Stock(1, 3400, 2, new Product(1, "콜라", new Category(1, "음료"))));
        stockAdapter = new StockAdapter(getApplicationContext(), stocks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(stockAdapter);
        marketName = findViewById(R.id.marketName);
        marketName.setText(getIntent().getStringExtra("name"));
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);
        select = findViewById(R.id.select);
        def = item2.getTextColors();
        btnCart = findViewById(R.id.btnCart);
        btnBack = findViewById(R.id.btnBack1);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BasketActivity.class);
                startActivity(intent);

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.item1){
            select.animate().x(0).setDuration(100);
            item1.setTextColor(Color.WHITE);
            item2.setTextColor(Color.BLACK);
            item3.setTextColor(Color.BLACK);
        } else if (view.getId() == R.id.item2){
            item1.setTextColor(Color.BLACK);
            item2.setTextColor(Color.WHITE);
            item3.setTextColor(Color.BLACK);
            int size = item2.getWidth();
            select.animate().x(size).setDuration(100);
        } else if (view.getId() == R.id.item3){
            item1.setTextColor(Color.BLACK);
            item3.setTextColor(Color.WHITE);
            item2.setTextColor(Color.BLACK);
            int size = item2.getWidth() * 2;
            select.animate().x(size).setDuration(100);
        }
    }

}

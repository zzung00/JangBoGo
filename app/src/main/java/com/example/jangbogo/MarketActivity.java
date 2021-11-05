package com.example.jangbogo;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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
    private Retrofit retrofit;
    private JangBoGoService service;
    private List<Stock> stocks = new ArrayList<>();
    private Market market;
    private RecyclerView recyclerView;
    private StockAdapter stockAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        market = getIntent().getParcelableExtra("market");
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        stockAdapter = new StockAdapter(getApplicationContext(), stocks);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(stockAdapter);
        marketName = findViewById(R.id.marketName);
        marketName.setText(market.getName());
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

        stockAdapter.setOnItemClickListener(new StockAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Dialog dialog = new Dialog(MarketActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.activity_inform_purchase_dialog);
                dialog.getWindow().setLayout(900, 540);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
                dialog.show();
                Button btnAdd = dialog.findViewById(R.id.btnAdd);
                Button btnSub = dialog.findViewById(R.id.btnSub);
                Button btnCancle = dialog.findViewById(R.id.btnCancle);
                Button btnPut = dialog.findViewById(R.id.btnPut);
                EditText editCount = dialog.findViewById(R.id.editCount);

                btnCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });


        retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.2/").addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(JangBoGoService.class);
        Call<List<Stock>> call = service.loadAllStockByMarketId(market.getId());
        call.enqueue(new Callback<List<Stock>>() {
            @Override
            public void onResponse(Call<List<Stock>> call, Response<List<Stock>> response) {
                if (response.isSuccessful()) {
                    List<Stock> res = response.body();
                    stocks.addAll(res);
                    stockAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Stock>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
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
            stockAdapter.getFilter().filter("");
        } else if (view.getId() == R.id.item2){
            item1.setTextColor(Color.BLACK);
            item2.setTextColor(Color.WHITE);
            item3.setTextColor(Color.BLACK);
            int size = item2.getWidth();
            select.animate().x(size).setDuration(100);
            stockAdapter.getFilter().filter("음료");
        } else if (view.getId() == R.id.item3){
            item1.setTextColor(Color.BLACK);
            item3.setTextColor(Color.WHITE);
            item2.setTextColor(Color.BLACK);
            int size = item2.getWidth() * 2;
            select.animate().x(size).setDuration(100);
            stockAdapter.getFilter().filter("스낵");
        }
    }

}

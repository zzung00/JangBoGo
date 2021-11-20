package com.example.jangbogo;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartActivity extends AppCompatActivity {
    private Button btnPay, btnBack;
    private int sum = 0;
    private TextView txtTotalPrice;
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private ArrayList<CartItem> cartItems = new ArrayList<>();
    private MarketActivity marketActivity;
    private Retrofit retrofit;
    private JangBoGoService service;
    private Market market;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        marketActivity = (MarketActivity) MarketActivity.activity;
        market = getIntent().getParcelableExtra("market");
        cartItems = getIntent().getParcelableArrayListExtra("cartItems");
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        btnPay = findViewById(R.id.btnPayment);
        btnBack = findViewById(R.id.btnBack2);
        cartAdapter = new CartAdapter(cartItems);
        recyclerView = (RecyclerView)findViewById(R.id.cartRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);

        for (int i = 0; i < cartItems.size(); i++) {
            sum += cartItems.get(i).getPrice() * cartItems.get(i).getCount();
        }
        txtTotalPrice.setText(sum+"");

        btnPay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (cartItems.isEmpty()) {
                    showEmptyDialog();
                } else {

                    Gson gson = new GsonBuilder()
                            .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context)
                                    -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                            .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context)
                                    -> LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                            .registerTypeAdapter(LocalTime.class, (JsonDeserializer<LocalTime>) (json, typeOfT, context)
                                    -> LocalTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("HH:mm:ss")))
                            .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (localDateTime, typeOfT, context)
                                    -> new JsonPrimitive(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(localDateTime)))
                            .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (localDate, typeOfT, context)
                                    -> new JsonPrimitive(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate)))
                            .registerTypeAdapter(LocalTime.class, (JsonSerializer<LocalTime>) (localTime, typeOfT, context)
                                    -> new JsonPrimitive(DateTimeFormatter.ofPattern("HH:mm:ss").format(localTime)))
                            .create();

                    retrofit = new Retrofit.Builder().baseUrl("http://172.30.1.58/").addConverterFactory(GsonConverterFactory.create(gson)).build();
                    service = retrofit.create(JangBoGoService.class);
                    Cart cart = new Cart(cartAdapter.getItemCount(), sum, cartItems, market);
                    Call<Order> call = service.pay(cart);
                    call.enqueue(new Callback<Order>() {
                        @Override
                        public void onResponse(Call<Order> call, Response<Order> response) {
                            if (response.isSuccessful()) {
                                Order res = response.body();
                                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Order> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                cartItems.remove(position);
                marketActivity.getCartItems().remove(position);
                cartAdapter.notifyItemRemoved(position);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void showEmptyDialog() {
        Dialog dialog = new Dialog(CartActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_empty_cart_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, 400);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
        dialog.show();
        TextView txtOkay = dialog.findViewById(R.id.txtOkay);

        txtOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}

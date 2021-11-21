package com.example.jangbogo.view;

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

import com.example.jangbogo.R;
import com.example.jangbogo.adapter.CartAdapter;
import com.example.jangbogo.model.Cart;
import com.example.jangbogo.model.CartItem;
import com.example.jangbogo.model.Market;
import com.example.jangbogo.model.Order;
import com.example.jangbogo.service.JangBoGoAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    private Button btnPay, btnBack;
    private int sum = 0;
    private TextView txtTotalPrice;
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private ArrayList<CartItem> cartItems = new ArrayList<>();
    private MarketActivity marketActivity;
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
                    Cart cart = new Cart(cartAdapter.getItemCount(), sum, cartItems, market);
                    Call<Order> call = JangBoGoAPI.getInstance().getService().pay(cart);
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
                sum -= cartItems.get(position).getPrice() * cartItems.get(position).getCount();
                cartItems.remove(position);
                txtTotalPrice.setText(sum + "");
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

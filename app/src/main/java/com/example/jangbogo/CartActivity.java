package com.example.jangbogo;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private Button btnPay, btnBack;
    private TextView txtTotalPrice;
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private ArrayList<CartItem> cartItems = new ArrayList<>();
    private MarketActivity marketActivity;
    //private ArrayList<OrderItem> orderItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        marketActivity = (MarketActivity) MarketActivity.activity;
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        btnPay = findViewById(R.id.btnPayment);
        btnBack = findViewById(R.id.btnBack2);
        cartItems = getIntent().getParcelableArrayListExtra("cartItems");
        cartAdapter = new CartAdapter(cartItems);
        recyclerView = (RecyclerView)findViewById(R.id.cartRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);
        int sum = 0;

        for (int i = 0; i < cartItems.size(); i++) {
            sum += cartItems.get(i).getPrice() * cartItems.get(i).getCount();
        }
        txtTotalPrice.setText(sum+"");

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartItems.isEmpty()) {
                    showEmptyDialog();
                } else {
                    Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                    startActivity(intent);
                    finish();
                    //intent.putParcelableArrayListExtra("orderItems", orderItems);
                    //putExtra 이용하여 주문내역으로 값 전달
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

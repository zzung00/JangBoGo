package com.example.jangbogo;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MarketActivity extends AppCompatActivity implements  View.OnClickListener {
    private TextView item1;
    private TextView item2;
    private TextView item3;
    private TextView select;
    private ImageButton btnCart;
    private ImageButton btnBack;
    private ColorStateList def;
    private FragmentManager fragmentManager;
    private Item1Fragment item1Fragment;
    private Item2Fragment item2Fragment;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);
        select = findViewById(R.id.select);
        def = item2.getTextColors();
        fragmentManager = getSupportFragmentManager();
        item1Fragment = new Item1Fragment();
        item2Fragment = new Item2Fragment();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, item1Fragment).commitAllowingStateLoss();
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
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        transaction = fragmentManager.beginTransaction();

        if (view.getId() == R.id.item1){
            select.animate().x(0).setDuration(100);
            item1.setTextColor(Color.WHITE);
            item2.setTextColor(Color.BLACK);
            item3.setTextColor(Color.BLACK);
            transaction.replace(R.id.frameLayout, item1Fragment).commitAllowingStateLoss();
        } else if (view.getId() == R.id.item2){
            item1.setTextColor(Color.BLACK);
            item2.setTextColor(Color.WHITE);
            item3.setTextColor(Color.BLACK);
            int size = item2.getWidth();
            select.animate().x(size).setDuration(100);
            transaction.replace(R.id.frameLayout, item2Fragment).commitAllowingStateLoss();
        } else if (view.getId() == R.id.item3){
            item1.setTextColor(Color.BLACK);
            item3.setTextColor(Color.WHITE);
            item2.setTextColor(Color.BLACK);
            int size = item2.getWidth() * 2;
            select.animate().x(size).setDuration(100);
            transaction.replace(R.id.frameLayout, item1Fragment).commitAllowingStateLoss();
        }
    }

}

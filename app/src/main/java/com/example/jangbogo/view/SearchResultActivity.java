package com.example.jangbogo.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jangbogo.R;
import com.example.jangbogo.adapter.SearchAdapter;
import com.example.jangbogo.model.Market;
import com.example.jangbogo.model.SearchItem;
import com.example.jangbogo.service.JangBoGoService;
import com.example.jangbogo.view.MarketActivity;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchResultActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private List<SearchItem> searchItems = new ArrayList<>();
    private JangBoGoService service;
    private Retrofit retrofit;
    private String query;
    private ImageView btnBackInSearch;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        query = getIntent().getStringExtra("query");
        recyclerView = (RecyclerView) findViewById(R.id.searchRecyclerView);
        searchAdapter = new SearchAdapter(getApplicationContext(), searchItems);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(searchAdapter);
        btnBackInSearch = findViewById(R.id.btnBackInSearch);

        btnBackInSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Market market = searchAdapter.getSearchItems().get(position).getMarket();
                Intent intent = new Intent(getApplicationContext(), MarketActivity.class);
                intent.putExtra("market", market);
                startActivity(intent);
            }
        });

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

        retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.2/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        service = retrofit.create(JangBoGoService.class);

        Call<List<SearchItem>> call = service.search(query);
        call.enqueue(new Callback<List<SearchItem>>() {
            @Override
            public void onResponse(Call<List<SearchItem>> call, Response<List<SearchItem>> response) {
                if (response.isSuccessful()) {
                    List<SearchItem> res = response.body();
                    searchAdapter.setSearchItems(res);
                    searchAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<SearchItem>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}

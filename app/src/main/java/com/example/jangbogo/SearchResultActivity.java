package com.example.jangbogo;

import android.os.Bundle;
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

public class SearchResultActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private List<SearchItem> searchItems = new ArrayList<>();
    private JangBoGoService service;
    private Retrofit retrofit;
    private String query;

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

        retrofit = new Retrofit.Builder().baseUrl("http://172.20.10.2/").addConverterFactory(GsonConverterFactory.create()).build();
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

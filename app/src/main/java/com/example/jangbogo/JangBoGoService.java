package com.example.jangbogo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JangBoGoService {
    @GET("market/all")
    Call<List<Market>> listAllMarket();

    @GET("stock/stocks")
    Call<List<Stock>> loadAllStockByMarketId();
}

package com.example.jangbogo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JangBoGoService {
    @GET("market/all")
    Call<List<Market>> listAllMarket();

    @GET("stock/stocks")
    Call<List<Stock>> loadAllStockByMarketId(@Query("marketId") int id);

    @GET("search/query")
    Call<List<SearchItem>> search(@Query("query") String query);

    @POST("market/purchase")
    Call<Order> pay(@Body Cart cart);

    @GET("order/all")
    Call<List<Order>> getAllOrders();
}

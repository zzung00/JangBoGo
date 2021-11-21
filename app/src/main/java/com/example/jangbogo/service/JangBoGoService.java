package com.example.jangbogo.service;

import com.example.jangbogo.model.Cart;
import com.example.jangbogo.model.Market;
import com.example.jangbogo.model.Order;
import com.example.jangbogo.model.OrderItem;
import com.example.jangbogo.model.SearchItem;
import com.example.jangbogo.model.Stock;
import com.example.jangbogo.model.User;

import java.util.HashMap;
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

    @GET("order/orderItem")
    Call<List<OrderItem>> loadOrderItem(@Query("orderId") int id);

    @POST("user/signup")
    Call<User> signUp(@Body HashMap<String, Object> request);

    @GET("user/signin")
    Call<User> signIn(@Query("username") String username, @Query("password") String password);
}

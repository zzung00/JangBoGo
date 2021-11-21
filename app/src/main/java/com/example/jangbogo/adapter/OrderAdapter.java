package com.example.jangbogo.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jangbogo.R;
import com.example.jangbogo.model.Order;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private List<Order> orders = new ArrayList<>();

    public OrderAdapter(List<Order> orders) {
        super();
        this.orders = orders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_order_list, parent, false);
        OrderAdapter.ViewHolder vh = new OrderAdapter.ViewHolder(view);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.txtMarketLogoName.setText(order.getMarket().getName());
        holder.txtMarketName.setText(order.getMarket().getName());
        holder.txtOrderTotal.setText("총 " + String.valueOf(order.getTotal()) + "원");
        holder.txtOrderDate.setText(order.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMarketLogoName;
        TextView txtMarketName;
        TextView txtOrderTotal;
        TextView txtOrderDate;
        Button btnOrderDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMarketLogoName = itemView.findViewById(R.id.txtMarketLogoName);
            txtMarketName = itemView.findViewById(R.id.txtMarketName);
            txtOrderTotal = itemView.findViewById(R.id.txtOrderTotal);
            txtOrderDate = itemView.findViewById(R.id.txtOrderDate);

        }
    }
}

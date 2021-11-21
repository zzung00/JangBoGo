package com.example.jangbogo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jangbogo.R;
import com.example.jangbogo.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {
    private Context context;
    private List<OrderItem> orderItems = new ArrayList<>();

    public OrderItemAdapter(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.order_detail_list_item, parent, false);
        OrderItemAdapter.ViewHolder vh = new OrderItemAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderItem orderItem = orderItems.get(position);
        holder.txtOrderProduct.setText(orderItem.getProduct().getName());
        holder.txtOrderCount.setText(Integer.toString(orderItem.getCount()));
        holder.txtOrderTotal.setText("총 " + orderItem.getCount() * orderItem.getPrice() +"원");
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtOrderProduct;
        TextView txtOrderCount;
        TextView txtOrderTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrderProduct = itemView.findViewById(R.id.txtOrderProduct);
            txtOrderCount = itemView.findViewById(R.id.txtOrderCount);
            txtOrderTotal = itemView.findViewById(R.id.orderTotal);
        }
    }
}

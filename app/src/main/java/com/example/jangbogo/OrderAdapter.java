package com.example.jangbogo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private List<OrderItem> orderItems = new ArrayList<>();

    public OrderAdapter(List<OrderItem> orderItems) {
        super();
        this.orderItems = orderItems;
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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderItem orderItem = orderItems.get(position);
        //holder.txtMarketLogoName.setText();

    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMarketLogoName;
        TextView txtMarketName;
        TextView txtOrderTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMarketLogoName = itemView.findViewById(R.id.txtMarketLogoName);
            txtMarketName = itemView.findViewById(R.id.txtMarketName);
            txtOrderTotal = itemView.findViewById(R.id.txtOrderTotal);
        }
    }
}

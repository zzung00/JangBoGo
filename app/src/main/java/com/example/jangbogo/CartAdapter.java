package com.example.jangbogo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<CartItem> cartItems = new ArrayList<>();

    public CartAdapter(List<CartItem> cartItems) {
        super();
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_cart_list, parent, false);
        CartAdapter.ViewHolder vh = new CartAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        //이미지 불러오는 코드 들어가야함
        holder.txtListName.setText(cartItem.getProduct().getName());
        holder.txtListCount.setText(Integer.toString(cartItem.getCount()));
        holder.txtTotal.setText(Integer.toString(cartItem.getPrice() * cartItem.getCount()));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCartList;
        TextView txtListName;
        TextView txtListCount;
        TextView txtTotal;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCartList = itemView.findViewById(R.id.imgCartList);
            txtListName = itemView.findViewById(R.id.txtListName);
            txtListCount = itemView.findViewById(R.id.txtListCount);
            txtTotal = itemView.findViewById(R.id.txtTotal);
        }
    }
}
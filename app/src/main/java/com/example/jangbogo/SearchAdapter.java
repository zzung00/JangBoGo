package com.example.jangbogo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<SearchItem> searchItems = new ArrayList<>();

    public SearchAdapter(Context context, List<SearchItem> searchItems) {
        this.context = context;
        this.searchItems = searchItems;
    }

    public void setSearchItems(List<SearchItem> searchItems) {
        this.searchItems = searchItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_search_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchItem searchItem = searchItems.get(position);
        holder.marketNameInSearch.setText(searchItem.getMarket().getName());

        if (searchItem.getStock() != null) {
            holder.progressBar.setVisibility(View.VISIBLE);
            holder.possessName.setText(searchItem.getStock().getProduct().getName());
            if (searchItem.getStock().getCount() <= 30) {
                holder.txtPossibility.setText("현재 재고 보유 가능성 낮음");
                holder.progressBar.setProgress(30);
                holder.progressBar.getIndeterminateDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            }else if (searchItem.getStock().getCount() >= 31 || searchItem.getStock().getCount() <= 60){
                holder.txtPossibility.setText("현재 재고 보유 가능성 있음");
                holder.progressBar.setProgress(60);
                holder.progressBar.getIndeterminateDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
            } else {
                holder.txtPossibility.setText("현재 재고 보유 가능성 높음");
                holder.progressBar.setProgress(100);
                holder.progressBar.getIndeterminateDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
            }
        }else {
            holder.marketNameInSearch.setTextSize(25);
        }
    }

    @Override
    public int getItemCount() {
        return searchItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView marketNameInSearch;
        TextView possessName;
        TextView txtPossibility;
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            marketNameInSearch = itemView.findViewById(R.id.marketNameInSearch);
            possessName = itemView.findViewById(R.id.possessName);
            txtPossibility = itemView.findViewById(R.id.txtPossbility);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

}

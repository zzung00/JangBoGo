package com.example.jangbogo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder> implements Filterable {
    private Context context;
    private List<Stock> filterList = new ArrayList<>();
    private List<Stock> stocks = new ArrayList<>();
    private StockFilter stockFilter = new StockFilter();

    public StockAdapter(Context context, List<Stock> stocks) {
        super();
        this.context = context;
        this.stocks = stocks;
        this.filterList = stocks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_stock, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Stock stock = filterList.get(position);
        holder.stockName.setText(stock.getProduct().getName());
        holder.stockPrice.setText(Integer.toString(stock.getPrice()));
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    @Override
    public Filter getFilter() {
        if (stockFilter == null) {
            stockFilter = new StockFilter();
        }
        return stockFilter;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView stockImg;
        TextView stockName, stockPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stockImg = itemView.findViewById(R.id.stockImg);
            stockName = itemView.findViewById(R.id.stockName);
            stockPrice = itemView.findViewById(R.id.stockPrice);

        }
    }

    class StockFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();

            if (charSequence == null || charSequence.length() == 0) {
                filterResults.values = stocks;
                filterResults.count = stocks.size();
            } else {
                List<Stock> filteringList = new ArrayList<>();
                for (Stock s : stocks) {

                    if (s.getProduct().getCategory().getName().toUpperCase().contains(charSequence.toString().toUpperCase())) {
                        filteringList.add(s);
                        System.out.println(s.getProduct().getName());
                    }
                }
                filterResults.values = filteringList;
                filterResults.count = filteringList.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filterList = (ArrayList<Stock>)results.values;
            notifyDataSetChanged();
        }
    }
}
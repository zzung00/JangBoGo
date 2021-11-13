package com.example.jangbogo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

public class SearchAdapter extends BaseAdapter implements Filterable {
    private ArrayList<Market> markets = new ArrayList<>();
    private ArrayList<Market> filteredList = markets;
    private Filter listFilter;

    public SearchAdapter() {

    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_map, parent, false);
        }

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter();
        }
        return listFilter;
    }

    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                results.values = markets;
                results.count = markets.size();
            }else {
                ArrayList<Market> marketList = new ArrayList<>();

                for (Market m : markets) {
                    if (m.getName().toUpperCase().contains(constraint.toString().toUpperCase())) {
                        markets.add(m);
                    }
                }
                results.values = marketList;
                results.count = marketList.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (ArrayList<Market>) results.values;

            if (results.count > 0) {
                notifyDataSetChanged();
            }else {
                notifyDataSetInvalidated();
            }
        }
    }
}

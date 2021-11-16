package com.example.jangbogo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

public class SearchAdapter extends CursorAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private SearchView searchView;

    public SearchAdapter(Context context, Cursor c, SearchView s) {
        super(context, c, false);
        this.context = context;
        this.searchView = s;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.activity_search_item, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String market = cursor.getString(cursor.getColumnIndexOrThrow("marketName"));
        String product = cursor.getString(cursor.getColumnIndexOrThrow("productName"));
        TextView marketName = (TextView) view.findViewById(R.id.marketNameInSearch);
        marketName.setText(market);
        TextView possessName = (TextView) view.findViewById(R.id.possessName);
        possessName.setText(product);
        TextView possibility = (TextView) view.findViewById(R.id.txtPossbility);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtMarket = (TextView) view.findViewById(R.id.marketNameInSearch);
                searchView.setIconified(true);
            }
        });
    }
}

package com.example.jangbogo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SearchView;

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
        View view = layoutInflater.inflate(R.layout )
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}

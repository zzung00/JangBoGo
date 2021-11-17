package com.example.jangbogo;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchItem implements Parcelable {
    private Stock stock;
    private Market market;

    public SearchItem(Stock stock, Market market) {
        this.stock = stock;
        this.market = market;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Stock getStock() {
        return stock;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public Market getMarket() {
        return market;
    }

    protected SearchItem(Parcel in) {
        market = in.readParcelable(Market.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(market, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SearchItem> CREATOR = new Creator<SearchItem>() {
        @Override
        public SearchItem createFromParcel(Parcel in) {
            return new SearchItem(in);
        }

        @Override
        public SearchItem[] newArray(int size) {
            return new SearchItem[size];
        }
    };
}

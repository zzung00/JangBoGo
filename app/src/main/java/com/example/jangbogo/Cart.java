package com.example.jangbogo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Cart implements Parcelable {
    private Integer id;
    private int count;
    private int total;
    private ArrayList<CartItem> cartItems;
    private Market market;

    public Cart(int count, int total, ArrayList<CartItem> cartItems, Market market) {
        this.count = count;
        this.total = total;
        this.cartItems = cartItems;
        this.market = market;
    }


    protected Cart(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        count = in.readInt();
        total = in.readInt();
        cartItems = in.createTypedArrayList(CartItem.CREATOR);
        market = in.readParcelable(Market.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeInt(count);
        dest.writeInt(total);
        dest.writeTypedList(cartItems);
        dest.writeParcelable(market, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public List<CartItem> getCartItem() {
        return cartItems;
    }

    public void setCartItem(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

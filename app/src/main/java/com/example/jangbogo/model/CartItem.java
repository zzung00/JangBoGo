package com.example.jangbogo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CartItem implements Parcelable {
    private Integer id;
    private Product product;
    private int count;
    private int price;

    public CartItem(Integer id, Product product, int count, int price) {
        this.id = id;
        this.product = product;
        this.count = count;
        this.price = price;
    }

    protected CartItem(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        product = in.readParcelable(Product.class.getClassLoader());
        count = in.readInt();
        price = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeParcelable(product, flags);
        dest.writeInt(count);
        dest.writeInt(price);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

}

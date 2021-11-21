package com.example.jangbogo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderItem implements Parcelable {
    private Integer id;
    private int count;
    private int price;
    private Product product;

    public OrderItem(Integer id, int count, int price, Product product) {
        this.id = id;
        this.count = count;
        this.price = price;
        this.product = product;
    }

    protected OrderItem(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        count = in.readInt();
        price = in.readInt();
        product = in.readParcelable(Product.class.getClassLoader());
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
        dest.writeInt(price);
        dest.writeParcelable(product, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderItem> CREATOR = new Creator<OrderItem>() {
        @Override
        public OrderItem createFromParcel(Parcel in) {
            return new OrderItem(in);
        }

        @Override
        public OrderItem[] newArray(int size) {
            return new OrderItem[size];
        }
    };

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}

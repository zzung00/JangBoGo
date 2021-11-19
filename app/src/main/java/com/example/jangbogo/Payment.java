package com.example.jangbogo;

import android.os.Parcel;
import android.os.Parcelable;

public class Payment implements Parcelable {
    private Integer id;
    private int count;
    private int price;
    private Product product;
    private Market market;

    public Payment() {
    }

    public void setCartItems(Integer id, int count, int price, Product product, Market market) {
        this.id = id;
        this.price = price;
        this.count = count;
        this.product = product;
        this.market = market;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    protected Payment(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        count = in.readInt();
        price = in.readInt();
        product = in.readParcelable(Product.class.getClassLoader());
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
        dest.writeInt(price);
        dest.writeParcelable(product, flags);
        dest.writeParcelable(market, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Payment> CREATOR = new Creator<Payment>() {
        @Override
        public Payment createFromParcel(Parcel in) {
            return new Payment(in);
        }

        @Override
        public Payment[] newArray(int size) {
            return new Payment[size];
        }
    };
}

package com.example.jangbogo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.util.List;

public class Order implements Parcelable {
    private Integer id;
    private LocalDate date;
    private int total;
    private Market market;
    private List<OrderItem> orderItem;

    protected Order(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        total = in.readInt();
        market = in.readParcelable(Market.class.getClassLoader());
        orderItem = in.createTypedArrayList(OrderItem.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeInt(total);
        dest.writeParcelable(market, flags);
        dest.writeTypedList(orderItem);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public Market getMarket() {
        return market;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }
}

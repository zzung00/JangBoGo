package com.example.jangbogo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Market implements Parcelable {
    private Integer id;
    private String name;
    private double lat;
    private double lng;
    private String operHour;
    private String tel;
    private String address;

    public Market(Integer id, String name, double lat, double lng, String operHour, String tel, String address) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.operHour = operHour;
        this.tel = tel;
        this.address = address;
    }

    protected Market(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
        operHour = in.readString();
        tel = in.readString();
        address = in.readString();
    }

    public static final Creator<Market> CREATOR = new Creator<Market>() {
        @Override
        public Market createFromParcel(Parcel in) {
            return new Market(in);
        }

        @Override
        public Market[] newArray(int size) {
            return new Market[size];
        }
    };

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLng() {
        return lng;
    }

    public void setOperHour(String operHour) {
        this.operHour = operHour;
    }

    public String getOperHour() {
        return operHour;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

    public void setAddress(String address) { this.address = address;  }

    public String getAddress() {
        return address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeString(operHour);
        dest.writeString(tel);
        dest.writeString(address);
    }
}

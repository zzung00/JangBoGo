package com.example.jangbogo;

public class Market {
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
}

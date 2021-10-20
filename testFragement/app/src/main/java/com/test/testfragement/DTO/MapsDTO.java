package com.test.testfragement.DTO;

public class MapsDTO {
    private String kind; // 종류 : 병원 체육시설 약국
    private double lat; // 위도
    private double lng; // 경도
    private String name; // 병원 이름
    private String phone; // 번호

    public MapsDTO(){

    }

    public MapsDTO(String kind, double lat, double lng, String name, String phone) {
        this.kind = kind;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.phone = phone;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "MapsDTO{" +
                "kind='" + kind + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

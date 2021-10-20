package com.test.testfragement.DTO;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MyItem implements ClusterItem {

    private final LatLng position;
    private final String title;
    private final String phone;
    private final BitmapDescriptor des;


    public MyItem(double lat, double lng, String title, String phone, BitmapDescriptor des) {
        this.des = des;
        this.position = new LatLng(lat, lng);
        this.title = title;
        this.phone = phone;
    }


    @Override
    public LatLng getPosition() {
        return position;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSnippet() {
        return null;
    }

    public BitmapDescriptor getDes(){
        return des;
    }

    public String getPhone(){
        return phone;
    }
}
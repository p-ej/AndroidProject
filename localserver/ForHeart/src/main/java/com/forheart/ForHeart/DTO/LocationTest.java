package com.forheart.ForHeart.DTO;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "location2")
public class LocationTest {
	private String kind; // 종류 : 병원 체육시설 약국
	private Double lat; // 위도
	private Double lng; // 경도
	private String name; // 병원 이름
	private String phone; // 번호

	public LocationTest(){

	}
	
	public LocationTest(String kind, Double lat, Double lng, String name, String phone) {
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

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
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
}

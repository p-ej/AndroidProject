package com.forheart.ForHeart.DTO;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "location2")
public class LocationTest {
	private String kind; // ���� : ���� ü���ü� �౹
	private Double lat; // ����
	private Double lng; // �浵
	private String name; // ���� �̸�
	private String phone; // ��ȣ

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

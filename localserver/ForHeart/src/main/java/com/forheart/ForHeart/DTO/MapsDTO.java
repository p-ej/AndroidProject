package com.forheart.ForHeart.DTO;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "location2")
public class MapsDTO {
	private String kind; // ���� : ���� ü���ü� �౹
	private double lat; // ����
	private double lng; // �浵
	private String name; // ���� �̸�
	private String phone; // ��ȣ

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
}

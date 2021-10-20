package com.forheart.ForHeart.DTO;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "test")
public class TestDTO {

	private String test;
	private String test2;
	
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getTest2() {
		return test2;
	}

	public void setTest2(String test2) {
		this.test2 = test2;
	}
	
	

}
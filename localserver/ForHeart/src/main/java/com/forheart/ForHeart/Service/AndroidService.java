package com.forheart.ForHeart.Service;

import java.util.List;

import com.forheart.ForHeart.DTO.LocationTest;
import com.forheart.ForHeart.DTO.MapsDTO;
import com.forheart.ForHeart.DTO.TestDTO;

public interface AndroidService {
	public TestDTO insertTest(String test); // test mongoDB insert Method
	public TestDTO getTest(String test); // test mongoDB select Method
	public LocationTest selectTest(String name); // test 메소드
	public List<MapsDTO> findAll(String kind); // 실제 데이터를 불러오는 메소드
}

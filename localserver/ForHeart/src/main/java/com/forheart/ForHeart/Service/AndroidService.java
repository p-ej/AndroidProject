package com.forheart.ForHeart.Service;

import java.util.List;

import com.forheart.ForHeart.DTO.LocationTest;
import com.forheart.ForHeart.DTO.MapsDTO;
import com.forheart.ForHeart.DTO.TestDTO;

public interface AndroidService {
	public TestDTO insertTest(String test); // test mongoDB insert Method
	public TestDTO getTest(String test); // test mongoDB select Method
	public LocationTest selectTest(String name); // test �޼ҵ�
	public List<MapsDTO> findAll(String kind); // ���� �����͸� �ҷ����� �޼ҵ�
}

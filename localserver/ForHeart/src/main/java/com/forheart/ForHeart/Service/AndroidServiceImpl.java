package com.forheart.ForHeart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forheart.ForHeart.DAO.AndroidDaoImpl;
import com.forheart.ForHeart.DTO.LocationTest;
import com.forheart.ForHeart.DTO.MapsDTO;
import com.forheart.ForHeart.DTO.TestDTO;

@Service
public class AndroidServiceImpl implements AndroidService{

	// dao 계층과 통신을 위한 DI
	@Autowired
	public AndroidDaoImpl androidDao;
	
	@Override
	public TestDTO insertTest(String test) {
		return androidDao.insertTest(test);
	}

	@Override
	public TestDTO getTest(String test) {
		return androidDao.getTest(test);
	}

	@Override
	public LocationTest selectTest(String name) {
		return androidDao.selectTest(name);
	}

	@Override
	public List<MapsDTO> findAll(String kind) {
		return androidDao.findAll(kind);
	}

}

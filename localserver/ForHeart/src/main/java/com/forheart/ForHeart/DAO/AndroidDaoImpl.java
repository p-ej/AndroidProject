package com.forheart.ForHeart.DAO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.forheart.ForHeart.DTO.LocationTest;
import com.forheart.ForHeart.DTO.MapsDTO;
import com.forheart.ForHeart.DTO.TestDTO;

import java.util.*;

@Repository
public class AndroidDaoImpl implements AndroidDao{

	@Autowired
	MongoTemplate mongoTemplate;

	// Test MongoDB 안에 데이터가 추가되는지 확인
	public TestDTO insertTest(String test) {

		Map<String, String> testMap = new HashMap<String, String>();

		testMap.put("test2", test);

		mongoTemplate.insert(testMap, "test"); // (컬렉션 안의  컬럼 : 데이터값, 컬렉션 이름)
		
		////////////////////////////////////////////////
		
		TestDTO testDTO = getTest(test);

		return testDTO;

	}
	
	// Test MongoDB 안에 데이터가 검색되는지 확인
	public TestDTO getTest(String test) {
		
		Query query = new Query(Criteria.where("test2").is(test));
		System.out.println(mongoTemplate.findOne(query, TestDTO.class, "test").getTest2()); // 마지막은 컬렉션 이름 
		return mongoTemplate.findOne(query, TestDTO.class, "test"); // 마지막은 컬렉션 이름 
		
	}

	// MongoDB 안에 저장된 위도,경도가 잘 검색되는지 확인 
	@Override
	public LocationTest selectTest(String name) {
		
		Query query = new Query(Criteria.where("name").is(name)); // 이름 (나중에 kind로 변경)
		System.out.println(query);
		System.out.println(mongoTemplate.findOne(query, LocationTest.class, "location2").getName());
		return mongoTemplate.findOne(query, LocationTest.class, "location2"); // 최상위 컬렉션은 location

	}

	
	// 목록 조회
	@Override
	public List<MapsDTO> findAll(String kind){
		List<MapsDTO> list = mongoTemplate.find(Query.query(Criteria.where("kind").is(kind)), MapsDTO.class);
		
		for(int i =0;i<10;i++) {
			System.out.println("find list : "+list.get(i).getName());
		}
		return list;
	}
	
	// 데이터 조회
//	public List<MapsDTO> findondlist(String name){
//		List<MapsDTO> list = mongoTemplate.find(Query.query(Criteria.where("name").is(name)), MapsDTO.class);
//		
//		System.out.println("find list : "+list.get(0).getName());
//		System.out.println("find list : "+list.get(0).getKind());
//		System.out.println("find list : "+list.get(0).getLat());
//		System.out.println("find list : "+list.get(0).getLng());
//		System.out.println("find list : "+list.get(0).getPhone());
//
//		return list;
//	}
	
}


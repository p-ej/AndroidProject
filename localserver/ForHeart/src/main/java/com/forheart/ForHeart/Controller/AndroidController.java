package com.forheart.ForHeart.Controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.forheart.ForHeart.DAO.AndroidDaoImpl;
import com.forheart.ForHeart.DTO.LocationTest;
import com.forheart.ForHeart.DTO.MapsDTO;
import com.forheart.ForHeart.DTO.TestDTO;
import com.forheart.ForHeart.Service.AndroidServiceImpl;


//안드로이드(클라이언트)에서 http 요청(URL)을 받을 컨트롤러
@Controller
public class AndroidController {
	
	// log 출력할 static 객체 생성 
	public static final Logger logger = LoggerFactory.getLogger(AndroidController.class);
	
	// Server 계층과 통신을 위한 DI
	@Autowired
	AndroidServiceImpl androidService;

	// 해당 매핑 주소로 값을 전달받을때 처리
	@RequestMapping(value = "/AndroidTest", method = RequestMethod.POST)
	@ResponseBody // 데이터를 Json 객체로 반환하기 위한 어노테이션
	public Map<String, String> androidTest(HttpServletRequest request) {
		
		logger.info("Get URL : '{}'", request.getRequestURL());
		logger.info("Get URI : '{}'", request.getRequestURI());
		
		System.out.println("in AndroidTest : " + request.getParameter("test2")); // 이 테스트 스트링은 컬렉션인가 열인가 

//		Map<String, String> result = new HashMap<String, String>();

//		String result = androidDao.insertTest(request.getParameter("test"));

		TestDTO testDTO = new TestDTO(); 
		testDTO = androidService.insertTest(request.getParameter("test2")); // text 의 값.
		
		Map<String, String> result = new HashMap<String, String>();
		
		if (testDTO == null) {
			logger.info("result 의 값이 존재하지 않음 (데이터가 비어있다.) result : '{}'", result);
			return null;
		}
		
		result.put("test2", testDTO.getTest2());
		logger.info("result 의 값 result : '{}'", result);
		return result;
	}
	
	
	// 찾을 병원의 데이터를 찾을 컨트롤러 
	@RequestMapping(value = "/ForHeartTest", method = RequestMethod.POST)
	@ResponseBody // 데이터를 Json 객체로 반환하기 위한 어노테이션
	public List<MapsDTO> androidTest2(HttpServletRequest request) {
		System.out.println("in AndroidTest : " + request.getParameter("kind")); // 안드로이드에서 전송된 키 값 파라미터로 받음.

		logger.info("Get URL : '{}'", request.getRequestURL());
		logger.info("Get URI : '{}'", request.getRequestURI());
		
//		MapsDTO mapsDTO = new MapsDTO();
		List<MapsDTO> list = androidService.findAll(request.getParameter("kind")); // 안드로이드에서 입력된 값을 몽고에 전송. (병원이름을 검색 -> 폰번호 가져옴)
//		List<MapsDTO> list = androidDao.findondlist(request.getParameter("name"));
//		Map<String, String> result2 = new HashMap<String, String>();
		
		if (list == null) {
			logger.info("list 의 값이 존재하지 않음 (데이터가 비어있다.) list : '{}'", list);
			return null;
		}
		
//		result2.put("name", MapsDTO.getName());
		
		logger.info("list 의 값 list : '{}'", list);
		return list; // list 라이브러리 반환
	}

}

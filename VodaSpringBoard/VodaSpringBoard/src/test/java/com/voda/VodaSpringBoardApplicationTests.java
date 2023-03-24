package com.voda;

<<<<<<< HEAD
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VodaSpringBoardApplicationTests {

	@Test
	void contextLoads() {
	}

}
=======
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import com.voda.dto.MemberDTO;
import com.voda.mapper.MemberMapper;

@SpringBootTest
class SpringBoardProject2ApplicationTests {
	
	@Autowired
	MemberMapper mapper;
	
	@DisplayName("회원정보검색 테스트")
	@Test
	void searchMemberTest() {
		System.out.println("회원정보 검색 테스트");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("kind", "id");
		map.put("search", "0");
		List<MemberDTO> list = mapper.searchMember(map);
		assertEquals(0, list.size(),"전체 조회 오류");
	}
	
	@DisplayName("전체 정보 조회 테스트")
	@Test
	void selectAllMember() {
		List<MemberDTO> list = mapper.selectAllMember();
		System.out.println("전체회원정보 검색 테스트");
		assertNotEquals(0, list.size());
		fail();
	}
	
	@BeforeEach
	void beforeEach() {
		System.out.println("테스트 메서드 수행전에 실행되는 메서드");
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("테스트 메서드 수행후에 실행되는 메서드");
	}

	@BeforeAll
	void beforeAll() {
		System.out.println("beforeAll");
	}
}

>>>>>>> e484cca1b55f86259538034f4b577b15647d80d0

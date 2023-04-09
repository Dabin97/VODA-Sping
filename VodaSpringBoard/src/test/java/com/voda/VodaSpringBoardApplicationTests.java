package com.voda;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import com.voda.dto.MemberDTO;
import com.voda.mapper.BoardMapper;
import com.voda.service.BoardService;

@SpringBootTest
class VodaSpringBoardApplicationTests {

	@Autowired
    private BoardMapper mapper;

    @Autowired
    private BoardService boardService;

    @Autowired
    private MainController mainController;

    @Autowired
    private HttpServletRequest request;

    private HttpSession session;
    
    @BeforeEach
    void setUp() {
        session = request.getSession();
        MemberDTO dto = new MemberDTO();
        dto.setId("admin");
        session.setAttribute("member", dto);
    }
    

    @DisplayName("찜 기능 테스트")
    @Test
    @Order(1)
    void boardContentHeartTest() {
        System.out.println("찜 기능 테스트");
        int bno = 1;

        ResponseEntity<String> response = null;
        try {
            response = mainController.boardContentHeart(bno, session);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("해당 컨텐츠에 찜을 하셨습니다.", response.getBody());
    }

    @DisplayName("찜 삭제 테스트")
    @Test
    @Order(2)
    void boardContentHeartDeleteTest() {
        System.out.println("찜 삭제 테스트");
        int bno = 1;

        ResponseEntity<String> response = null;
        try {
            response = mainController.boardContentHeart(bno, session);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("해당 컨텐츠에 찜을 해제하셨습니다.", response.getBody());
    }
	
}

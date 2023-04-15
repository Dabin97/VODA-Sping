// package com.voda;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.servlet.ModelAndView;
//import static org.junit.jupiter.api.Assertions.fail;
//
//import com.voda.controller.admincontroller.AdminMemberController;
//import com.voda.dto.MemberDTO;
//import com.voda.dto.SecessionDTO;
//import com.voda.service.MemberService;
//import com.voda.service.SecessionService;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import com.voda.vo.PaggingVO;
//
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockHttpSession;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.MethodOrderer;
//import com.voda.mapper.BoardMapper;
//import com.voda.service.BoardService;
//
//@TestMethodOrder(MethodOrderer.Alphanumeric.class)
//@SpringBootTest
//class VodaSpringBoardApplicationTests {
//
//
//	
//	   @Autowired
//	    AdminMemberController adminMemberController;
//	     
//	     @Autowired
//		MemberService memberService; 
//	    
//	    @Mock
//	    private SecessionService secessionService;
//
//	    private MockMvc mockMvc;
//	  
//	  @BeforeEach void setUp() { 
//	  
//	  }
//	  
//	  @DisplayName("관리자페이지 회원 삭제 테스트")
//	    @Test
//	    @Order(1)
//	    public void deleteMemberTest() {
//	        String[] id = {"2463405740"};
//
//	        ResponseEntity<String> response = null;
//	        try {
//	        	
//	            response = adminMemberController.deleteMember(id);
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//
//	        System.out.println(response);
//	    }
//	 
 

	
    




    
//
//    @DisplayName("찜 기능 테스트")
//    @Test
//    @Order(2)
//    void boardContentHeartTest() {
//        System.out.println("찜 기능 테스트");
//        int bno = 1;
//
//        ResponseEntity<String> response = null;
//        try {
//            response = mainController.boardContentHeart(bno, session);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("해당 컨텐츠에 찜을 하셨습니다.", response.getBody());
//    }
//
//    @DisplayName("찜 삭제 테스트")
//    @Test
//    @Order(3)
//    void boardContentHeartDeleteTest() {
//        System.out.println("찜 삭제 테스트");
//        int bno = 1;
//
//        ResponseEntity<String> response = null;
//        try {
//            response = mainController.boardContentHeart(bno, session);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("해당 컨텐츠에 찜을 해제하셨습니다.", response.getBody());
//    }
//	
//}

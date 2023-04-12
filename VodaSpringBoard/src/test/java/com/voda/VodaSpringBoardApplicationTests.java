 package com.voda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;
import static org.junit.jupiter.api.Assertions.fail;

import com.voda.dto.MemberDTO;
import com.voda.dto.SecessionDTO;
import com.voda.service.MemberService;
import com.voda.service.SecessionService;

import static org.assertj.core.api.Assertions.assertThat;
import com.voda.vo.PaggingVO;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.Order;

import com.voda.mapper.BoardMapper;
import com.voda.service.BoardService;


class VodaSpringBoardApplicationTests {


    @Mock
    private MemberService memberService;
    
    @Mock
    private SecessionService secessionService;

    @InjectMocks
    private MainController mainController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        session = new MockHttpSession();
        MemberDTO dto = new MemberDTO();
        dto.setId("admin");
        session.setAttribute("member", dto);
    }
    
	@Autowired
    private BoardMapper mapper;

    @Autowired
    private BoardService boardService;

    @Autowired
    private HttpServletRequest request;

    private HttpSession session;
 

    @DisplayName("탈퇴대기회원조회 테스트")
    @Test
    @Order(1)
    void secessionList() {
        // given
        List<SecessionDTO> list = Arrays.asList(new SecessionDTO(), new SecessionDTO(), new SecessionDTO());
        when(secessionService.selectMemberList(anyInt(), anyInt())).thenReturn(list); // secessionService에 대한 mock 객체를 이용해 테스트를 수행합니다.
        when(secessionService.selectMemberCount()).thenReturn(10); // secessionService에 대한 mock 객체를 이용해 테스트를 수행합니다.

        // when
        ModelAndView modelAndView = null;
        try {
            modelAndView = mainController.secessionList(3);
        } catch (Exception e) {
            e.printStackTrace();
            fail("An exception occurred during the test: " + e.getMessage());
        }

        // then
        assertThat(modelAndView.getViewName()).isEqualTo("admin_withdrawal_member");

        List<SecessionDTO> actualList = (List<SecessionDTO>) modelAndView.getModel().get("list");
        assertThat(actualList).isEqualTo(list);

        PaggingVO actualPagging = (PaggingVO) modelAndView.getModel().get("pagging");
        assertThat(actualPagging.getPageOfContentCount()).isEqualTo(7);
        assertThat(actualPagging.getCurrentPageNo()).isEqualTo(3);
        assertThat(actualPagging.getTotalPage()).isEqualTo(2);
        assertThat(actualPagging.getTotalPageGroup()).isEqualTo(1);
        assertThat(actualPagging.getNowPageGroupNo()).isEqualTo(1);
        assertThat(actualPagging.getStartPageOfPageGroup()).isEqualTo(1);
        assertThat(actualPagging.getEndPageOfPageGroup()).isEqualTo(2);
        assertThat(actualPagging.isPriviousPageGroup()).isEqualTo(false);
        assertThat(actualPagging.isNextPageGroup()).isEqualTo(false);
    }
    
//    @Autowired
//    private MockMvc mockMvc;
//    
//    @DisplayName("회원가입 테스트")
//    @Test
//    @Order(1)
//    void register() throws Exception {
//        // given
//        MemberDTO dto = new MemberDTO();
//        dto.setName("ppppppp");
//        dto.setEmail("pppptest@gmail.com");
//        dto.setId("pppptest1");
//        dto.setPasswd("Nn!123345");
//        dto.setNick("pppppNick");
//               
//        // when & then
//        mockMvc1.perform(post("/register")
//                .flashAttr("dto", dto))
//                .andExpect(status().isBadRequest());
//    }



    
//
//    @DisplayName("찜 기능 테스트")
//    @Test
//    @Order(1)
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
//    @Order(2)
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
}


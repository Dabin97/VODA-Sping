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
    }

    @DisplayName("회원조회 테스트")
    @Test
    void memberList() {
        // given
        List<MemberDTO> list = Arrays.asList(new MemberDTO(), new MemberDTO(), new MemberDTO());
        when(memberService.selectMemberList(anyInt(), anyInt())).thenReturn(list);
        when(memberService.selectMemberCount()).thenReturn(10);

        // when
        ModelAndView modelAndView = null;
        try {
            modelAndView = mainController.memberList(3);
        } catch (Exception e) {
            e.printStackTrace();
            fail("An exception occurred during the test: " + e.getMessage());
        }

        // then
        assertThat(modelAndView.getViewName()).isEqualTo("admin_list_member");

        List<MemberDTO> actualList = (List<MemberDTO>) modelAndView.getModel().get("list");
        assertThat(actualList).isEqualTo(list);

        PaggingVO actualPagging = (PaggingVO) modelAndView.getModel().get("pagging");
        assertThat(actualPagging.getPageOfContentCount()).isEqualTo(5);
        assertThat(actualPagging.getCurrentPageNo()).isEqualTo(4);
        assertThat(actualPagging.getTotalPage()).isEqualTo(7);
        assertThat(actualPagging.getTotalPageGroup()).isEqualTo(2);
        assertThat(actualPagging.getNowPageGroupNo()).isEqualTo(2);
        assertThat(actualPagging.getStartPageOfPageGroup()).isEqualTo(4);
        assertThat(actualPagging.getEndPageOfPageGroup()).isEqualTo(7);
        assertThat(actualPagging.isPriviousPageGroup()).isEqualTo(true);
        assertThat(actualPagging.isNextPageGroup()).isEqualTo(false);
    }
    
    
    @Autowired
    private MockMvc mockMvc1;

    @DisplayName("탈퇴대기회원조회 테스트")
    @Test
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
        assertThat(actualPagging.getPageOfContentCount()).isEqualTo(10);
        assertThat(actualPagging.getCurrentPageNo()).isEqualTo(4);
        assertThat(actualPagging.getTotalPage()).isEqualTo(7);
        assertThat(actualPagging.getTotalPageGroup()).isEqualTo(2);
        assertThat(actualPagging.getNowPageGroupNo()).isEqualTo(2);
        assertThat(actualPagging.getStartPageOfPageGroup()).isEqualTo(4);
        assertThat(actualPagging.getEndPageOfPageGroup()).isEqualTo(7);
        assertThat(actualPagging.isPriviousPageGroup()).isEqualTo(true);
        assertThat(actualPagging.isNextPageGroup()).isEqualTo(false);
    }
    
    @Autowired
    private MockMvc mockMvc;
    
    @DisplayName("회원가입 테스트")
    @Test
    void register() throws Exception {
        // given
        MemberDTO dto = new MemberDTO();
        dto.setName("ppppppp");
        dto.setEmail("pppptest@gmail.com");
        dto.setId("pppptest1");
        dto.setPasswd("Nn!123345");
        dto.setNick("pppppNick");
               
        // when & then
        mockMvc1.perform(post("/register")
                .flashAttr("dto", dto))
                .andExpect(status().isBadRequest());
    }
} 


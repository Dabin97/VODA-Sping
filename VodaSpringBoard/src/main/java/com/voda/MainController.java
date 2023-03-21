package com.voda;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voda.dto.MemberDTO;
import com.voda.service.MemberService;



@Controller
public class MainController {
	private MemberService memberService;
	
	
	public MainController(MemberService memberService) {
		
		this.memberService = memberService;
	}

////////////////////회원 페이지//////////////////////////////

	

	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/register")
	public String registerView() {
		return "register";
	}

	@RequestMapping("/login/member")
	public String memberLogin(String id, String passwd, HttpSession session) {
		MemberDTO dto = memberService.login(id, passwd);
		session.setAttribute("dto", dto);
		return "redirect:/main";
	}
	
	
	////////////////////관리자 페이지//////////////////////////////
	@RequestMapping("/admin/index")
	public String adminLogin() {
		return "admin_before_login";
	} 
	
	@RequestMapping("/admin/login")
	public String adminLogin(String id, String passwd, HttpSession session) {
		MemberDTO dto = memberService.login(id, passwd);
		session.setAttribute("dto", dto);
		return "redirect:/admin_main";
	}
	
	@RequestMapping("/admin_main")
	public String adminMain() {
		return "admin_main";
	}
	
	@RequestMapping("/member/add/view")
	public String memberAddView() {
		return "admin_member_add";
	}

	@RequestMapping("/member/add")
	public String memberAdd(MemberDTO dto) {
		memberService.insertMember(dto);
		return "redirect:/admin_list_member";
	}
	
	
	//회원 삭제 팝업창 만들기
	@RequestMapping("/member/delete")
	public String memberDelete(MemberDTO dto) {
		//회원 삭제 버튼 누른 후 팝업창 뜨고 ok누르면 멤버 리스트로 리턴
		//무튼 ok 눌렀을 때 멤버 삭제...
		return "redirect:/admin_list_member";
	}
	
	@RequestMapping("/member/delete/view")
	public String memberDeleteView() {
		return "/admin_list_member";
	}	
	
//테이블 수정 창이나 팝업창에서 cancle 버튼 누르면 memberlist.html 이동 혹은 창만 닫힘 (->만들기..)
}

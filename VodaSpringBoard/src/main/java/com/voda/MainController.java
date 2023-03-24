package com.voda;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.voda.dto.ManagerDTO;
import com.voda.dto.MemberDTO;
import com.voda.dto.SecessionDTO;
import com.voda.service.MemberService;
import com.voda.service.SecessionService;
import com.voda.vo.PaggingVO;



@Controller
public class MainController {
	private MemberService memberService;
	private SecessionService secessionService;
	
	
	
	public MainController(MemberService memberService, SecessionService secessionService) {
	
		this.memberService = memberService;
		this.secessionService = secessionService;
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
	public String adminIndex() {
		return "admin_before_login";
	} 
	
	@PostMapping("/admin/login")
	public String adminLogin(String mid, String mpasswd, HttpSession session) {
		ManagerDTO dto = memberService.loginAdmin(mid, mpasswd);
		session.setAttribute("manager", dto);
		if(dto == null) {
            return "admin_before_login";
    }
		return "redirect:/admin_main";
	}
	
	@RequestMapping("/admin_main") 
	public String adminMain() {
		return "admin_main";
	}
	
	@GetMapping("/admin/logout")
    public String logoutAdmin(HttpSession session){
        session.invalidate();
        return "redirect:/admin/index"; //index로 보내서 오류뜸
    }
	//
	//@RequestMapping("/admin/member/list")
	//public ModelAndView memberList(ModelAndView view) {
	//List<MemberDTO> list = memberService.selectAllMember();
	//view.addObject("list", list);
	//view.setViewName("admin_list_member");
	//return view;
	//}
	//
	@RequestMapping("/admin/member/list")
	public ModelAndView memberList(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo) {
		ModelAndView view = new ModelAndView();
		view.setViewName("admin_list_member");
		// 게시판 글목록
		List<MemberDTO> list = memberService.selectMemberList(pageNo, 7);
		// 페이징 정보
		int count = memberService.selectMemberCount();
		PaggingVO pagging = new PaggingVO(count, pageNo, 7);
		
		view.addObject("list", list);
		view.addObject("pagging", pagging);
		
		return view;
	}
	
	//@RequestMapping("/admin/member/list")
	//public ModelAndView memberList(
	//@RequestParam(name = "pageNo", defaultValue = "1") int pageNo) {
	//ModelAndView view = new ModelAndView();
	//List<MemberDTO> list = memberService.selectMemberList(pageNo);
	//view.addObject("list", list);
	//PaggingVO pagging = new  PaggingVO(memberService.selectMemberCount(),	pageNo, 5);
	//view.addObject("pagging", pagging);
	//view.setViewName("admin_qna");
	//return view;
	//}
	
	
	@RequestMapping("/member/edit/view/{id}")
	public ModelAndView memberEditView(@PathVariable String id,ModelAndView view) {
		MemberDTO dto = memberService.selectMember(id);
		view.addObject("dto", dto);
		view.setViewName("admin_member_edit");
		return view;
	}
	
	//@RequestMapping("/member/edit")
	//public String memberAdd(MemberDTO dto) {
	//memberService.insertMember(dto);   -->insert는 회원가입할 때 참고..
	//return "redirect:/admin_list_member";
	//}
	
	@RequestMapping("/member/edit")
	public String memberEdit(MemberDTO dto) {
		int result = memberService.editMember(dto);   
		return "redirect:/admin/member/list";
	}
	
	@RequestMapping("/admin/secession")
	public ModelAndView secessionList(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo) {
		ModelAndView view = new ModelAndView();
		view.setViewName("admin_withdrawal_member");
		// 게시판 글목록
		List<SecessionDTO> list = secessionService.selectMemberList(pageNo, 7);
		
		// 페이징 정보
		int count = secessionService.selectMemberCount();
		PaggingVO pagging = new PaggingVO(count, pageNo, 7);
		
		view.addObject("list", list);
		view.addObject("pagging", pagging);
		
		return view;
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
	//
	//@RequestMapping("/list/paging")
	//public ModelAndView memberListPaiging(
	//@RequestParam(name = "pageNo", defaultValue = "1") int pageNo) {
	//ModelAndView view = new ModelAndView();
	//List<MemberDTO> list = memberService.selectAllMember(pageNo);
	//view.addObject("list", list);
	//PaggingVO pagging = new  PaggingVO(memberService.selectMemberCount(),	pageNo, 5);
	//view.addObject("pagging", pagging);
	//view.setViewName("admin_list_member");
	//return view;
	//}
	
	}

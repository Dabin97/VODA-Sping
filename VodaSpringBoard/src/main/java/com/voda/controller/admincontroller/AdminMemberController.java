package com.voda.controller.admincontroller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;
import java.io.PrintWriter;

import com.voda.dto.ManagerDTO;
import com.voda.dto.MemberDTO;
import com.voda.dto.SecessionDTO;
import com.voda.service.MemberService;
import com.voda.service.SecessionService;
import com.voda.vo.PaggingVO;

@Controller
@RequestMapping("/admin")
public class AdminMemberController {

	private MemberService memberService;
	private SecessionService secessionService;
	
	public AdminMemberController(MemberService memberService, SecessionService secessionService) {
		this.memberService = memberService;
		this.secessionService = secessionService;
	}
	
	@PostMapping("/login")
	public String adminLogin(String mid, String mpasswd, HttpSession session) {
		ManagerDTO dto = memberService.loginAdmin(mid, mpasswd);
		session.setAttribute("dto", dto); 
		if(dto == null) { return "/A_adminpage/main/admin_before_login";}
		return "redirect:/admin_main";
		}

	@GetMapping("/logout")
	public String logoutAdmin(HttpSession session){
		session.invalidate();
		return "redirect:/admin/index"; //index로 보내서 오류뜸
		}
	
	@GetMapping("/member/list")
	public ModelAndView memberList(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/A_adminpage/member/admin_list_member");
		// 게시판 글목록
		List<MemberDTO> list = memberService.selectMemberList(pageNo, 7);
		// 페이징 정보
		int count = memberService.selectMemberCount();
		PaggingVO pagging = new PaggingVO(count, pageNo, 7);
		
		view.addObject("list", list);
		view.addObject("pagging", pagging);
		
		return view;
	}
	
	@GetMapping("/member/edit/view/{id}")
	public ModelAndView memberEditView(@PathVariable String id,ModelAndView view) {
		MemberDTO dto = memberService.selectMember(id);
		view.addObject("dto", dto);
		view.setViewName("/A_adminpage/member/admin_member_edit");
		return view;
	}
	
	@PostMapping("/member/edit") //관리자가 회원 정보 수정
	public String memberEdit(MemberDTO dto) {
		System.out.println(dto);
		int result = memberService.editMember(dto);   
		return "redirect:/admin/member/list";
	}
	
//	@GetMapping("/member/delete") //관리자가 회원 삭제 원본
//	public ResponseEntity<String> deleteMember(@RequestParam String[] id) {
//		System.out.println(Arrays.toString(id));
//		int result1 = secessionService.deleteSecession(id);
//		int result = memberService.deleteMember(id);
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("count", String.valueOf(result));
//		if(result != 0) {
//			map.put("message", "데이터 삭제 성공");
//		}else {
//			map.put("message", "데이터 삭제 실패");
//			System.out.println(result);
//		}
//		return new ResponseEntity(map,HttpStatus.OK);
//	}
	
	@PostMapping("/member/delete") 
	public ResponseEntity<String> deleteMember(@RequestParam String[] id) {
	    System.out.println(Arrays.toString(id));
	    System.out.println(id);
	    int result1 = secessionService.deleteSecession(id);
	    int result = memberService.deleteMember(id);
	    HashMap<String, String> map = new HashMap<String, String>();
	    map.put("count", String.valueOf(result));
	    try {	        
	        if (result != 0) {
	            map.put("message", "데이터 삭제 성공");
	        } else {
	            map.put("message", "데이터 삭제 실패");
	            System.out.println(result);
	        }
	        //throw new Exception("Something went wrong"); 예외 강제 발생 코드, 예외를 발생시킬때만 사용할 것
	    } catch (Exception e) {
	        e.printStackTrace();
	        // 예외를 파일에 기록하는 코드
	        try {
	            PrintWriter writer = new PrintWriter(new FileWriter("C:\\exception.txt"));
	            writer.println(new Date() + ": " + e.getMessage());
	            e.printStackTrace(writer);
	            writer.close();
	        } catch (IOException ex) {
	            ex.printStackTrace(); 
	        }
	    }
	    return new ResponseEntity(map, HttpStatus.OK);
	}
	
	@PostMapping("/member/search") // 관리자 페이지 회원 검색 부분
	public ResponseEntity<String> selectSearchMemberList(String kind, String search){
		List<MemberDTO> list = memberService.selectSearchMember(kind,search);
			
		return new ResponseEntity(list,HttpStatus.OK);
	}
	
	@PostMapping("/secession/search") // 관리자 페이지 탈퇴대기회원 검색 부분
	public ResponseEntity<String> selectSearchSecessionList(String kind, String search){
		List<SecessionDTO> list = secessionService.selectSearchMember(kind,search);
			
		return new ResponseEntity(list,HttpStatus.OK);
	}
}

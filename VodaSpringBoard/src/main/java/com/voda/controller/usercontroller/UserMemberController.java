package com.voda.controller.usercontroller;

import com.voda.dto.MemberDTO;
import com.voda.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class UserMemberController {
	private MemberService memberService;
	
	public UserMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@PostMapping("/member_login") 
	public String login(String id, String passwd, HttpSession session) {
		MemberDTO dto = memberService.login(id, passwd);
		session.setAttribute("member", dto);
		if(dto == null) {
			return "redirect:/index";
		}
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate(); 
		return "redirect:/";
	}
	
	
	@GetMapping("/edit/view")
    public String edit(HttpSession session) {
        return "/B_userpage/user/profile_edit";
    }
	
	@PostMapping("/profile/edit")  //회원이 본인 정보 수정
	public String profileEdit(MemberDTO dto , HttpSession session, HttpServletRequest request) {
		 MemberDTO member = (MemberDTO) session.getAttribute("member");
		 String id = member.getId();
		 dto.setId(id);
		 int result = memberService.editProfile(dto); 
		 System.out.println(dto);
		return "/B_userpage/user/index";
	}

	@GetMapping("/register/view")
	public String registerView() {
		return "/B_userpage/user/register"; 
	}
 
	@PostMapping("/register")
	public String register(MemberDTO dto) {
		System.out.println(dto);
		int result = memberService.insertMember(dto);
		return "redirect:/index";
	}
	
	 @PostMapping("/idCheck")
	  @ResponseBody
	  public ResponseEntity<String> idCheck(@RequestParam String id) {
	    MemberDTO isDuplicated = memberService.idCheck(id);
	    if (isDuplicated != null) {
	      return new ResponseEntity("duplicated",HttpStatus.OK);
	    } else {
	      return new ResponseEntity("available",HttpStatus.OK);
	    }
	  }
}

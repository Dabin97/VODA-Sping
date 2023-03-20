package com.voda;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voda.dto.MemberDTO;



@Controller
public class MainController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	
	@RequestMapping("/register")
	public String registerView() {
		return "register";
	}

	@RequestMapping("/login")
	public String login(String id, String passwd, HttpSession session) {
		MemberDTO dto = memberService.login(id, passwd);
		session.setAttribute("dto", dto);
		return "redirect:/main";
	}
	
	@RequestMapping("/member/edit")
	public String memberEdit() {
		return "admin_member_edit";
	}

}

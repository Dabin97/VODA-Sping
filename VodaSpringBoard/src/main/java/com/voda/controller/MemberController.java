package com.voda.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voda.dto.MemberDTO;
import com.voda.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	private MemberService memberService;
	
	public MemberController(MemberService memberservice) {
		this.memberService = memberservice;
	}
	
	@PostMapping("/login")
	public String login(String id, String passwd, HttpSession session) {
		MemberDTO dto = memberService.login(id, passwd);
		session.setAttribute("member", dto);
		if(dto == null) {
				return "redirect:/index";
		}
		return "redirect:/main";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}


}

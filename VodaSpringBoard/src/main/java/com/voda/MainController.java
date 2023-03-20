package com.voda;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.voda.service.BoardService;
import com.voda.service.MemberService;
import com.voda.dto.MemberDTO;

@Controller
public class MainController {
	private MemberService memberService;
	private BoardService boardService;

	public MainController(MemberService memberService, BoardService boardService) {
		this.memberService = memberService;
		this.boardService = boardService;
	}

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
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	

	
	
	
}

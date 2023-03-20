package com.voda;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
//	@RequestMapping("/review/write")
//	public String reviewWrite() {
//		reurn 
//	}
	@RequestMapping("/review/like/{rno}")
	public ResponseEntity<String> reviewLike(@PathVariable(name = "rno") int rno, 
			HttpSession session){
		HashMap<String, Object> map = new HashMap<String, Object>();
		MemberDTO dto = (MemberDTO) session.getAttribute("dto");
		
		int result = boardService.insertReviewLike(rno,dto.getId());
		
		if(result == 0)
			map.put("msg", "좋아요를 취소하셨습니다.");
		else
			map.put("msg", "좋아요를 하셨습니다.");
		
		map.put("rlike",boardService.selectReviewLike(rno));
		
		return new ResponseEntity(map,HttpStatus.OK);
	}
	
	@RequestMapping("/review/hate/{rno}")
	public ResponseEntity<String> reviewHate(@PathVariable(name = "rno") int rno, 
			HttpSession session){
		HashMap<String, Object> map = new HashMap<String, Object>();
		MemberDTO dto = (MemberDTO) session.getAttribute("dto");
		
		int result = boardService.insertReviewHate(rno,dto.getId());
		
		if(result == 0)
			map.put("msg", "싫어요를 취소하셨습니다.");
		else
			map.put("msg", "싫어요를 하셨습니다.");
		
		map.put("bhate",boardService.selectReviewHate(rno));
		
		return new ResponseEntity(map,HttpStatus.OK);
	}
	

	
	
	
}

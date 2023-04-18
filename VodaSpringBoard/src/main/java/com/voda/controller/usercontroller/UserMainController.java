package com.voda.controller.usercontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.voda.dto.BoardDTO;
import com.voda.service.BoardService;

@Controller
public class UserMainController {
	
	private BoardService boardService;
	public UserMainController(BoardService boardService) {
		this.boardService = boardService;
	}
	 
	@RequestMapping("/index") 
	public String index() { 
		return "/B_userpage/user/index";  
	} 
	

	@RequestMapping("/")
	public ModelAndView MainContentList(HttpSession session) {
	    ModelAndView view = new ModelAndView();
	    view.setViewName("/B_userpage/main/main");

	    List<BoardDTO> list = boardService.selectMainContentList();
	    List<BoardDTO> nlist = boardService.selectNewContentList();
	    List<BoardDTO> elist = boardService.selectExpireContentList();
	    view.addObject("list", list);
	    view.addObject("nlist", nlist);
	    view.addObject("elist", elist);
	    return view;
	}
}

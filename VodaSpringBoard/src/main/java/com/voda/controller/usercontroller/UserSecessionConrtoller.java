package com.voda.controller.usercontroller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.voda.dto.SecessionDTO;
import com.voda.service.SecessionService;

@Controller
@RequestMapping("/member/secession")
public class UserSecessionConrtoller {
	private SecessionService secessionService;
	
	public UserSecessionConrtoller(SecessionService secessionService) {
		this.secessionService = secessionService;
	}
	
	@GetMapping("/view/{id}")
	public ModelAndView secessionView(@PathVariable String id, HttpSession session) {
	    ModelAndView mv = new ModelAndView();
	    String msg = "";
	    SecessionDTO secessionDTO = secessionService.selectSecessionId(id);
	    if (secessionDTO != null) {
	        mv.addObject("msg", "이미 탈퇴신청하셨습니다.");
	        mv.setViewName("/B_userpage/user/profile_edit");
	    } else {
	        mv.setViewName("/B_userpage/user/member_secession");
	    }
	    return mv;
	}
	
	@GetMapping("")
	public String secessionMember(SecessionDTO dto, HttpSession session) {	 
		int sno = secessionService.goSecession(dto, null);	
		return "redirect:/";
	}
}

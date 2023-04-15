package com.voda.controller.admincontroller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.voda.dto.SecessionDTO;
import com.voda.service.SecessionService;
import com.voda.vo.PaggingVO;

@Controller
@RequestMapping
public class AdminSecessionController {
	private SecessionService secessionService;
	
	public AdminSecessionController(SecessionService secessionService) {
		this.secessionService = secessionService;
	}

	@GetMapping("/admin/secession")
	public ModelAndView secessionList(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/A_adminpage/member/admin_withdrawal_member");
		// 게시판 글목록
		List<SecessionDTO> list = secessionService.selectMemberList(pageNo, 7);
		// 페이징 정보
		int count = secessionService.selectMemberCount();
		PaggingVO pagging = new PaggingVO(count, pageNo, 7);
		view.addObject("list", list);
		view.addObject("pagging", pagging);
		return view;
	}
	
}

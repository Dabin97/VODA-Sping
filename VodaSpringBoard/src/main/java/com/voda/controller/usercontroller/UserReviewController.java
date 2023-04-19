package com.voda.controller.usercontroller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voda.dto.MemberDTO;
import com.voda.dto.ReviewDTO;
import com.voda.service.ReviewService;

@Controller
@RequestMapping("/member/review")
public class UserReviewController { 

	private ReviewService reviewService;
	
	public UserReviewController(ReviewService reviewService) {
		this.reviewService = reviewService; 
	}
	
	/*
	 * @PostMapping("/register") public String registerReview(ReviewDTO dto) {
	 * reviewService.insertReview(dto); return
	 * "redirect:/board/content/detail/{bno}"; }
	 */
	@PostMapping("write/{bno}")
	public String insertReview(ReviewDTO review, HttpSession session) {

		MemberDTO member = (MemberDTO) session.getAttribute("member");
		String id = member.getId();
		review.setId(id);

		reviewService.insertReview(review);
		
		return "redirect:/board/content/detail/" + review.getBno();
	}
}

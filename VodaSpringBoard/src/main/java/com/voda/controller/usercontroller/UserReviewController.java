package com.voda.controller.usercontroller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/review/register")
	public String registerReview(ReviewDTO dto) {
		reviewService.insertReview(dto);
		return "redirect:/content_page";
	}
	
	@RequestMapping("/write")
	public String insertReview(ReviewDTO review, HttpSession session) {
		//댓글 작성자 정보 추가
		MemberDTO dto = (MemberDTO) session.getAttribute("dto");
		review.setId(dto.getId());
		
		reviewService.insertReview(review);
		
		return "redirect:/content/detail/{bno}"+review.getBno();
	}
}

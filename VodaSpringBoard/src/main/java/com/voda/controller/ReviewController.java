package com.voda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voda.dto.ReviewDTO;
import com.voda.service.ReviewService;

@Controller	
@RequestMapping("/review")
public class ReviewController {
	private ReviewService reviewService;
	
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@RequestMapping("/review/register")
	public String memberReviewRegister(ReviewDTO dto) {
	reviewService.insertReview(dto);
	return "redirect:/member/review";
	}

}

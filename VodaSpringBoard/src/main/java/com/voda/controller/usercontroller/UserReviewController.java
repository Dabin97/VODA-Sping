package com.voda.controller.usercontroller;

import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@PostMapping("/like/{rno}")
	public ResponseEntity<String> reviewLike(@PathVariable(name = "rno") int rno, HttpSession session){
		HashMap<String, Object> map = new HashMap<String, Object>();
		MemberDTO dto = (MemberDTO) session.getAttribute("member");
		
		int result = reviewService.insertBoardLike(rno,dto.getId());
		
		if(result == 0)
			map.put("msg", "좋아요를 취소하셨습니다.");
		else
			map.put("msg", "좋아요를 하셨습니다.");
		
		map.put("rlike",reviewService.selectBoardLike(rno));
		 
		return new ResponseEntity(map,HttpStatus.OK);
	}
	
	@PostMapping("/hate/{rno}")
	public ResponseEntity<String> reviewHate(@PathVariable(name = "rno") int rno, HttpSession session){
		HashMap<String, Object> map = new HashMap<String, Object>();
		MemberDTO dto = (MemberDTO) session.getAttribute("member");
		
		int result = reviewService.insertBoardHate(rno,dto.getId());
		
		if(result == 0)
			map.put("msg", "싫어요를 취소하셨습니다.");
		else
			map.put("msg", "싫어요를 하셨습니다.");
		
		map.put("rhate",reviewService.selectBoardHate(rno));
		
		return new ResponseEntity(map,HttpStatus.OK);
	}
	
	@PostMapping("/delete") //리뷰삭제
	public ResponseEntity<String> deleteUserReview(@RequestParam String id) {
		//System.out.println(Arrays.toString(id));
		int result = reviewService.deleteUserReview(id);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("count", String.valueOf(result));
		if(result != 0) {
			map.put("message", "리뷰 삭제 성공");
		}else {
			map.put("message", "리뷰 삭제 실패");
			System.out.println(result);
		}
		return new ResponseEntity(map,HttpStatus.OK);
	}
}

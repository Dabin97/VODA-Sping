package com.voda.controller.admincontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.voda.dto.ReviewDTO;
import com.voda.service.ReviewService;
import com.voda.vo.PaggingVO;

@Controller
@RequestMapping("/admin/review")
public class AdminReviewController {
	
	private ReviewService reviewService;
	
	public AdminReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@GetMapping("/list") //컨텐츠 등록 게시판 리스트 - Main의 역할
	public ModelAndView adminReviewList(@RequestParam(name = "pageNo", defaultValue = "1")int pageNo) {
	ModelAndView view = new ModelAndView();
	view.setViewName("/A_adminpage/review/admin_review_list");
	//게시판 글목록
	List<ReviewDTO> list = reviewService.selectReviewList(pageNo, 7);
	 
	//페이징 정보
	int count = reviewService.selectReviewCount();
	PaggingVO pagging = new PaggingVO(count, pageNo, 7);
	
	view.addObject("list",list); 
	view.addObject("pagging",pagging); 
	
	return view;
	}
	
	@GetMapping("/search") // 검색 부분
	public ResponseEntity<String> selectSearchReviewtList(String kind, String search){
		List<ReviewDTO> list = reviewService.selectSearchReview(kind,search);
			
		return new ResponseEntity(list,HttpStatus.OK);
	}
	
	@GetMapping("/detail/{rno}")
	public ModelAndView adminReviewDetail(@PathVariable("rno") int rno, HttpSession session) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/A_adminpage/review/admin_review_detail");
		
		//게시글 조회
		ReviewDTO review = reviewService.selectAllReview(rno);
		
		view.addObject("review", review);
		
		return view;
		}
	
	@GetMapping("/delete/{rno}") //게시글 첨부파일 댓글삭제 모두 
	public String deleteReview(@PathVariable(name ="rno")int rno) {

		reviewService.deleteReview(rno);
		return "redirect:/admin/review/list";
	}
}

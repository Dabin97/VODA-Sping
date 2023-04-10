package com.voda.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.voda.dto.BoardDTO;
import com.voda.dto.MemberDTO;
import com.voda.dto.ReviewDTO;
import com.voda.mapper.ReviewMapper;

@Service
public class ReviewService {
	private ReviewMapper mapper;
	
	public ReviewService(ReviewMapper mapper) {
		this.mapper = mapper;
	}

	public int insertReview(ReviewDTO dto) {
		return mapper.insertReview(dto);
	}

	public List<ReviewDTO> selectReviewList(int pageNo, int i) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("contentCount", i);
		return mapper.selectReviewList(map);

	}

	public int selectReviewCount() {
		return mapper.selectReviewCount();
	}



	public ReviewDTO selectAllReview(int rno) {
		return mapper.selectAllReview(rno);
	}

	public void deleteReview(int rno) {
		mapper.deleteReview(rno);
		
	}
	
	public List<ReviewDTO> selectSearchReview(String kind, String search) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("kind", kind);
		map.put("search", search);
		return mapper.selectSearchReview(map);
	}

	public List<ReviewDTO> selectReview(int bno) {
		return mapper.selectReview(bno);
	}


}

package com.voda.service;

import com.voda.dto.ReviewDTO;
import com.voda.mapper.ReviewMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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

	public List<ReviewDTO> selectMyReview(String id) {
		return mapper.selectMyReview(id);
	}

	public int insertBoardLike(int rno,String id) {
		int r = 0;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rno", rno);
		map.put("id", id);
		try {
			r = mapper.insertBoardLike(map);
		}catch (Exception e) {
			mapper.deleteBoardLike(map);
		}
		return r;
	}
	public int insertBoardHate(int rno,String id) {
		int r = 0;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rno", rno);
		map.put("id", id);
		try {
			r = mapper.insertBoardHate(map);
		}catch (Exception e) {
			mapper.deleteBoardHate(map);
		}
		return r;
	}

	public int selectBoardLike(int rno) {
		return mapper.selectBoardLike(rno);
	}

	public int selectBoardHate(int rno) {
		return mapper.selectBoardHate(rno);
	}

	public int deleteUserReview(String id) {
		return mapper.deleteUserReview(id);
    }

	public int ReviewCHK(HashMap<String, Object> paramMap) {
		return mapper.ReviewCHK(paramMap);
    }

	public List<ReviewDTO> selectMianReviewList() {
		List<ReviewDTO> list = mapper.selectMainReviewList();
		return list;
	}




}

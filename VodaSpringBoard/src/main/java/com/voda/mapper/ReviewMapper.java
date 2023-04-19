package com.voda.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.voda.dto.ReviewDTO;


@Mapper
public interface ReviewMapper {

	int insertReview(ReviewDTO dto);

	List<ReviewDTO> selectReviewList(HashMap<String, Object> map);

	int selectReviewCount();


	ReviewDTO selectAllReview(int rno);

	void deleteReview(int rno);
	
	List<ReviewDTO> selectSearchReview(HashMap<String, Object> map);

	List<ReviewDTO> selectReview(int bno);

	List<ReviewDTO> selectMyReview(String id);

	int insertBoardLike(HashMap<String, Object> map);

	int deleteBoardLike(HashMap<String, Object> map);

	int insertBoardHate(HashMap<String, Object> map);

	int deleteBoardHate(HashMap<String, Object> map);

	int selectBoardLike(int rno);

	int selectBoardHate(int rno);

	int ReviewCHK(HashMap<String, Object> paramMap);

	List<ReviewDTO> selectMainReviewList();


}

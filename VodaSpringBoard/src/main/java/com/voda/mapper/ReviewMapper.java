package com.voda.mapper;

import com.voda.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;


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
	
	int deleteUserReview(String id);

	int ReviewCHK(HashMap<String, Object> paramMap);

	List<ReviewDTO> selectMainReviewList();


}

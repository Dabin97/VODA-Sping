package com.voda.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.voda.dto.ReviewDTO;


@Mapper
public interface ReviewMapper {

	int insertReview(ReviewDTO review);

	List<ReviewDTO> selectReviewList(HashMap<String, Object> map);

	int selectReviewCount();


	ReviewDTO selectAllReview(int rno);

	void deleteReview(int rno);
	
	List<ReviewDTO> selectSearchReview(HashMap<String, Object> map);

	List<ReviewDTO> selectReview(int bno);


}

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


}

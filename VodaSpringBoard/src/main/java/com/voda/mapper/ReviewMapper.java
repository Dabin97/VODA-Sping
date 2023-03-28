package com.voda.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.voda.dto.ReviewDTO;


@Mapper
public interface ReviewMapper {

	int insertReview(ReviewDTO dto);


}

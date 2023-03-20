package com.voda.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

	int insertReviewLike(HashMap<String, Object> map);
	int deleteReviewLike(HashMap<String, Object> map);
	int insertReviewHate(HashMap<String, Object> map);
	int deleteReviewHate(HashMap<String, Object> map);
	int selectReviewLike(int rno);
	int selectReviewHate(int rno);
	
	


}

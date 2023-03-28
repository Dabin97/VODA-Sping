package com.voda.service;

import org.springframework.stereotype.Service;

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



}

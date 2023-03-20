package com.voda.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.voda.mapper.BoardMapper;

@Service
public class BoardService {
	private BoardMapper mapper;
	
	public BoardService(BoardMapper mapper) {
		this.mapper = mapper;
	}

	public int insertReviewLike(int rno, String id) {
		int r = 0;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rno", rno);
		map.put("id", id);
		try {
			r = mapper.insertReviewLike(map);
		}catch (Exception e) {
			mapper.deleteReviewLike(map);
		}
		return r;
	}

	public int selectReviewLike(int rno) {
		return mapper.selectReviewLike(rno);
	}

	public int insertReviewHate(int rno, String id) {
		int r = 0;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rno", rno);
		map.put("id", id);
		try {
			r = mapper.insertReviewHate(map);
		}catch (Exception e) {
			mapper.deleteReviewHate(map);
		}
		return r;
	}

	public int selectReviewHate(int rno) {
		return mapper.selectReviewHate(rno);

	}

}

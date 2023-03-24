package com.voda.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.voda.mapper.MemberMapper;
import com.voda.dto.MemberDTO;

@Service
public class MemberService {
private MemberMapper mapper;
	
	public MemberService(MemberMapper mapper) {
		this.mapper = mapper;
	}

	public MemberDTO login(String id, String passwd) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("passwd", passwd);
		return mapper.login(map);
	}

}
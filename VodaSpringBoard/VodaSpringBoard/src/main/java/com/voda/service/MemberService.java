package com.voda.service;

import java.util.HashMap;
<<<<<<< HEAD
import java.util.List;

import org.springframework.stereotype.Service;

import com.voda.dto.MemberDTO;
import com.voda.mapper.MemberMapper;

@Service
public class MemberService {
  private MemberMapper mapper;

public MemberService(MemberMapper mapper) {
	
	this.mapper = mapper;
}

=======

import org.springframework.stereotype.Service;

import com.voda.mapper.MemberMapper;
import com.voda.dto.MemberDTO;

@Service
public class MemberService {
private MemberMapper mapper;
	
	public MemberService(MemberMapper mapper) {
		this.mapper = mapper;
	}
>>>>>>> e484cca1b55f86259538034f4b577b15647d80d0

	public MemberDTO login(String id, String passwd) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("passwd", passwd);
		return mapper.login(map);
	}

<<<<<<< HEAD

	public int insertMember(MemberDTO dto) {
		return mapper.insertMember(dto);
		
	}


	public List<MemberDTO> selectAllMember() {
		
		return mapper.selectAllMember();
	}


	public List<MemberDTO> selectMemberList(int pageNo, int i) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("contentCount", i);
		return mapper.selectMemberList(map);

	}


	public int selectMemberCount() {
		return mapper.selectMemberCount();
	}


	





  
}
=======
}
>>>>>>> e484cca1b55f86259538034f4b577b15647d80d0

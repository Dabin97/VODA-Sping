package com.voda.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.voda.dto.ManagerDTO;
import com.voda.dto.MemberDTO;
import com.voda.mapper.MemberMapper;

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


	public int editMember(MemberDTO dto) {
		return mapper.editMember(dto);
		
	}


	public MemberDTO selectMember(String id) {
		return mapper.selectMember(id);
	}


	public ManagerDTO loginAdmin(String mid, String mpasswd) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mid", mid);
		map.put("mpasswd", mpasswd);
		return mapper.loginAdmin(map);
	}


	public int deleteMember(String[] id) {
		return mapper.deleteMember(id);
	}


	public MemberDTO idCheck(String id) {
		// TODO Auto-generated method stub
		return mapper.idCheck(id);
	}


	public int editProfile(MemberDTO dto) {
		
		return mapper.editProfile(dto);
	}



	public List<MemberDTO> selectSearchMember(String kind, String search) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("kind", kind);
		map.put("search", search);
		return mapper.selectSearchMember(map);
	}


	public void insertSnsUser(HashMap<String, String> snsUser) {
		mapper.insertSnsUser(snsUser);
	}


	public HashMap<String, Object> selectSnsUser(String snsId) {
		return mapper.selectSnsUser(snsId);
	}


  
}

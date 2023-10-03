package com.voda.mapper;

import com.voda.dto.ManagerDTO;
import com.voda.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface MemberMapper {
	MemberDTO login(HashMap<String, Object> map); //회원로그인

	List<MemberDTO> searchMember(HashMap<String, Object> map);

	int insertMember(MemberDTO dto);

	List<MemberDTO> selectAllMember();

	List<MemberDTO> selectMemberList(HashMap<String, Object> map);

	int selectMemberCount();
	
	int editMember(MemberDTO dto);

	MemberDTO selectMember(String id);

	ManagerDTO loginAdmin(HashMap<String, Object> map); //관리자 로그인

	int deleteMember(String[] id);

	MemberDTO idCheck(String id);

	int editProfile(MemberDTO dto);

	List<MemberDTO> selectSearchMember(HashMap<String, Object> map);

	void insertSnsUser(HashMap<String, String> snsUser);

	HashMap<String, Object> selectSnsUser(String snsId);

}


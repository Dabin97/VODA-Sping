package com.voda.mapper;

import java.util.HashMap;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> db9fbcbbda40993a7db66c067ea1a0739e8e5672

import org.apache.ibatis.annotations.Mapper;

import com.voda.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	MemberDTO login(HashMap<String, Object> map);

<<<<<<< HEAD
	int insertMember(MemberDTO dto);

	List<MemberDTO> selectAllMember();

	List<MemberDTO> selectMemberList(HashMap<String, Object> map);

	int selectMemberCount();

	

=======
>>>>>>> db9fbcbbda40993a7db66c067ea1a0739e8e5672
}


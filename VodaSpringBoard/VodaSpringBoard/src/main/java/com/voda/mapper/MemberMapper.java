package com.voda.mapper;

import java.util.HashMap;
<<<<<<< HEAD

=======
>>>>>>> e484cca1b55f86259538034f4b577b15647d80d0
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.voda.dto.MemberDTO;

<<<<<<< HEAD
=======

>>>>>>> e484cca1b55f86259538034f4b577b15647d80d0
@Mapper
public interface MemberMapper {

	MemberDTO login(HashMap<String, Object> map);

<<<<<<< HEAD

	int insertMember(MemberDTO dto);

	List<MemberDTO> selectAllMember();

	List<MemberDTO> selectMemberList(HashMap<String, Object> map);

	int selectMemberCount();
}

=======
	List<MemberDTO> searchMember(HashMap<String, Object> map);

	List<MemberDTO> selectAllMember();


}
>>>>>>> e484cca1b55f86259538034f4b577b15647d80d0

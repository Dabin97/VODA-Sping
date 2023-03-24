package com.voda.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.voda.dto.MemberDTO;


@Mapper
public interface MemberMapper {

	MemberDTO login(HashMap<String, Object> map);

	List<MemberDTO> searchMember(HashMap<String, Object> map);

	List<MemberDTO> selectAllMember();


}
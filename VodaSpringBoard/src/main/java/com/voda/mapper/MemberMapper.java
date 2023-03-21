package com.voda.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.voda.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	MemberDTO login(HashMap<String, Object> map);

	int insertMember(MemberDTO dto);

}


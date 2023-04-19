package com.voda.mapper;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.voda.dto.SecessionDTO;

@Mapper
public interface SecessionMapper {

	List<SecessionDTO> selectMemberList(HashMap<String, Object> map);

	int selectMemberCount();

	int selectSecessionSno();

	int goSecession(SecessionDTO dto);

	SecessionDTO selectSecessionId(String id);

	int deleteSecession(String[] id);

	List<SecessionDTO> selectSearchSecession(HashMap<String, Object> map);

}

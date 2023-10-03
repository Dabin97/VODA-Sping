package com.voda.mapper;

import com.voda.dto.SecessionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface SecessionMapper {

	List<SecessionDTO> selectMemberList(HashMap<String, Object> map);

	int selectMemberCount();

	int selectSecessionSno();

	int goSecession(SecessionDTO dto);

	SecessionDTO selectSecessionId(String id);

	int deleteSecession(String[] id);

}

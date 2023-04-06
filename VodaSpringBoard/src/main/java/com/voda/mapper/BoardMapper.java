package com.voda.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.voda.dto.BoardDTO;
import com.voda.dto.FileDTO;
import com.voda.dto.MemberDTO;
@Mapper
public interface BoardMapper {

	int insertReviewLike(HashMap<String, Object> map);
	void deleteReviewLike(HashMap<String, Object> map);
	int selectImageFileNo();
	int insertBoardImage(HashMap<String, Object> map);
	FileDTO selectImageFile(int bno);
	FileDTO selectFile(HashMap<String, Object> map);
	int selectBoardBno();
	int insertBoard(BoardDTO dto);
	int insertFile(FileDTO fileDTO);
	BoardDTO selectBoard(int bno);
	List<FileDTO> selectFileList(int bno);
	int updateBoard(BoardDTO dto);
	List<String> deleteFileList(HashMap<String, Object> map);
	int deleteFile(HashMap<String, Object> map);
	void deleteBoard(int bno);
	List<BoardDTO> selectBoardList(HashMap<String, Object> map);
	int selectBoardCount();
	List<MemberDTO> selectSearchContent(HashMap<String, Object> map);
	List<BoardDTO> selectNewList(HashMap<String, Object> map);
	List<BoardDTO> selectExpireList(HashMap<String, Object> map);
	List<BoardDTO> selectMainContentList();
	FileDTO selectMainImageFile();
	List<BoardDTO> selectNewContentList();
	List<BoardDTO> selectExpireContentList();

	

}

package com.voda.mapper;

<<<<<<< HEAD
import java.util.ArrayList;
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
	FileDTO selectImageFile(int fno);
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
	
=======
import java.util.HashMap;

public class BoardMapper {

	public int insertReviewLike(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void deleteReviewLike(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		
	}
>>>>>>> d2dd624d3cf6421541d07926c0a5279697b2d111

}

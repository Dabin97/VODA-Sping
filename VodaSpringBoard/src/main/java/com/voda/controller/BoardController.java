package com.voda.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.voda.dto.BoardDTO;
import com.voda.dto.FileDTO;
import com.voda.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	private BoardService boardService;
	
	public BoardController(BoardService boardService) {
	this.boardService = boardService;
	}
	
	@RequestMapping("/member/content/detail/{bno}")
    public ModelAndView memberContentDetail(@PathVariable("bno") int bno, HttpSession session) {
        ModelAndView view = new ModelAndView();
        view.setViewName("member_content_detail");
        
        //게시글 조회
        BoardDTO board = boardService.selectBoard(bno);
        //첨부파일 목록 조회
        List<FileDTO> fList = boardService.selectFileList(bno);
    
        
        view.addObject("board", board);
        view.addObject("fList", fList);
        
        return view;
        }

}

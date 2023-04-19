package com.voda.controller.usercontroller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.voda.dto.BoardDTO;
import com.voda.dto.FileDTO;
import com.voda.dto.MemberDTO;
import com.voda.dto.ReviewDTO;
import com.voda.service.BoardService;
import com.voda.service.MemberService;
import com.voda.service.ReviewService;

@Controller
@RequestMapping("/board")
public class UserBoardController {
	private BoardService boardService;
	private ReviewService reviewService;

	
	public UserBoardController(BoardService boardService, ReviewService reviewService) {
		this.boardService = boardService;
		this.reviewService = reviewService;

	}

	@GetMapping("/new_expire")
	public ModelAndView new_expire(HttpSession session) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/B_userpage/new_expire/new_expire");

	    List<BoardDTO> list = boardService.selectMainContentList();
	    List<BoardDTO> nlist = boardService.selectNewContentList();
	    List<BoardDTO> elist = boardService.selectExpireContentList();
	    view.addObject("list", list);
	    view.addObject("nlist", nlist);
	    view.addObject("elist", elist);
	    return view;
	}
	
	@GetMapping("/new_expire_netflix")
	public ModelAndView new_expire_netflix(HttpSession session) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/B_userpage/new_expire/new_expire_netflix");

	    List<BoardDTO> list = boardService.selectMainContentList();
	    List<BoardDTO> nlist = boardService.selectNewContentList();
	    List<BoardDTO> elist = boardService.selectExpireContentList();
	    view.addObject("list", list);
	    view.addObject("nlist", nlist);
	    view.addObject("elist", elist);
	    return view;
	}
	
	@GetMapping("/new_expire_watcha")
	public ModelAndView new_expire_watcha(HttpSession session) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/B_userpage/new_expire/new_expire_watcha");

	    List<BoardDTO> list = boardService.selectMainContentList();
	    List<BoardDTO> nlist = boardService.selectNewContentList();
	    List<BoardDTO> elist = boardService.selectExpireContentList();
	    view.addObject("list", list);
	    view.addObject("nlist", nlist);
	    view.addObject("elist", elist);
	    return view;
	}
	
	@GetMapping("/new_expire_wavve")
	public ModelAndView new_expire_wavve(HttpSession session) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/B_userpage/new_expire/new_expire_wavve");

	    List<BoardDTO> list = boardService.selectMainContentList();
	    List<BoardDTO> nlist = boardService.selectNewContentList();
	    List<BoardDTO> elist = boardService.selectExpireContentList();
	    view.addObject("list", list);
	    view.addObject("nlist", nlist);
	    view.addObject("elist", elist);
	    return view;
	}
	
	@GetMapping("/new_expire_tving")
	public ModelAndView new_expire_tving(HttpSession session) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/B_userpage/new_expire/new_expire_tving");

	    List<BoardDTO> list = boardService.selectMainContentList();
	    List<BoardDTO> nlist = boardService.selectNewContentList();
	    List<BoardDTO> elist = boardService.selectExpireContentList();
	    view.addObject("list", list);
	    view.addObject("nlist", nlist);
	    view.addObject("elist", elist);
	    return view;
	}
	
	@GetMapping("/new_expire_laftel")
	public ModelAndView new_expire_laftel(HttpSession session) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/B_userpage/new_expire/new_expire_laftel");
	    List<BoardDTO> list = boardService.selectMainContentList();
	    List<BoardDTO> nlist = boardService.selectNewContentList();
	    List<BoardDTO> elist = boardService.selectExpireContentList();
	    view.addObject("list", list);
	    view.addObject("nlist", nlist);
	    view.addObject("elist", elist);
	    return view;
	}
	
	@GetMapping("/new_expire_disney")
	public ModelAndView new_expire_disney(HttpSession session) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/B_userpage/new_expire/new_expire_disney");

	    List<BoardDTO> list = boardService.selectMainContentList();
	    List<BoardDTO> nlist = boardService.selectNewContentList();
	    List<BoardDTO> elist = boardService.selectExpireContentList();
	    view.addObject("list", list);
	    view.addObject("nlist", nlist);
	    view.addObject("elist", elist);
	    return view;
	}
	
	@GetMapping("/my_page/{id}")
	public ModelAndView my_page(@PathVariable String id, HttpSession session) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/B_userpage/user/my_page");
	    List<BoardDTO> list = boardService.selectMainContentList();
		List<BoardDTO> hlist = boardService.selectHeartList(id);
		view.addObject("hlist", hlist);
		view.addObject("list", list);
		return view;
	}


	
	@GetMapping("/search")
	public ModelAndView SearchContentList() {
	    ModelAndView view = new ModelAndView();
	    view.setViewName("B_userpage/search/search");
	    
	    List<BoardDTO> list = boardService.selectMainContentList();
	    view.addObject("list", list);

	    return view; 
	}
	
	@GetMapping("/image/{bno}")
	public void imageDown(@PathVariable("bno") int bno, HttpServletResponse response) {
		FileDTO dto = boardService.selectImageFile(bno);
		
		String path = dto.getPath();
		File file = new File(path);
		String fileName = dto.getFileName();
		
		try {
			fileName = URLEncoder.encode(fileName,"utf-8");
		} catch (UnsupportedEncodingException e1) { 
			e1.printStackTrace();
		}
		
		response.setHeader("Content-Disposition", "attachement;fileName="+fileName);
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setContentLength((int)file.length());
		try(FileInputStream fis = new FileInputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());) {
			
			byte[] buffer = new byte[1024*1024];
			
			while(true) {
				int size = fis.read(buffer);
				if(size == -1) break;
				bos.write(buffer,0,size);
				bos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/memberContent/search") // 검색 부분
	public ResponseEntity<String> selectMemberSearchContentList(String select_box, String search){
		List<BoardDTO> list = boardService.selectMemberSearchContent(select_box,search);
		
		return new ResponseEntity(list,HttpStatus.OK);
	}
	
	@GetMapping("/content/detail/{bno}")
	public ModelAndView updateView(@PathVariable int bno, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		BoardDTO board = boardService.selectBoard(bno, session);
		List<ReviewDTO> rList = reviewService.selectReview(bno);
		List<BoardDTO> list = boardService.selectMainContentList();
//		int result = boardService.selectBoardHeart(bno);
		
		
		MemberDTO dto = (MemberDTO) session.getAttribute("member");
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if(dto != null ) {
			paramMap.put("id", dto.getId());
			paramMap.put("bno", bno);
			
			int result = boardService.selectBoardHeartCHK(paramMap);
			
			mv.addObject("result", result);
		}

		
		//리뷰 목록 조회
		mv.addObject("list", list);
		mv.addObject("board", board);
		mv.addObject("rList", rList);
		mv.setViewName("/B_userpage/content/content_page");
		
		return mv;
	}
	

	@GetMapping("/zoom_search")
	public ModelAndView zoom_search(HttpSession session) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/B_userpage/search/zoom_search");

	    List<BoardDTO> list = boardService.selectMainContentList();
	    List<BoardDTO> nlist = boardService.selectNewContentList();
	    List<BoardDTO> elist = boardService.selectExpireContentList();
	    view.addObject("list", list);
	    return view;
	}
	
	@PostMapping("/heart") 
	public ResponseEntity<String> boardContentHeart(@RequestParam("bno") int bno, HttpSession session) {
	    HashMap<String, Object> map = new HashMap<String, Object>();
	    int result = -1;
	    MemberDTO dto = (MemberDTO) session.getAttribute("member");
	    HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", dto.getId());
		paramMap.put("bno", bno);

	    if(dto == null) {
	        map.put("msg", "로그인 후 이용해주세요.");
	        return new ResponseEntity(map, HttpStatus.OK);
	    }
	    try {
	        boardService.insertBoardHeart(bno, dto.getId());
	        result = boardService.selectBoardHeartCHK(paramMap);
	       // throw new Exception("Something went wrong"); //예외 확인용 Exception 만드는 코드 - 예외 확인할때만 쓸것
	    } catch (Exception e) {
            e.printStackTrace();
            PrintStream ps = null;
            FileOutputStream fos=null;
            File file = new File("C:\\temp\\exception.txt"); // 로그 파일 경로 및 파일명 설정 - 그냥 txt로하면 안만들어지니까 temp 임시 폴더 생성
            try {
                fos = new FileOutputStream(file,true); 
                ps=new PrintStream(fos); 
                System.setErr(ps);
                System.err.println("Exception: " + e.getMessage()); // 예외 메시지 파일에 기록
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if(ps != null) ps.close();
                if(fos != null) try { fos.close(); } catch (IOException ex) { }
            }
        }
	    
	    if (result == 0) {
	    	map.put("dto", dto);
	        map.put("msg", "해당 컨텐츠에 찜을 해제하셨습니다.");
	    } else {
	    	map.put("dto", dto);
	        map.put("msg", "해당 컨텐츠에 찜을 하셨습니다.");
	    }   
	    map.put("fHeart", result);
	    return new ResponseEntity(map, HttpStatus.OK);
	}
	


	
	
	
	
	
	
	
	
}

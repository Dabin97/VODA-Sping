package com.voda.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.voda.dto.BoardDTO;
import com.voda.dto.FileDTO;
import com.voda.dto.MemberDTO;
import com.voda.dto.ReviewDTO;
import com.voda.service.BoardService;
import com.voda.service.MemberService;
import com.voda.service.ReviewService;
import com.voda.vo.PaggingVO;

@Controller
public class MainController {
	private MemberService memberService;
	private BoardService boardService;
	private ReviewService reviewService;
	

	public MainController(MemberService memberService, BoardService boardService, ReviewService reviewService) {
		super();
		this.memberService = memberService;
		this.boardService = boardService;
		this.reviewService = reviewService;
	}

	@RequestMapping("/index")
	public String index() {
		return "index"; 
	}
	
	
	@RequestMapping("/main")
	public String main() {
		return "main"; 
	}
	
	
	@RequestMapping("/content_page")
	public String contentview(HttpSession session) {
		return "content_page";
	} 
	
	@RequestMapping("/register/view")
	public String registerView() {
		return "register";
	}
	
	@RequestMapping("/admin/content/register/view")
	public String adminContentRegisterView() {
		return "admin_content_register";
	}
	
	
	@RequestMapping("/admin/content/add") //등록
	public String adminContentAppend(BoardDTO dto, @RequestParam("file") MultipartFile[] file) {
			int bno = boardService.insertBoard(dto);
			
			//파일 업로드할 경로 설정
			String root = "c:\\fileupload\\";
			//현재 날짜 시간
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
			String date = sdf.format(Calendar.getInstance().getTime());
			
			for(int i=0;i<file.length;i++) {
				if(file[i].getSize() == 0) continue; 
				//서버에 파일을 저장할때 파일명을 날짜시간으로 변경
				//DB에 저장할 때는 원본파일명과 변경된 파일명 모두 저장
				//원본 파일명 뽑음
				String originFileName = file[i].getOriginalFilename();
				//저장할 파일명
				String fileName = date + "_" + i  +originFileName+originFileName.substring(originFileName.lastIndexOf('.'));
				System.out.println("저장할 파일명 : " + fileName);
				
				//실제 파일이 업로드 되는 부분
				try {
					File saveFile = new File(root + fileName); 
					file[i].transferTo(saveFile);
					boardService.insertFile(new FileDTO(saveFile, bno, i));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
				
		return "redirect:/admin/content/list";
	}
	
	
	@RequestMapping("/fileUpload") //UploadAdapter.js에 해당 경로가 연결되어있다. 그리고 해당 js는 board_write_view.html에 제이쿼리로 적용되어있다.
	public ResponseEntity<String> fileUpload(@RequestParam(value="upload") MultipartFile file, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		//원본 파일명
		String originFileName = file.getOriginalFilename();
		//upload 경로 설정
		String root = "c:\\fileupload\\";
		 
		//저장할 파일명
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		String date = sdf.format(Calendar.getInstance().getTime());		
		
		MemberDTO dto = (MemberDTO)session.getAttribute("dto");
		String fileName = date + "_" + dto.getId() +originFileName+ originFileName.substring(originFileName.lastIndexOf('.'));
		System.out.println("저장할 파일명 : " + fileName);
		
		File savefile =  new File(root + fileName); 
		//저장한 파일의 경로를 테이블에 저장
		int fno = boardService.uploadImage(savefile.getAbsolutePath());
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			file.transferTo(savefile); //파일 업로드
			map.put("uploaded", true);
			map.put("url", "/image/"+fno);
			map.put("bi_no", fno); //만든 파일 경로를 bi_no로 보냄. uploadAdater.js에서 bi_no는 파일번호이고 json객체로 받아야하기때문에 hashmap으로 보냄
			
		} catch (IOException e) {
			map.put("uploaded", false);
			map.put("message", "파일 업로드 중 에러 발생");
		}
		return new ResponseEntity(map,HttpStatus.OK); 
	}
	
	@RequestMapping("/image/{fno}")
	public void imageDown(@PathVariable("fno") int fno, HttpServletResponse response) {
		FileDTO dto = boardService.selectImageFile(fno);
		
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
	
	
	@RequestMapping("/filedown") //borad_view 첨부파일 목록 출력
	public void fileDown(int bno, int fno, HttpServletResponse response) { //되돌려줄것없이 write로 뿌릴것만 있으므로 void
		FileDTO dto = boardService.selectFile(bno, fno);	//fileUpload와 중간은 비슷함, bno와 fno를 둘다 보냄줌
		
		try (BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream()); 
			 FileInputStream fis = new FileInputStream(dto.getPath());) {

			byte[] buffer = new byte[1024 * 1024];

			while (true) {
				int count = fis.read(buffer);
				if (count == -1) break;
				bos.write(buffer, 0, count);
				bos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/admin/content/list")
	public ModelAndView adminContentList(@RequestParam(name = "pageNo", defaultValue = "1")int pageNo) {
		ModelAndView view = new ModelAndView();
		view.setViewName("admin_content_list");
		//게시판 글목록
		List<BoardDTO> list = boardService.selectBoardList(pageNo, 7);
		 
		//페이징 정보
		int count = boardService.selectBoardCount();
		PaggingVO pagging = new PaggingVO(count, pageNo, 7);
		
		view.addObject("list",list); 
		view.addObject("pagging",pagging); 
		
		return view;
	}
	
	
	@RequestMapping("/admin/content/edit/view/{bno}") //boardView메소드와 동일 필요없는것만 제거 - 수정창 이동
	public ModelAndView adminContentUpdateView(@PathVariable(name ="bno")int bno) {
		ModelAndView view = new ModelAndView();
		view.setViewName("admin_content_edit");

		//게시글 조회
		BoardDTO board = boardService.selectBoard(bno);
		//첨부파일 목록 조회
		List<FileDTO> fList = boardService.selectFileList(bno);
		
		view.addObject("board", board);
		view.addObject("fList", fList);
		
		return view;
	}
	
	@RequestMapping("/admin/content/edit") //수정메소드
	public String adminContentUdate(BoardDTO dto, String[] del_file, @RequestParam("file") MultipartFile[] file) { //del_file : 삭제할 파일번호, 여러개받을거라 배열로
		boardService.updateBoard(dto);
		//파일 삭제 - 물리적
		//삭제할 파일 목록 받기 -- del_file에 아무것도 없을때 터지므로 if문으로 걸러준다.
		if(del_file != null && del_file.length != 0) { 
			List<String> filePath = boardService.deleteFileList(dto.getBno(),del_file);
			for(String f : filePath) {
				File dFile = new File(f); 
				dFile.delete();
				}
			//파일 삭제 - DB
			boardService.deleteFile(dto.getBno(),del_file);
		}
		
		//새 첨부파일 업로드처리(board-mapper insertFile의 메소드에 서브쿼리 추가함) -boardWrite를 복사해온다.
		// 파일 업로드할 경로 설정
		String root = "c:\\fileupload\\";
		// 현재 날짜 시간
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		String date = sdf.format(Calendar.getInstance().getTime());
		// 저장한 파일경로

		for (int i = 0; i < file.length; i++) {
			if (file[i].getSize() == 0)
				continue;
			// 서버에 파일을 저장할때 파일명을 날짜시간으로 변경
			// DB에 저장할 때는 원본파일명과 변경된 파일명 모두 저장
			// 원본 파일명 뽑음
			String originFileName = file[i].getOriginalFilename();
			// 저장할 파일명
			String fileName = date + "_" + i + originFileName + originFileName.substring(originFileName.lastIndexOf('.'));
			System.out.println("저장할 파일명 : " + fileName);

			try {
				// 실제 파일이 업로드 되는 부분
				File saveFile = new File(root + fileName);
				file[i].transferTo(saveFile);
				boardService.insertFile(new FileDTO(saveFile, dto.getBno(), 0));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:/admin/content/list" + dto.getBno();
	}

	
	@RequestMapping("/admin/content/delete/{bno}") //게시글 첨부파일 댓글삭제 모두 
	public String deleteBoard(@PathVariable(name ="bno")int bno) {
		//첨부파일 목록 조회
		List<FileDTO> fList = boardService.selectFileList(bno);
		//첨부파일 삭제 - bno가 외래키로 설정이 되어있다면 bno만 지워도 다 같이 지워질것이다.
		for(FileDTO f : fList) {
			File d = new File(f.getPath());
			d.delete();
		}
		boardService.deleteFile(bno, null); //d를 null로 넣고 deleteFile sql문을 고친다.
		
		//게시글 삭제
		boardService.deleteBoard(bno);
		return "redirect:/admin/content/list";
		//bno삭제시 comment도 삭제되도록 외래키로 묶어줌. 그러나 파일삭제는 외래키지정으로는 안되고 따로 메인컨트롤러 메소드에서 설정해주어야한다.
	}
	
	

	
	
	@RequestMapping("/member/review/register")
	public String registerReview(ReviewDTO dto) {
		reviewService.insertReview(dto);
		return "redirect:/content_page";
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

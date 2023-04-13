package com.voda.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.voda.dto.BoardDTO;
import com.voda.dto.FileDTO;
import com.voda.dto.ManagerDTO;
import com.voda.dto.MemberDTO;
import com.voda.dto.ReviewDTO;
import com.voda.dto.SecessionDTO;
import com.voda.service.BoardService;
import com.voda.service.MemberService;
import com.voda.service.ReviewService;
import com.voda.service.SecessionService;
import com.voda.vo.PaggingVO;


@Controller

public class MainController {
	private MemberService memberService;
	private BoardService boardService;
	private ReviewService reviewService;
	private SecessionService secessionService; 
	

	public MainController(MemberService memberService, BoardService boardService, ReviewService reviewService, SecessionService secessionService) {
		super();
		this.memberService = memberService; 
		this.boardService = boardService;
		this.reviewService = reviewService;
		this.secessionService = secessionService;
	} 
	
	 

		
	
	
}
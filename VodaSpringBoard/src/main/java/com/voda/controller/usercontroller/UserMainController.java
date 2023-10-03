package com.voda.controller.usercontroller;

import com.voda.dto.BoardDTO;
import com.voda.dto.ReviewDTO;
import com.voda.service.BoardService;
import com.voda.service.MemberService;
import com.voda.service.ReviewService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;

@Controller
public class UserMainController {
	
	private BoardService boardService;
	private MemberService memberService;
	private ReviewService reviewService;
	
	public UserMainController(BoardService boardService, MemberService memberservice, ReviewService reviewService) {
		this.boardService = boardService; 
		this.memberService = memberservice;
		this.reviewService = reviewService;
	}
	
//	@RequestMapping("/index") 
//	public String index() { 
//		return "/B_userpage/user/index";  
//	} 
	
	private static String CLIENT_ID = "";
	private static String CLIENT_SECRET = "";
	
	@RequestMapping("/index")
	public ModelAndView index(HttpSession session, ModelAndView view) throws UnsupportedEncodingException {
		String clientId = CLIENT_ID;
		String redirectURI = URLEncoder.encode("http://localhost:9999/naver/callback", "UTF-8");
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code" + "&client_id=" + clientId
				+ "&redirect_uri=" + redirectURI + "&state=" + state;
		view.addObject("apiURL", apiURL);
		session.setAttribute("state", state);
		view.setViewName("/B_userpage/user/index");
		return view;
	} 

	
	@RequestMapping("/naver/callback")
	public ModelAndView naverCallback(HttpSession session, ModelAndView view, String code, String state)
			throws UnsupportedEncodingException, JSONException {

		String redirectURI = URLEncoder.encode("http://localhost:9999/naver/callback", "UTF-8");

		String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code" + "&client_id=" + CLIENT_ID
				+ "&client_secret=" + CLIENT_SECRET 
				+ "&redirect_uri=" + redirectURI 
				+ "&code=" + code + "&state=" + state;
		
		String res = requestNaverServer(apiURL, null);
		JSONObject json = new JSONObject(res);
		String access_token = json.getString("access_token");
		
		// access_token을 이용하여 사용자 프로필 정보를 가져옴
		String profile_apiURL = "https://openapi.naver.com/v1/nid/me";
		String header = "Bearer " + access_token;
		String profile_res = requestNaverServer(profile_apiURL, header);
		
		if(profile_res != null && !profile_res.equals("")) {
			JSONObject profile_json = new JSONObject(profile_res);
			JSONObject profile_response = profile_json.getJSONObject("response");
			session.setAttribute("user", profile_response.toString());
			session.setAttribute("accessToken", access_token);
			session.setAttribute("refreshToken", json.getString("refresh_token"));
			System.out.println("profile_res = " + profile_res); 
			
			
			// 사용자 정보를 userInfo로 추가하여 view에 담아서 보내기
//			String name = profile_response.getString("name");
			String nickname = profile_response.getString("nickname");
			String snsId = profile_response.getString("id");
			String userInfo = nickname;
			view.addObject("userInfo", userInfo);
			
			// 사용자 정보를 세션에 저장
			session.setAttribute("user", profile_json.getJSONObject("response").toString());
			
			//간편로그인으로 받은 정보를 sns_member 테이블에 등록하는 메서드가 필요함
			HashMap<String, String> snsUser = new HashMap<String, String>();
			snsUser.put("sns_id", snsId);
			snsUser.put("sns_nickname", nickname);
			snsUser.put("sns_type", "naver");
			System.out.println(snsUser);
			
			// 이미 가입된 유저인지 체크
			HashMap<String, Object> snsMember = memberService.selectSnsUser(snsId);
			if (snsMember == null) {
				memberService.insertSnsUser(snsUser);
				snsMember = memberService.selectSnsUser(snsId);
			}  else {
			    // 이미 가입된 유저일 경우, 로그인 처리
				session.setAttribute("user", snsMember.toString());
			}

		}else {
			view.addObject("res", "로그인 실패");
		}
		view.setViewName("/B_userpage/main/main");		
		List<BoardDTO> list = boardService.selectMainContentList();
	    List<BoardDTO> nlist = boardService.selectNewContentList();
	    List<BoardDTO> elist = boardService.selectExpireContentList();
	    view.addObject("list", list);
	    view.addObject("nlist", nlist);
	    view.addObject("elist", elist);
		return view;
	}

	
	@RequestMapping("/naver/getProfile")
	public String getProfilenaver(String accessToken) throws JSONException {
	    String apiURL = "https://openapi.naver.com/v1/nid/me";
	    String header = "Bearer " + accessToken;
	    String result = requestNaverServer(apiURL, header);
	    
	    JSONObject json = new JSONObject(result);
	    return json.toString();     
	}

	
//	@RequestMapping("/naver/getProfile")
//	public ModelAndView getProfilenaver(ModelAndView view, HttpSession session) throws JSONException {
//		String accessToken = (String) session.getAttribute("accessToken");
//		String apiURL = "https://openapi.naver.com/v1/nid/me";
//		String header = "Bearer " + accessToken;
//		String result = requestNaverServer(apiURL, header);
//		
//		JSONObject json = new JSONObject(result);
//		view.addObject("userInfo", json);		
//		view.setViewName("/B_userpage/main/main");
//		return view;
//	}
	
	
	@RequestMapping("/naver/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	public String requestNaverServer(String apiURL, String header) {
		StringBuilder res = new StringBuilder();
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			if(header != null && !header.equals("")) {
				con.setRequestProperty("Authorization", header);
			}
				
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
				System.out.println(res.toString());
			}
		} catch (Exception e) {
			// Exception 로깅
		}
		return res.toString();
	}
	

	@RequestMapping("/")
	public ModelAndView MainContentList(HttpSession session) {
	    ModelAndView view = new ModelAndView();	
	    view.setViewName("/B_userpage/main/main");

	    List<BoardDTO> list = boardService.selectMainContentList();
//	    List<BoardDTO> nlist = boardService.selectNewContentList();
//	    List<BoardDTO> elist = boardService.selectExpireContentList();
//	    List<ReviewDTO> rlist = reviewService.selectMianReviewList();
	    view.addObject("list", list);
//	    view.addObject("nlist", nlist);
//	    view.addObject("elist", elist);
//	    view.addObject("rlist", rlist);
	    return view;
	}
}

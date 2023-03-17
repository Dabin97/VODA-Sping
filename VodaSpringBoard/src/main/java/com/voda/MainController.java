package com.voda;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("/")
	public String main() {
		return "index";
	}
	
	@RequestMapping("/register/view")
	public String registerView() {
		return "register";
	}
	
	@RequestMapping("/admin/content") //경로명 더 명확하게 바꿔야할듯 list랑 edit부분
	public String adminContentEdit() {
		return "admin_content_edit";
	}
	@RequestMapping("/admin/content/list")
	public String adminContentList() {
		return "admin_content_list";
	}
	
}

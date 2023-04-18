package com.voda.controller.admincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminMainController {
	
	@RequestMapping("/admin_main") 
	public String adminMain() {
		return "/A_adminpage/main/admin_main";
		}
	
	@RequestMapping("/admin/index") 
	public String adminIndex() {
		return "/A_adminpage/main/admin_before_login";
	} 

}

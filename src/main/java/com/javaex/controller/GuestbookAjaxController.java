package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GuestbookAjaxController {
	//필드
	//생성자
	//메소드gs
	//메소드일반
	
	//ajax용 방명록
	//http://localhost:8888/ajaxguestbook/index
	@RequestMapping(value="/ajaxguestbook/index", method= { RequestMethod.GET, RequestMethod.POST })
	public String ajaxindex() {
		System.out.println("GuestbookAjaxController.ajaxindex()");
		
		return "/ajaxguestbook/index";
	}
	
}

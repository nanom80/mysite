package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	//필드
	
	//생성자
	//메소드gs
	
	//메소드일반
	//사이트 시작 페이지 http://localhost:8888/
	@RequestMapping(value="", method= {RequestMethod.GET, RequestMethod.POST})
	public String index() {
		System.out.println("MainController.index()");
		
		return "main/index";
	}
	
	@RequestMapping(value="/{id}", method= {RequestMethod.GET, RequestMethod.POST})
	public String index(@PathVariable String id) {
		System.out.println("MainController.index()");
		System.out.println(id+" 회원의 정보를 가져와서 출력한다");
		
		return "main/index";
	}
	
	@RequestMapping(value="/{id}/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String index(@PathVariable String id,
			            @PathVariable int no
			           ) {
		System.out.println("MainController.index()");
		System.out.println(id+" "+no+" 회원의 정보를 가져와서 출력한다");
		
		return "main/index";
	}
}

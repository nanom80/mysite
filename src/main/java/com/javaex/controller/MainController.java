package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	/*
	 * 1.기본(스프링, jsp, mybatis) : 메인, 회원가입폼, 회원가입, 로그인폼, 방명록
	 * 2.세션 : 로그인, 로그아웃, 회원수정폼, 회원수정, 게시판
	 * 3.세션 : 리스트페이징+검색
	 * 4.js
	 * 
	 * 
	 * 계층형 게시판 만들기
	 * 
	 */
	
	//필드
	
	//생성자
	//메소드gs
	
	//메소드일반
	//사이트 시작 페이지 http://localhost:8888/
	//http://13.125.206.63:8080/
	@RequestMapping(value="", method= {RequestMethod.GET, RequestMethod.POST})
	public String index() {
		System.out.println("MainController.index()");
		
		return "main/index";
	}
	
	/*
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
	*/
}

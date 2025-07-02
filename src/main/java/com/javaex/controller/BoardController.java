package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVO;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	//필드
	@Autowired
	private BoardService boardService;
	
	//생성자
	
	//메소드gs
	
	//메소드일반
	
	//게시판 전체 리스트
	//Model model : 자바 객체 → JSP로 전달 (리스트 출력, 상세보기 등)
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("<BoardController.list>");
		
		List<BoardVO> boardList = boardService.exeList();
		
		for (BoardVO board : boardList) {
		    System.out.println(board);
		}
		
		model.addAttribute("boardList", boardList);
		
		return "board/list";
	}
	
	//게시판 전체 리스트2(페이징)
	//@RequestParam("crtPage") > 'value=' 삭제 가능
	//required = false, defaultValue = "1" > 파라미터 없이 http://localhost:8888/board/list2 주소로 접속 가능
	@RequestMapping(value="/list2", method= {RequestMethod.GET, RequestMethod.POST})
	public String list2(@RequestParam(value="crtPage", required = false, defaultValue = "1") int crtPage, Model model) {
		
		System.out.println("<BoardController.list2>");
		
		Map<String, Object> pMap = boardService.exeList2(crtPage);
		
		System.out.println("<BoardController.list2> "+pMap);
		
		model.addAttribute("pMap", pMap);
		
		return "board/list2";
		
	}
	
	//게시판 전체 리스트3(페이징)
	//@RequestParam("crtPage") > 'value=' 삭제 가능
	//required = false, defaultValue = "1" > 파라미터 없이 http://localhost:8888/board/list2 주소로 접속 가능
	@RequestMapping(value="/list3", method= {RequestMethod.GET, RequestMethod.POST})
	public String list3(@RequestParam(value="crtPage", required = false, defaultValue = "1") int crtPage,
			            @RequestParam(value="kwd", required = false, defaultValue = "") String kwd,
			            Model model) {
		
		System.out.println("<BoardController.list3>");
		System.out.println("<BoardController.list3> currentPage: "+crtPage+" kwd: "+kwd);
		
		Map<String, Object> pMap = boardService.exeList3(crtPage, kwd);
		System.out.println("<BoardController.list3> "+pMap);
		
		/*
		for (BoardVO board : boardList) {
		    System.out.println(board);
		}
		*/
		model.addAttribute("pMap", pMap);
		
		return "board/list3";
		
	}
	

}

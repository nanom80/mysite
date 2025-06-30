package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	//Model model : 자바 객체 → JSP로 전달 (리스트 출력, 상세보기 등)
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("BoardController.list()");
		
		List<BoardVO> boardList = boardService.exeList();
		
		for (BoardVO board : boardList) {
		    System.out.println(board);
		}
		
		model.addAttribute("boardList", boardList);
		
		return "board/list";
	}

}

package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.repository.BoardRepository;
import com.javaex.vo.BoardVO;

@Service
public class BoardService {
	//필드
	@Autowired
	private BoardRepository boardRepository;
	//생성자
	
	//메소드gs
	
	//메소드일반
	
	//게시판
	public List<BoardVO> exeList() {
		System.out.println("<BoardService.exeList()>");
	
		List<BoardVO> boardList = boardRepository.boardSelectList();
	
		return boardList;
	}
	
	//게시판2(페이징)
	public Map<String, Object> exeList2(int crtPage) {
		System.out.println("<BoardService.exeList2>");
		
		System.out.println("<BoardService.exeList2> currentPage: "+crtPage);
		
		//리스트 가져오기////////////////////////////////////////
		//한페이지의 출력갯수
		int listCnt = 10;
		
		//시작번호
		//1 -> ( 0번부터 ~ 10개)
		//2 -> (10번부터 ~ 10개)
		//3 -> (20번부터 ~ 10개)
		int startRowNo = (crtPage-1)*listCnt;
		
		//두개의 데이터를 묶는다 -> Map사용
		Map<String, Integer> limitMap = new HashMap<String, Integer>();
		limitMap.put("startRowNo", startRowNo);
		limitMap.put("listCnt", listCnt);
		
		//레파지토리에 보내서 데이타를 받아온다
		List<BoardVO> boardList = boardRepository.boardSelectList2(limitMap);
		
		
		//페이징////////////////////////////////////////////////
		//페이지당 버튼갯수
		int pageBtnCount = 5;
		
		//마지막 페이지 버튼 번호
		/*
		 * 1 2 3 4 5 >
		 * 1->(1,5)
		 * 2->(1,5)
		 * ...
		 * 5->(1,5)
		 * 6->(6,10)
		 * ...
		 */
		int endPageBtnNo = ((int)Math.ceil((double)crtPage/pageBtnCount))*pageBtnCount;
		
		//시작버튼번호
		int startPageBtnNo = endPageBtnNo - pageBtnCount + 1;
		
		System.out.println("<BoardService.exeList2> startPageBtnNo: "+startPageBtnNo+" / endPageBtnNo: "+endPageBtnNo+" /pageBtnCount: "+pageBtnCount);
		
		//전체글갯수
		int totalCount = boardRepository.selectTotalCount();
		System.out.println("<BoardService.exeList2> totalCount:"+totalCount);
		
		//다음 화살표 유무
		//boolean next = listCnt * endPageBtnNo < totalCount;
		boolean next = false;
		if(listCnt * endPageBtnNo < totalCount) {
			next = true;
		} else {
			endPageBtnNo = (int)Math.ceil((double)totalCount/listCnt);
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		System.out.println("<BoardService.exeList2> prev: "+prev+" next: "+next);
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("boardList", boardList); //리스트
		
		pMap.put("prev", prev);
		pMap.put("next", next);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("pageBtnCount", pageBtnCount);
		
		return pMap;
	}
	
	//게시판3(페이징+검색)
	public Map<String, Object> exeList3(int crtPage, String kwd) {
		System.out.println("<BoardService.exeList3>");
		System.out.println("<BoardService.exeList3> currentPage: "+crtPage+" kwd: "+kwd);
		
		//리스트 가져오기////////////////////////////////////////
		//한페이지의 출력갯수
		int listCnt = 10;
		
		//시작번호
		int startRowNo = (crtPage-1)*listCnt;
		
		//두개의 데이터를 묶는다 -> Map사용
		Map<String, Object> limitMap = new HashMap<String, Object>();
		limitMap.put("startRowNo", startRowNo);
		limitMap.put("listCnt", listCnt);
		limitMap.put("kwd", kwd);
		
		//레파지토리에 보내서 데이타를 받아온다
		List<BoardVO> boardList = boardRepository.boardSelectList3(limitMap);
		
		System.out.println("<BoardService.exeList3> "+boardList);
		
		//페이징////////////////////////////////////////////////
		//페이지당 버튼갯수
		int pageBtnCount = 5;
		
		//마지막 페이지 버튼 번호
		int endPageBtnNo = ((int)Math.ceil((double)crtPage/pageBtnCount))*pageBtnCount;
		
		//시작버튼번호
		int startPageBtnNo = endPageBtnNo - pageBtnCount + 1;
		
		System.out.println("<BoardService.exeList3> startPageBtnNo: "+startPageBtnNo+" / endPageBtnNo: "+endPageBtnNo+" /pageBtnCount: "+pageBtnCount);
		
		//전체글갯수
		int totalCount = boardRepository.selectTotalCountByKwd(kwd);
		System.out.println("<BoardService.exeList3> totalCount:"+totalCount);
		
		//다음 화살표 유무
		//boolean next = listCnt * endPageBtnNo < totalCount;
		boolean next = false;
		if(listCnt * endPageBtnNo < totalCount) {
			next = true;
		} else {
			endPageBtnNo = (int)Math.ceil((double)totalCount/listCnt);
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		System.out.println("<BoardService.exeList3> prev: "+prev+" next: "+next);
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("boardList", boardList); //리스트
		
		pMap.put("prev", prev);
		pMap.put("next", next);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("pageBtnCount", pageBtnCount);
		
		return pMap;
	}
}

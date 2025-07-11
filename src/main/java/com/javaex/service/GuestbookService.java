package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.repository.GuestbookRepository;
import com.javaex.vo.GuestbookVO;

@Service
public class GuestbookService {

	//필드
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	//생성자
	
	//메소드gs
	
	//메소드일반
	//-전체방명록 리스트 가져오기
	public List<GuestbookVO> exeGetGuestbookList() {
		System.out.println("GuestbookService.exeGetGuestbookList()");
		
		List<GuestbookVO> guestbookList = guestbookRepository.guestbookSelect();
		
		return guestbookList;
	}
	
	
	//-방명록 저장하기
	public int exeGuestbookAdd(GuestbookVO guestbookVO) {
		System.out.println("GuestbookService.exeGuestbookAdd()");
		
		//dao를 통해서 일한다
		//GuestbookDAO guestbookDAO =  new GuestbookDAO();
		int count = guestbookRepository.guestbookInsert(guestbookVO);
		
		return count;
	}
	
	//-방명록 삭제하기
	public int exeGuestbookRemove(GuestbookVO guestbookVO) {
		System.out.println("GuestbookService.exeGuestbookRemove()");
		
		//dao를 통해서 일한다
		//GuestbookDAO guestbookDAO = new GuestbookDAO();
		int count = guestbookRepository.guestbookDelete(guestbookVO);
		
		return count;
	}
	
	
	//--방명록 저장하기 key조회(ajax)
	public GuestbookVO exeGuestbookAddKey(GuestbookVO guestbookVO) {
		System.out.println("GuestbookService.exeGuestbookAddKey()");
		
		int count = guestbookRepository.guestbookInsertKey(guestbookVO); //저장키값 가져오기
		
		//추가된 방명록 글 가져오기
		GuestbookVO gVO = guestbookRepository.guestbookSelectOne(guestbookVO.getNo());
		
		return gVO;
		
	}
	
	
	
	
	
}
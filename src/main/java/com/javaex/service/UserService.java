package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.repository.UserRepository;
import com.javaex.vo.GuestbookVO;
import com.javaex.vo.UserVO;

//UserController의 @Autowired를 쓰려면 @Service 추가해줘야한다
@Service
public class UserService {
	
	//필드
	@Autowired
	private UserRepository userRepository;
	//생성자
	
	//메소드gs
	
	//메소드일반
	
	//회원가입
	public int exeJoin(UserVO userVO) {
		System.out.println("UserService.exeJoin()");
		System.out.println(userVO);
		int count = userRepository.userInsert(userVO);
		
		return count;
	}
	
	//로그인
	public UserVO exeLogin(UserVO userVO) {
		System.out.println("UserService.exeLogin()");
		userVO = userRepository.userSelectOneByIdPw(userVO);
		System.out.println(userVO);
		
		return userVO;
	}
	
	//방명록쓰기
	public int exeGuestbookWrite(GuestbookVO guestbookVO) {
		System.out.println("UserService.exeGeustbookWrite()");
		int count = userRepository.userGuestbookWrite(guestbookVO);
		System.out.println(guestbookVO);
		
		return count;
	}
	
}

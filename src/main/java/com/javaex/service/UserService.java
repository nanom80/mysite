package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.repository.UserDAO;
import com.javaex.vo.UserVO;

//UserController의 @Autowired를 쓰려면 @Service 추가해줘야한다
@Service
public class UserService {
	
	//필드
	@Autowired
	private UserDAO userDAO;
	//생성자
	
	//메소드gs
	
	//메소드일반
	public int exeUserAdd(UserVO userVO) {
		System.out.println("UserService.exeUserAdd()");
		
		int count = userDAO.userInsert(userVO);
		
		return count;
	}
}

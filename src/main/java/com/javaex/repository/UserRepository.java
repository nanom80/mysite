package com.javaex.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVO;
import com.javaex.vo.UserVO;

@Repository
public class UserRepository {
	//필드
	@Autowired
	private SqlSession sqlSession;
	
	//생성자
	
	//메소드gs
	
	//입력
	public int userInsert(UserVO userVO) {
		
		System.out.println("UserRepository.userInsert()");
		
		int count = sqlSession.insert("mysite.insert", userVO);
		
		return count;
	}
	
	//조회
	public UserVO userSelectOneByIdPw(UserVO userVO) {
		
		System.out.println("UserRepository.userSelectOneByIdPw()");
		System.out.println(userVO);
		
		UserVO authUser = sqlSession.selectOne("mysite.select", userVO);
		
		return authUser;
	}
	
	//방명록쓰기
	public int userGuestbookWrite(GuestbookVO guestbookVO) {
		System.out.println("UserRepository.userGuestbookWrite()");
		System.out.println(guestbookVO);
		
		int count = sqlSession.insert("mysite.insertGuestbook", guestbookVO);
		
		return count;
	}
	
	//user정보가져오기(no) 회원수정폼
	public UserVO userSelectByNo(int no) {
		System.out.println("UserRepository.selectOneByNo()");
		
		UserVO userVO = sqlSession.selectOne("mysite.selectOneByNo", no);
		
		return userVO;
		
	}
	
	//회원수정
	public int userUpdate(UserVO userVO) {
		System.out.println("UserRepository.userUpdate()");
		
		System.out.println(userVO);
		
		int count = sqlSession.update("mysite.update", userVO);
		System.out.println(count);
		
		return 0;
	}
	
	
}

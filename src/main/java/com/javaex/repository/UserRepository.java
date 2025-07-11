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
	
	//메소드일반
	//--user저장(회원가입)
	public int userInsert(UserVO userVO) {
		System.out.println("UserRepository.userInsert()");
		
		int count = sqlSession.insert("user.insert", userVO);
		
		return count;
	}
	
	
	//--user정보가져오기(id password) -->세션저장용
	public UserVO userSelectOneByIdPw(UserVO userVO) {
		System.out.println("UserRepository.userSelectOneByIdPw()");
		
		//System.out.println(userVO);  //id pw   0x999
		
		UserVO authUser = sqlSession.selectOne("user.selectOneByIdPw", userVO);  
		
		//System.out.println(authUser); //다 들어있다  0x567

		return authUser;
		
	}
	
	//--user정보가져오기(no) -->회원수정폼
	public UserVO userSelectByNo(int no) {
		System.out.println("UserRepository.userSelectByNo()");
		
		UserVO userVO = sqlSession.selectOne("user.selectByNo", no);
		
		return userVO;
		
	}
	
	
	//--회원정보수정
	public int userUpdate(UserVO userVO) {
		System.out.println("UserRepository.userUpdate()");
		
		int count = sqlSession.update("user.update", userVO);
		
		return count;
	}
	
	
	//--아이디사용유무체크(회원가입)
	public UserVO userSelectById(String id) {
		System.out.println("UserRepository.userSelectById()");
		
		UserVO userVO = sqlSession.selectOne("user.selectOneById", id);
		return userVO;
	}
	
	//방명록쓰기
	public int userGuestbookWrite(GuestbookVO guestbookVO) {
		System.out.println("UserRepository.userGuestbookWrite()");
		System.out.println(guestbookVO);
		
		int count = sqlSession.insert("mysite.insertGuestbook", guestbookVO);
		
		return count;
	}
	
	
	
}
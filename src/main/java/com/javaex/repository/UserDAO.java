package com.javaex.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVO;

@Repository
public class UserDAO {
	//필드
	@Autowired
	private SqlSession sqlSession;
	
	//생성자
	
	//메소드gs
	
	//입력
	public int userInsert(UserVO userVO) {
		
		System.out.println("UserDAO.userInsert()");
		
		int count = sqlSession.insert("user.insert", userVO);
		
		return count;
	}
}

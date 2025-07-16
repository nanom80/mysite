package com.javaex.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.FileVO;

@Repository
public class FileRepository {
	@Autowired
    private SqlSession sqlSession;

    public int insertFile(FileVO fileVO) {
        return sqlSession.insert("file.insertFile", fileVO);
    }
}

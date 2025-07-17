package com.javaex.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVO;

@Repository
public class GalleryRepository {
	
	@Autowired
    private SqlSession sqlSession;
	
    public List<GalleryVO> selectList() {
        return sqlSession.selectList("gallery.selectList");
    }
    
    public int insert(GalleryVO galleryVO) {
        return sqlSession.insert("gallery.insert", galleryVO);
    }
    
    //hashMap
    public int delete(int no, int user_no) {
        Map<String, Object> map = new HashMap<>();
        map.put("no", no);
        map.put("user_no", user_no);
        return sqlSession.delete("gallery.delete", map);
    }
    
    public int delete(GalleryVO galleryVO) {
    	return sqlSession.delete("gallery.delete", galleryVO);
    }
    
}

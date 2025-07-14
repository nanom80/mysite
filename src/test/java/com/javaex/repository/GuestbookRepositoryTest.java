package com.javaex.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javaex.vo.GuestbookVO;


@SpringBootTest
public class GuestbookRepositoryTest {

	// 필드
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	@Test
	public void selectAll() {
		List<GuestbookVO> guestbookList = guestbookRepository.guestbookSelect();
		System.out.println("----------------------------------------");
		System.out.println(guestbookList);
		System.out.println("----------------------------------------");
		
		assertThat(guestbookList).isNotNull();
	}
	
	@Test
	public void delete() {
		GuestbookVO guestbookVO = new GuestbookVO();
		guestbookVO.setNo(105);
		guestbookVO.setPassword("111");
		
		System.out.println(guestbookVO);
		
		int count = guestbookRepository.guestbookDelete(null);
		assertThat(count).isEqualTo(0);
		//System.out.println(count);
		
	}
	
	@Test
	public void insert() {
		GuestbookVO guestbookVO = new GuestbookVO();
		guestbookVO.setNo(1);
		guestbookVO.setName("111");
		guestbookVO.setPassword("111");
		guestbookVO.setContent("111");
		
		System.out.println(guestbookVO);
		
		int count = guestbookRepository.guestbookInsert(guestbookVO);
		
		assertThat(count).isEqualTo(1);
		//System.out.println(count);
	}
	
	
	
	
}
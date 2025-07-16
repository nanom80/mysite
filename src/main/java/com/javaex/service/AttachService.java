package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.repository.FileRepository;
import com.javaex.vo.FileVO;
import com.javaex.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@Service
public class AttachService {
	//필드
	@Autowired
    private FileRepository fileRepository;
	private HttpSession session;
	//생성자
	//메소드gs
	//메소드일반
	public String exeUpload(MultipartFile file) {
		System.out.println("AttachService.exeUpload()");
		
		String saveDir = "C:\\javaStudy\\upload\\";
		
		System.out.println(file.getOriginalFilename());
		
		//(1)파일정보를 추출, 저장(DB)
		//오리지날 파일명
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);
		
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf(".")+1);
		System.out.println(exName);
		
		//저장파일명(덮어쓰기 방지를 위해서 겹치지 않는 이름으로 저장)
		System.out.println(System.currentTimeMillis());
		System.out.println(UUID.randomUUID().toString());
		
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + "." + exName;
		System.out.println(saveName);
		
		//파일경로
		String filePath = saveDir + saveName;
		System.out.println(filePath);
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println(fileSize);
		
		//UserVO authUser = (UserVO) session.getAttribute("authUser");
		//int userNo = authUser.getNo();
		
		//vo에 묶는다
		FileVO fileVO = new FileVO();
		fileVO.setUserNo(1);
        fileVO.setOrgName(orgName);
        fileVO.setExName(exName);
        fileVO.setSaveName(saveName);
        fileVO.setFilePath(filePath);
        fileVO.setFileSize(fileSize);
        
		System.out.println(fileVO);
		
		//db저장
		fileRepository.insertFile(fileVO);
		
		//(2)실물파일을 하드디스크에 저장
		try {
			byte[] fileData = file.getBytes();
			
			OutputStream os= new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return saveName;
		
	}

}

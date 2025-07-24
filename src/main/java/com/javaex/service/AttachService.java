package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.repository.GalleryRepository;
import com.javaex.vo.GalleryVO;
import com.javaex.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@Service
public class AttachService {
	//필드
	@Autowired
    private GalleryRepository galleryRepository;
	private HttpSession session;
	//생성자
	//메소드gs
	//메소드일반
	
	//리스트
    public List<GalleryVO> getGalleryList() {
        return galleryRepository.selectList();
    }
	
    //저장
	public String uploadImage(MultipartFile file, String content, UserVO user) {
		System.out.println("AttachService.exeUpload()");
		
		String osName = System.getProperty("os.name");
		
		String saveDir = "";
			
		if(osName.contains("win")) {
			System.out.println("win");
			saveDir = "C:\\javaStudy\\upload\\";
		} else {
			saveDir = "/data/upload/";
		}
		
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
		
		System.out.println(file.getOriginalFilename());
		
		//UserVO authUser = (UserVO) session.getAttribute("authUser");
		//int userNo = authUser.getNo();
		
		//vo에 묶는다
		GalleryVO galleryVO = new GalleryVO();
		galleryVO.setUser_no(user.getNo());
		galleryVO.setContent(content);
		galleryVO.setFilePath(filePath);
		galleryVO.setOrgName(orgName);
		galleryVO.setSaveName(saveName);
		galleryVO.setFileSize(fileSize);
        
		System.out.println(galleryVO);
		
		//db저장
		galleryRepository.insert(galleryVO);
		
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
	
	//삭제
    /* repository에서 hash맵을 쓰도록 해봄
    public boolean deleteImage(int no, int user_no) {
        return galleryRepository.delete(no, user_no) > 0;
    }
    */
    
	//repository에서 hash맵을 안쓰도록 해봄
    public boolean deleteImage(int no, int user_no) {
        GalleryVO galleryVO = new GalleryVO();
        galleryVO.setNo(no);
        galleryVO.setUser_no(user_no);
        return galleryRepository.delete(galleryVO) > 0;
    }

}

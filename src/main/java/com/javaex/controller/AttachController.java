package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AttachService;
import com.javaex.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class AttachController {
	
	//필드
	@Autowired
	private AttachService attachService;
	
	//생성자
	
	//메소드gs
	
	//메소드일반
	
	//파일업로드폼
	@RequestMapping(value="/gallery/form", method= {RequestMethod.GET, RequestMethod.POST})
	public String form() {
		System.out.println("AttachController.form()");
		
		return "gallery/form";
	}
	
	//업로드 (보내준 파일을 받아서 저장)
	@RequestMapping(value="/gallery/upload", method= {RequestMethod.GET, RequestMethod.POST})
	public String upload(@RequestParam(value="file") MultipartFile file,
			             @RequestParam("content") String content,
			             HttpSession session) {
		System.out.println("AttachController.upload()");
		
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		
		UserVO authUser = (UserVO) session.getAttribute("authUser");
        if(authUser == null) return "redirect:/user/loginForm";
		attachService.uploadImage(file, content, authUser);
		
		return "redirect:/gallery";
	}
	
	// 삭제 (AJAX)
    @ResponseBody
    @RequestMapping(value="/gallery/delete", method=RequestMethod.POST)
    public boolean delete(@RequestParam("no") int no, HttpSession session) {
        UserVO authUser = (UserVO) session.getAttribute("authUser");
        if(authUser == null) return false;
        return attachService.deleteImage(no, authUser.getNo());
    }

	
}

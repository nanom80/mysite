package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.AttachService;
import com.javaex.vo.GalleryVO;

@Controller
public class GalleryController {
	
	@Autowired
	private AttachService attachService;
	
	//갤러리 리스트
	@RequestMapping(value="/gallery", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("GalleryController.list()");
		
		List<GalleryVO> galleryList = attachService.getGalleryList();
	    model.addAttribute("galleryList", galleryList);

		return "gallery/list";
	}

}

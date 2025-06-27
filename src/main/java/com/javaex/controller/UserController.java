package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.GuestbookVO;
import com.javaex.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	//필드
	@Autowired
	private UserService userService;
	//생성자
	//메소드gs
	
	//메소드일반
	
	//회원가입폼
	//http://localhost:8888/user/joinform
	@RequestMapping(value="/user/joinform", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController.joinForm()");
		
		return "user/joinForm";
	}
	
	//회원가입
	//http://localhost:8888/user/join?id=111&password=111&name=유재석&gender=남
	@RequestMapping(value="user/join", method= {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVO userVO) {
		System.out.println("UserController.join()");
		
		try {
			userService.exeJoin(userVO);
			return "/user/joinok";
		} catch(DuplicateKeyException e) {
			System.out.println("계정중복");
			return "redirect:/user/joinForm";
		} catch(Exception e) {
			return "redirect:/user/joinForm";
		}
		
	}
	
	//로그인폼
	//http://localhost:8888/user/loginform
	//사용자가 사용하는 url은 user/loginform 이렇게 되어있지만 아래의 return "user/loginForm";의 loginForm은 jsp파일이다. 다른 것임.
	@RequestMapping(value="/user/loginform", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController.loginForm()");
		
		return "user/loginForm";
	}
	
	//로그인
	//http://localhost:8888/user/login?id=111&password=111
	@RequestMapping(value="user/login", method= {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVO userVO, HttpSession session) {
		System.out.println("UserController.login()");
		
		UserVO authUser = userService.exeLogin(userVO);
		System.out.println(authUser);
		
		//세션 영역에 로그인 확인용 값을 넣어준다 -> 로그인
		session.setAttribute("authUser", authUser);
		
		return "redirect:/";
	}
	
	//로그아웃
	//http://localhost:8888/user/logout
	@RequestMapping(value="user/logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("UserController.logout()");
		
		//세션 영역의 로그인 확인용 값을 지운다
		//session.removeAttribute("authUser");
		session.invalidate(); //세션정보를 모두 지운다
		
		return "redirect:/";
	}
	
	//방명록폼
	//http://localhost:8888/guestbook/addlist
	@RequestMapping(value="/guestbook/addlist", method= {RequestMethod.GET, RequestMethod.POST})
	public String addlist() {
		System.out.println("UserController.addlist()");
		
		return "guestbook/addlist";
	}
	
	//방명록작성
	//http://localhost:8888/guestbook/addlistWrite?id=111&name=111&password=111&content=111
	@RequestMapping(value="/guestbook/addlistWrite", method= {RequestMethod.GET, RequestMethod.POST})
	public String addlistWrite(@ModelAttribute GuestbookVO guestbookVO) {
		int count = userService.exeGuestbookWrite(guestbookVO);
		
		System.out.println(count);
		
		return "redirect:/guestbook/addlist";
	}
	
}

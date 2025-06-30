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

import org.springframework.ui.Model;
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
	public String addlistWrite(@ModelAttribute GuestbookVO guestbookVO, HttpSession session) {
		
		// 로그인한 사용자 정보 가져오기
	    UserVO authUser = (UserVO) session.getAttribute("authUser");
	    
	    // 방명록에 로그인 ID 저장
	    if (authUser != null) {
	        guestbookVO.setId(authUser.getId());
	    }
		
		int count = userService.exeGuestbookWrite(guestbookVO);
		
		System.out.println(count);
		
		return "redirect:/guestbook/addlist";
	}
	
	//회원정보 수정폼
	@RequestMapping(value = "/user/editform", method = { RequestMethod.GET, RequestMethod.POST })
	public String editForm(HttpSession session, Model model) {
		System.out.println("UserController.editForm()");
		
	    UserVO authUser = (UserVO)session.getAttribute("authUser");
	    System.out.println(authUser);

	    // 로그인 확인
	    if (authUser == null) {
	        return "redirect:/user/loginform";
	    }

	    int no = authUser.getNo();

	    // 회원정보 가져오기
	    UserVO userVO = userService.exeEditForm(no);
	    
	    System.out.println("userVO = " + userVO);

	    // 모델에 담기
	    model.addAttribute("userVO", userVO);

	    return "user/editForm";
	}
	
	//회원정보 수정
	@RequestMapping(value = "/user/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(@ModelAttribute UserVO userVO, HttpSession session) {
		System.out.println("UserController.edit()");
		
		//0.DS가 파라미터 값을 묶어서 준다
		System.out.println(userVO);
		
		//1.세션에서 no값을 꺼내온다
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		int no = authUser.getNo();
		System.out.println(no);
		
		//2.DS가 묶어준 userVO에 세선에서 꺼낸 no를 추가한다
		userVO.setNo(no);
		System.out.println(userVO);
		
		//3.서비스에 묶어둔 userVO를 넘긴다
		userService.exeEdit(userVO);
		
		//4.헤더의 이름 변경 -> 세션의 이름 변경
		//1번에서 가져온 authUser의 비밀번호,이름,성별을 변경한다
		authUser.setPassword(userVO.getPassword());
		authUser.setName(userVO.getName());
		authUser.setGender(userVO.getGender());
		
		
		
		return "redirect:/";
	}
	
}

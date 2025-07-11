package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.MysiteApplication;
import com.javaex.service.UserService;
import com.javaex.vo.GuestbookVO;
import com.javaex.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/user")
public class UserController {

    //필드
	@Autowired
	private UserService userService;

    //생성자
	//메소드gs
	
	//메소드일반
	
	//회원가입폼
	//http://localhost:8888/user/joinform
	@RequestMapping(value = "/joinform", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("UserController.joinForm()");
		
		return "user/joinForm";
	}
	
	//회원가입
	//http://localhost:8888/user/join?id=111&password=111&name=유재석&gender=남
	//@ModelAttribute UserVO userVO -> 클라이언트의 요청 파라미터를 UserVO 객체로 자동 바인딩
	//@ModelAttribute : 폼 입력값 → 자바 객체로 자동 변환
	//즉 @ModelAttribute UserVO userVO : form 데이터 → 객체로 자동 바인딩 (회원가입, 로그인)
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVO userVO) {
		System.out.println("UserController.join()");

		try {
			userService.exeJoin(userVO);
			return "user/joinok";

		} catch (DuplicateKeyException e) {
			System.out.println(e);
			System.out.println("중복아이디");
			return "redirect:/user/joinform";

		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/user/joinform";
		}

	}
	
	//아이디사용가능체크(회원가입)
	@ResponseBody //컨트롤러 메서드의 반환값을 뷰 이름으로 해석하지 않고, HTTP 응답 본문(Body)으로 직접 전송하라는 의미. JSP 찾지 않음.
	@RequestMapping(value="/idcheck", method= {RequestMethod.GET, RequestMethod.POST} )
	public String idcheck(@RequestParam(value="id") String id, Model model) {
		System.out.println("UserController.idcheck()");
		
		boolean isUse = userService.exeIdcheck(id);
		System.out.println(isUse);
		
		
		//기본방식(X)
		//모델에 담으면 jsp에서 꺼내서 jsp를 가지고 공식응답문서를 만든다
		//model.addAttribute("isUse", isUse); 
		
		//데이타(json형식)만 보내준다 (html없음)
		//json형식 {"isUse": true}
		String result = "{\"isUse\":"+isUse+"}";
		//@ResponseBody 상단에 붙이고 데이터는 return 으로 보낸다
		
		return result;
	}
	
	//로그인폼
	//http://localhost:8888/user/loginform
	//사용자가 사용하는 url은 user/loginform 이렇게 되어있지만 아래의 return "user/loginForm";의 loginForm은 jsp파일이다. 다른 것임.
	@RequestMapping(value = "/loginform", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("UserController.loginForm()");

		return "user/loginForm";
	}
	
	//로그인
	//http://localhost:8888/user/login?id=111&password=111
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVO userVO, HttpSession session) {
		System.out.println("UserController.login()");

		UserVO authUser = userService.exeLogin(userVO);
		System.out.println(authUser);

		// 세션영역에 확인용 값을 넣어준다 -->로그인
		session.setAttribute("authUser", authUser);

		return "redirect:/";
	}
	
	//로그아웃
	//http://localhost:8888/user/logout
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("UserController.logout()");

		// 세션의 확인용 값을 지운다
		// session.removeAttribute("authUser");
		session.invalidate();

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
	@RequestMapping(value = "/editform", method = { RequestMethod.GET, RequestMethod.POST })
	public String editForm(HttpSession session, Model model) {
		System.out.println("UserController.editForm()");

		//세션에 값이 있는지 가져온다
		UserVO authUser = (UserVO)session.getAttribute("authUser");

		if(authUser == null) { //로그인 안했을때
			
			return "redirect:/user/loginform";
		
		}else { //로그인 했을때

			//세션에서 no값을 가져온다(지금접속한(로그인된) 사용자의 no값)
			//*파라미터터로 안받고 왜 세션에서 꺼내쓸까????
			//UserVO authUser = (UserVO)session.getAttribute("authUser");
			int no = authUser.getNo();
			
			//no를 서비스에 넘겨서 no회원의 정보를 useVO 형태로 받는다
			UserVO userVO = userService.exeEditForm(no);
			
			//userVO 모델에 담는다 --> D.S야 request의 어트리뷰트에 넣어라
			model.addAttribute("userVO", userVO);
		}
		

		return "user/editForm";
	}
	
	//회원정보 수정
	@RequestMapping(value = "/edit", method = {RequestMethod.GET, RequestMethod.POST })
	public String edit(@ModelAttribute UserVO userVO, HttpSession session) {
		System.out.println("UserController.edit()");
		
		//0.DS가 파라미터 값을 묶어서 준다
		
		//1.세션에서 no값을 꺼내온다
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		int no = authUser.getNo();
		
		//2.DS가 묶어준 userVO에 세션에서 꺼낸 no를 추가한다
		userVO.setNo(no);
		
		//3.서비스에 묶어둔 userVO를 넘긴다
		userService.exeEdit(userVO);
		
		//-----
		
		//4.해더의 이름 변경  --> 세션의 이름변경
		// 위에 1번에서 가져온 authUSer에 이름을 변경한다
		authUser.setName(userVO.getName());
		
		
		//메인리다이렉트 시킨다
		return "redirect:/";
	}
	
	
	
	
}

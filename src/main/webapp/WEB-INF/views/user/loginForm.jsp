<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>MySite</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reset.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/mysite.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user.css">
    </head>
    
    <body>
        <div class="wrap">
            <!-- header -->
            <c:import url="/WEB-INF/views/include/header.jsp">
            </c:import>
            <!-- header -->
            
            <nav>
                <ul class="clearfix">
                    <li><a href="">입사지원서</a></li>
                    <li><a href="">게시판</a></li>
                    <li><a href="">갤러리</a></li>
                    <li><a href="">방명록</a></li>
                </ul>
            </nav>
            
            <div class="content2 clearfix">
                <aside>
                    <h2>유저</h2>
                    <ul>
                        <li><a href="">회원정보</a></li>
                        <li><a href="">로그인</a></li>
                        <li><a href="">회원가입</a></li>
                    </ul>
                </aside>
                
                <main>
                    <div class="main-head clearfix">
                        <h3>회원정보</h3>
                        <ol class="clearfix">
                            <li>홈</li>
                            <li>유저</li>
                            <li>로그인</li>
                        </ol>
                    </div>
                    
                    <div id="user-loginform">
                        <form class="form-box" action="${pageContext.request.contextPath}/user/login" method="get">
                            <div class="info-row">
                                <label class="info-title" for="txt-idcheck">아이디</label>
                                <input id="txt-idcheck" type="text" name="id" value="">
                            </div>
                            <div class="info-row">
                                <label class="info-title" for="txt-pwd">패스워드</label>
                                <input id="txt-pwd" type="password" name="password" value="">
                            </div>
                            <div class="btn-group">
                                <button class="btn btn-blue btn-lg" type="submit">로그인</button>
                            </div>
                        </form>
                    </div>
                </main>
            </div>
            
            <!-- footer -->
            <c:import url="/WEB-INF/views/include/footer.jsp">
            </c:import>
            <!-- footer -->
        </div>
    </body>
</html>
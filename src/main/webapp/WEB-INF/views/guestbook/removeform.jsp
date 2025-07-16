<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>MySite</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reset.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/mysite.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/guestbook.css">
        
        <!-- js -->
		<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-3.7.1.js"></script>
    </head>

    <body>
        <div class="wrap">

            <!-- header -->
            <c:import url="/WEB-INF/views/include/header.jsp" />
            <!-- header -->

            <div class="content2 clearfix">
                <aside>
                    <h2>방명록</h2>
                    <ul>
                        <li><a href="">일반방명록</a></li>
                        <li><a href="">ajax방명록</a></li>
                    </ul>
                </aside>

                <main>
                    <div class="main-head clearfix">
                        <h3>일반방명록</h3>
                        <ol class="clearfix">
                            <li>홈</li>
                            <li>방명록</li>
                            <li>일반방명록</li>
                        </ol>
                    </div>

                    <div id="guestbook-removeform">
                        <form class="form-box" action="" method="">
                            <table>
                                <colgroup>
                                    <col style="width: 10%;">
                                    <col style="width: 50%;">
                                    <col style="width: 25%;">
                                    <col style="width: 15%;">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th>비밀번호</th>
                                        <td>
                                            <input type="password" name="password" value="">
                                        </td>
                                        <td class="text-left">
                                            <button class="btn btn-blue btn-input" type="submit">삭제</button>
                                        </td>
                                        <td class="txt-center">
                                            <a id="btn-main" class="btn btn-gray btn-input" href="">메인으로 돌아가기</a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </main>
            </div>

            <!-- footer -->
            <c:import url="/WEB-INF/views/include/footer.jsp" />
            <!-- footer -->

        </div>
    </body>
</html>
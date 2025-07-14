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

            <nav>
                <ul class="clearfix">
                    <li><a href="">입사지원서</a></li>
                    <li><a href="${pageContext.request.contextPath}/board/list3">게시판</a></li>
                    <li><a href="">갤러리</a></li>
                    <li><a href="${pageContext.request.contextPath}/guestbook">방명록</a></li>
                </ul>
            </nav>

            <div class="content2 clearfix">
                <aside>
                    <h2>방명록</h2>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/guestbook">일반방명록</a></li>
                        <li><a href="${pageContext.request.contextPath}/ajaxguestbook/index">ajax방명록</a></li>
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

                    <div id="guestbook-addlist">
                    	<form class="form-box" action="${pageContext.request.contextPath}/guestbook" method="get">
                            <table>
                                <colgroup>
                                    <col style="width: 70px;">
                                    <col style="width: 340px;">
                                    <col style="width: 70px;">
                                    <col style="width: 340px;">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th>
                                            <label for="txt-name">이름</label>
                                        </th>
                                        <td>
                                            <input id="txt-name" type="text" name="name" value="">
                                        </td>
                                        <th>
                                            <label for="txt-password">패스워드</label>
                                        </th>
                                        <td>
                                            <input id="txt-password" type="password" name="password" value="">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <textarea id="text-content" name="content"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4" class="btn-box">
                                            <button id="btnAdd" class="btn btn-blue btn-lg" type="submit">등록</button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>

                        <!-- 방명록 항목 반복 
                        <table class="guestbook-item">
                            <colgroup>
                                <col style="width: 10%;">
                                <col style="width: 40%;">
                                <col style="width: 40%;">
                                <col style="width: 10%;">
                            </colgroup>
                            <tbody>
                                <tr>
                                    <td>1234555</td>
                                    <td>이정재</td>
                                    <td>2020-03-03 12:12:12</td>
                                    <td class="txt-center">
                                        <a class="btn btn-gray btn-sm" href="">삭제</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4">방명록 글입니다. 방명록 글입니다.</td>
                                </tr>
                            </tbody>
                        </table>

                        <table class="guestbook-item">
                            <colgroup>
                                <col style="width: 10%;">
                                <col style="width: 40%;">
                                <col style="width: 40%;">
                                <col style="width: 10%;">
                            </colgroup>
                            <tbody>
                                <tr>
                                    <td>1234555</td>
                                    <td>이정재</td>
                                    <td>2020-03-03 12:12:12</td>
                                    <td class="txt-center">
                                        <a class="btn btn-gray btn-sm" href="">삭제</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4">방명록 글입니다. 방명록 글입니다.</td>
                                </tr>
                            </tbody>
                        </table>

                        <table class="guestbook-item">
                            <colgroup>
                                <col style="width: 10%;">
                                <col style="width: 40%;">
                                <col style="width: 40%;">
                                <col style="width: 10%;">
                            </colgroup>
                            <tbody>
                                <tr>
                                    <td>1234555</td>
                                    <td>이정재</td>
                                    <td>2020-03-03 12:12:12</td>
                                    <td class="txt-center">
                                        <a class="btn btn-gray btn-sm" href="">삭제</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4">방명록 글입니다. 방명록 글입니다.</td>
                                </tr>
                            </tbody>
                        </table>
                        -->
                        
                    </div>
                </main>
            </div>

            <!-- footer -->
            <c:import url="/WEB-INF/views/include/footer.jsp" />
            <!-- footer -->

        </div>
<script>
$(document).ready(function(){
	console.log('돔트리완성');
	
	fetchList();
	
});


function fetchList(){
	$('#btnAdd').on('click',function(){
		console.log('버튼클릭');
		
		$.ajax({
			
			url : "${pageContext.request.contextPath }/api/guestbook/list",
			type : "post",
			//contentType : "application/json",
			//data : {name: "홍길동"},

			dataType : "json",
			success : function(result){
				/*성공시 처리해야될 코드 작성*/
				console.log(result);
				
				//화면에 그린다
				for(let i=0; i<result.length; i++){
					render(result[i]);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});
	
}

//guestbookVO를 화면에 그린다
function render(guestbookVO){
	console.log(guestbookVO);
	console.log('그린다');
	
	let str =  '<table class="guestbook-item">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tbody>';
		str += '		<tr>';
		str += '			<td>'+ guestbookVO.no +'</td>';
		str += '			<td>'+ guestbookVO.name +'</td>';
		str += '			<td>'+ guestbookVO.regDate +'</td>';
		str += '			<td class="txt-center">';
		str += '				<a class="btn btn-gray btn-sm" href="">삭제</a>';
		str += '			</td>';
		str += '		</tr>';
		str += '		<tr>';
		str += '			<td colspan="4">'+ guestbookVO.content +'</td>';
		str += '		</tr>';
		str += '	</tbody>';
		str += '</table>';

	$('#gbListArea').append(str);
	
}


</script>

    </body>
</html>
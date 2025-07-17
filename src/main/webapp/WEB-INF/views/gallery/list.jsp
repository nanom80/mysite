<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>MySite</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/mysite.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/gallery.css">
</head>
<body>
<div class="wrap">
    <!-- header -->
    <c:import url="/WEB-INF/views/include/header.jsp" />
    <!-- header -->

    <div class="content2 clearfix">
        <aside>
            <h2>갤러리</h2>
            <ul>
                <li><a href="">일반갤러리</a></li>
                <li><a href="">첨부파일연습</a></li>
            </ul>
        </aside>
        <main>
            <div class="main-head clearfix">
                <h3>일반갤러리</h3>
                <ol class="clearfix">
                    <li>홈</li>
                    <li>갤러리</li>
                    <li>일반갤러리</li>
                </ol>
            </div>

            <div id="gallery-list">
                <c:if test="${not empty authUser}">
                    <button id="btn-upload" class="btn btn-blue btn-md" type="button" style="float:right;">이미지올리기</button>
                </c:if>
                <div style="clear:both;"></div>

                <ul class="clearfix">
                    <c:forEach var="img" items="${galleryList}">
                        <li>
                            <div>authUser.no: ${authUser.no} / img.user_no: ${img.user_no}</div>
                            <div class="card">
                                <img 
                                    class="gallery-img"
                                    src="${pageContext.request.contextPath}/upload/${img.saveName}"
                                    alt="이미지"
                                    data-no="${img.no}" 
                                    data-src="${pageContext.request.contextPath}/upload/${img.saveName}"
                                    data-content="${img.content}"
                                    data-writer="${img.userName}"
                                    data-user-no="${img.user_no}"
                                >
                                <div class="writer">
                                    작성자: <strong>${img.userName}</strong>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </main>
    </div>
    
    <footer>
        <p>Copyright ⓒ 2025 황일영. All right reserved</p>
    </footer>
</div>

<!-- 업로드 모달 -->
<div id="modal-upload" class="modal-bg">
    <div class="modal-content">
        <div class="clearfix">
            <button class="btn-close" type="button">X</button>
        </div>
        <p class="title">이미지등록 모달창</p>
        <form id="imgupload-form" action="${pageContext.request.contextPath}/gallery/upload" method="post" enctype="multipart/form-data">
            <div class="info-row">
                <label for="txt-content">글작성</label>
                <input id="txt-content" type="text" name="content" value="">
            </div>
            <div class="info-row">
                <label for="txt-file">이미지선택</label>
                <input type="file" name="file" value="">
            </div>
            <div class="btn-box">
                <button type="submit" class="btn-del btn btn-blue btn-md">등록</button>
            </div>
        </form>
    </div>
</div>

<!-- 이미지보기 모달창 -->
<div id="modal-view" class="modal-bg">
    <div class="modal-content">
        <div class="clearfix">
            <button class="btn-close" type="button">X</button>
        </div>
        <p class="title">이미지보기 모달창</p>
        <div id="img-view">
            <img id="modal-view-img" src="" alt="">
            <!-- <img id="modal-view-img" src="${pageContext.request.contextPath}/upload/${img.saveName}" alt="이미지"> -->
            <div class="img-content" id="modal-view-content">
            여기는 입력한 코멘트가 나옵니다.
            </div>
            <div id="modal-user-no"></div>
            <div>authUser.no: ${authUser.no} / img.user_no: ${modalUserNo}</div>
            <input type="hidden" id="auth-user-no" value="${authUser.no}">
            <button id="modal-del-btn" class="btn-del" style="float:right; display:none;">삭제</button>
        </div>
    </div>
    <!-- footer -->
    <c:import url="/WEB-INF/views/include/footer.jsp" />
    <!-- footer -->
</div>

<script>
document.addEventListener("DOMContentLoaded", function() {
    // 업로드 모달
    var modalUpload = document.getElementById('modal-upload');
    var btnUpload = document.getElementById('btn-upload');
    var btnCloseUpload = modalUpload ? modalUpload.querySelector('.btn-close') : null;

    if(btnUpload && modalUpload) {
        btnUpload.addEventListener('click', function() {
            modalUpload.classList.add('active');
        });
    }
    if(btnCloseUpload && modalUpload) {
        btnCloseUpload.addEventListener('click', function() {
            modalUpload.classList.remove('active');
        });
    }
    if(modalUpload) {
        modalUpload.addEventListener('click', function(e) {
            if(e.target === modalUpload) {
                modalUpload.classList.remove('active');
            }
        });
    }

    // 이미지보기 모달
    var modalView = document.getElementById('modal-view');
    var btnCloseView = modalView ? modalView.querySelector('.btn-close') : null;
    var modalViewImg = document.getElementById('modal-view-img');
    var modalViewContent = document.getElementById('modal-view-content');
    var modalUserNo = document.getElementById('modal-user-no');
    var galleryImgs = document.querySelectorAll('.gallery-img');
    var authUserNo = document.getElementById('auth-user-no')?.value;
    var delBtn = document.getElementById('modal-del-btn');
    
    galleryImgs.forEach(function(img) {
        img.addEventListener('click', function() {
            var userNo = img.getAttribute('data-user-no');
            var content = img.getAttribute('data-content');
            var src = img.getAttribute('data-src');
            
            modalViewImg.src = src;
            modalViewContent.textContent = content;
            modalView.classList.add('active');
            
            // 삭제버튼 표시 여부 결정
            if (authUserNo && userNo && String(authUserNo) == String(userNo)) {
                delBtn.style.display = "inline-block";
            } else {
                delBtn.style.display = "none";
            }
        });
    });
    
    if(btnCloseView && modalView) {
        btnCloseView.addEventListener('click', function() {
            modalView.classList.remove('active');
        });
    }
    if(modalView) {
        modalView.addEventListener('click', function(e) {
            if(e.target === modalView) {
                modalView.classList.remove('active');
            }
        });
    }
    
    
});
</script>
</body>
</html>
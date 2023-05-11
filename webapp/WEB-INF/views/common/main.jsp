<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Project508</title>

    <link rel="stylesheet" href="resources/css/mainstyle.css">

    <script src="https://kit.fontawesome.com/a2e8ca0ae3.js" crossorigin="anonymous"></script>

</head>
<body>
    <main>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    
    <section class="content">
    	<section class="content-1">
    	
    	
    	</section>
    	
    	<section class="content-2">
    		<c:choose>
    			<c:when test="${empty sessionScope.loginMember}">
    				<form action="member/login" method="post" name="login-form" onsubmit="return loginValidate()">
    				
    				<fieldset id="id-pw-area">
    					
    					<section>
    						<input type="text" name="memberEmail" value="${cookie.saveId.value }" placeholder="아이디">
    						<input type="password" name="memberPw" placeholder="비밀번호" >
    					</section>
    					<section>
    						<!-- button의 type 기본 값은 submit -->
    						<button>로그인</button>
    					</section>
    				</fieldset>
    				
    				<c:if test="${!empty cookie.saveId.value}">
    					<c:set var="chk" value="checked" />
    				</c:if>	
    				
    				<label>
    					<input type="checkbox" name="saveId" ${chk} id="saveId"> <span id="saveSpan">아이디 저장</span>
    				</label>
    				
    				<article id="signup-find-area">
    					<a href="${contextPath}/member/signUp">회원가입</a>
    					<span>|</span>
    					<a href="#">ID/PW 찾기</a>
    				</article>
    				</form>
    			</c:when>
    			<c:otherwise>
    				<article class="login-area">
    					<a href="${contextPath}/member/myPage/profile">
    						<c:if test="${empty loginMember.profileImage}">
    							<img src="${contextPath}/resources/images/duck.jpg" id="member-profile">
    						</c:if>
    						
    						<c:if test="${!empty loginMember.profileImage}">
    							<img src="${contextPath}${loginMember.profileImage}" id="member-profile">
    						</c:if>
    					</a>
    					
    					<div class="my-info">
    						<div>
    							<a href="${contextPath}/member/myPage/info" id="nickname">${loginMember.memberNickname}</a>
    							<a href="${contextPath}/member/logout" id="logout-btn">로그아웃</a>
    						</div>
    						
    						<p>
                            	${loginMember.memberEmail}
                            </p>
    					</div>
    				</article> 
    			</c:otherwise>
    		</c:choose>
    	
    	</section>
      </section> 
    </main>

    <!-- footer include -->
     <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <!-- jQuery 라이브러리 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
   
    <!-- main.js 연결 -->
    <script src="${contextPath}/resources/js/main.js"></script>

</body>
</html>
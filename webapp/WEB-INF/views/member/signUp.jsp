<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<link rel="stylesheet" href="../resources/css/mainstyle.css">

<script src="https://kit.fontawesome.com/a2e8ca0ae3.js" crossorigin="anonymous"></script>
</head>
<body>
 <main>
        <!-- hedaer include -->
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <fieldset>
        <table>
            <form action="signUp" method="post" name="signUp-form" onsubmit="return signUpValidate()"> 
            <tr>
                <th>아이디</th>
                <td><input type="text" name="memberEmail" id="memberEmail"></td>
                <td><span id="emailMessage"></span> </td>
            </tr>   
            <tr>
                <th>비밀번호</th>
                <td><input type="password" name="memberPw" id="memberPw"></td>
                <td><span id="pwMessage"></span></td>
            </tr> 
            <tr>
                <th>비밀번호 확인</th>
                <td><input type="password" name="memberPwConfirm" id="memberPwConfirm"></td>
                <td></td>
            </tr>
            <tr>
                <th>닉네임</th>
                <td><input type="text" name="memberNickname" id="memberNickname"></td>
                <td><span id="nicknameMessage"></span></td>
            </tr>
            <tr>
                <th>연락처</th>
                <td><input type="tel" name="memberTel" id="memberTel"></td>
                <td><span id="telMessage"></span></td>
            </tr>
            <tr>
                <th>주소</th>
               
                <td>
                <div class="signUp-input-area">
                        <input type="text" id="sample4_postcode" name="memberAddress"
                                placeholder="우편번호" maxlength="6">
                        
                        <button type="button" onclick="return sample4_execDaumPostcode()">검색</button>
                    </div>
    
                    <div class="signUp-input-area">
                        <input type="text" id="sample4_roadAddress" name="memberAddress" placeholder="도로명주소">
                    </div>
    
                    <div class="signUp-input-area">
                        <input type="text" id="sample4_detailAddress" name="memberAddress" placeholder="상세주소">
                    </div>
    
                    <button type="submit" id="signUp-btn">가입하기</button>
                </td>
            </tr>
          
            </form>
        </table>
    </fieldset>
	</main>
 
       
        <jsp:include page="/WEB-INF/views/common/footer.jsp" />
	
    <!-- jQuery 라이브러리 추가(CDN) -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

    <!-- signUp.js 연결 -->
    <script src="${contextPath}/resources/js/member/signUp.js"></script>

</body>
</html>
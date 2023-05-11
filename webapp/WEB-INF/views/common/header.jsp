<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
    
<header>

	<!-- 클릭 시 메인페이지로 이동하는 로고 -->
	<section>
	    <a href="${contextPath}">
	        <img src="${contextPath}/resources/images/Hoon.jpg" id="home-logo" width="200px">
	    </a>
	</section>
	
	<section>
	    <article class="search-area">
	        <!-- form 내부 input 태그 값을 서버 또는 페이지로 전달 -->
	        <form action="#" name="search-form">
	
	            <!-- fieldset: form 내부에서 input을 종류별로 묶는 용도로 많이 사용 -->
	            <fieldset>
	
	                <!-- autocomplete="off" : HTML 기본 자동완성 사용 X -->
	                <input type="search" id="query" name="query" autocomplete="off" placeholder="">
	
	                <!-- 검색 버튼 -->
	                    <button type="submit" id="search-btn" class="fa-solid fa-magnifying-glass"></button>  
	                </fieldset>
	            </form>
	        </article>
	    </section>
	
	    <section>
	    
	    </section>
	
</header>
 

<nav>
    <ul>
    
 
 	<c:forEach var="boardType" items="${boardTypeList}">
 		<li><a href="${contextPath}/board/list/${boardType.boardCode}">${boardType.boardName}</a></li>
 	</c:forEach>
 	
 

        <li><a href="#">FAQ</a></li>
        <li><a href="#">1:1문의</a></li>
    </ul>
</nav>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<ul>
		<c:if test="${member == null }">
			<li><a href="/Spring-ShoppingEx/signin.do">로그인</a></li>
			<li><a href="/Spring-ShoppingEx/signup.do">회원가입</a></li>


		</c:if>
		<c:if test="${member != null }">
			<c:if test="${member.verify == 9}">
				<li><a href="/Spring-ShoppingEx/index.do">관리자 화면</a></li>
			</c:if>
			
			<li>${member.userName} 님 환영합니다.</li>
			<li>
			  <a href="/Spring-ShoppingEx/cartList.do">쇼핑리스트</a>
			</li>
			<li>
			<a href="/Spring-ShoppingEx/orderList.do">주문리스트</a>
			</li>
			<li>
			  <a href="/Spring-ShoppingEx/signout.do">로그아웃</a>
			</li>
			
		</c:if>
	</ul>

</body>
</html>

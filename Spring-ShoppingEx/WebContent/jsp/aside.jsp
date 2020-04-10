<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-compat/3.0.0-alpha1/jquery.min.js"></script>

<style>

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
 <c:if test="${member.verify == 0 || member == null}">
                  <h3>카테고리</h3>
 <ul>
                <li><a href="/Spring-ShoppingEx/slist.do?cateCode=100&l=1">무기</a>
                
					<ul class="low">
					 <li><a href="/Spring-ShoppingEx/slist.do?cateCode=101&l=2">돌격소총</a></li>
					 <li><a href="/Spring-ShoppingEx/slist.do?cateCode=102&l=2">기관단총</a></li>
					 <li><a href="/Spring-ShoppingEx/slist.do?cateCode=103&l=2">경기관총</a></li>
					 <li><a href="/Spring-ShoppingEx/slist.do?cateCode=104&l=2">산탄총</a></li>
					 <li><a href="/Spring-ShoppingEx/slist.do?cateCode=105&l=2">지정사수소총</a></li>
					 <li><a href="/Spring-ShoppingEx/slist.do?cateCode=106&l=2">저격소총</a></li>
					 <li><a href="/Spring-ShoppingEx/slist.do?cateCode=107&l=2">기타</a></li>
					</ul>
			    </li>



     					<li><a href="/Spring-ShoppingEx/slist.do?cateCode=200&l=1">탄약</a></li>
						<li><a href="/Spring-ShoppingEx/slist.do?cateCode=300&l=1">방어구</a></li>
						<li><a href="/Spring-ShoppingEx/slist.do?cateCode=400&l=1">회복제</a></li>
</ul> 
</c:if>



  <c:if test="${member.verify == 9}">
   
 
					<ul>
					    <li><a href="/Spring-ShoppingEx/register.do">상품 등록</a></li>
     					<li><a href="/Spring-ShoppingEx/list.do">상품 목록</a></li>
     					<li><a href="/Spring-ShoppingEx/adminList.do">주문 목록</a></li>
						<li><a href="/Spring-ShoppingEx/allReply.do">상품 소감</a></li>
						<li><a href="">유저 목록</a></li>
					</ul>
  
  
  </c:if>
        
								
</body>
</html>
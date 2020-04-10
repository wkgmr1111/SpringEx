<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 class="title">
<c:if test="${member.verify == 0 || member == null}">

   <a href="/Spring-ShoppingEx/home.do">shop</a>
   
</c:if>



  <c:if test="${member.verify == 9}">
   
  <a href="/Spring-ShoppingEx/home.do">Admin</a>
  
  
  </c:if>
</h1>
</body>
</html>
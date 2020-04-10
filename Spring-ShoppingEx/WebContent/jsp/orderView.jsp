<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jquery 라이브러리  -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-compat/3.0.0-alpha1/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style>
body {
	font-family: '맑은 고딕', verdana;
	padding: 0;
	margin: 0;
}

ul {
	padding: 0;
	margin: 0;
	list-style: none;
	 
}

div#root {
	width: 90%;
	margin: 0 auto;
}

header#header {
	font-size: 60px;
	padding: 20px 0;
}

header#header h1 a {
	color: #000;
	font-weight: bold;
}

nav#nav {
	background: #f9f9f9;
	padding: 10px;
	text-align: right;
}

nav#nav ul li {
	display: inline-block;
	margin-left: 10px;
}

 section#container {
	padding: 20px 0;
	border-top: 2px solid #eee;
	border-bottom: 2px solid #eee;
}

section#container:after {
	content: "";
	display: block;
	clear: both;
}

aside {
	float: left;
	width: 200px;
}

div#container_box {
	float: right;
	width: calc(100% - 200px - 20px);
}

aside ul li {
	text-align: center;
	margin-bottom: 10px;
}

aside ul li a {
	display: block;
	width: 100%;
	padding: 10px 0;
}

aside ul li a:hover {
	background: #eee;
}

footer#footer {
	background: #f9f9f9;
	padding: 20px;
}

footer#footer ul li {
	display: inline-block;
	margin-right: 10px;
}
</style>
<style>
/*
 section#content ul li { display:inline-block; margin:10px; }
 section#content div.goodsThumb img { width:200px; height:200px; }
 section#content div.goodsName { padding:10px 0; text-align:center; }
 section#content div.goodsName a { color:#000; }
*/
 .orderInfo { border:5px solid #eee; padding:10px 20px; margin:20px 0;}
 .orderInfo span { font-size:20px; font-weight:bold; display:inline-block; width:90px; }
 
 .orderView li { margin-bottom:20px; padding-bottom:20px; border-bottom:1px solid #999; }
 .orderView li::after { content:""; display:block; clear:both; }
 
 .thumb { float:left; width:200px; }
 .thumb img { width:200px; height:200px; }
 .gdsInfo { float:right; width:calc(100% - 220px); line-height:2; }
 .gdsInfo span { font-size:20px; font-weight:bold; display:inline-block; width:100px; margin-right:10px; }
</style>
</head>
<body>
	<div id="root">
		<header id="header">
			<div id="header_box">
				<%@ include file="header.jsp"%>
			</div>
		</header>

		<nav id="nav">
			<div id="nav-box">
				<%--   <%@ include file ="nav.jsp" %> --%>
				<ul>

					<c:if test="${member != null }">

						<li><a href="/Spring-ShoppingEx/home.do">일반화면</a></li>

						<li><a href="/Spring-ShoppingEx/signout.do">로그아웃</a></li>
					</c:if>
				</ul>
			</div>
		</nav>

		<section id="container">
			<aside>
				<%@ include file="aside.jsp"%>
			</aside>
			<div id="container_box">
			<section>
			   <div class="orderInfo">
			      <c:forEach items="${orderView }" varStatus="status" var="oview">
			         <c:if test="${status.first }">
			            <p><span>수령인</span>${oview.orderRec}</p>
			            <p><span>주소</span>(${oview.userAddr1}) ${oview.userAddr2} ${oview.userAddr3} </p>
			            <p><span>가격</span><fmt:formatNumber pattern="###,###,###" value="${oview.amount }"/>원</p>
			            <p><span>상태</span>${oview.delivery}</p>
			         
			         </c:if>
			      
			      </c:forEach>
			   </div>
			   
			   <ul class="orderView">
			      <c:forEach items="${orderView }" var="oview">
			         <li>
			            <div class="thumb">
			              <img src="/Spring-ShoppingEx${oview.gdsThumbImg }"/>
			            
			            </div>
			            <div class="gdsInfo">
			               <p>
			                    <span>상품명</span>${oview.gdsName }<br/>
			                    <span>개당가격</span><fmt:formatNumber pattern="###,###,###" value="${oview.gdsPrice }"/>원 <br/>
			                    <span>구입수량</span>${oview.cartStock }개<br/>
			                    <span>최종가격</span><fmt:formatNumber pattern="###,###,###" value="${oview.gdsPrice * oview.cartStock }"/>원
			               </p>
			            </div>
			         </li>
			      </c:forEach>
			   
			   </ul>
			</section>


			</div>
		</section>

		<footer id="footer">
			<div id="footer_box">
				<%@ include file="footer.jsp"%>
			</div>

		</footer>


	</div>
</body>
</html>

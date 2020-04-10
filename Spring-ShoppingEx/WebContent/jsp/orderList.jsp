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
body {font-family: '맑은 고딕', verdana;padding: 0;margin: 0;}

ul {padding: 0;margin: 0;list-style: none;}

div#root {width: 90%;margin: 0 auto;}
header#header {font-size: 60px;padding: 20px 0;}
header#header h1 a {color: #000;font-weight: bold;}
nav#nav {background: #f9f9f9;padding: 10px;text-align: right;}
nav#nav ul li {display: inline-block;margin-left: 10px;}

section#container {padding: 20px 0;border-top: 2px solid #eee;border-bottom: 2px solid #eee;}

section#container:after {content: "";display: block;clear: both;}

aside{float: left;width: 200px;}

div#container_box {float: right;width: calc(100% - 200px - 20px);}
 
aside#aside h3 { font-size:22px; margin-bottom:20px; text-align:center; }
aside ul li {text-align: center;margin-bottom: 10px;}
aside ul li a {display: block;width: 100%;padding: 10px 0;}
aside ul li a:hover {background: #eee;}
footer#footer {background: #f9f9f9;padding: 20px;}
footer#footer ul li {display: inline-block;margin-right: 10px;}

aside#aside li { position:relative; }
aside#aside li:hover { background:#eee; }   
aside#aside li > ul.low { display:none; position:absolute; top:0; left:180px;  }
aside#aside li:hover > ul.low { display:block; }
aside#aside li:hover > ul.low li a { background:#eee; border:1px solid #eee; }
aside#aside li:hover > ul.low li a:hover { background:#fff;}
aside#aside li > ul.low li { width:180px; }
</style>
<style>
#container_box table{width: 900px;}
#container_box table th{font-size: 20px; font-weight:bold;
                        text-align: center; padding: 10px; border-bottom: 2px solid #666;  }
#container_box table tr:hover {background: #eee;}
#container_box table td{padding: 10px; text-align: center;}
#container_box table img { width:150px; height: auto; }
</style>
<style>
/*
 section#content ul li { display:inline-block; margin:10px; }
 section#content div.goodsThumb img { width:200px; height:200px; }
 section#content div.goodsName { padding:10px 0; text-align:center; }
 section#content div.goodsName a { color:#000; }
*/
 section#content ul li { border:5px solid #eee; padding:10px 20px; margin-bottom:20px; }
 section#content .orderList span { font-size:20px; font-weight:bold; display:inline-block; width:90px; margin-right:10px; }
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
			<aside id="aside">
				<%@ include file="aside.jsp"%>
			</aside>
			<div id="container_box">
			      <section id="content">
			          <ul class="orderList">
			             <c:forEach items="${olist }" var="olist">
			             <li>
			                <div>
			                  <p><span>주문번호</span><a href="/Spring-ShoppingEx/orderView.do?n=${olist.orderId }">${olist.orderId }</a></p>
			                  <p><span>수령인</span><a href="${olist.orderRec }"></a>${olist.orderRec} </p>
			                  <p><span>주소</span>(${olist.userAddr1}) ${olist.userAddr2 } ${olist.userAddr3} </p>
			                  <p><span>가격</span><fmt:formatNumber pattern="###,###,###" value="${olist.amount }"/>원</p>
			                  <p><span>상태</span>${olist.delivery}"</p>
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

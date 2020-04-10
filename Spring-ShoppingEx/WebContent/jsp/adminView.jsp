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
 .orderInfo { border:5px solid #eee; padding:10px 20px; margin:20px 0;}
 .orderInfo span { font-size:20px; font-weight:bold; display:inline-block; width:90px; }
 
 .orderView li { margin-bottom:20px; padding-bottom:20px; border-bottom:1px solid #999; }
 .orderView li::after { content:""; display:block; clear:both; }
 
 .thumb { float:left; width:200px; }
 .thumb img { width:200px; height:200px; }
 .gdsInfo { float:right; width:calc(100% - 220px); line-height:2; }
 .gdsInfo span { font-size:20px; font-weight:bold; display:inline-block; width:100px; margin-right:10px; }
 .deliveryChange { text-align:right; }
 .delivery1_btn,
 .delivery2_btn { font-size:16px; background:#fff; border:1px solid #999; margin-left:10px; }

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
			      <c:forEach items="${AdorderView }" varStatus="status" var="adoview">
			         <c:if test="${status.first }">
			            <P><span>주문자</span>${adoview.userId }</p>
			            <p><span>수령인</span>${adoview.orderRec}</p>
			            <p><span>주소</span>(${adoview.userAddr1}) ${adoview.userAddr2} ${adoview.userAddr3} </p>
			            <p><span>가격</span><fmt:formatNumber pattern="###,###,###" value="${adoview.amount }"/>원</p>
			            <p><span>상태</span>${adoview.delivery}</p>

								<div class="deliveryChange">
								       <div class="deliveryChange">
										<form action="adminView.do" method="post" class="deliveryForm">

											<input type="text" name="orderId"
												value="${adoview.orderId}" /> 
										    <input type="hidden"name="delivery" id="delivery" class="delivery" value="" />
												
											<script>
											$(document).ready(function () {
												
												
											
												$(".delivery1_btn").click(function() {
													console.log("배송중?")
													$("#delivery").val("배송 중");
															run();
														});

												$(".delivery2_btn").click(function() {
													$(".delivery").val("배송 완료");
															run();

														});

												function run() {
													$(".deliveryForm").submit();
												}
											})
											</script>
									<button type="button"  class="delivery1_btn">배송 중</button>
									<button type="button" class="delivery2_btn">배송 완료</button>
										</form>
									</div>
								
								
								
								
								</div>
							</c:if>
			         
			      </c:forEach>
			   </div>
			   
			   <ul class="orderView">
			      <c:forEach items="${AdorderView }" var="adoview">
			         <li>
			            <div class="thumb">
			              <img src="/Spring-ShoppingEx${adoview.gdsThumbImg }"/>
			            
			            </div>
			            <div class="gdsInfo">
			               <p>
			                    <span>상품명</span>${adoview.gdsName }<br/>
			                    <span>개당가격</span><fmt:formatNumber pattern="###,###,###" value="${adoview.gdsPrice }"/>원 <br/>
			                    <span>구입수량</span>${adoview.cartStock }개<br/>
			                    <span>최종가격</span><fmt:formatNumber pattern="###,###,###" value="${adoview.gdsPrice * adoview.cartStock }"/>원
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

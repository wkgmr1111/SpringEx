<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
	<title>Home</title>
<style type="text/css">

div#root {width: 90%;margin: 0 auto;}
 header#header div#header_box { text-align:center; padding:30px 0; }
 header#header div#header_box h1 { font-size:50px; }
 header#header div#header_box h1 a { color:#000; }
 
 nav#nav div#nav_box { font-size:14px; padding:10px; text-align:right; }
 nav#nav div#nav_box li { display:inline-block; margin:0 10px; }
 nav#nav div#nav_box li a { color:#333; }

nav#nav {background: #f9f9f9;padding: 10px;text-align: right;}
nav#nav ul li {display: inline-block;margin-left: 10px;}
section#container {padding: 20px 0;border-top: 2px solid #eee;border-bottom: 2px solid #eee;}

section#container:after {content: "";display: block;clear: both;}

aside{float: left;width: 200px;}

div#container_box {float: right;width: calc(100% - 200px - 30px); }

footer#footer {background: #f9f9f9;padding: 20px;}
footer#footer ul li {display: inline-block;margin-right: 10px;}
section#content div.input_area { padding:0px 40px; margin-bottom:20px; margin-left: 20%;}

section#content input#userId { margin-left: 5px;}
section#content form{margin-left :13%;}
section#content button{margin-left: 32%;}


</style>

</head>

<body>

<div id="root">

    <header id="header">
    <div id="header_box">
      <%@ include file ="header.jsp" %>
    </div>
    </header>

    <nav id="nav">
       <div id="nav-box">
        <%@ include file ="nav.jsp" %>
       
       </div>
    </nav>
    
    <section id="container">
       <div id="container_box">
        <section id="content">
					<form action="login.do" method="post" autocomplete="off">
						<div class="input_area">
							<label for="userId">&nbsp 아이디</label> 
							<input type="email" id="userId" name="userId" required="required" />
						</div>

						<div class="input_area">
							<label for="userPass">패스워드</label> <input type="password"
								id="userPass" name="userPass"  required="required" />
						</div>

						

						<button type="submit" id="signin_btn" name="signin_btn">로그인</button>
                     <c:if test="${msg == false }">
                        
                       <p style="color:#f00;" >로그인에 실패했습니다.</p>
                     </c:if>
                   
					</form>
			</section>
       </div>
    </section>
    
   <footer id="footer">
     <div id="footer_box">
        <%@ include file ="footer.jsp" %>
     </div>
   
   </footer>


</div>
</body>
</html>

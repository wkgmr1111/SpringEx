<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-compat/3.0.0-alpha1/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

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
	<title>Home</title>
</head>

<body>

<div id="root">
    <header id="header">
    <div id="header_box">
      <%@ include file ="header.jsp" %>
    </div>
    </header>

    <nav id="nav">
       <div id="nav_box">
        <%@ include file ="nav.jsp" %>
       
       </div>
    </nav>
    <section id="container">
                 <aside id="aside">
       
                  <%@ include file ="aside.jsp" %>
                 </aside>
      
       
       <div id="container_box">
               <section id="content">
                                      본문영역
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

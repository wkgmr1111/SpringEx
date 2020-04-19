<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>


<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Spring 게시판!</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>



  <div class="collapse navbar-collapse" id="navbarsExample03">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#" onclick="location.href='${path}/board/getBoardList'">Board <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Q&A</a>
      </li>
  
     <li class="nav-item active">
         <span class="nav-link">${user.uid }환영합니다!!</span>
         
      </li>
     <li class="nav-item active">
        <a class="nav-link" href="#" onclick="location.href='${path}/user/signout'">로그아웃 <span class="sr-only">(current)</span></a>
      </li>
    <!--   <li class="nav-item">

        <a class="nav-link disabled" href="#">Disabled</a>

      </li>

      <li class="nav-item dropdown">

        <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>

        <div class="dropdown-menu" aria-labelledby="dropdown03">

          <a class="dropdown-item" href="#">Action</a>

          <a class="dropdown-item" href="#">Another action</a>

          <a class="dropdown-item" href="#">Something else here</a>

        </div>

      </li> -->

    </ul>
    <form class="form-inline my-2 my-md-0">
      <input class="form-control" type="text" placeholder="Search">
    </form>
  </div>
</nav>
</body>
</html>
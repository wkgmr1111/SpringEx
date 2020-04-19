<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<script>
function fn_btnSignUp() {
	location.href="${path}/login/signupForm"
}

</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 로그인 폼 {s} -->
	<form:form class="form-signin" name="form" id="form" role="form"
		modelAttribute="userVO" method="post"
		action="${path}/user/mypage">
		<div class="text-center mb-4">
			<h1 class="h3 mb-3 font-weight-normal">Lee's Board</h1>
		</div>
		<div class="form-label-group">
			<form:input path="uid" id="uid" class="form-control"
				placeholder="User ID" required="" autofocus="" />
			<label for="uid" class="sr-only">User ID</label>
		</div>
		<div class="form-label-group">
			<form:password path="pwd" id="pwd" class="form-control"
				placeholder="User Password" required="" />
			<label for="pwd" class="sr-only">User Password</label>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
			in</button>
		<span style="font-size: 11pt;"><a href="#" onclick="fn_btnSignUp()">Sign up</a></span>
		<p class="mt-5 mb-3 text-muted text-center">© 2020. Lee's  All
			rights reserved.</p>
	</form:form>




</body>
</html>
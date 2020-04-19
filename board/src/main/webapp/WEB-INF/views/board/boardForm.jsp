<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri ="http://www.springframework.org/tags/form" %> 
<!-- form:form태그 사용 데이터바인딩관련된 여러가지태그를 사용할수있는 이점
 form이라는 키워드와 name이라는 속성대신 path라는 속성사용  -->

<!DOCTYPE html>
<html>

<head>
<style>
.ck.ck-editor{
width: 1110px;

}
 .ck-editor__editable{
height: 300px;
} 
</style>
<meta charset="UTF-8">
<title>board</title>

<script src="https://cdn.ckeditor.com/ckeditor5/18.0.0/classic/ckeditor.js"></script>
<script>
	$(document).on('click', '#btnSave', function() {

		$("#form").submit();

	});

	$(document).on('click','#btnList',function() {

			location.href = "${path}/board/getBoardList";

	});
	
	
	
	
	
</script>
<script >

$(document).ready(function() {
	var mode ='<c:out value="${mode}"/>';
	
	
	if(mode =='edit'){
		$("#reg_id").prop('readonly',true);
		$("input:hidden[name='bid']").val('<c:out value="${boardContent.bid}"/>');
		$("input:hidden[name='mode']").val('<c:out value="${mode}"/>');
		
		$("#reg_id").val('<c:out value="${boardContent.reg_id}"/>');
		$("#title").val('<c:out value="${boardContent.title}"/>');
		$("#content").val('<c:out value="${boardContent.content}"/>');
		$("#tag").val('<c:out value="${boardContent.tag}"/>')
		
	}
})
</script>
</head>

<body>

	<article>

		<div class="container" role="main">

			<h2>board Form</h2>

			<form:form name="form" id="form" role="form" modelAttribute="BoardVO" method="post"
				action="${pageContext.request.contextPath}/board/saveBoard">
                <!--숨김처리되어 보이지는 않으나, 데이터를 서버로전송할떄 반드시필요한 데이터  -->
                <form:hidden path="bid"/>
                <!--mode는 일반적인html form태그사용 이유는 boardVO에 mode라는 값이 없기때문에  -->
                <input type="hidden" name="mode"/>
                
				<div class="mb-3">

					<label for="title">제목</label><form:input path="title" class="form-control" id="title"
					 	placeholder="제목을 입력해 주세요"/>

				</div>



				<div class="mb-3">

					<label for="reg_id">작성자</label> <form:input path="reg_id" type="text"
						class="form-control" id="reg_id"
						placeholder="이름을 입력해 주세요"/>

				</div>



				<div class="mb-3">

					<label for="content">내용</label>

					<form:textarea path="content" class="form-control" rows="15"  id="content"
						placeholder="내용을 입력해 주세요"></form:textarea>

				</div>



				<div class="mb-3">

					<label for="tag">TAG</label> <form:input path="tag" type="text"
						class="form-control"  id="tag" placeholder="태그를 입력해 주세요"/>
				</div>
			</form:form>
			<div>
				<button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
			
				<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
			</div>
		</div>
	</article>
<script src="${pageContext.request.contextPath}/resources/common/js/ckeditor.js"></script>
</body>


</html>
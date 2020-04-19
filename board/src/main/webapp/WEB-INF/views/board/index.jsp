<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>

<script>
$(document).on("click",'#btnWriteForm', function () {
	
	location.href= "${path}/board/boardForm";
})

 function fn_contentView(bid) {
	var url="${path}/board/getBoardContent";
	url= url +"?bid=" +bid;
	location.href= url;
} 
</script>


<script>

//이전 버튼 이벤트

function fn_prev(page, range, rangeSize, searchType,keyword) {

		var page = ((range - 2) * rangeSize) + 1;
		var range = range - 1;
		var url = "${path}/board/getBoardList";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		url = url + "&searchType=" + $('#searchType').val();
		url = url + "&keyword=" +keyword

		location.href = url;

	}



  //페이지 번호 클릭

	function fn_pagination(page, range, rangeSize, searchType, keyword) {

		var url = "${path}/board/getBoardList";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		url = url + "&searchType=" + $('#searchType').val();
		url = url + "&keyword=" +keyword;

		location.href = url;	

	}

	//다음 버튼 이벤트

	function fn_next(page, range, rangeSize,searchType,keyword) {
		var page = parseInt((range * rangeSize)) + 1;
		var range = parseInt(range) + 1;
		var url = "${path}/board/getBoardList";

		url = url + "?page=" + page;
		url = url + "&range=" + range;
		url = url + "&searchType=" + $('#searchType').val();
		url = url + "&keyword=" +keyword

		location.href = url;

	}

</script>
<script>
//검색버튼 이벤트
$(document).on('click','#btnSearch', function (e) {
	
	e.preventDefault();
	var url ="${path}/board/getBoardList";
	url =url + "?searchType=" + $('#searchType').val();
	url = url + "&keyword=" +$('#keyword').val();
	location.href = url;
	console.log(url);
	
})

</script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<article>
<div class="container">

<h2>board list</h2>
<div class="table-responsive">

	<table class="table table-stiped table-sm">
		<colgroup>
			<col style="width:5%;" />
			<col style="width:auto;" />
			<col style="width:15%;" />
			<col style="width:10%;" />
			<col style="width:10%;" />
		</colgroup>

		<thead>
			<tr>
				<th>NO</th>
				<th>글제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>

			<c:choose>

				<c:when test="${empty boardList }" >
					<tr><td colspan="5" align="center">데이터가 없습니다.</td></tr>
				</c:when> 
				<c:when test="${!empty boardList}">
					<c:forEach var="list" items="${boardList}">
						<tr>
							<td><c:out value="${list.bid}"/></td>
							
					        <td><a href="#" onclick="fn_contentView(<c:out value="${list.bid}"/>)"><c:out value="${list.title}"/></a></td>
 							<td><c:out value="${list.reg_id}"/></td>
							<td><c:out value="${list.view_cnt}"/></td>
							<td><c:out value="${list.reg_dt}"/></td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</tbody>
	</table>
</div>
		<div >
			<button type="button" class="btn btn-sm btn-primary" id="btnWriteForm">글쓰기</button>
		</div>
		<br>
      <!-- pagination{s} -->

	<div id="paginationBox">
		<ul class="pagination">
			<c:if test="${pagination.prev}">
				<li class="page-item"><a class="page-link" href="#" onClick="fn_prev('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}','${pagination.searchType}','${pagination.keyword}')">Previous</a></li>
			</c:if>

			<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="idx">
				<li class="page-item" <c:out value="${idx == idx ? 'active' : ''}"/> ><a class="page-link" href="#" onClick="fn_pagination('${idx}', '${pagination.range}', '${pagination.rangeSize}','${pagination.searchType}','${pagination.keyword}')"> ${idx} </a></li>
			</c:forEach>

		
			<c:if test="${pagination.next}">
				<li class="page-item"><a class="page-link" href="#" onClick="fn_next('${pagination.range}', 

'${pagination.range}', '${pagination.rangeSize}','${pagination.searchType}','${pagination.keyword}')" >Next</a></li>
			</c:if>
		</ul>
	</div>

	<!-- pagination{e} -->
	<!--search  -->
   <div class="form-group row justify-content-center">
        <div class="w100" style="padding-right:10px">
           <select class="form-control form-control-sm" name="searchType" id="searchType">
              <option value="title">제목</option>
              <option value="reg_id">작성자</option>
              <option value="content">본문</option>
           
           
           </select>
        
        </div>
        <div class="w300" style="padding-right:10px">
           <input type="text" class="form-control form-control-sm" name="keyword" id="keyword">
        
        </div>
        <div>
        
         <button class="btn btn-sm btn-primary" name="btnSearch" id="btnSearch">검색</button>
        </div>
   
   
   </div>

</div>
</article>
</body>
</html>
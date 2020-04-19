<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri ="http://www.springframework.org/tags/form" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.0/jquery.js"></script>

<script>
  //게시판 목록 이동 이벤트
  $(document).on('click',"#btnList", function() {
	location.href ="${path}/board/getBoardList"
   })
   //수정버튼 클릭 이벤트
  $(document).on('click',"#btnUpdate", function () {
	  
	
	 var url ="${path}/board/editForm";
	 url = url + "?bid="+ ${boardContent.bid};
	 url =url + "&mode=edit";
	 //신규 입력과 수정 간의 차이를 구분하기 위해 사용할 인자값.
	 location.href =url;
		  
	 
  })
   //삭제버튼 클릭 이벤트
   $(document).on('click','#btnDelete', function () {
	   var confirmdel = confirm("삭제하시겠습니까?")
		  if(confirmdel){
	 var url="${path}/board/deleteBoard";
	 url= url +"?bid=" +${boardContent.bid};
	 location.href=url;
			  
		  }else{
			  location.href="${path}/board/getBoardList";
		  }
	   
	   
    });
  
  $(document).ready(function() {
		 showReplyList();
	    });
  
  
 
  function showReplyList() {
	 var url ="${path}/restBoard/getReplyList";
	 var paramData ={"bid" : "${boardContent.bid}"};
	 $.ajax({
		 url:url,
		
		 data : paramData,
		 dataType : 'json',
		 success : function (result) {
			var htmls ="";
			if(result.length < 1){
				
			 htmls += "댓글이 등록되지않았습니다.";
			 $("#replyList").html(htmls);
			}else{
				
				$(result).each(function () {
				
					
					htmls += '<div class="media text-muted pt-3" id="rid ' + this.rid +'" >'; //각 댓글의 고유 id생성부분
					htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';
					htmls += '<title>PlaceHolder</title>';
					htmls += '<rect width="100%" height="100%" fill="#007bff"></rect>';
					htmls += '<text x ="50%" fill="#007bff" dy=".3em">32x32</text>';
					htmls += '</svg>';
					
					htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom holder-gray">';
					htmls += '<span class="d-block">';
					htmls += '<strong class="text-gray-dark">' +this.reg_id+'</strong>';
					
					htmls += '<a href="javascript:void(0)"  onclick="fn_editReply(' + this.rid + ', \'' + this.reg_id + '\', \'' + this.content + '\')" style="padding-right:5px">수정</a>';
                    htmls += '<a href="javascript:void(0)" onclick="fn_deleteReply(' + this.rid + ')" >삭제</a>';
                    htmls += '</span>';
                    htmls += '</span>';
                    htmls += this.content;
                    htmls += '</p>';
                    htmls += '</div>';

				});
				
				$("#replyList").html(htmls);			
						
				
			}
		 } //success 끝
		 
		 
		 
	 })//Ajax 끝
}
  
  //댓글 수정폼 이벤트 추가

	 function fn_editReply(rid, reg_id, content) {
console.log("들어옴?");
			var htmls = "";

			htmls += '<div class="media text-muted pt-3" id="rid' + rid + '">';

			htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';

			htmls += '<title>Placeholder</title>';

			htmls += '<rect width="100%" height="100%" fill="#007bff"></rect>';

			htmls += '<text x="50%" fill="#007bff" dy=".3em">32x32</text>';

			htmls += '</svg>';

			htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';

			htmls += '<span class="d-block">';

			htmls += '<strong class="text-gray-dark">' + reg_id + '</strong>';

			htmls += '<span style="padding-left: 7px; font-size: 9pt">';

			htmls += '<a href="javascript:void(0)" onclick="fn_updateReply(' + rid + ', \'' + reg_id + '\')" style="padding-right:5px">저장</a>';

			htmls += '<a href="javascript:void(0)" onClick="showReplyList()">취소<a>';

			htmls += '</span>';

			htmls += '</span>';		
			console.log("텍스트시작?");
			htmls += '<textarea name="editContent" id="editContent" class="form-control" rows="3">';

			htmls += content;

			htmls += '</textarea>';

			console.log("텍스트마지막?");

			htmls += '</p>';

			htmls += '</div>';

			

			$("#rid" + rid).replaceWith(htmls);

			$("#rid" + rid + "#editContent").focus();
            $('#replyList').html(htmls);
		
			console.log("마지막?");

};
  //댓글 저장 버튼 클릭이벤트
  $(document).on('click','#btnReplySave', function () {
	
	  var replyContent =$('#content').val();
	  var replyReg_id =$('#reg_id').val();
	  
	  var paramData = JSON.stringify({"content": replyContent,"reg_id" : replyReg_id, "bid": '${boardContent.bid}' });
	  
	  var headers ={"Content-Type": "application/json", "X-HTTP-Method-Override":"POST"};
	  
	  $.ajax({
		  
		  url:"${path}/restBoard/saveReply",
		  headers : headers,
		  data: paramData,
		  type: "post",
		  dataType : "json",
		  success: function (result) {
			showReplyList();
			$('#content').val('');
			$('#reg_id').val('');
			  
		},
		error: function (error) {
			console.log("에러 :" + error);
		}
		  
		  
		  
	  })
})
// 댓글 수정 내용 저장하기
function fn_updateReply(rid,reg_id) {
	var replyEditContent =$('#editContent').val();
	
	var paramData =JSON.stringify({"content":replyEditContent, "rid": rid});
	
	var headers ={"Content-Type": "application/json" ,"X-HTTP-Method-Override": "post"};
	
	$.ajax({
		url: "${path}/restBoard/updateReply",
		data: paramData,
		type: "post",
		dataType: "text",
		headers : headers,
		success: function (result) {
			console.log(result);
			showReplyList();
		},
		error : function (error) {
			console.log("에러:" +error)
		}
		
		
	})
}
//댓글 삭제하는 버튼이벤트
function fn_deleteReply(rid) {
	
	var paramData ={"rid":rid};
	$.ajax({
		url: "${path}/restBoard/deleteReply",
		data: paramData,
		type: 'get',
		dataType: 'text',
		success: function (result) {
			showReplyList();
		},
		error: function (error) {
			console.log("에러 : " + error);
		}
	})
	
}
</script>
<title>Insert title here</title>
</head>
<body>

	<article>
	     <div class="container" role="main">
	        <h2>board Content</h2>
	         <div class="bg-white rounded shadow-sm">
	             <div class="board_title"><c:out value="${boardContent.title }"/> </div>
	             <div class="board_info_box">
	                 <span class="board_author"><c:out value="${boardContent.reg_id }"/></span>
	                 <span class="board_date"><c:out value="${boardContent.reg_dt }"/> </span>
	                
	             </div>
	             <div class="baord_content"><c:out value="${boardContent.content }"/></div>
	             <div class="board_tag">TAG :<c:out value="${boardContent.tag }"/></div>
	         
	         
	         </div>
	         <div style="margin-top: 20px">
	         <button type="button" class="btn btn-sm btn-primary" id="btnUpdate">수정</button>
	         <button type="button" class="btn btn-sm btn-primary" id="btnDelete">삭제</button>
	         <button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
	           
	         </div>
	     
	      <!--댓글 폼  -->
	     <div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
	     <form:form name="form" id="form" role="form" modelAttribute="replyVO" method="post">
	          <form:hidden path="bid" id="bid"/>
	          <div class="row">
	             <div class="col-sm-10">
	                 <form:textarea path="content" id="content" class="form-control" rows="3" placeholder="댓글을 입력해주세요"/>	             
	             </div>
	             <div class="col-sm-2">
	                <form:input path="reg_id" id="reg_id" class="form-control" placeholder="댓글작성자"/>
	                <button type="button" class="btn btn-sm btn-primary" id="btnReplySave" style="width:100%; margin-top: 10px">저장</button>
	             </div>
	                
	          </div>
	     </form:form>
	     </div>
	     <!--댓글폼(end)  -->
	     
	     <!-- 댓글 리스트  -->
	     <div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
	         <h6 class="border-bootm pb-2 mb-0">ReplyList</h6>
	         <div id="replyList">
	        
	         </div>
	         
	       
	     </div>
	     <!--댓글 리스트(end)  -->
	     
	     
	     
	     
	     
	     
	     </div>
	
	</article>
</body>
</html>
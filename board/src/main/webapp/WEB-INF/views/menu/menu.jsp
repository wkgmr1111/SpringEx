<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:url var="saveURL" value="/restMenu/saveMenu"></c:url> 
<c:url var="deleteURL" value="/restMenu/deleteMenu"></c:url> 
<c:url var="updateURL" value="/restMenu/updateMenu"></c:url> 
<c:url var="getMenuListURL" value="/restMenu/getMenuList"></c:url>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#paginationBox{
  padding:10px 0px;
}

</style>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.0/jquery.js"></script>
<script>
 $(function () {
	fn_showList();
})

function fn_showList() {
	 var paramData={};
	 
	 $.ajax({
		 url: "${path}/restMenu/getMenuList",
		 dataType: "json",
		 data: paramData,
		 type: "post",
		 success: function (result) {
			console.log(result);
			
			if(result.status == "ok"){
				  if(result.menuList.length > 0){
					  var list = result.menuList;
					  var htmls ="";
					  
					  result.menuList.forEach(function (e) {
					      htmls += '<tr>';
					      htmls += '<td>' +e.mid +'</td>';
					   //   htmls += '<td>' +e.code +'</td>';
					      htmls += '<td>';
					      htmls += '<a href="#" onclick="fn_menuInfo('+e.mid +',\''+ e.code +'\',\'' + e.codename +'\','+ e.sort_num +',\''+e.comment+'\')">';                          
					      htmls += e.code;
					      htmls += '</a>';
					      htmls += '</td>';
					      htmls += '<td>' +e.codename +'</td>';
					      htmls += '<td>' +e.sort_num +'</td>';
					      htmls += '<td>' +e.comment +'</td>';
					      htmls += '</tr>';
					});
				  }
				  
			}else{
				console.log("조회실패");
			}
			$("#menuList").html(htmls);
		}
	 });
}
//메뉴 정보 수정
function fn_menuInfo(mid,code,codename,sort_num,comment) {
	$("#mid").val(mid);
	$('#code').val(code);
	$('#codename').val(codename);
	$('#sort_num').val(sort_num);
	$('#comment').val(comment);
	
	//코드 부분 읽기모드로 전환
	$('#code').attr("readonly",true);
}
</script>
</head>
<body>

<article>
  <div class="container">
        
        
        
        <!--메뉴폼  -->
        
        <h4 class="mb-3">Menu Info</h4>
        <div>
            <form:form name="form" id="form" role="form" modelAttribute="menuVO" method="post" action="${path}/restMenu/saveMenu">
                       <form:hidden path="mid" id="mid"/>
               <div class="row">
                  <div class="col-md-4 mb-3">
                  
                     <label for="code">Code</label>
                       <form:input path="code" id="code" class="form-control" placeholder="" value="" required=""/>
                      <div class="invaild-feedback">Vaild code is required</div>
                       
                 
                  </div>
                 
                 <div class="col-md-5 mb-3">
                     <label for="codename">Code name</label>
                    <form:input path="codename" class="form-control" id="codename" value="" placeholder="" required=""/>
                     <div class="invaild-feedback">Vaild codename is required</div>
                     
                 </div>
                 
                 
                 <div class="col-md-4 mb-3">
                  
                     <label for="sort_num">sort_num</label>
                       <form:input path="sort_num" id="sort_num" class="form-control" placeholder="" required=""/>
                      <div class="invaild-feedback">Vaild sort_num is required</div>
                       
                 
                  </div>
                  
                  <div class="col-md-4 mb-3">
                  
                     <label for="comment">comment</label>
                       <form:input path="comment" id="comment" class="form-control" placeholder="" value="" required=""/>
                      <div class="invaild-feedback">Vaild comment is required</div>
                       
                 
                  </div>
                 
                 
               </div>
             </form:form>
        
        </div>
        <!--메뉴폼 끝  -->
        <div>
           <button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
           <script>
           //리스트를 클릭하게되면 리스트의 정보가 셋팅되므로 mid의 값을 확인 가능
           //값이있다는것은 데이터를 수정할수있다는 의미로 입력 url을 수정으로 변경
              $(document).on('click','#btnSave', function (e) {
				e.preventDefault();
				
				var url ="${path}/restMenu/saveMenu";
				
				if($('#mid').val() != 0){
					var url ="${path}/restMenu/updateMenu";
				}
				var paramData={
						 "code" : $("#code").val(),
						 "codename" : $("#codename").val(),
						 "sort_num": $("#sort_num").val(),
						 "comment" : $("#comment").val() };
				
				$.ajax({
				
					url: url,
					dataType: "json",
					data : paramData,
					type:"post",
					success: function (result) {
						fn_showList();
						$("#btnInit").trigger("click");
					}
					
				})

			})
			$(document).on('click','#btnInit', function (e) {
				
				$('#mid').val('');
				$('#code').val('');
				$('#codename').val('');
				$('#sort_num').val('');
				$('#comment').val('');
		
			})
	
			$(document).on('click','#btnDelete', function (e) {
				e.preventDefault();
				
				if($('#code').val() == ""){
					alert("삭제할 코드를 선택해주세요");
					return;
				}
				
				var url ="${path}/restMenu/deleteMenu";
				
				var paramData={
						"code" : $("#code").val()
				}
				
				$.ajax({
					url: url,
					data: paramData,
					type: 'post',
					dataType: 'json',
					success: function (result) {
						
						fn_showList();
						//삭제후 셋팅값 초기화
						$("#btnInit").trigger('click');
					}
					
				})
			})
			
           </script>
           <button type="button" class="btn btn-sm btn-primary" id="btnDelete">삭제</button>
           <button type="button" id="btnInit" class="btn btn-sm btn-primary">초기화</button>
           
        </div>
        
        <h4 class="mb-3" style="padding-top: 15px">Menu List</h4>
        
        <!--리스트 시작  -->
          <div class="table-responsive">
             <table class="table table-striped table-sm">
                  <colgroup>
                         <col style="width:10%;"/>
                         <col style="width:15%;"/>
                         <col style="width:15%;"/>
                         <col style="width:10%;"/>
                         <col style="width:auto%;"/>
                  
                  
                  </colgroup>
                  <thead>
                      <tr>
                         <th>menu id</th>
                         <th>code</th>
                         <th>codename</th>
                         <th>sort</th>
                         <th>comment</th>
                      
                      
                      </tr>
                  
                  </thead>
                  <tbody id="menuList">
                  </tbody>
             </table>

          </div>
          <!--리스트 끝  -->
  </div>


</article>
</body>
</html>
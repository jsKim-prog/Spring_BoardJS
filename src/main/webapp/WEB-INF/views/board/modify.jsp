<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<%@ include file="../includes/header.jsp"%>
<!--list.jsp에서 /board/register 경로 호출하면 폼 박스가 나옴  -->
<!-- 입력완료를 누르면 vo 객체가 만들어져서  /board/register 에 post 메서드가 실행-->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시글 수정</h1>
	</div> <!-- .col-lg-12 end  -->
</div> <!--.row end  -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Modify Page</div> <!--.panel-heading end  -->
				
			<div class="panel-body"> 
				<form role="form" action="/board/modify" method="post">
					<!-- 페이징처리를 위한 추가 -->
					<input type="hidden" name="pageNum" value="${cri.pageNum}"/>
					<input type="hidden" name="amount" value="${cri.amount}"/>
					<input type="hidden" name="keyword" value="${cri.keyword}"/>
					<input type="hidden" name="type" value="${cri.type}"/>
					<div class="form-group">
					<label>번호</label><input class="form-control" name="bno" value="${findboard.bno}" readonly="readonly">
					</div>
					<div class="form-group">
					<label>제목</label><input class="form-control" name="title" value="${findboard.title}">
					</div>
					<div class="form-group">
					<label>내용</label><textarea class="form-control" rows="3" name="content">${findboard.content}</textarea>
					</div>
					<div class="form-group">
					<label>작성자</label><input class="form-control" name="writer" value="${findboard.writer}" readonly="readonly">
					</div>
					<div class="form-group">
					<label>작성일</label><input class="form-control" name="regdate" value='<fmt:formatDate pattern="yyyy/MM/dd" value="${findboard.regdate}"/>' readonly="readonly">
					</div>
					<div class="form-group">
					<label>수정일</label><input class="form-control" name="updateDate" value='<fmt:formatDate pattern="yyyy/MM/dd" value="${findboard.updateDate}"/>' readonly="readonly">
					</div>
					<button type="submit" data-oper='modify' class="btn btn-success" >저장하기</button>
					<button type="submit" data-oper='remove' class="btn btn-default">삭제하기</button>
					<button type="submit" data-oper='list' class="btn btn-primary" >목록보기</button>
				</form>
			</div> <!-- .panel-body end  -->
		</div> <!-- .panel panel-default end -->		
	</div> <!-- .col-lg-12 end  -->
</div> <!--.row end  -->

<script type="text/javascript">
$(document).ready(function() {
	var formObj = $("form");
	$('button').on("click", function(e) {
		e.preventDefault();
		var operation = $(this).data("oper");
		console.log(operation);
		
		if(operation === 'remove'){
			formObj.attr("action", "/board/remove");
		}else if(operation === 'list'){
			formObj.attr("action", "/board/list").attr("method", "get");
			var pageNumTag = $("input[name='pageNum']").clone();
			var amountTag = $("input[name='amount']").clone();
			var keywordTag = $("input[name='keyword']").clone();
			var typeTag = $("inpu[name='type']").clone();
			
			formObj.empty(); /* list로 보낼 땐 비우고 보낸다. */
			/* 검색조건, 페이징 정보 유지하여 리스트페이지까지 보낸다.(get) */
			formObj.append(pageNumTag);
			formObj.append(amountTag);
			formObj.append(keywordTag);
			formObj.append(typeTag);
		}
		formObj.submit(); /* operation= modify 라면 19행 실행  */
	});
});
</script>
<%@ include file="../includes/footer.jsp"%>
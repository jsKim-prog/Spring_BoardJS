<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<%@ include file="../includes/header.jsp"%>
<!--list.jsp에서 /board/register 경로 호출하면 폼 박스가 나옴  -->
<!-- 입력완료를 누르면 vo 객체가 만들어져서  /board/register 에 post 메서드가 실행-->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시글 상세보기</h1>
	</div> <!-- .col-lg-12 end  -->
</div> <!--.row end  -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Read Page</div> <!--.panel-heading end  -->
				
			<div class="panel-body"> 
					<div class="form-group">
					<label>번호</label><input class="form-control" name="bno" value="${findboard.bno}" readonly="readonly">
					</div>
					<div class="form-group">
					<label>제목</label><input class="form-control" name="title" value="${findboard.title}" readonly="readonly">
					</div>
					<div class="form-group">
					<label>내용</label><textarea class="form-control" rows="3" name="content" readonly="readonly">${findboard.content}</textarea>
					</div>
					<div class="form-group">
					<label>작성자</label><input class="form-control" name="writer" value="${findboard.writer}" readonly="readonly">
					</div>
					<div class="form-group">
					<label>작성일</label><input class="form-control" name="regdate" value='<fmt:formatDate pattern="yyyy/MM/dd" value="${findboard.regdate}"/>' readonly="readonly">
					</div>
					<button data-oper='modify'  class="btn btn-success">수정하기</button>
					<button data-oper='list' class="btn btn-primary">목록보기</button>
					<form id="operForm" action="/board/modify" method="get">
					<input type="hidden" id="bno" name="bno" value="${findboard.bno}"/>
					<input type="hidden" name="pageNum" value="${cri.pageNum}"/>
					<input type="hidden" name="amount" value="${cri.amount}"/>
					</form>
			</div> <!-- .panel-body end  -->
		</div> <!-- .panel panel-default end -->		
	</div> <!-- .col-lg-12 end  -->
</div> <!--.row end  -->
<script type="text/javascript">
$(document).ready(function() {
	var operForm = $("#operForm");
	$("button[data-oper='modify']").on("click", function(e) {
		operForm.attr("action", "/board/modify").submit();
	});
	$("button[data-oper='list']").on("click", function(e) {
		operForm.find("#bno").remove(); 
		operForm.attr("action", "/board/list");
		operForm.submit();
	});	
});
</script>

<%@ include file="../includes/footer.jsp"%>
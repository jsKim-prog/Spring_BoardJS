<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<%@ include file="../includes/header.jsp"%>
<!--list.jsp에서 /board/register 경로 호출하면 폼 박스가 나옴  -->
<!-- 입력완료를 누르면 vo 객체가 만들어져서  /board/register 에 post 메서드가 실행-->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시글 작성</h1>
	</div> <!-- .col-lg-12 end  -->
</div> <!--.row end  -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Register</div> <!--.panel-heading end  -->
				
			<div class="panel-body"> <!-- form시작 -->
				<form role="form" action="/board/register" method="post">
					<div class="form-group">
					<label>제목</label><input class="form-control" name="title">
					</div>
					<div class="form-group">
					<label>내용</label><textarea class="form-control" rows="3" name="content"></textarea>
					</div>
					<div class="form-group">
					<label>작성자</label><input class="form-control" name="writer">
					</div>
					<button type="submit" class="btn btn-success">저장하기</button>
					<button type="reset" class="btn btn-default">초기화</button>
				</form>			
			</div> <!-- .panel-body end  -->
		</div> <!-- .panel panel-default end -->		
	</div> <!-- .col-lg-12 end  -->
</div> <!--.row end  -->

<%@ include file="../includes/footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../includes/header.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">JSCODE 게시판</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Board List Page
				<button id='regBtn' type="button"
					class="btn btn-primary btn-xs pull-right">새글 작성</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<!-- 검색창 -->
				<div class="row">
					<div class="col-lg-12">
					<div class="col-sm-6"></div>
					<form id="searchForm" action="/board/list" method="get">					
					<div class="col-sm-3">
						<select class="form-control" name = "type">
							<option value="" ${pageMaker.cri.type == null?'selected':'' }>선택없음</option>
							<option value="T" ${pageMaker.cri.type eq 'T' ?'selected':'' }>제목</option>
							<option value="C" ${pageMaker.cri.type eq 'C' ?'selected':'' }>내용</option>
							<option value="W" ${pageMaker.cri.type eq 'W' ?'selected':'' }>작성자</option>
							<option value="TC" ${pageMaker.cri.type eq 'TC' ?'selected':'' }>제목/내용</option>
							<option value="TW" ${pageMaker.cri.type eq 'TW' ?'selected':'' }>제목/작성자</option>
							<option value="TWC" ${pageMaker.cri.type eq 'TWC' ?'selected':'' }>제목/내용/작성자</option>
						</select></div>
						<div class="col-sm-3 form-group input-group">
						<input class="form-control" type="text" name="keyword" value="${pageMaker.cri.keyword}"/>
						<span class="input-group-btn">
							<button class="btn btn-default" type="button">
								<i class="fa fa-search"></i>
                             </button>
                        </span>
						</div>
						<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}"> 
						<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
					</form>
					</div><!-- col-lg-12 -->
				</div> <!--.row end(검색창 끝)  -->
			
				<table width="100%"
					class="table table-striped table-bordered table-hover">
					<!--id="dataTables-example" bootstrap 페이징처리  -->
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="board">
							<tr>
								<td>${board.bno}</td>
								<td><a class="move" href="${board.bno}">
										${board.title}</a></td>
								<td>${board.writer}</td>
								<td><fmt:formatDate pattern="yyyy/MM/dd"
										value="${board.regdate}" /></td>
								<td><fmt:formatDate pattern="yyyy/MM/dd"
										value="${board.updateDate}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 페이징 버튼 -->
				<div class="row">
					<div class="col-lg-12 ">
						<div class="dataTables_paginate paging_simple_numbers ">
							<!--paging  -->
							<ul class="pagination">
								<c:if test="${pageMaker.prev}">
									<li class="paginate_button previous"><a
										href="${pageMaker.startPage -1}">Previous</a></li>
								</c:if>
								<c:forEach var="num" begin="${pageMaker.startPage}"
									end="${pageMaker.endPage}">
									<li
										class='paginate_button  ${pageMaker.cri.pageNum == num ? "active" : "" } '><a
										href="${num}">${num}</a></li>
								</c:forEach>
								<c:if test="${pageMaker.next}">
									<li class='paginate_button next'><a
										href="${pageMaker.endPage+1}">Next</a></li>
								</c:if>
							</ul>
							<!-- pagination end -->
							<form id='actionForm' action="/board/list" method="get">
								<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}"/> 
								<input type="hidden" name="amount" value="${pageMaker.cri.amount}"/>
								<input type="hidden" name="type" value="${pageMaker.cri.type}"/>
								<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}"/>
							</form>
						</div>
						<!--paging  end-->
					</div>
				</div>
				<!-- /.row -->

			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Result</h4>
			</div>
			<div class="modal-body">처리 완료 되었습니다.</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var result = '${result}'; /* ${result} = controller에서 FlashAttribute로 1회성으로 만든 bno */
						/*  console.log(result); 개발자 창 bno 확인 가능 */
						checkModal(result);
						history.replaceState({}, null, null);
						function checkModal(result) {
							if (result === '' || history.state) { /* bno가 비어있다면 또는 history 초기화 상태면*/
								return;
							}
							if (parseInt(result) > 0) {
								$(".modal-body").html(
										parseInt(result) + " 번 게시글이 등록되었습니다.");
							}
							$("#myModal").modal("show");
						}
						/*id='regBtn'*/
						$("#regBtn").on("click", function() {
							self.location = "/board/register";
						});
						/* 페이징 번호 */
						var actionForm = $("#actionForm");
						$(".paginate_button a").on(
								"click",
								function(e) {
									e.preventDefault();
									console.log('click');
									actionForm.find("input[name='pageNum']")
											.val($(this).attr("href"));
									actionForm.submit();
								});
						/* 게시물조회 */
						$(".move")
								.on(
										"click",
										function(e) {
											e.preventDefault();
											actionForm
													.append("<input type='hidden' name='bno' value='"
															+ $(this).attr(
																	"href")
															+ "'>");
											actionForm.attr("action",
													"/board/get");
											actionForm.submit();
										});
						/* 검색버튼 */
						var searchForm = $("#searchForm");
						$("#searchForm button").on("click", function(e) {
							if(!searchForm.find("option:selected").val()){
								alert("검색종류를 선택하세요.");
								return false;
							}
							if(!searchForm.find("input[name='keyword']").val()){
								alert("키워드를 입력하세요.");
								return false;
							}
							searchForm.find("input[name='pageNum']").val("1");
							e.preventDefault();
							searchForm.submit();
						});
					});
</script>

<%@include file="../includes/footer.jsp"%>

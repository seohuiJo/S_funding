<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="requestboard.model.vo.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>펀딩 요청 글 내용</title>
<link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js"></script>
<!--폰트 어썸-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
</head>
<body id="page-top">
	<!-- 상단바-->
	<!-- Navigation-->
	<jsp:include page="../../common/header.jsp" />

	<br>
	<br>
	<!-- 메인 콘텐츠-->
	<section class="page-section bg-light" id="portfolio">
		<div class="container">
			<!--메인 콘텐츠 위치-->
			<div class="row">
				<!--아이콘-->
				<div class="col-1 col-sm-1 col-md-1">
					<span class="h2">
						<!--아이콘 코드 넣는곳--> 
						<svg class="bi bi-file-text" width="1.5em" height="1.5em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
							<path fill-rule="evenodd" d="M4 1h8a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2zm0 1a1 1 0 0 0-1 1v10a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V3a1 1 0 0 0-1-1H4z" />
							<path fill-rule="evenodd" d="M4.5 10.5A.5.5 0 0 1 5 10h3a.5.5 0 0 1 0 1H5a.5.5 0 0 1-.5-.5zm0-2A.5.5 0 0 1 5 8h6a.5.5 0 0 1 0 1H5a.5.5 0 0 1-.5-.5zm0-2A.5.5 0 0 1 5 6h6a.5.5 0 0 1 0 1H5a.5.5 0 0 1-.5-.5zm0-2A.5.5 0 0 1 5 4h6a.5.5 0 0 1 0 1H5a.5.5 0 0 1-.5-.5z" />
                        </svg>
					</span>
				</div>
				<!--제목-->
				<div class="col-11 col-sm-11 col-md-5 col-lg-5">
					<span class="h3">펀딩 요청 글 보기</span>
				</div>
				<!--버튼, 삭제/목록-->
				<div class="col-md-6 col-lg-6 d-flex justify-content-end">
					<!--삭제버튼은 로그인된 유저와 작성자 아이디가 같을때만 보이도록 한다.-->
					<span>
						<c:if test="${(not empty sessionScope.member && sessionScope.member.userId eq requestBoard.userId) || sessionScope.member.userId eq 'admin'}"> 
							<a href="/requestDelete?requestNo=${requestBoard.requestNo }"
								class="btn btn-secondary" onclick="return question_request();">삭제하기</a>
							&nbsp;&nbsp;
						</c:if>
					<a href="/RequestServlet" class="btn btn-primary">목록으로</a>
					</span>
				</div>
			</div>
			<hr>
			<!--콘텐츠 페이지 내용-->
			<div>
				<!--요청 글 내용-->
				<div>
					<div class="card">
						<div class="card-body">
							<!-- 요청 글 제목 -->
							<h2 class="card-title" id="requestTitle">${requestBoard.requestTitle }</h2>
							<br>
							<!-- 요청 글 내용 -->
							<pre class="card-text" style="white-space:pre-wrap;">${requestBoard.requestContent }</pre>
							<br>
							<hr>
							<!-- 요청 글 진행여부 -->
							<p><span class="h6">진행여부 : <span id="projectList">${requestBoard.projectList }</span></span></p>
							<!-- 요청 글 작성자 -->
							<span>작성자 : <span id="userId">${requestBoard.userId }</span></span>
							<span class="float-right">작성일 : <span id="rRegDate">${requestBoard.rRegdate }</span></span>
						</div>
					</div>
				</div>
				<hr>

				<!--공감/비공감 표시-->
				<div>
					<div class="card bg-light mb-3">
						<div class="card-header">
							<div class="row">
								<!-- 공감 -->
								<div class="col-lg-6">
									<!--if문처리는 아이콘 코드를 작성하는 방법의 가지를 생각해보자-->
									<!--scipt로 하여 제어하는방법 (1), style display로 하는방법 (2)-->
									<!--공감을 안눌렀으면 비워진하트-->
									<button id="goodNo" class="btn">
										<span>
											<svg class="bi bi-heart" width="3em" height="3em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
												<path fill-rule="evenodd" d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z" />
                                            </svg>
										</span>
									</button>
									<!--공감을 눌렀으면 채워진하트-->
									<button id="goodYes" class="btn" style="display: none;">
										<span>
											<svg class="bi bi-heart-fill" width="3em" height="3em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
												<path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z" />
											</svg>
										</span>
									</button>
									<!-- 공감 인원 -->
									<b class="h4"><span id="good">${requestBoard.good }</span></b>&nbsp;<span>명이
										함께 공감중 !</span>
								</div>
								<!-- 비공감 -->
								<div class="col-lg-6">
									<!--비공감을 안눌렀으면 비워진 손-->
									<button id="badNo" class="btn">
										<span><i class="far fa-thumbs-down fa-3x"></i></span>
									</button>

									<!--비공감을 눌렀으면 채워진 손-->
									<button id="badYes" class="btn" style="display: none;">
										<span><i class="fas fa-thumbs-down fa-3x"></i></span>
									</button>
									<!-- 비공감 인원 -->
									<b class="h4"><span id="bad">${requestBoard.bad }</span></b>&nbsp;<span>명이
										함께 비공감중 !</span>
								</div>
							</div>

						</div>
					</div>
				</div>
				<!-- 공감 비공감 끝 -->
				<hr>
				<!-- 댓글 -->
				<div>
					<!--댓글 내용 리스트-->
					<table class="table">
						<thead>
							<tr>
								<!-- 상단 위치 안내 행 -->
								<th scope="col" style="width: 10rem;">닉네임</th>
								<th scope="col" colspan="2">내용</th>
								<th scope="col" class="text-center" style="width: 10rem;">작성일</th>
							</tr>
						</thead>
						<tbody>
							<!-- JSTL 댓글 출력-->
							<c:forEach items="${requestBoard.comments }" var="comment">
								<tr>
									<!-- 실제 내용 행 -->
									<td scope="row">${comment.userId }</td>
									<td>${comment.content }</td>
									<!--if 작성자이면 수정, 삭제 보임, else 작성자 아니면 수정, 삭제 안보임-->
									<td class="text-center" style="width: 12rem;">
										<c:if test="${(not empty sessionScope.member && sessionScope.member.userId eq comment.userId) || sessionScope.member.userId eq 'admin'}">
											<!-- 댓글 수정 버튼 -->
											<a href="#" class="btn btn-primary"
												onclick="showModifyComment(this,'${comment.content }','${comment.userId }','${comment.cRegdate }');">수정</a>
											<!-- 댓글 삭제 버튼 -->
											<a
												href="/deleteRequestComment?commentNo=${comment.commentNo }&requestNo=${comment.requestNo}"
												class="btn btn-secondary" onclick="return question_cmt();">삭제</a>
										</c:if>
									</td>
									<td class="text-center">${comment.cRegdate }</td>
								</tr>
								<!-- 댓글 수정하기 위한 행 input (초기 hidden) -->
								<tr style="display: none;">
									<td scope="row">${comment.userId }</td>
									<td><input type="text" class="form-control"
										id=modifyComment value="${comment.content }">
									</td>
									<td class="float-right">
										<a class="btn btn-primary" href="javascript:void(0)"
										onclick="modifyComment(this,'${comment.commentNo}');">수정완료</a>
										<a class="btn btn-secondary" href="javascript:void(0)"
										onclick="modifyCancel(this);">취소</a></td>
									<td class="text-center">${comment.cRegdate }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- 댓글 수정 정보를 담는 폼 -->
					<form action="/modifyRequestComment" id="modifyForm" method="post">
						<input type="hidden" id="modComment" name="modComment">
						 <input type="hidden" id="modRequestNo" value="${requestBoard.requestNo}" name="modRequestNo">
						 <input type="hidden" id="modCommentNo" name="modCommentNo">
					</form>
					<hr>
					<!-- 댓글 쓰는 폼(댓글 새로 작성)-->
					<!-- 로그인 안하면 댓글 쓰는칸 안보임 -->
					<c:if test="${not empty sessionScope.member }">
					<form action="/requestCommentWrite" method="post" id="requestCmtWrite">
						<div class="form-row">
							<div class="col-8 col-sm-9 col-md-9 col-lg-10">
								<input type="text" id="inputCmt" class="form-control"
									placeholder="댓글 내용을 입력해 주세요" name="comment"> 
								<input type="hidden" name="requestNo" value="${requestBoard.requestNo }">
							</div>
							<div class="col text-center">
								<a href="javascript:void(0)" class="btn btn-primary" onclick="check_cmt_input();">댓글남기기</a>
							</div>
						</div>
					</form>
					</c:if>
				</div>
				<!-- 댓글 컨텐츠 끝 -->
			</div><!-- 요청글내용~댓글 끝 -->
		</div>
	</section>
	<!-- 댓글 수정/삭제 script -->
	<script>
			// 문서 로드, 공감 비공감 데이터 불러오기
			$(document).ready(function(){
				// alert("문서 로드 테스트");
				//console.log("goodYN : "+${goodYN});
				//console.log("badYN : "+${badYN});
				var goodCheck = ${goodYN};
				var badCheck = ${badYN};
				if(goodCheck ==1){
					// 과거에 공감 기록 O, (색칠 O)
					$("#goodNo").css('display','none');
					$("#goodYes").css('display','inline');
				} 
				if(badCheck==1){
					// 과거에 비공감 기록 O, (색칠 O)
					$("#badNo").css('display','none');
					$("#badYes").css('display','inline');
				}
			});
		
			// 공감하기 
			$("#goodNo").click(function(){
				// JSTL 변수 가져오기
				var requestNo = '${requestBoard.requestNo }';	// 공감 누를 글번호 가져오기
				var goodOld = '${requestBoard.good }';	// 기존 공감 total 수
				$.ajax({
					url : "/likeServlet",
					data : {
						requestNo : requestNo,
						goodOld : goodOld
					},
					type : "get",
					success : function(data){
						var result = data.result;
						var good = data.good;
						console.log("jsp/요청 결과 : "+result);
						console.log("jsp/공감추가후/전체인원 : " + good);
						if(result==1){
							// 하트 색칠
							$("#goodNo").css('display','none');
							$("#goodYes").css('display','inline');
							// 공감 인원 수 변경
							$("#good").html(good);
						} else if(result==0){
							alert("공감추가/서비스,DB 요청 실패");
						} else if(result==2){
							alert("공감추가 실패/로그인후에 이용하여 주세요.");
						} else{
							alert("공감추가/서블릿에서 아무일도 일어나지않음")
						}
					},
					error : function(data){
						console.log("jsp/공감추가에러/error result :"+data.result);
						alert("잠시후에 다시 시도하여 주세요.");
					}
				});
			});
			// 공감하기 취소
			$("#goodYes").click(function(){
				var requestNo = '${requestBoard.requestNo }';	// 공감 지울 글번호 가져오기
				var goodOld = '${requestBoard.good }';	// 기존 공감 total 수
				$.ajax({
					url : "/likeCancelServlet",
					data : {
						requestNo : requestNo,
						goodOld : goodOld
					},
					type : "get",
					success : function(data){
						var result = data.result;
						var good = data.good;
						console.log("jsp/요청 결과 : "+result);
						console.log("jsp/공감취소후/전체인원 : " + good);
						if(result==1){
							// 하트 색칠 지우기
							$("#goodNo").css('display','inline');
							$("#goodYes").css('display','none');
							// 공감 인원 수 변경
							$("#good").html(good);
						} else if(result==0){
							alert("공감취소/서비스,DB 요청 실패");
						} else if(result==2){
							alert("공감취소 실패/로그인후에 이용하여 주세요.");
						} else{
							alert("공감취소/서블릿에서 아무일도 일어나지않음")
						}
					},
					error : function(){
						console.log("jsp/공감취소에러/error result :"+data.result);
						alert("잠시후에 다시 시도하여 주세요.");
					}
				});
			});

			// 비공감 하기
			$("#badNo").click(function(){
				var requestNo = '${requestBoard.requestNo }';	// 비공감 누를 글번호 가져오기
				var badOld = '${requestBoard.bad }';	// 기존 비공감 total 수
				$.ajax({
					url : "/dislikeServlet",
					data : {
						requestNo : requestNo,
						badOld : badOld
					},
					type : "get",
					success : function(data){
						var result = data.result;
						var bad = data.bad;
						console.log("jsp/요청 결과 : "+result);
						console.log("jsp/비공감추가후/전체인원 : " + bad);
						if(result==1){
							// 비공감 색칠
							$("#badNo").css('display','none');
							$("#badYes").css('display','inline');
							// 비공감 인원 수 변경
							$("#bad").html(bad);
						} else if(result==0){
							alert("비공감추가/서비스,DB 요청 실패");
						} else if(result==2){
							alert("비공감추가 실패/로그인후에 이용하여 주세요.");
						} else{
							alert("비공감추가/서블릿에서 아무일도 일어나지않음")
						}
					},
					error : function(){
						console.log("jsp/비공감추가에러/error result :"+data.result);
						alert("잠시후에 다시 시도하여 주세요.");
					}
				});
			});
			// 비공감 취소
			$("#badYes").click(function(){
				var requestNo = '${requestBoard.requestNo }';	// 비공감 지울 글번호 가져오기
				var badOld = '${requestBoard.bad }';	// 기존 비공감 total 수
				$.ajax({
					url : "/dislikeCancelServlet",
					data : {
						requestNo : requestNo,
						badOld : badOld
					},
					type : "get",
					success : function(data){
						var result = data.result;
						var bad = data.bad;
						console.log("jsp/요청 결과 : "+result);
						console.log("jsp/비공감취소후/전체인원 : " + bad);
						if(result==1){
							// 비공감 색칠 없애기
							$("#badNo").css('display','inline');
							$("#badYes").css('display','none');
							// 비공감 인원 수 변경
							$("#bad").html(bad);
						} else if(result==0){
							alert("비공감취소/서비스,DB 요청 실패");
						} else if(result==2){
							alert("비공감취소 실패/로그인후에 이용하여 주세요.");
						} else{
							alert("비공감취소/서블릿에서 아무일도 일어나지않음")
						}
					},
					error : function(){
						console.log("jsp/비공감취소에러/error result :"+data.result);
						alert("잠시후에 다시 시도하여 주세요.");
					}
				});
			});

			// 게시글을 삭제할 것인지 한번 확인 과정을 가지는 스크립트
			function question_request() {
				// JSTL 변수 가져오는법
				var requestNo = '${requestBoard.requestNo}';
				// confirm은 확인 창
				var check = window.confirm("해당 글을 삭제하시겠습니까?");
				if (check) {
					return true;
				} else {
					return false;
				}
			}
			// 댓글 입력 칸 비었는지 확인
			function check_cmt_input(){
				var checkCmt = $("#inputCmt").val();
				console.log("checkCmt"+checkCmt);
				if(checkCmt != ""){
					$("#requestCmtWrite").submit();
				} else{
					alert("댓글 내용을 입력해주세요.");
				}
			}
			// 댓글 수정 텍스트 박스를 나타나게 하는 함수
			function showModifyComment(obj, comment, userId, regDate) {
				// 우리는 obj만 쓸것 (나머지 인자값은 이렇게쓸수잇다는 예시)
				console.log(obj);
				$(obj).parents("tr").next().show(); // display:none으로 숨겨둔아이
				$(obj).parents("tr").hide();
			}
			// 댓글 수정 텍스트 박스를 사라지게 하는 함수
			function modifyCancel(obj) {
				console.log(obj);
				$(obj).parents("tr").prev().show();
				$(obj).parents("tr").hide();
			}
			// form 태그에 넘겨줄 값을 기존
			function modifyComment(obj, commentNo) {
				// 수정하는 input태그를 선택해서 해당 input태그의 value를 가져와서 보내준다
				var comment = $(obj).parent().siblings(1).children().val();
				console.log("comment : " + comment);
				$("#modCommentNo").val(commentNo);
				$("#modComment").val(comment);
				$("#modRequestNo").val($("#modRequestNo").val());
				//우리가 지정한 action url로 서비스를 요청
				$("#modifyForm").submit();
			}
			// 댓글 삭제 확인 여부
			function question_cmt() {
				var check = window.confirm("선택하신 댓글을 삭제하시겠습니까?");
				if (check) {
					return true;
				} else {
					return false;
				}
			}
		</script>

	<!-- Footer 푸터, 하단바 -->
	<jsp:include page="../../common/footer.html" />

	<!-- Bootstrap core JS-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
	<!-- Third party plugin JS-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
	<!-- Contact form JS-->
	<script src="assets/mail/jqBootstrapValidation.js"></script>
	<script src="assets/mail/contact_me.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>
</html>
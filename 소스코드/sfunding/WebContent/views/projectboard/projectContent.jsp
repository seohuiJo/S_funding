<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>펀딩 프로젝트 상세 내용</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js"></script>
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
<link href="/views/css/styles.css" rel="stylesheet" />
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
				<div class="col-2 col-sm-2 col-md-2 col-lg-1">
					<span class="h5"> <!--아이콘 코드 넣는곳--> <svg
							class="bi bi-folder-fill" width="1em" height="1em"
							viewBox="0 0 16 16" fill="currentColor"
							xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd"
								d="M9.828 3h3.982a2 2 0 0 1 1.992 2.181l-.637 7A2 2 0 0 1 13.174 14H2.826a2 2 0 0 1-1.991-1.819l-.637-7a1.99 1.99 0 0 1 .342-1.31L.5 3a2 2 0 0 1 2-2h3.672a2 2 0 0 1 1.414.586l.828.828A2 2 0 0 0 9.828 3zm-8.322.12C1.72 3.042 1.95 3 2.19 3h5.396l-.707-.707A1 1 0 0 0 6.172 2H2.5a1 1 0 0 0-1 .981l.006.139z" />
                            </svg>
					</span>
				</div>
				<!--제목-->
				<div class="col-5 col-sm-5 col-md-5 col-lg-6">
					<span class="h5">펀딩 프로젝트 상세 내용</span>
				</div>
				<div class="col-5 col-sm-5 col-md-5 col-lg-5 d-flex justify-content-end">
					<!-- 등록한 아이디와 게시글 작성자가 같을 때만 보임. -->
					<!-- 수정하기, 삭제하기 버튼 -->
					<c:if test="${(sessionScope.member != null && sessionScope.member.userId eq content.userId) || sessionScope.member.userId eq 'admin'}">
						<a href="/projectUpdate?projectNo=${content.projectNo }"
							class="btn btn-primary">수정하기</a>&nbsp;&nbsp;&nbsp;
						<a href="/projectDelete?projectNo=${content.projectNo}&image1=${image1.filePath}&image2=${image2.filePath }&image3=${image3.filePath}"
							class="btn btn-secondary" onclick="return question_request();">삭제하기</a>&nbsp;&nbsp;&nbsp;
							
						<input type="hidden" name="userId" value="${content.userId }">
					</c:if>
					<!-- 목록가기 버튼 -->
					<a href="/project" class="btn btn-primary">목록가기</a>
				</div>
			</div>
			<hr>
			<br>
			<!--콘텐츠 페이지 내용-->
			<!--프로젝트 정보 (위)-->
			<div>
				<p class="text-center">${content.category}</p>
				<div class="d-flex justify-content-center" style="margin-top: 70px;">
					<p class="h1">${content.projectTitle}</p>
				</div>
				<br>
				<p class="text-center h">
					창작자 : <span class="text-muted">${content.userId}</span>
				</p>
			</div>
			<hr>
			<br>
			<!--프로젝트 정보 (중간)-->
			<div class="row">
				<!--프로젝트 등록 이미지 파일 (좌측)-->
				<div class="col-md-6 col-lg-8">
					<div class="text-center">
						<img src="/upload/photo/${image1.filePath}" name="image1"
							class="rounded mx-auto d-block" class="rounded" alt="..." width="90%">
					</div>
					<br>
				</div>
				<br>
				<!--프로젝트 내용 (우측)-->
				<!--현재모인금액, 현재 후원자 수, 시작일, 마감일-->
				<div class="col-md-6 col-lg-4 d-flex justify-content-center">
					<div class="card" style="width: 20rem;">
						<ul class="list-group list-group-flush">
							<li class="list-group-item">현재 모인 금액
								<p class="h3 text-right" style="margin-top: 15px;">
									<span value="{$content.sumMoney}">${content.sumMoney }</span>&nbsp;원
								</p>
							</li>
							<li class="list-group-item">목표 금액
								<p class="h3 text-right" style="margin-top: 15px;">
									<span>${content.targetMoney}</span>&nbsp;원
								</p>
							</li>
							<li class="list-group-item">현재 후원자 수
								<p class="h3 text-right" style="margin-top: 15px;">
									<span>${content.sponsorCount}</span>&nbsp;명
								</p>
							</li>
							<li class="list-group-item">시작일
								<p class="h3 text-right" style="margin-top: 15px;">
									<span>${content.startDate}</span>
								</p>
							</li>
							<li class="list-group-item">마감일
								<p class="h3 text-right" style="margin-top: 15px;">
									<span>${content.endDate}</span>
								</p>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<br>
			<hr>
			<br>
			<!--프로젝트 정보 (아래)-->
			<div class="row">
				<!--펀딩 안내 글 (좌측)-->
				<div class="col-md-6 col-lg-8">
					<div class="card">
						<div class="card-header">프로젝트 세부내용</div>
						<div class="card-body">
							<div class="card-text">
								<img src="/upload/photo/${image2.filePath}" name="image2" 
									class="rounded mx-auto d-block" class="rounded" alt="..."
									width="90%">
								<br> 
								<img src="/upload/photo/${image3.filePath}" name="image3"
									class="rounded mx-auto d-block" class="rounded" alt="..."
									width="90%">
								<br>
							<pre style="white-space:pre-wrap;">&nbsp;${content.projectContent }</pre>
							</div>
						</div>
					</div>
					<br>
				</div>
				<br>

				<!-- option 1 -->
				<div class="col-md-6 col-lg-4 d-flex justify-content-center">
					<form action="/projectSupport?projectNo=${content.projectNo}" method="post" id="funding">
						<!--상품 펀딩하기 버튼-->
						<a class="btn btn-primary btn-lg btn-block" onclick="return check_login();"
						style="width: 20rem;">프로젝트 펀딩하기 !</a>
						<br>
						<!--상품 옵션 페이지-->
						<!--제품 옵션과 설명 부분, 옵션이 많으면 반복됨-->
						<div>
							<!--제품명 선택, 라디오 버튼-->
							<div class="form-check">
								<input class="form-check-input" type="radio"
									name="exampleRadios" id="option1" value="1">
									<label class="form-check-label" for="option1">${option1.productName }</label>
							</div>
							<br>
							<div class="card" style="width: 20rem;">
								<div class="card-body">
									<p class="card-text">${option1.option }</p>
									<p class="card-text">
										<small class="text-muted"> 제품가격 : <span id="price">${option1.price }</span>원
										</small>
									</p>
								</div>
							</div>
							<br>

							<!-- option2  -->
							<div class="form-check">
								<input class="form-check-input" type="radio"
									name="exampleRadios" id="option2" value="2">
								<label class="form-check-label" for="option2">${option2.productName }</label>
							</div>
							<br>
							<div class="card" style="width: 20rem;">
								<div class="card-body">
									<p class="card-text">${option2.option }</p>
									<p class="card-text">
										<small class="text-muted">제품가격 : <span id="price">${option2.price }</span>원
										</small>
									</p>
								</div>
							</div>
							<br>

							<!-- option3 -->
							<div class="form-check">
								<input class="form-check-input" type="radio" name="exampleRadios" id="option3" value="3">
								<label class="form-check-label" for="option3">${option3.productName }</label>
							</div>
							<br>
							<div class="card" style="width: 20rem;">
								<div class="card-body">
									<p class="card-text">${option3.option }</p>
									<p class="card-text">
										<small class="text-muted"> 제품가격 : <span id="price">${option3.price }</span>원
										</small>
									</p>
								</div>
							</div>
							<br>
						</div>
						<!--제품 옵션, 설명 (한개) 끝-->
					</form>
				</div>
			</div>
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
							<!-- JSTL 댓글 출력, forEach반복문 시작-->
							<!-- var, items 필수 속성값. var는 items에 담겨있는 List 객체를 변수를 통해여 화면에 보여줌 -->
							<c:forEach items="${content.comments}" var="comment">
								<tr>
									<!-- 실제 내용 행 -->
									<td scope="row">${comment.userId}</td>
									<td>${comment.content}</td>
									<td class="text-center" style="width: 12rem;">
									<!--if 작성자이면 수정, 삭제 보임, else 작성자 아니면 수정, 삭제 안보임 혹은 admin은 항상보임 -->
										<c:if test="${(not empty sessionScope.member && sessionScope.member.userId eq comment.userId) || sessionScope.member.userId eq 'admin'}"> 
											<!-- 댓글 수정 버튼 -->
											<a href="#" class="btn btn-primary"
											onclick="showModifyComment(this, '${comment.userId}','${comment.content}','${comment.cRegdate}');">수정</a>
											<!-- 댓글 삭제 버튼 -->
											<a href="/projectCommentDelete?commentNo=${comment.commentNo }&projectNo=${comment.projectNo}" class="btn btn-secondary" onclick="return question_cmt();">삭제</a>
										</c:if>
									</td>
									<td class="text-center">${comment.cRegdate}</td>
								</tr>
								<!-- 댓글 수정하기 위한 행 input (초기 hidden) -->
								<tr style="display:none;">
									<td scope="row">${comment.userId}</td>
									<td><input type="text" class="form-control"
										id=modifyComment value="${comment.content}"></td>
									<td class="float-right">
										<a class="btn btn-primary" href="javascript:void(0)"
										onclick="modifyComment(this, '${comment.commentNo}');">수정완료</a>
										<a class="btn btn-secondary" href="javascript:void(0)"
										onclick="modifyCancel(this);">취소</a>
									</td>
									<td class="text-center">${comment.cRegdate}</td>
								</tr>
							</c:forEach>
							<!-- JSTL 댓글 출력, forEach 반복문 끝 -->
						</tbody>
					</table>
					<!-- 댓글 수정 정보를 담는 폼 -->
					<form action="/projectCommentModify" id="modifyForm" method="post">
						<input type="hidden" id="modComment" name="modComment"> 
						<input type="hidden" id="modProjectNo" value="${content.projectNo}" name="modProjectNo"> 
						<input type="hidden" id="modCommentNo" name="modCommentNo">
					</form>
					<hr>
					<!--댓글 쓰는 폼(댓글 새로 작성)-->
					<!-- 로그인 안하면 댓글 쓰는칸 안보임 -->
					<c:if test="${not empty sessionScope.member }">
					<form action="/projectCommentWrite" method="post" id="projectCmtWrite">
						<div class="form-row">
							<div class="col-8 col-sm-9 col-md-9 col-lg-10">
								<input type="text" id="inputCmt" class="form-control" placeholder="댓글 내용을 입력해 주세요" name="comment">
								<input type="hidden" name="projectNo" value="${content.projectNo }">
							</div>
							<div class="col text-center">
								<a href="javascript:void(0)" class="btn btn-primary" onclick="check_cmt_input();">댓글남기기</a>
							</div>
						</div>
					</form>
					</c:if>
				</div>
				<!-- 댓글 컨텐츠 끝 -->
			</div>
	</section>
	<script>
		// 펀딩하기 버튼, 로그인 여부를 확인
		function check_login(){
			var checkLogin = '${sessionScope.member.userId}';
			if(checkLogin==""){
				alert("로그인 후에 이용가능한 서비스입니다.");
				return false;
			} else{
				check_option();
			}
		}
		
		// 펀딩하기를 진행하기전에, 옵션을 선택했는지 확인
		function check_option(){
			var option = $("input:radio[name='exampleRadios']:checked").val();
			if(option == null){
				alert("펀딩 상품 옵션을 선택해주세요.");
				return false;
			} else{
				var optionName = $("input:radio[name='exampleRadios']:checked").next().html();
				var checkFunding = window.confirm("'"+optionName + "' 옵션으로 펀딩 하시겠습니까?")
				if(checkFunding){
					$("#funding").submit();
				} else{
					return false;
				}
			}
		}
	
		// 게시글을 삭제할 것인지 한번 확인 과정을 가지는 스크립트
		function question_request() {
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
				$("#projectCmtWrite").submit();
			} else{
				alert("댓글 내용을 입력해주세요.");
			}
		}
		
		// 댓글 수정 텍스트 박스를 나타나게 하는 함수
    	function showModifyComment(obj, comment, userId, regDate) {
    		// 여기서는 obj만 사용(나머지 인자값은 이렇게 쓸 수 있다는 예시)
			console.log(obj);
    		$(obj).parents("tr").next().show();
    		// display : none으로 숨겨둔 것
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
    		// 수정하는 input태그를 선택해서 해당 input태그의 value를 가져와서 보내준다.
    		var comment = $(obj).parent().siblings(1).children().val();
    		console.log("comment : "+comment);
    		$("#modCommentNo").val(commentNo);
    		$("#modComment").val(comment);
    		$("#modProjectNo").val($("#modProjectNo").val());
    		// 우리가 지정한 action url로 서비스를 요청
    		$("#modifyForm").submit();
    	}
    	
    	// 댓글 삭제 확인 여부
    	function question_cmt() {
    		var check = window.confirm("선택하신 댓글을 삭제하시곘습니까?");
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="sanghwiLee" />
<title>프로젝트 수정하기</title>
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
<link href="/css/styles.css" rel="stylesheet" />

</head>

<body id="page-top">
	<!-- 상단바-->
	<jsp:include page="/common/header.jsp" />
	<br>
	<br>
	<!-- 메인 콘텐츠-->
	<section class="page-section bg-light" id="portfolio">
		<div class="container">
			<!--메인 콘텐츠 위치-->
			<div class="row">
				<!--아이콘 위치-->
				<div class="col-2">
					<span class="h2"> <!--아이콘 코드 넣는곳--> <svg
							class="bi bi-pencil-square" width="1em" height="1em"
							viewBox="0 0 16 16" fill="currentColor"
							xmlns="http://www.w3.org/2000/svg">
                            <path
								d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                            <path fill-rule="evenodd"
								d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
                        </svg>
					</span>
				</div>
				<!--제목 위치-->
				<div class="col-10">
					<span class="h2">펀딩 프로젝트 수정하기</span>
				</div>
			</div>
			<hr>
			<br>
			<!--콘텐츠 페이지 내용 위치-->
			<div>
				<!-- enctype="multipart/form-data"  -->
				<form action="/projectModifySuccess?keyValue=multipart"
					enctype="multipart/form-data" method="post" id="info">
					<input type="hidden" name="projectNo" value="${content.projectNo }">
					
					<table class="table">
						<tr>
							<td style="width: 15rem;">카테고리</td>

							<c:if test="${!empty content.category }">
								<td>
									<select class="custom-select mr-sm-2" id="projectCategory" style="width: 110px;" name="projectCategory">
										<c:forEach var="list" items="${content.category }" varStatus="i">
											<option value="${content.category }">${content.category }</option>
										</c:forEach>
									</select>
								</td>
							</c:if>
						</tr>

						<tr style="">
							<td>펀딩프로젝트 명</td>
							<td>
								<input type="text" class="form-control" name="project_title" id="projectTitle" 
								placeholder="펀딩프로젝트 명을 입력하세요" value="${content.projectTitle}">
							</td>
						</tr>

						<tr>
							<td>대표사진(썸네일)</td>
							<td><input type="hidden" name="status1" value="stay">
							
								<c:if test="${not empty image1.fileName }">
									<input type="file" name="image1" id="image1"
										class="form-control" style="display: none;" onchange="loadImg1(this);">
									<span class="delFile1" name="fileName">${image1.fileName }</span>
									<button type="button" id="fileDelBtn1"
										class="btn btn-primary btn-sm delFile1">파일삭제</button>
									<input type="hidden" name="oldFilename1"
										value="${image1.fileName }">
									<input type="hidden" name="oldFilepath1"
										value="${image1.filePath }">
									<input type="hidden" id="fileNo1" name="fileNo1"
										value="${image1.fileNo }">

								</c:if> <c:if test="${empty image1.fileName }">
									<input type="file" name="image1" class="form-control" id="id"
										size="30" placeholder="내용을 입력해주세요">
								</c:if></td>
						</tr>
						<tr>
							<td>사진 1</td>
							<td><input type="hidden" name="status2" value="stay">
								<c:if test="${not empty image2.fileName }">
									<input type="file" name="image2" id="image2"
										class="form-control" placeholder="내용을 입력해주세요" style="display: none;">
									<span class="delFile2">${image2.fileName }</span>
									<button type="button" id="fileDelBtn2"
										class="btn btn-primary btn-sm delFile2">파일삭제</button>
									<input type="hidden" name="oldFilename2"
										value="${image1.fileName }">
									<input type="hidden" name="oldFilepath2"
										value="${image1.filePath }">
									<input type="hidden" name="fileNo2" value="${image2.fileNo }">


								</c:if> <c:if test="${empty image2.fileName }">
									<input type="file" name="image2" class="form-control" id="id"
										size="30" placeholder="내용을 입력해주세요">
								</c:if></td>
						</tr>
						<tr>
							<td>사진 2</td>
							<td><input type="hidden" name="status3" value="stay">
								<c:if test="${not empty image3.fileName }">
									<input type="file" name="image3" id="image3"
										class="form-control" id="id" placeholder="내용을 입력해주세요" style="display: none;">
									<span class="delFile3">${image3.fileName }</span>
									<button type="button" id="fileDelBtn3"
										class="btn btn-primary btn-sm delFile3">파일삭제</button>
									<input type="hidden" name="oldFilename3"
										value="${image3.fileName }">
									<input type="hidden" name="oldFilepath3"
										value="${image3.filePath }">
									<input type="hidden" name="fileNo3" value="${image3.fileNo }">
										
								</c:if> <c:if test="${empty image3.fileName }">
									<input type="file" name="image3" class="form-control" id="id"
										size="30" placeholder="내용을 입력해주세요">

								</c:if></td>
						</tr>
						<tr>
							<td>프로젝트 소개</td>
							<td>
								<textarea class="form-control" id="projectContent" name="project_content"rows="10" cols="10"
									placeholder="프로젝트 설명을 입력해주세요.">${content.projectContent }</textarea>
							</td>
						</tr>

						<tr>
							<td>펀딩마감일</td>
							<td>
								<input type="date" class="form-control" id="endDate" name="end_date" size="30"  value="${content.endDate}">
								<input type="hidden" value="${condent.startDate }">
							</td>
						</tr>
						<tr>
							<td>목표금액</td>
							<td>
								<input type="text" class="form-control" id="targetMoney" name="target_money" size="30"
								placeholder="ex) 1500000 와 같은 형식으로 입력해주세요." value="${content.targetMoney}">
							</td>

						</tr>
						<tr>
							<td class="h5 font-weight-bold">상품 이름 (옵션 1번)</td>
							<td>
								<input type="text" class="form-control" id="option1_name" name="option1_name" size="30"
								placeholder="옵션 1번의 상품 이름을 입력해주세요. ex) 39,900+" value="${option1.productName }">
							</td>


						</tr>
						<tr>
							<td>상품 가격</td>
							<td><input type="text" class="form-control" id="option1_price" name="option1_price" size="30"
								placeholder="옵션 1번의 상품 가격을 입력해주세요. ex)39900" value="${option1.price }">
							</td>
						</tr>

						<tr>
							<td>상품 구성</td>
							<td><input type="text" class="form-control" id="option1_product" name="option1_product" size="30"
								placeholder="옵션 1번의 상품 구성을 입력해주세요. ex)캐릭터 마우스" value="${option1.option }">
							</td>
						</tr>

						<tr>
							<td class="h5 font-weight-bold">상품 이름 (옵션 2번)</td>
							<td><input type="text" class="form-control" id="option2_name" name="option2_name" size="30"
								placeholder="옵션 2번의 상품 이름을 입력해주세요. ex) 49,900+" value="${option2.productName }">
							</td>
						</tr>


						<tr>
							<td>상품 가격</td>
							<td><input type="text" class="form-control" id="option2_price" name="option2_price" size="30"
								placeholder="옵션 2번의 상품 가격을 입력해주세요. ex)49900" value="${option2.price }">
							</td>
						</tr>

						<tr>
							<td>상품 구성</td>
							<td><input type="text" class="form-control" id="option2_product" name="option2_product" size="30"
								placeholder="옵션 2번의 상품 구성을 입력해주세요. ex)캐릭터 마우스 + 키보드 스킨" value="${option2.option }">
							</td>
						</tr>

						<tr>
							<td class="h5 font-weight-bold">상품 이름 (옵션 3번)</td>
							<td><input type="text" class="form-control" id="option3_name" name="option3_name" size="30"
								placeholder="옵션 3번의 상품 이름을 입력해주세요. ex) 59,900+" value="${option3.productName }">
							</td>


						</tr>
						<tr>
							<td>상품 가격</td>
							<td><input type="text" class="form-control" id="option3_price" name="option3_price" size="30"
								placeholder="옵션 3번의 상품 가격을 입력해주세요. ex)59900" value="${option3.price }">
							</td>
						</tr>

						<tr>
							<td>상품 구성</td>
							<td><input type="text" class="form-control" id="option3_product" name="option3_product" size="30"
								placeholder="옵션 3번의 상품 구성을 입력해주세요. ex)캐릭터마우스 + 키보드 스킨 + 노트북 파우치" value="${option3.option }">
							</td>
						</tr>

						<tr>
							<td colspan="2" class="text-center">
								<a class="btn btn-secondary" href="/project">목록으로</a> &nbsp;&nbsp;&nbsp;&nbsp;
								<input type="submit" class="btn btn-primary" value="수정하기">
							</td>
						</tr>
					</table>
				</form>
				<br>
			</div>
		</div>
	</section>
	<script>
		// 글작성에 필요한 내용을 다 입력했는지 확인
		function checkProjectWrite(){
			var category = $("#projectCategory").val();
			var title = $("#projectTitle").val();
			var img1 = $("#image1").val();
			var img2 = $("#image2").val();
			var img3 = $("#image3").val();
			var content = $("#projectContent").val();
			var endDate = $("#endDate").val();
			var targetMoeny = $("#targetMoney").val();
			// 옵션 1
			var opName1 = $("#option1_name").val();
			var opPrice1 = $("#option1_price").val();
			var opProduct1 = $("#option1_product").val();
			// 옵션 2
			var opName2 = $("#option2_name").val();
			var opPrice2 = $("#option2_price").val();
			var opProduct2 = $("#option2_product").val();
			// 옵션 3
			var opName3 = $("#option3_name").val();
			var opPrice3 = $("#option3_price").val();
			var opProduct3 = $("#option3_product").val();
			
			if(category == ""){
				alert("카테고리를 선택해주세요.");
				return false;
			} else if(title ==""){
				alert("제목을 작성해주세요.");
				return false;
			} else if(img1==""){
				alert("대표사진파일을 등록해주세요.");
				return false;
			} else if(img2==""){
				alert("첫번째 사진파일을 등록해주세요.");
				return false;
			} else if(img3==""){
				alert("두번째 사진파일을 등록해주세요.");
				return false;
			} else if(content ==""){
				alert("프로젝트 설명을 작성해주세요.");
				return false;
			} else if(endDate ==""){
				alert("펀딩 마감일을 지정해주세요.");
				return false;
			} else if(targetMoeny==""){
				alert("목표 금액을 작성해주세요.");
				return false;
			} else if(opName1 ==""){
				alert("첫번째 옵션이름을 작성해주세요.");
				return false;
			} else if(opPrice1 ==""){
				alert("첫번째 옵션가격을 지정해주세요.");
				return false;
			} else if(opProduct1 ==""){
				alert("첫번째 옵션 제품설명을 작성해주세요.");
				return false;
			} else if(opName2 ==""){
				alert("두번째 옵션이름을 작성해주세요.");
				return false;
			} else if(opPrice2 ==""){
				alert("두번째 옵션가격을 작성해주세요.");
				return false;
			} else if(opProduct2 ==""){
				alert("두번째 옵션 제품설명을 작성해주세요.");
				return false;
			} else if(opName3 ==""){
				alert("세번째 옵션이름을 작성해주세요.");
				return false;
			} else if(opPrice3 ==""){
				alert("세번째 옵션가격을 작성해주세요.");
				return false;
			} else if(opProduct3 ==""){
				alert("세번째 옵션 제품설명을 작성해주세요.");
				return false;
			} else{
				var check = window.confirm("펀딩 프로젝트를 수정하시겠습니까?");
				console.log(check);
				if(check){
					return true;
				}else{
					return false;
				}
			}
		}
	</script>
	
	<!-- Footer 푸터, 하단바 -->
	<jsp:include page="/common/footer.html" />

	<!-- Bootstrap core JS-->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
		$(function() {
			$("#fileDelBtn1").click(function() {
				$(".delFile1").hide();
				$("#image1").show();
				$("input[name=status1]").val('delete');
			});
		});
		$(function() {
			$("#fileDelBtn2").click(function() {
				$(".delFile2").hide();
				$("#image2").show();
				$("input[name=status2]").val('delete');
			});
		});
		$(function() {
			$("#fileDelBtn3").click(function() {
				$(".delFile3").hide();
				$("#image3").show();
				$("input[name=status3]").val('delete');
			});
		});
		
        $('#end_date').change(function (){
            var date = $('#end_date').val();
            alert(date);
        });
    </script>


	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
	<!-- Third party plugin JS-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
	<!-- Contact  JS-->
	<script src="assets/mail/jqBootstrapValidation.js"></script>
	<script src="assets/mail/contact_me.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>

</html>
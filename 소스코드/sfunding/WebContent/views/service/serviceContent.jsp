<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>스펀딩 - 고객센터</title>
<link rel="icon" type="image/x-icon" href="/views/service/assets/img/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js" crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script> -->
<!--  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script> -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
     .target { display: inline-block; width: 200px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; }
</style>
</head>

<body id="page-top">
    <!-- 상단바-->
    <jsp:include page="/common/header.jsp"/>
    <br><br>
    
    <!-- 메인 콘텐츠-->
    <section class="page-section bg-light" id="portfolio">
        <div class="container">
            <!--메인 콘텐츠 위치-->
            <div class="row">
                <!--아이콘 위치-->
                <div class="col-1 col-sm-1 col-md-1">
                    <span class="h2">
                        <!--아이콘 코드 넣는곳-->
                        <svg class="bi bi-card-list" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M14.5 3h-13a.5.5 0 0 0-.5.5v9a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z" />
                            <path fill-rule="evenodd" d="M5 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 5 8zm0-2.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm0 5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5z" />
                            <circle cx="3.5" cy="5.5" r=".5" /><circle cx="3.5" cy="8" r=".5" /><circle cx="3.5" cy="10.5" r=".5" />
                        </svg>
                    </span>
                </div>
                <!--제목 위치-->
                <div class="col-11 col-sm-11 col-md-5 col-lg-5">
                    <span class="h2">고객센터 글 보기</span>
                </div>
                
                <!--버튼, 삭제/목록-->
				<div class="col-md-6 col-lg-6 d-flex justify-content-end">
					<!--삭제버튼은 로그인된 유저와 작성자 아이디가 같을때만 보이도록 한다.-->
					<span>
						<c:if test="${(not empty sessionScope.member && sessionScope.member.userId eq service.userId) || sessionScope.member.userId eq 'admin'}"> 
                            <a href="/serviceModifyForm?serviceNo=${service.serviceNo}&serviceContent=${serviceSelect.serviceContent}&serviceCategory=${serviceSelect.serviceCategory}" class="btn btn-primary">
                            	글 수정
                            </a>&nbsp;&nbsp;&nbsp;
							<a href="/serviceDelete?serviceNo=${service.serviceNo}"
								class="btn btn-secondary" onclick="return question();">글 삭제</a>
							&nbsp;&nbsp;
						</c:if> 
						<a href="/service" class="btn btn-primary">목록으로</a>
					</span>
				</div>
            </div>
            <hr>
            <br>
            <!--콘텐츠 페이지 내용 위치-->
            <div>
                <!--고객센터 글 내용-->
				<div>
					<div class="card">
						<div class="card-body">
							<!-- 고객센터 글 카테고리 -->
							<h4 class="card-title">${service.serviceCategory}</h4>
							<br>
							<!-- 고객센터 글 내용 -->
							<pre class="card-text" style="white-space:pre-wrap;">${service.serviceContent }</pre>
							<br>
							<hr>
							<!-- 고객센터 글 작성자 -->
							<span>작성자 : <span id="userId">${service.userId }</span></span>
							<span class="float-right">작성일 : <span id="rRegDate">${service.sRegdate }</span></span>
						</div>
					</div>
				</div>
					<hr>
				<!-- 댓글 테이블 -->
				<div>
                    <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">No.</th>
                                    <th scope="col">내용</th>
                                    <th scope="col">작성자</th>
                                    <th scope="col">등록일</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${pageData.pageList}" var="comment" varStatus="i">
                                    <tr>
                                        <th scope="row">${size-((currentPage-1)*10+i.index)}</th>
                                        <td>${comment.content}</td>
                                        <td>${comment.userId}</td>
                                        <td>${comment.cRegdate}</td>
                                        <td>
                                        <c:if test="${(not empty sessionScope.member && sessionScope.member.userId eq 'admin')}">
                                        	<a href="#" class="btn btn-primary" onclick="showModifyComment(this, '${comment.content}', '${comment.userId }','${comment.cRegdate }');">수정</a>

                                            <a href="/serviceDeleteComment?commentNo=${comment.commentNo }&serviceNo=${service.serviceNo}" onclick="return question();" class="btn btn-secondary">삭제</a>
                                    	</c:if>
                                    	</td>
                                    </tr>
                                    <tr style="display:none">
                                        <td>
                                            <input type="text" size="40" id="modifyMent" value="${comment.content}" />
                                        </td>
                                        <td>
                                        	<c:if test="${(not empty sessionScope.member && sessionScope.member.userId eq 'admin')}">
                                            	<a class="btn btn-primary" href="javascript:void(0)" onclick="modifyComment(this, '${comment.commentNo}');">수정완료</a>
                                            	<a class="btn btn-secondary" href="javascript:void(0)" onclick="modifyCancel(this);">취소</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <form action="/serviceModifyComment" id="modifyForm" method="post">
                                    <input type="hidden" id="modComment" name="modComment">
                                    <input type="hidden" id="modServiceNo" name="modServiceNo" value="${service.serviceNo }">
                                    <input type="hidden" id="modCommentNo" name="modCommentNo">
                                </form>
                            </tbody>
                        </table>

					<c:if test="${(not empty sessionScope.member && sessionScope.member.userId eq 'admin')}">
						<form action="/serviceCommentWrite" method="post">
							<div class="form-row">
								<div class="col-8 col-sm-9 col-md-9 col-lg-10">
									<input type="text" class="form-control col-10" name="comment"
										placeholder="댓글을 작성해보세요">
								</div>
								<input type="hidden" name="serviceNo"
									value="${service.serviceNo }" /> &nbsp;
								<div class="col text-center">
									<input class="btn btn-primary" type="submit" value="작성">
								</div>
							</div>
						</form>
					</c:if>

				</div>
            </div><!-- 콘텐츠 내용 끝 -->
        </div>
    </section>
    
    <script>
					function question() {
						var check = window.confirm("정말로 삭제 하시겠습니까?");
						if (check) {
							return true;
						} else {
							return false;
						}
					}
					function showModifyComment(obj, comment, userId, regdate) {
						$(obj).parents("tr").next().show();
						$(obj).parents("tr").hide();
					}

					function modifyCancel(obj) {
						$(obj).parents("tr").prev().show()
						$(obj).parents("tr").hide();
					}

					function modifyComment(obj, commentNo) {
						var comment = $(obj).parent().prev().find("input")
								.val();

						$("#modCommentNo").val(commentNo);
						/* $("#modComment").val($("#modifyMent").val()); */
						$("#modComment").val(comment);
						$("#modifyForm").submit();
					}
				</script>

    <!-- Footer 푸터, 하단바 -->
  	<jsp:include page="/common/footer.html"/>
    
    <script>
      function question() {
         var check = window.confirm("정말로 삭제 하시겠습니까?");
         if(check) {
            return true;
         } else {
            return false;
         }
      }
     </script>


    <!-- Bootstrap core JS-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
    <!-- Third party plugin JS-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
    <!-- Contact form JS-->
<!--     <script src="assets/mail/jqBootstrapValidation.js"></script>
    <script src="assets/mail/contact_me.js"></script> -->
    <!-- Core theme JS-->
<!--     <script src="js/scripts.js"></script> -->
</body></html>
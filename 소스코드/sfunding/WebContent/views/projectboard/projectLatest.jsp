<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>최신 등록 프로젝트 리스트</title>
    <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>

    <script src="js/scripts.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</head>
<body>
	<!-- 최신 등록 프로젝트 -->
	<div class="container">
		<div class="text-center">
			<h2 class="section-heading text-uppercase" style="text-align: left;">최신 프로젝트</h2>
		</div>
		<div class="row">
		<!-- 프로젝트 항목 반복 시작 -->
		<c:forEach items="${latestPageData.pageList }" var="project" varStatus="i">
			<div class="col-lg-4 col-sm-6 mb-4">
				<div class="portfolio-item">
					<a href="/projectSelect?projectNo=${project.projectNo }">
						<img class="img-fluid" src="/upload/photo/${project.image.filePath}" alt=""  style="weight:100%;height:300px;"/>
					</a>
					<div class="portfolio-caption" style="height:300px;">
						<div class="portfolio-caption-heading" style="text-align: left;">
							<a href="/projectSelect?projectNo=${project.projectNo }" style="color:black;">${project.projectTitle}</a>
						</div><br>
						<div class="portfolio-caption-subheading text-muted"
							style="text-align: left;">${project.projectContent}</div><br>
						<div class="progress progress-bar" style="width: ${project.rate }%; height: 12px;">${project.rate }</div><br>
							<div class="d-flex justify-content-start">
								<span>
									<svg class="bi bi-calendar3" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
										<path fill-rule="evenodd" d="M14 0H2a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zM1 3.857C1 3.384 1.448 3 2 3h12c.552 0 1 .384 1 .857v10.286c0 .473-.448.857-1 .857H2c-.552 0-1-.384-1-.857V3.857z" />
										<path fill-rule="evenodd" d="M6.5 7a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" />
									</svg>
								</span>&nbsp; 
								<span>시작일 : &nbsp;${project.startDate }</span>
							</div><br>
							
							<p class="text-left">
								<span>
								<svg class="bi bi-basket2-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  									<path fill-rule="evenodd" d="M11.314 1.036a.5.5 0 0 1 .65.278l2 5a.5.5 0 1 1-.928.372l-2-5a.5.5 0 0 1 .278-.65zm-6.628 0a.5.5 0 0 0-.65.278l-2 5a.5.5 0 1 0 .928.372l2-5a.5.5 0 0 0-.278-.65z"/>
 									<path fill-rule="evenodd" d="M1.5 7a.5.5 0 0 0-.489.605l1.5 7A.5.5 0 0 0 3 15h10a.5.5 0 0 0 .489-.395l1.5-7A.5.5 0 0 0 14.5 7h-13zM4 10a1 1 0 0 1 2 0v2a1 1 0 1 1-2 0v-2zm3 0a1 1 0 0 1 2 0v2a1 1 0 1 1-2 0v-2zm4-1a1 1 0 0 0-1 1v2a1 1 0 1 0 2 0v-2a1 1 0 0 0-1-1z"/>
									<path d="M0 6.5A.5.5 0 0 1 .5 6h15a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5H.5a.5.5 0 0 1-.5-.5v-1z"/>
								</svg>
								</span>
							모인 금액 : &nbsp;${project.sumMoney }</p>
							
							
							<p class="text-left">
							<span>
								<svg class="bi bi-cloud-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
 								<path fill-rule="evenodd" d="M3.5 13a3.5 3.5 0 1 1 .59-6.95 5.002 5.002 0 1 1 9.804 1.98A2.5 2.5 0 0 1 13.5 13h-10z"/>
								</svg>
							</span>
							
							달성률 : &nbsp;${project.rate }</p>
					</div>
				</div>
			</div>
			</c:forEach>
			<!-- 프로젝트 반복 끝 -->
		</div><!-- row 끝 -->
	</div>

</body>
</html>
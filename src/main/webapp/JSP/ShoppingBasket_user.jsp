<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.util.Map"%>
<%@ page import="Domain.Common.Dto.OrderDto"%>
<%@ page import="Domain.Common.Dao.OrderDaoimpl"%>
<%@ page import="java.util.* "%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href=" ${pageContext.request.contextPath}/CSS/Common_User.css"
	rel="stylesheet" type="text/css">
<link href=" ${pageContext.request.contextPath}/CSS/mCommon.css"
	rel="stylesheet" type="text/css" media="all and (max-width: 480px) ">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link href="https://fonts.googleapis.com/css2?family=Chewy&display=swap"
	rel="stylesheet">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>장바구니</title>
</head>
<body>
	<header>
		<div class="header">
			<div class="banner">
				<div class="logo">
					<a href="./Main.jsp"> <img
						src="${pageContext.request.contextPath}/SRC/logo.png"></img>
					</a>
				</div>
				<div class="banner_top">
					<span class="material-symbols-outlined" id="login-button">login</span>
					<script type="text/javascript"
						src="${pageContext.request.contextPath}/JS/Login.js"></script>
					<a href=""><span class="material-symbols-outlined">search</span></a>
					<a href=""><span class="material-symbols-outlined">person</span></a>
					<a href=""><span class="material-symbols-outlined">shopping_bag</span></a>
				</div>
				<div class="banner_middle">
					<div class="df">Daily Friday</div>
				</div>
				<nav class="banner_bottom">
					<ul class="navbar">
						<li class="nav-item"><a href="#" class="nav-link">상의</a>
							<div class="dropdown-menu">
								<a href="#" class="dropdown-item">반팔</a> <a href="#"
									class="dropdown-item">긴팔</a> <a href="#" class="dropdown-item">니트</a>
							</div></li>
						<li class="nav-item"><a href="#" class="nav-link">하의</a>
							<div class="dropdown-menu">
								<a href="#" class="dropdown-item">치마</a> <a href="#"
									class="dropdown-item">반바지</a> <a href="#" class="dropdown-item">트랙팬츠</a>
								<a href="#" class="dropdown-item">청바지</a>
							</div></li>
						<li class="nav-item"><a href="#" class="nav-link">외투</a>
							<div class="dropdown-menu">
								<a href="#" class="dropdown-item">패딩</a> <a href="#"
									class="dropdown-item">가디건</a> <a href="#" class="dropdown-item">블레이저</a>
								<a href="#" class="dropdown-item">자켓</a>
							</div></li>
						<li class="nav-item"><a href="#" class="nav-link">고객센터</a>
							<div class="dropdown-menu">
								<a href="#" class="dropdown-item">1:1문의</a> <a href="#"
									class="dropdown-item">자주 묻는 질문</a> <a href="#"
									class="dropdown-item">환불문의</a> <a href="#"
									class="dropdown-item">입고 지연 상품 안내</a>
							</div></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>


	<h1>장바구니</h1>
	<div class="Main">

		<c:set var="result" value="${requestScope.result}" />
		<c:set var="resultList" value="${result.result}" />
		<c:set var="resultElement" value="" scope="request" />

		<c:if test="${resultList != null}">
			<c:catch var="resultConversionError">
				<c:set var="resultElement" value="${resultList[0]}" />
			</c:catch>
		</c:if>

		<c:choose>
			<c:when test="${resultConversionError != null}">
				<c:set var="singletonList" value="${result.result}" scope="request" />
				<c:set var="resultget"
					value="${singletonList ne null ? [singletonList] : []}" />
			</c:when>
			<c:otherwise>
				<c:set var="resultget" value="${resultList}" />
			</c:otherwise>
		</c:choose>

		<table>
			<thead>
				<tr>
					<th>주문 수량</th>
					<th>주소</th>
					<th>가격</th>
					<th>주문 날짜</th>
					<th>상품명</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${resultget}" var="vo" varStatus="status">
					<tr>
						<td>${vo.odr_amount != null ? vo.odr_amount : 0 }</td>
						<td>${vo.adr_addr != null ? vo.adr_addr : '-' }</td>
						<td>${vo.price != null ? vo.price : 0 }</td>
						<td>${vo.odr_date != null ? vo.odr_date : '-' }</td>
						<td>${vo.product_name != null ? vo.product_name : '-' }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<button class="order-button" type="button">주문하기</button>

	</div>
	<hr style="margin-left: 10px; margin-right: 10px;">

	<Footer>
		<div class="Footer">
			<p>
				<a href="">공지사항</a> | <a href="">이용약관</a> | <a href="">개인정보취급 방침</a>
			</p>
			<p>&copy;상호명 (주) Daily Friday 대표 : 9조</p>
			<p>사업자 등록 번호 : 916-14-56874 | 대구 중구 덕산동 00빌딩</p>
			<p>대표 전화 번호 : 010-4568-5468</p>
			<p>email : dfteam9@naver.com</p>
		</div>
	</Footer>
</body>

</html>
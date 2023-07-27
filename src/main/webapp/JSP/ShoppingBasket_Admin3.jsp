<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="Domain.Common.Dto.OrderDto" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>


<!DOCTYPE html>
<html>
<head>


<link href=" ${pageContext.request.contextPath}/CSS/Common_Admin.css" 
	rel="stylesheet" type="text/css">
<link href=" ${pageContext.request.contextPath}/CSS/mCommon.css"
	rel="stylesheet" type="text/css" media="all and (max-width: 480px) ">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link href="https://fonts.googleapis.com/css2?family=Chewy&display=swap"
	rel="stylesheet">
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
 <!-- jQuery UI CSS 파일 -->
  <link href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" rel="stylesheet" />

  <!-- Bootstrap CSS 파일 -->
  <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet" />
  
  	 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	  <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
	  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.6.0/underscore.js"></script>
	  <script src="${pageContext.request.contextPath}/JS/OrderTable.js"></script>
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.4.0/axios.min.js" integrity="sha512-uMtXmF28A2Ab/JJO2t/vYhlaa/3ahUOgj1Zf27M5rOo8/+fcTUVH0/E0ll68njmjrLqOBjXM3V9NiPFL5ywWPQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
 
  
<title>장바구니</title>
</head>

<%
String memberId = (String) request.getAttribute("member_id");
String role = (String) request.getAttribute("role");
%>

<body>
 
	<header>
		<div class="header">
			<div class="banner">
				<div class="logo">
					<a href="./Main.jsp"> <img src="${pageContext.request.contextPath}/SRC/logo.png"></img>
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

	<main>
		
		<div id="ShoppingBasketAdminMain">
			<div class="odrmanage">쇼핑몰관리</div>
			<div class="odrlisttag">주문목록조회</div>
			<!-- 검색창 -->
			
			<div class="searchwrapper">
				<div class="midsearchwrapper">
			
					<select name="category" id="c_select">
						<option value="주문 ID">주문 ID</option>
						<option value="User ID">User ID</option>
						<option value="제품코드">제품코드</option>
						<option value="제품명">제품명</option>
						<option value="주소">주소</option>
						<option value="수량">수량</option>
						<option value="날짜">날짜</option>
						<option value="가격">가격</option>
					</select>
					<input type="text" id="odrtype" name="orderId" autocomplete="off">
					
					<button class="search_btn">조회</button>
					<script type="text/javascript"
						src="${pageContext.request.contextPath}/JS/Order2.js"></script>
					
					<div class ="buttons">
						<input type="button" id="edit_button" value="수정">
						<input type="button" id="delete_button" value="삭제">
	  				</div>
	  				
			
				</div>
			<!-- 추천창 -->
				<div id="suggestion_box" class="invisible">
					<div id="suggestedd_items"></div>
				</div>
			</div>
						
		<!-- 주문 전체 조회 결과 출력 -->
		<div class="table-editable" id="table-e">
		 
		  <table class="table">
		    <thead>
		      <tr id="tablehead">
		        <th>주문 ID</th>
		        <th>회원 ID</th>
		        <th>상품 코드</th>
		        <th>상품 이름</th>
		        <th>주소</th>
		        <th>주문 수량</th>
		        <th>주문 일자</th>
		        <th>가격</th>
		        <th><span class="table-add glyphicon glyphicon-plus" id="plusbt"></span></th>
		      </tr>
		    </thead>
		    <tbody class="table-body">
		    
		    </tbody>
		  </table>
		</div>

	 
  </div>

	</main>

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
	
						<!-- axios cdn -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.4.0/axios.min.js" integrity="sha512-uMtXmF28A2Ab/JJO2t/vYhlaa/3ahUOgj1Zf27M5rOo8/+fcTUVH0/E0ll68njmjrLqOBjXM3V9NiPFL5ywWPQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- 	<script>
	const search_btn_el = document.querySelector(".search_btn");
	
	search_btn_el.addEventListener("click",function(){
		const projectPath='%{pageContext.request.contextPath}';
		console.log("Search_btn_el click!");
		axios.get("http://localhost:8080"+projectPath+"/order/search.do")
		.then(response=>{alert("Success!",response.data);
			const list = response.data;
			list.forEach((bookdto)=>{console.log(bookdto);});
		})
		.catch(error=>{alert("FAIL!!");})
	}) 
	
	</script> -->

	
</body>

</html>
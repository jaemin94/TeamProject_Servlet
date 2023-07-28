<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href=" ${pageContext.request.contextPath}/CSS/Common.css"
	rel="stylesheet" type="text/css">
<link href=" ${pageContext.request.contextPath}/CSS/mCommon.css"
	rel="stylesheet" type="text/css" media="all and (max-width: 480px) ">

<!-- 구글 아이콘 / 폰트 -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link href="https://fonts.googleapis.com/css2?family=Chewy&display=swap"
	rel="stylesheet">

<!-- Swiper -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.4.0/axios.min.js"
	integrity="sha512-uMtXmF28A2Ab/JJO2t/vYhlaa/3ahUOgj1Zf27M5rOo8/+fcTUVH0/E0ll68njmjrLqOBjXM3V9NiPFL5ywWPQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script
	src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
<script defer src="${pageContext.request.contextPath}/JS/Swiper.js"
	type="text/javascript"></script>
<script defer
	src="${pageContext.request.contextPath}/JS/ProductDetail.js"
	type="text/javascript"></script>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%@ page import="java.util.List" %>
<%@ page import = "Domain.Common.Dto.ProdDto" %>
<%
String memberId = (String) request.getAttribute("member_id");
String role = (String) session.getAttribute("ROLE");




List<Integer> basketlist = (List<Integer>) session.getAttribute("Basket"); //세션으로 장바구니 정보 전달(임시로 list로 구현)
int alarmCnt;
if(!(basketlist==null)){//장바구니 물품 여부 확인
	alarmCnt = basketlist.size();//있으면 물건의 개수 만큼 alarmCnt에 저장
}else{
	alarmCnt = 0;//없으면 0
}
System.out.println("ROLE : " + role);
%>

<style>

#shopping{
	position:relative;
}
.dotted_bg{
	width:15px;height:15px;
	background-color:red;
	border-radius:50%;
	position:absolute;
	left:22px;
	
	font-size:0.7rem;
	font-family: arial;
	color:white;
	font-weight:700;
	text-align:center;
	line-height:15px;
}
</style>


<title>DFMall</title>

</head>
<body>
	<header>
		<div class="header">
			<div class="banner">
				<div class="logo">
					<a href="${pageContext.request.contextPath}/JSP/Main.jsp"> <img
						src="${pageContext.request.contextPath}/SRC/logo.png"></img>
					</a>
				</div>
				<div class="banner_top">
					<span class="material-symbols-outlined" id="login-button">login</span>
 					<script type="text/javascript" src="${pageContext.request.contextPath}/JS/Login.js"></script>
					<a href=""><span class="material-symbols-outlined">search</span></a>
					<a href=""><span class="material-symbols-outlined">person</span></a>


					
					<span class="material-symbols-outlined" id="shopping">
					  <span class="dotted_bg" id="alarmCount">
					  <%=alarmCnt %>//장바구니의 수 출력
					  </span>
					  shopping_bag
					</span>
					
					<script>
					
					  // 동적으로 투명도를 설정하고자 하는 <span> 요소를 가져옴.
					  var alarmCountEl = document.getElementById('alarmCount');
					
					  // alarmCnt 값이 "0"이면 투명하게, 그렇지 않으면 불투명하게 설정.
					  alarmCountEl.style.opacity = (<%=alarmCnt %> === 0) ? '0' : '1';
					</script>


					
					
					
					<script>
    					// 세션에서 역할 정보 가져오기
    					let role = '<%=session.getAttribute("ROLE")%>';
						let shoppingBtn = document.getElementById("shopping");

						// 역할에 따른 이벤트 처리
						if (role === "Role_Member") {
							shoppingBtn
									.addEventListener(
											"click",
											function() {
												window.location.href = "./order/ShoppingBasket_Admin.jsp";
											});
						} else if (role === "Role_user") {
							shoppingBtn
									.addEventListener(
											"click",
											function() {
												window.location.href = "./order/ShoppingBasket_user.jsp";
											});
						} else {
							shoppingBtn.addEventListener("click", function() {
								alert("로그인이 필요한 기능입니다.");
							});
						}
					</script>

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
		<div class="main">
			<h1 class="bs">Best Sellers</h1>
			<section class="swiper_section">
				<!-- Slider main container -->

				<div class="swiper">
					<!-- Additional required wrapper -->
					<div class="swiper-wrapper" id="swiperWrapper">
						<!-- Slides -->
						<div class="swiper-slide">
							<img src="${pageContext.request.contextPath}/SRC/332578_1.jpg"
								id="img1"></img>
						</div>
						<div class="swiper-slide">
							<img src="${pageContext.request.contextPath}/SRC/406530_1.jpg"
								id="img2"></img>
						</div>
						<div class="swiper-slide">
							<img
								src="${pageContext.request.contextPath}/SRC/65f58b33-7e4c-44f5-9d92-ad98e4689fa4_20221108192859.jpg"
								id="img3"></img>
						</div>
						<div class="swiper-slide">
							<img src="${pageContext.request.contextPath}/SRC/회색 반팔.jpg"
								id="img4"></img>
						</div>
						<div class="swiper-slide">
							<img
								src="${pageContext.request.contextPath}/SRC/1671524103263_8175765baf6408b47c8fec63314384e8_0.jpg"></img>
						</div>
						<div class="swiper-slide">
							<img
								src="${pageContext.request.contextPath}/SRC/bd4ffa64-07fb-4f07-99bb-b1b3e236746b.jpg"
								id="img5"></img>
						</div>
					</div>
					<!-- If we need pagination -->
					<div class="swiper-pagination"></div>

					<!-- If we need navigation buttons -->
					<div class="swiper-button-prev"></div>
					<div class="swiper-button-next"></div>

					<!-- If we need scrollbar -->
					<div class="swiper-scrollbar"></div>
				</div>

			</section>

			<hr>

			<section>
				<h2 class="bs">New Arrival!</h2>
				<aside></aside>

				<article>
					<div class="grid-container" id="gridContainer">
						<div class="grid-item">
							<img
								src="${pageContext.request.contextPath}/SRC/2000005784729_1_large.jpg"
								id="img1"></img>
						</div>
						<div class="grid-item">
							<img
								src="${pageContext.request.contextPath}/SRC/1000543535401_i1_290.jpg"
								id="img1"></img>
						</div>
						<div class="grid-item">
							<img src="${pageContext.request.contextPath}/SRC/blue  긴.jpg"
								id="img1"></img>
						</div>
						<div class="grid-item">
							<img src="${pageContext.request.contextPath}/SRC/blue shot.jpg"
								id="img1"></img>
						</div>

						<div class="grid-item">
							<img
								src="${pageContext.request.contextPath}/SRC/65f58b33-7e4c-44f5-9d92-ad98e4689fa4_20221108192859.jpg"
								id="img1"></img>
						</div>
						<div class="grid-item">
							<img
								src="${pageContext.request.contextPath}/SRC/55e6196dfa7a26e039a67d04fb7a6c3343c8905c2828953037cf168bdbde.jpg"
								id="img1"></img>
						</div>
						<div class="grid-item">
							<img
								src="${pageContext.request.contextPath}/SRC/230208004099500.jpg"
								id="img1"></img>
						</div>
						<div class="grid-item">
							<img src="${pageContext.request.contextPath}/SRC/자주 가디건.jpg"
								id="img1"></img>
						</div>

						<div class="grid-item">
							<img src="${pageContext.request.contextPath}/SRC/여자 블랙 자켓.jpg"
								id="img1"></img>
						</div>
						<div class="grid-item">
							<img
								src="${pageContext.request.contextPath}/SRC/check short p.jpg"
								id="img1"></img>
						</div>
						<div class="grid-item">
							<img src="${pageContext.request.contextPath}/SRC/a.jpg" id="img1"></img>
						</div>
						<div class="grid-item">
							<img
								src="${pageContext.request.contextPath}/SRC/여성 베이지 테니스 스커트.jpg"
								id="img1"></img>
						</div>

					</div>
				</article>
				<aside></aside>
			</section>
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



</body>
</html>
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
<script defer
	src="${pageContext.request.contextPath}/JS/pageRout.js"
	type="text/javascript"></script>
	
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>장바구니</title>
<%
String memberId = (String) request.getAttribute("member_id");
String role = (String) session.getAttribute("ROLE");
%>

<script>
let role = '<%= session.getAttribute("ROLE") %>';
</script>

</head>
<body>
	<header>
		<div class="header">
			<div class="banner">
				<div class="logo" id="logo">
					<a href=""> <img
						src="${pageContext.request.contextPath}/SRC/logo.png"></img>
					</a>
				</div>
				<div class="banner_top">
					<span class="material-symbols-outlined" id="login-button">login</span>
					<a href=""><span class="material-symbols-outlined">search</span></a>
					<a href=""><span class="material-symbols-outlined">person</span></a>
					<span class="material-symbols-outlined" id="shopping">shopping_bag</span>
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
    <hr>
    <!-- 도서 조회하기 -->
    <hr>
    <div>
        <div class="search_block">
            <input placeholder="keyfield" />
            <input placeholder="keyword" />
            <button class="search_btn">조회</button>
        </div>
        <div class="show_block" style="width:500px;height:500px;border:1px solid;overflow:auto;">
            <table>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Product Name</th>
                        <th>Address</th>
                        <th>Amount</th>
                        <th>Price</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>

    <hr>
    <!-- 사서 메뉴로 이동하기 -->
    <!-- 회원 메뉴로 이동하기 -->
    <hr/>
    <c:if test="${empty ROLE}">
        <a href=<c:url value="/login.do" /> >LOGIN</a></br>
    </c:if>
    <c:if test="${not empty ROLE}">
        <a href=<c:url value="/logout.do" /> >LOGOUT</a></br>
    </c:if>
    
    <hr/>
    <div class="msg">
        ${msg}
    </div>

    EL's Project PATH : ${pageContext.request.contextPath}<br/>
    EL's Project ServerPort :${pageContext.request.serverPort}<br/>
    
    <!-- axios cdn -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.4.0/axios.min.js" integrity="sha512-uMtXmF28A2Ab/JJO2t/vYhlaa/3ahUOgj1Zf27M5rOo8/+fcTUVH0/E0ll68njmjrLqOBjXM3V9NiPFL5ywWPQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script>
    const search_btn_el = document.querySelector('.search_btn');
    search_btn_el.addEventListener('click', function () {
        const projectPath = '${pageContext.request.contextPath}';
        const ServerPort = '${pageContext.request.serverPort}';
        console.log("search_btn_el click..", projectPath);

        const show_block_el = document.querySelector('.show_block tbody');
        
        axios.get("http://localhost:" + ServerPort + projectPath + "/order/ShoppingBasket_user.do")
            .then(response => {
                console.log("success!", response.data);
                const list = response.data;
                show_block_el.innerHTML = "";
                
                list.forEach((dto) => {
                    console.log('dto', dto);
                    const dto_row = document.createElement('tr');

                    const odr_date = new Date(dto.odr_date);
                    const odr_date_str = odr_date.toLocaleDateString();

                    dto_row.innerHTML += "<td>" + dto.order_id + "</td>";
                    dto_row.innerHTML += "<td>" + dto.product_name + "</td>";
                    dto_row.innerHTML += "<td>" + dto.adr_addr + "</td>";
                    dto_row.innerHTML += "<td>" + dto.odr_amount + "</td>";
                    dto_row.innerHTML += "<td>" + dto.price + "</td>";
                    dto_row.innerHTML += "<td>" + odr_date_str + "</td>";

                    show_block_el.appendChild(dto_row);
                });
            })
            .catch(error => {
                console.log("Error in HTTP request:", error);
            });
    });
   
    </script>
    <button class="order-button" type="button">주문하기</button>

	
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
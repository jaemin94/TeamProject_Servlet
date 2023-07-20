<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="Domain.Common.Dto.OrderDto , java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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
		
		<div class="main">
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
					<input type="text" id="odrtype" autocomplete="off">
					
					<input type="button" id="submint_button" value="조회">
					
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
		</form>	
    <section>
         <h1>주문 전체 조회 결과</h1>
    <table border="1">
        <tr>
            <th>주문 ID</th>
            <th>회원 ID</th>
            <th>상품 코드</th>
            <th>상품 이름</th>
            <th>주소</th>
            <th>주문 수량</th>
            <th>주문 일자</th>
            <th>가격</th>
        </tr>
        <%-- 서블릿에서 전달받은 주문 정보 리스트를 반복하여 출력 --%>
        <%@ page import="java.util.List" %>
        <%@ page import="java.util.Iterator" %>
        <%@ page import="java.util.Map" %>
        <%@ page import="java.util.HashMap" %>
        <%@ page import="java.util.ArrayList" %>
        <%@ page import="java.util.Date" %>
        <%@ page import="java.text.SimpleDateFormat" %>

        <%
        List<OrderDto> orderList = (List<OrderDto>) request.getAttribute("orderList");
        if (orderList != null && !orderList.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (OrderDto order : orderList) {
                out.println("<tr>");
                out.println("<td>" + order.getOrder_id() + "</td>");
                out.println("<td>" + order.getMember_id() + "</td>");
                out.println("<td>" + order.getProduct_code() + "</td>");
                out.println("<td>" + order.getProduct_name() + "</td>");
                out.println("<td>" + order.getAdr_addr() + "</td>");
                out.println("<td>" + order.getOdr_amount() + "</td>");
                out.println("<td>" + sdf.format(order.getOdr_date()) + "</td>");
                out.println("<td>" + order.getPrice() + "</td>");
                out.println("</tr>");
            }
        } else {
            out.println("<tr><td colspan=\"8\">주문 정보가 없습니다.</td></tr>");
        }
        %>
    </table>
   
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
	
	<script>
  document.getElementById('select-all-checkbox').addEventListener('change', function() {
    var checkboxes = document.querySelectorAll('.li input[type="checkbox"]');
    var selectAllCheckbox = document.getElementById('select-all-checkbox');
    
    checkboxes.forEach(function(checkbox) {
      checkbox.checked = selectAllCheckbox.checked;
    });
  });
  
  document.getElementById('edit-button').addEventListener('click', function() {
    var selectedOrderIds = [];
    var checkboxes = document.querySelectorAll('.li input[type="checkbox"]');
    
    checkboxes.forEach(function(checkbox) {
      if (checkbox.checked) {
        var orderId = checkbox.parentElement.querySelector('span:nth-child(1)').innerText;
        selectedOrderIds.push(orderId);
      }
    });
    
    // 선택된 주문 ID에 대한 수정 기능 실행
    // selectedOrderIds 배열을 이용하여 선택된 주문 ID를 전달하고 수정을 수행
    
    // 예시: 선택된 주문 ID를 콘솔에 출력
    console.log(selectedOrderIds);
  });
  
  document.getElementById('delete-button').addEventListener('click', function() {
    var selectedOrderIds = [];
    var checkboxes = document.querySelectorAll('.li input[type="checkbox"]');
    
    checkboxes.forEach(function(checkbox) {
      if (checkbox.checked) {
        var orderId = checkbox.parentElement.querySelector('span:nth-child(1)').innerText;
        selectedOrderIds.push(orderId);
      }
    });
    
    // 선택된 주문 ID에 대한 삭제 기능 실행
    // selectedOrderIds 배열을 이용하여 선택된 주문 ID를 전달하고 삭제를 수행
    
    // 예시: 선택된 주문 ID를 콘솔에 출력
    console.log(selectedOrderIds);
  });
</script>
	
</body>

</html>

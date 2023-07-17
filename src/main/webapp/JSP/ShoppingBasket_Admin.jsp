<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.PrintWriter" %>

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
    <section>
      <ul class="table">
      
      		<li class="li" id="listhead">
				<span>oder-id</span>
				<span>user-id</span>
				<span>prod-code</span>
				<span>prod-name</span>
				<span>address</span>
				<span>amount</span>
				<span>odr-date</span>
				<span>price</span>
			</li>
      		<% 
      		Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			response.setContentType("text/html;charset=utf-8");
			PrintWriter outt = response.getWriter();
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
	        	String url = "jdbc:mysql://localhost:3306/shoppingdb";
	        	String id = "root";
	       		String pw = "1234";
	       		conn = DriverManager.getConnection(url, id, pw);
	        	
	        	String query = "SELECT * FROM tbl_order";
	        	pstmt = conn.prepareStatement(query);
	        	rs = pstmt.executeQuery();
	        	
	        	while (rs.next()) {
	        		String Order_id= rs.getString("order_id");
					String Member_id=rs.getString("Member_id");
					int Product_code=rs.getInt("product_code");
					String Product_name=rs.getString("product_name");
					String Adr_addr=rs.getString("adr_addr");
					int Odr_amount=rs.getInt("odr_amount");
					Date Odr_date=rs.getDate("odr_date");
					int Price=rs.getInt("price");
	           %>
	       		<li class="li">
	          	  <span><%=Order_id %></span>
	          	  <span><%= Member_id %></span>
	          	  <span><%= Product_code %></span>
	          	  <span id="pname"><%= Product_name%></span>
	          	  <span><%= Adr_addr %></span>
	          	  <span><%= Odr_amount%></span>
	          	  <span><%= Odr_date%></span>
	          	  <span><%= Price %></span>
	          	</li>
	           <% 
	           	} 
	        	rs.close();
	            pstmt.close();
	            conn.close();
	            }catch(Exception e){
				e.printStackTrace();			
			}		
			%>
      </ul>
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
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
<link href=" ${pageContext.request.contextPath}/CSS/Common.css" rel="stylesheet" type="text/css">
<link href=" ${pageContext.request.contextPath}/CSS/mCommon.css" rel="stylesheet" type="text/css" media="all and (max-width: 480px) ">
 
 <!-- 구글 아이콘 / 폰트 -->
 <link rel="stylesheet"
    href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
  <link href="https://fonts.googleapis.com/css2?family=Chewy&display=swap" rel="stylesheet">
  
  <!-- Swiper -->
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
   <script  src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
  	<script defer src="${pageContext.request.contextPath}/JS/Swiper.js" type="text/javascript"></script>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>DFMall</title>
</head>
<body>
	<header>
    <div class="header">
      <div class="banner">
        <div class="logo">
          <a href = "./Main.jsp">
            <img src="${pageContext.request.contextPath}/SRC/logo.png"></img>
          </a>
        </div>
        <div class="banner_top">
          <span class="material-symbols-outlined" id="login-button">login</span>
          <script type="text/javascript" src="${pageContext.request.contextPath}/JS/Login.js"></script>
          <a href=""><span class="material-symbols-outlined">search</span></a>
          <a href=""><span class="material-symbols-outlined">person</span></a>
          <a href=""><span class="material-symbols-outlined">shopping_bag</span></a>
        </div>
        <div class="banner_middle">
          <div class="df">
            Daily Friday
          </div>
        </div>
        <nav class="banner_bottom">
            <ul class="navbar">
              <li class="nav-item"> 
                <a href="#" class="nav-link">상의</a>
                <div class="dropdown-menu">
                  <a href="#" class="dropdown-item">반팔</a>
                  <a href="#" class="dropdown-item">긴팔</a>
                  <a href="#" class="dropdown-item">니트</a>
                </div>
              </li>
              <li class="nav-item">
                <a href="#" class="nav-link">하의</a>
                <div class="dropdown-menu">
                  <a href="#" class="dropdown-item">치마</a>
                  <a href="#" class="dropdown-item">반바지</a>
                  <a href="#" class="dropdown-item">트랙팬츠</a>
                  <a href="#" class="dropdown-item">청바지</a>
                </div>
              </li>
              <li class="nav-item">
                <a href="#" class="nav-link">외투</a>
                <div class="dropdown-menu">
                  <a href="#" class="dropdown-item">패딩</a>
                  <a href="#" class="dropdown-item">가디건</a>
                  <a href="#" class="dropdown-item">블레이저</a>
                  <a href="#" class="dropdown-item">자켓</a>
                </div>
              </li>
              <li class="nav-item">
                <a href="#" class="nav-link">고객센터</a>
                <div class="dropdown-menu">
                  <a href="#" class="dropdown-item">1:1문의</a>
                  <a href="#" class="dropdown-item">자주 묻는 질문</a>
                  <a href="#" class="dropdown-item">환불문의</a>
                  <a href="#" class="dropdown-item">입고 지연 상품 안내</a>
                </div>
              </li>
            </ul>
          </nav>
      </div>
    </div>
  </header>

  <main>
    <div class="main">

       <section class="swiper_section">
            <!-- Slider main container -->
            <div class="swiper">
                <!-- Additional required wrapper -->
                <div class="swiper-wrapper">
                    <!-- Slides -->
                    <div class="swiper-slide"><img src="data:image/jpeg;base64,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"></div>
                    <div class="swiper-slide"><img src="data:image/jpeg;base64,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"></div>
                    <div class="swiper-slide"><img src=""></div>
                    <div class="swiper-slide"><img src=""></div>
                    <div class="swiper-slide"><img src=""></div>
                    <div class="swiper-slide"><img src=""></div>

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

        <aside></aside>

        <article>
          <div class="grid-container">
            <div class="grid-item">1</div>
            <div class="grid-item">2</div>
            <div class="grid-item">3</div>
            <div class="grid-item">4</div>

            <div class="grid-item">1</div>
            <div class="grid-item">2</div>
            <div class="grid-item">3</div>
            <div class="grid-item">4</div>

            <div class="grid-item">1</div>
            <div class="grid-item">2</div>
            <div class="grid-item">3</div>
            <div class="grid-item">4</div>

          
    </div>
    </article>
    <aside>

    </aside>
    </section>
	</div>
  </main>

  <hr style="margin-left: 10px; margin-right: 10px;">

  <Footer>
    <div class="Footer">
      <p><a href="">공지사항</a> | <a href="">이용약관</a> | <a href="">개인정보취급 방침</a></p>
      <p>&copy;상호명 (주) Daily Friday 대표 : 9조</p>
      <p>사업자 등록 번호 : 916-14-56874 | 대구 중구 덕산동 00빌딩</p>
      <p>대표 전화 번호 : 010-4568-5468</p>
      <p>email : dfteam9@naver.com</p>
    </div>
  </Footer>



</body>
</html>
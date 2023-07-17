<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href=" ${pageContext.request.contextPath}/CSS/ProductDetail.css" rel="stylesheet" type="text/css">
  <link href=" ${pageContext.request.contextPath}/CSS/Common.css" rel="stylesheet" type="text/css">
<link href=" ${pageContext.request.contextPath}/CSS/mCommon.css" rel="stylesheet" type="text/css" media="all and (max-width: 480px) ">
  <link href="https://fonts.googleapis.com/css2?family=Chewy&display=swap" rel="stylesheet">
  <link rel="stylesheet"
    href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

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

    <!-- main upper -->
    <div class="wrapper--productpage">
      <div class="productmaininfo">
        <div class="productmainim">
          <div class="productmainimg">
            <div id="frontimg"></div>
            <div id="backimg"></div>
          </div>  
        </div>  
      


        <div class="productin">
          <div class="productinfo">
            <li>상품명 : <div>bluefootedtshirt</div>
            </li>
            <li>판매가 : <div>1222won</div>
            </li>
            <li>배송비 : <div>1111</div>
            </li>
            <li class="color">색상 :
              <div class="option">
                <a href="">option</a>
                <ul class="optiontwodepth">
                  <li><a href="">pink (+200won)</a></li>
                  <li><a href="">blue (+200won)</a></li>
                  <li><a href="">white</a></li>
                  <li><a href="">black</a></li>
                </ul>

              </div>
            </li>
          </div>

          <div class="buybutton">
            <div class="topbutton">
              <button type="button">구매하기</button>
            </div>
            <div class="bottombutton">
              <button type="button">장바구니</button>
              <button type="button">찜하기</button>
            </div>
          </div>

        </div>

      </div>

      <!--main middle  -->

      <div class="productpagemiddle">
        <li><a href="">Detail</a> </li>
        <li><a href="">Guide</a> </li>
        <li><a href="">Review</a> </li>
        <li><a href="">QnA</a> </li>
      </div>

      <div class="detailwrap">

        <!--detail section  -->
        <section class="dtinfo">
          <article>
            <div>
              📌<br>
              해외배송가능<br>
              재입고 후 순차 발송 예정
            </div>
            <p></p>

            건조하고 조직감이 느껴지는 텍스쳐
            <br>
            여유 있는 루즈한 실루엣
            <br>
            여름철 활용도 높은 아이템
            <br>
            시원한 컬러의 프린팅
            <br>
            <p></p>

          </article>
        </section>

        <section class="detailsec1">
          <article>
            <div>
              <img src="src/frontt.jpg" alt="">
            </div>
          </article>

        </section>

        <section class="detailsec2">
  
            <article>
             <!--  <img src="" alt="">  -->
            </article>
            <aside class="dclean">
              INFO <br><br>
              컬러 : 화이트 <br><br>
              사이즈 : S-M <br><br>
              혼용률 : 면100 <br><br>
              텍스처 : 깔끔하고 탄탄한 텍스처입니다. <br><br>
              세탁주의사항 <br><br>

              의류의 형태감 보존을 원할 시 드라이크리닝 권장
              데님 및 염색된 원단(블랙 포함)의 경우 이염 방지를 위해
              꼭! 단독세탁 해주세요. (드라이크리닝 불가제품 제외)
              <br><br>
              제품측정방법에 따라 1-3cm의 오차가 발생할 수 있습니다.
              제조사: 보안사항(국내사입) 제조국: 한국 제조년월: 10일전
              품질보증기준: 자주하시는 질문참고
              as책임자와 전화번호: (주)에프터먼데이 1544-9987
              모델이미지 보다 디테일이미지의 색상이 실제색상과 비슷합니다.
              에프터먼데이의 모든 작업물을 무단으로 사용할 경우 민,형사상의
              처벌을 받을 수 있습니다.
            </aside>


    
          <div class="warninfo">
            <article>
              <p>
                상품 이미지는 모니터 해상도, 색상 설정에 따라 이미지가 왜곡되거나 실제 색상과 차이가 있을 수 있습니다.
                <br>
                사이즈 실측은 상품의 특성 및 측정방식에 따라 오차가 발생할 수 있습니다.
              </p>

            </article>
          </div>



        </section>


      </div>



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
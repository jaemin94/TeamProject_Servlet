<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href="${pageContext.request.contextPath}/CSS/Product.css" rel="stylesheet" type="text/css">
  <link href=" ${pageContext.request.contextPath}/CSS/Common.css" rel="stylesheet" type="text/css">
<link href=" ${pageContext.request.contextPath}/CSS/mCommon.css" rel="stylesheet" type="text/css" media="all and (max-width: 480px) ">
  <link href="https://fonts.googleapis.com/css2?family=Chewy&display=swap" rel="stylesheet">
  <link rel="stylesheet"
    href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<script defer
	src="${pageContext.request.contextPath}/JS/pageRout.js"
	type="text/javascript"></script>
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
          <a href = "">
            <img src="${pageContext.request.contextPath}/SRC/logo.png"></img>
          </a>
        </div>
        <div class="banner_top">
          <span class="material-symbols-outlined" id="login-button">login</span>
          <a href=""><span class="material-symbols-outlined">search</span></a>
          <a href=""><span class="material-symbols-outlined">person</span></a>
          <span class="material-symbols-outlined" id="shopping">shopping_bag</span>
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
      <div class="addmaininfo">
        <form action="" role="form" method="get" class="productinfo" id="addform" name="addform">
          <div class="addmainimg">
            <div class="addimgbutton">
              <label for="addimgfile"> 메인 이미지 선택</label>
            </div>
            <input type="file" id="addimgfile" accept="image/jepg,image/jpg,image/png" required>
          </div>


          <div class="productin">
            <div class="productinfor">
              <li class="addmaininformation">
                <label for="category">카테고리 :</label>
                <div class="addin">
                  <select name="category" id="cate" required>
                    <option value="" disabled selected> 선택 </option>
                    <optgroup label="상의">
                      <option value="halfslv">반팔</option>
                      <option value="longslv">긴팔</option>
                      <option value="nt">니트</option>
                    </optgroup>
                    <optgroup label="하의">
                      <option value="skirt">치마</option>
                      <option value="shorts">반바지</option>
                      <option value="trackp">트랙팬츠</option>
                      <option value="denim">청바지</option>
                    </optgroup>
                    <optgroup label="외투">
                      <option value="padding">패딩</option>
                      <option value="cardigan">가디건</option>
                      <option value="blazer">블레이져</option>
                      <option value="jacket">자켓</option>
                    </optgroup>
                </div>

                </select>
              </li>


              <li class="addmaininformation">
                <label for="name"> 상품명 : </label>
                <div class="addin">
                  <input type="text" id="name" required>
                </div>
              </li>
              <li class="addmaininformation">
                <label for="price"> 판매가 : </label>
                <div class="addin">
                  <input type="text" placeholder="단위 = 원" id="price" required pattern="[0-9]+$">
                </div>
              </li>
              <li class="addmaininformation">
                <label for="deliveryfee"> 배송비 : </label>
                <div class="addin">
                  <input type="text" placeholder="단위 = 원" id="deliveryfee" pattern="[0-9]+$" required>
                </div>
              </li>

              <li class="addmaininformation">
                <label for="op1"> 옵션 : </label>
                <div class="addin"><input type="text" id="op1">
                  <input type="button" value="+" id="add4">
                </div>
              </li>
            </div>
            <div class="addbutton">
              <div class="addbutton2">
                <button type="submit" form="addform">메인 정보 등록</button>
              </div>
            </div>
          </div>


        </form>


      </div>
    </div>

    </div>

    <!--main middle  -->

    <!--detail section  -->
    <div class="adddetailinfo">
      <p style=" margin: 10px auto;
      width: 100%;
      border: 1px dashed rgb(192, 192, 192);"></p>
      <form action="" id="adddetail">
        <section>
          <article>
            <label for="dinfo"> 상품 상세 설명 :</label>
            <textarea name="dinfo" form="adddetail" id="dinfo" cols="100" rows="10"></textarea>
          </article>
        </section>

        <section>

          <div>
            <div>
               <label for="detimg">상세 정보 파일 선택</label>
            </div>
           
            <input type="file" accept="image/*" id="detimg" required>
          </div>
         
        </section>

        <button type="submit" form="adddetail"> 상세 정보 등록</button>
      </form>
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

  <script>

  </script>

</body>

</html>
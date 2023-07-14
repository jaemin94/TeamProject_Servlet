<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>

<head>
  <title>�α���</title>
  <link href="${pageContext.request.contextPath}/CSS/Login.css"  rel="stylesheet" type="text/css">
  <link href=" ${pageContext.request.contextPath}/CSS/Common.css" rel="stylesheet" type="text/css">

  <link rel="stylesheet"
    href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
  
    <link href="https://fonts.googleapis.com/css2?family=Chewy&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Chewy&display=swap" rel="stylesheet">

</head>

<body>

  <header>
    <div class="header">
      <div class="banner">
        <div class="logo">
          <a href = "./Main.jsp">
            <img src=""></img>
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
                <a href="#" class="nav-link">����</a>
                <div class="dropdown-menu">
                  <a href="#" class="dropdown-item">����</a>
                  <a href="#" class="dropdown-item">����</a>
                  <a href="#" class="dropdown-item">��Ʈ</a>
                </div>
              </li>
              <li class="nav-item">
                <a href="#" class="nav-link">����</a>
                <div class="dropdown-menu">
                  <a href="#" class="dropdown-item">ġ��</a>
                  <a href="#" class="dropdown-item">�ݹ���</a>
                  <a href="#" class="dropdown-item">Ʈ������</a>
                  <a href="#" class="dropdown-item">û����</a>
                </div>
              </li>
              <li class="nav-item">
                <a href="#" class="nav-link">����</a>
                <div class="dropdown-menu">
                  <a href="#" class="dropdown-item">�е�</a>
                  <a href="#" class="dropdown-item">�����</a>
                  <a href="#" class="dropdown-item">������</a>
                  <a href="#" class="dropdown-item">����</a>
                </div>
              </li>
              <li class="nav-item">
                <a href="#" class="nav-link">������</a>
                <div class="dropdown-menu">
                  <a href="#" class="dropdown-item">1:1����</a>
                  <a href="#" class="dropdown-item">���� ���� ����</a>
                  <a href="#" class="dropdown-item">ȯ�ҹ���</a>
                  <a href="#" class="dropdown-item">�԰� ���� ��ǰ �ȳ�</a>
                </div>
              </li>
            </ul>
          </nav>
      </div>
    </div>
  </header>
  <!-- �α��� �� -->
   <div class="Main">
  <form id="login-form">
    <h1>�α���</h1>
    <label><input type="text" id="username" placeholder="���̵�"></label>
    <label><input type="password" id="password" placeholder="��й�ȣ"></label>
    <button class="login" type="submit">�α���</button>
  </form>
	</div>
  <hr />
   <Footer>
    <div class="Footer">
      <p><a href="">��������</a> | <a href="">�̿���</a> | <a href="">����������� ��ħ</a></p>
      <p>&copy;��ȣ�� (��) Daily Friday ��ǥ : 9��</p>
      <p>����� ��� ��ȣ : 916-14-56874 | �뱸 �߱� ���굿 00����</p>
      <p>��ǥ ��ȭ ��ȣ : 010-4568-5468</p>
      <p>email : dfteam9@naver.com</p>
    </div>
  </Footer>



</body>

</html>
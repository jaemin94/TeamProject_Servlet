<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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


  <main>

    <!-- main upper -->
    <div class="wrapper--productpage">
      <div class="addmaininfo">
        <form action="" role="form" method="get" class="productinfo" id="addform" name="addform">
          <div class="addmainimg">
            <div class="addimgbutton">
              <label for="addimgfile"> ���� �̹��� ����</label>
            </div>
            <input type="file" id="addimgfile" accept="image/jepg,image/jpg,image/png" required>
          </div>


          <div class="productin">
            <div class="productinfor">
              <li class="addmaininformation">
                <label for="category">ī�װ� :</label>
                <div class="addin">
                  <select name="category" id="cate" required>
                    <option value="" disabled selected> ���� </option>
                    <optgroup label="����">
                      <option value="halfslv">����</option>
                      <option value="longslv">����</option>
                      <option value="nt">��Ʈ</option>
                    </optgroup>
                    <optgroup label="����">
                      <option value="skirt">ġ��</option>
                      <option value="shorts">�ݹ���</option>
                      <option value="trackp">Ʈ������</option>
                      <option value="denim">û����</option>
                    </optgroup>
                    <optgroup label="����">
                      <option value="padding">�е�</option>
                      <option value="cardigan">�����</option>
                      <option value="blazer">������</option>
                      <option value="jacket">����</option>
                    </optgroup>
                </div>

                </select>
              </li>


              <li class="addmaininformation">
                <label for="name"> ��ǰ�� : </label>
                <div class="addin">
                  <input type="text" id="name" required>
                </div>
              </li>
              <li class="addmaininformation">
                <label for="price"> �ǸŰ� : </label>
                <div class="addin">
                  <input type="text" placeholder="���� = ��" id="price" required pattern="[0-9]+$">
                </div>
              </li>
              <li class="addmaininformation">
                <label for="deliveryfee"> ��ۺ� : </label>
                <div class="addin">
                  <input type="text" placeholder="���� = ��" id="deliveryfee" pattern="[0-9]+$" required>
                </div>
              </li>

              <li class="addmaininformation">
                <label for="op1"> �ɼ� : </label>
                <div class="addin"><input type="text" id="op1">
                  <input type="button" value="+" id="add4">
                </div>
              </li>
            </div>
            <div class="addbutton">
              <div class="addbutton2">
                <button type="submit" form="addform">���� ���� ���</button>
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
            <label for="dinfo"> ��ǰ �� ���� :</label>
            <textarea name="dinfo" form="adddetail" id="dinfo" cols="100" rows="10"></textarea>
          </article>
        </section>

        <section>

          <div>
            <div>
               <label for="detimg">�� ���� ���� ����</label>
            </div>
           
            <input type="file" accept="image/*" id="detimg" required>
          </div>
         
        </section>

        <button type="submit" form="adddetail"> �� ���� ���</button>
      </form>
    </div>






  </main>

  <hr style="margin-left: 10px; margin-right: 10px;">

  <Footer>
    <div class="Footer">
      <p><a href="">��������</a> | <a href="">�̿���</a> | <a href="">����������� ��ħ</a></p>
      <p>&copy;��ȣ�� (��) Daily Friday ��ǥ : 9��</p>
      <p>����� ��� ��ȣ : 916-14-56874 | �뱸 �߱� ���굿 00����</p>
      <p>��ǥ ��ȭ ��ȣ : 010-4568-5468</p>
      <p>email : dfteam9@naver.com</p>
    </div>
  </Footer>

  <script>

  </script>

</body>

</html>
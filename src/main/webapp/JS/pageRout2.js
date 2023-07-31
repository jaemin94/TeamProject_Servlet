/**
 * 
 */
	const baseURL = "http://localhost:8080/TeamProject2";
	let pagePath = null;
	let fullURL = null;
	let shoppingBtn = document.getElementById("shopping");
	const ed_btn = document.querySelector(".edit");
	const login_btn = document.getElementById("login-button");
	
const logo_btn = document.getElementById("logo");
logo_btn.addEventListener("click",function(){

	pagePath = "/JSP/Main2.jsp"
	fullURL = baseURL + pagePath;
	
	location.href = fullURL;
});

function handleLoginLogout() {
  const roleValueInput = document.getElementById("roleValue");
  const loginButton = document.getElementById("login-button");

  // 숨겨진 input 필드에서 현재 ROLE 값을 가져옵니다.
  

  if (role === 'Role_Member' || role === 'Role_user') {
    // 사용자가 로그인한 경우 로그아웃 처리를 위해 /Logout.do로 Ajax 요청을 보냅니다.
    axios.post('/TeamProject2/logout.do')
      .then(response => {
        if (response.status === 200) {
          // 세션 정보를 지웁니다.
          
          loginButton.textContent = 'login'; // 로그인 아이콘으로 변경합니다.
        }
      })
      .catch(error => {
        console.error('Logout error:', error);
      });
  } else {
    // 사용자가 로그인하지 않은 경우, 로그인 페이지로 이동하도록 구현하면 됩니다.
    // 예시로 주석 처리하였습니다.
    pagePath = "/member/auth/Login.jsp"
	fullURL = baseURL + pagePath;
	
	location.href = fullURL;
  }
}

document.addEventListener('DOMContentLoaded', () => {
  if (role === 'Role_Member' || role === 'Role_user') {
    login_btn.textContent = 'logout';
  } else {
    login_btn.textContent = 'login';
  }
  login_btn.addEventListener('click', handleLoginLogout);
});


  
/*  // 로그인 버튼 클릭 이벤트 핸들러
  login_btn.addEventListener("click", function() {
    // 로그인 버튼 클릭 시 로그인 페이지로 이동
	pagePath = "/member/auth/Login.jsp"
	fullURL = baseURL + pagePath;
	
	location.href = fullURL;
  });
  */

    if (role === "Role_Member") {
        shoppingBtn.addEventListener("click", function() {
			pagePath = "/order/ShoppingBasket_Admin.jsp"
			fullURL = baseURL + pagePath;
	
			location.href = fullURL;
        });
    } else if (role === "Role_user") {
        shoppingBtn.addEventListener("click", function() {
           			pagePath = "/order/ShoppingBasket_user2.jsp"
			fullURL = baseURL + pagePath;
	
			location.href = fullURL;
        });
    } else {
        shoppingBtn.addEventListener("click", function() {
            alert("잘못된 접근입니다.");
        });
    };

ed_btn.addEventListener("click",function(){
			pagePath = "/order/ShoppingBasket_Admin2.jsp"
			fullURL = baseURL + pagePath;
			location.href = fullURL;
							});
    
    
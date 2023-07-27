/**
 * 
 */
	const baseURL = "http://localhost:8080/TeamProject2";
	let pagePath = null;
	let fullURL = null;
	let shoppingBtn = document.getElementById("shopping");
	const ed_btn = document.querySelector(".edit");
	
const logo_btn = document.getElementById("logo");
logo_btn.addEventListener("click",function(){

	pagePath = "/JSP/Main.jsp"
	fullURL = baseURL + pagePath;
	
	location.href = fullURL;
});
  
  // 로그인 버튼 클릭 이벤트 핸들러
  document.getElementById("login-button").addEventListener("click", function() {
    // 로그인 버튼 클릭 시 로그인 페이지로 이동
	pagePath = "/member/auth/Login.jsp"
	fullURL = baseURL + pagePath;
	
	location.href = fullURL;
  });
  

    if (role === "Role_Member") {
        shoppingBtn.addEventListener("click", function() {
			pagePath = "/order/ShoppingBasket_Admin.jsp"
			fullURL = baseURL + pagePath;
	
			location.href = fullURL;
        });
    } else if (role === "Role_user") {
        shoppingBtn.addEventListener("click", function() {
           			pagePath = "/order/ShoppingBasket_user.jsp"
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
    
    
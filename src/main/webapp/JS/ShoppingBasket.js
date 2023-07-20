/**@charset "UTF-8";
 * 
 */
document.addEventListener("DOMContentLoaded", function() {
    let role = '<%= session.getAttribute("role") %>';
    let shoppingBtn = document.getElementById("shopping");

    // 역할에 따른 이벤트 처리
    if (role === "Role_Member") {
        shoppingBtn.addEventListener("click", function() {
            window.location.href = "./ShoppingBasket_Admin.jsp";
        });
    } else if (role === "Role_user") {
        shoppingBtn.addEventListener("click", function() {
            window.location.href = "./ShoppingBasket_user.jsp";
        });
    } else {
        shoppingBtn.addEventListener("click", function() {
            alert("잘못된 접근입니다.");
        });
    }
    });
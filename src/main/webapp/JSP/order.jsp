<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>상품 주문하기</h1>
    <form action="/Test4" method="post">
        <label for="member_id">회원 아이디:</label>
        <input type="text" id="member_id" name="member_id" required><br><br>

        <label for="product_code">제품 코드:</label>
        <input type="number" id="product_code" name="product_code" required><br><br>

        <label for="odr_amount">주문 수량:</label>
        <input type="number" id="odr_amount" name="odr_amount" required><br><br>

        <input type="submit" value="주문하기">
    </form>
</form>
</body>
</html>
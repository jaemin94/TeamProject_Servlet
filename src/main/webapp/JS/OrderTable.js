// 주문 정보를 선택할 때 해당 행에 selected 클래스를 추가하는 코드
const orderCheckboxes = document.querySelectorAll(".order-checkbox");
orderCheckboxes.forEach((checkbox) => {
  checkbox.addEventListener("click", function() {
    const orderRow = this.parentElement.parentElement;
    if (this.checked) {
      orderRow.classList.add("selected");
    } else {
      orderRow.classList.remove("selected");
    }
  });
});

// 주문 정보 행 추가 버튼 클릭 시 이벤트 처리
$('#plusbt').click(function () {
  var varTable = $(this).closest('.table');
  var varTableLastRow = varTable.find('#tablebodyh');
  var copyRow = varTableLastRow.clone(true);
  fncRowReset(copyRow);
  varTable.append(copyRow);
});

// 주문 정보 삭제 버튼 클릭 시 이벤트 처리
$('.table-remove').click(function () {
  $(this).parents('tr').detach();
});

// 주문 정보 행 이동 버튼 클릭 시 이벤트 처리
$('.table-up').click(function () {
  var $row = $(this).parents('tr');
  if ($row.index() === 1) return; // Don't go above the header
  $row.prev().before($row.get(0));
});

$('.table-down').click(function () {
  var $row = $(this).parents('tr');
  $row.next().after($row.get(0));
});

// 주문 정보를 JSON 형태로 변환하여 서버로 전송하는 코드
document.getElementById("edit_button").addEventListener("click", sendData);
function sendData() {
  // Collect data from the UI
  const selectedOrders = document.querySelectorAll("tbody tr.selected");
  const orderUpdateRequest = [];
  selectedOrders.forEach((order) => {
    // Extract data from the order table row
    const orderId = order.querySelector("[name='order_id']").textContent;
    const memberIdElement = order.querySelector("[name^='member_id']");
	const memberId = memberIdElement ? memberIdElement.textContent : "";
	const odrAmount = order.querySelector("[name^='odr_amount']").textContent;
	const odrPrice = order.querySelector("[name^='price']").textContent;

    // Validate data before constructing an object for each order and pushing it to the array
    if (orderId && memberId && !isNaN(parseInt(odrAmount)) && !isNaN(parseInt(odrPrice))) {
      const orderData = {
        "order_id": orderId,
        "member_id": memberId,
        "odr_amount": parseInt(odrAmount),
        "price":parseInt(odrPrice)
      };
      orderUpdateRequest.push(orderData);
    } else {
      // If any required field is missing or invalid, skip this order and log an error
      console.error("Invalid order data detected. Skipping order:", order);
    }
  });
  console.log(orderUpdateRequest); 
  // Send the JSON data to the server using AJAX only if there are valid orders
  if (orderUpdateRequest.length > 0) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/TeamProject2/JSP/Shopping_Basket_Admin/update", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
      if (xhr.readyState === XMLHttpRequest.DONE) {
        if (xhr.status === 200) {
          // Handle successful response from the server
          const response = JSON.parse(xhr.responseText);
          console.log(response);
        } else {
          // Handle error response from the server
          console.error("Request failed with status:", xhr.status);
        }
      }
    };
    xhr.send(JSON.stringify(orderUpdateRequest));
  } else {
    // If there are no valid orders, log an error or handle it appropriately
    console.error("No valid orders to send to the server.");
   
    console.log(orderUpdateRequest.length)
  }
}

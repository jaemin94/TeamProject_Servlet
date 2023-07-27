// 주문 정보를 선택할 때 해당 행에 selected 클래스를 추가하는 코드
function addCheckboxEventListeners() {
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
}

// 주문 정보를 서버로부터 가져와서 테이블에 표시하는 코드
function fetchAndDisplayOrderData() {
  fetch('/TeamProject2/order/search.do')
    .then((response) => {
      if (!response.ok) {
        throw new Error('주문 정보를 가져오는데 실패했습니다.');
      }
      return response.json();
    })
    .then((orderList) => {
      displayOrderList(orderList);
      addCheckboxEventListeners(); // 주문 정보를 테이블에 표시한 후 이벤트 리스너 등록
    })
    .catch((error) => {
      console.error(error);
      displayOrderList([]); // 에러 발생 시 빈 배열로 테이블에 표시
      addCheckboxEventListeners(); // 에러 발생 시 이벤트 리스너 등록
    });
}

// 주문 정보를 테이블에 표시하는 코드
function displayOrderList(orderList) {
  var tbody = document.getElementById('order-list-body');
  tbody.innerHTML = ''; // 이전에 테이블에 추가된 주문 정보가 있다면 모두 삭제

  orderList.forEach((order) => {
    var row = document.createElement('tr');

    // 주문 정보를 각 열에 추가
    row.innerHTML = `
      <td contenteditable="false"><input type="checkbox" class="order-checkbox" /></td>
      <td contenteditable="true" name="order_id">${order.order_id}</td>
      <td contenteditable="true" name="member_id">${order.member_id || ''}</td>
      <td contenteditable="true" name="product_code">${order.product_code || ''}</td>
      <td contenteditable="true" name="product_name">${order.product_name || ''}</td>
      <td contenteditable="true" name="adr_addr">${order.adr_addr || ''}</td>
      <td contenteditable="true" name="odr_amount">${order.odr_amount || ''}</td>
      <td contenteditable="true" name="odr_date">${order.odr_date ? new Date(order.odr_date).toLocaleString() : ''}</td>
      <td contenteditable="true" name="price">${order.price || ''}</td>
      <td>
        <span class="table-remove glyphicon glyphicon-remove" id="removebt"></span>
      </td>
    `;

    tbody.appendChild(row);
  });
}

// 주문 정보를 JSON 형태로 변환하여 서버로 전송하는 코드
function sendData() {
  // ... (기존 코드를 그대로 유지)
}

// 초기화 함수 - 이벤트 리스너 등록 및 주문 정보 가져오기
function initialize() {
  fetchAndDisplayOrderData();
}

// DOMContentLoaded 이벤트 발생 시 초기화 함수 호출
document.addEventListener('DOMContentLoaded', function () {
  initialize();
  document.getElementById("edit_button").addEventListener("click", sendData);
});
//----------------------------------------------------------------------------

function sendData() {
  // Collect data from the UI
  const selectedOrders = document.querySelectorAll("tbody tr.selected");
  const orderUpdateRequest = [];
  let isValidData = true;

  selectedOrders.forEach((order) => {
    // Extract data from the order table row
    const orderId = order.querySelector("[name='order_id']").textContent;
    const memberIdElement = order.querySelector("[name^='member_id']");
    const memberId = memberIdElement ? memberIdElement.textContent : "";
    const odrAmount = order.querySelector("[name^='odr_amount']").textContent;
    const odrPrice = order.querySelector("[name^='price']").textContent;

    // Validate data before constructing an object for each order and pushing it to the array
    if (!orderId || !memberId || isNaN(parseInt(odrAmount)) || isNaN(parseInt(odrPrice))) {
      // If any required field is missing or invalid, skip this order and log an error
      console.error("Invalid order data detected. Skipping order:", order);
      isValidData = false;
      return; // Exit the forEach loop immediately
    }

    const orderData = {
      "order_id": orderId,
      "member_id": memberId,
      "odr_amount": parseInt(odrAmount),
      "price": parseInt(odrPrice)
    };
    orderUpdateRequest.push(orderData);
  });

  if (!isValidData) {
    console.error("Please fill in all required fields with valid data.");
    return; // Do not send the request if there are invalid orders
  }

  console.log(orderUpdateRequest); 
  // Send the JSON data to the server using AJAX only if there are valid orders
  if (orderUpdateRequest.length > 0) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", '/TeamProject2/order/updateadmin.do', true);
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
  }
}

// DOMContentLoaded 이벤트 발생 시 초기화 함수 호출
document.addEventListener('DOMContentLoaded', initialize);
document.getElementById("edit_button").addEventListener("click", sendData);
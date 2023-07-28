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
         // 체크박스 클릭 시 선택된 주문 ID 출력 (테스트용)
      const selectedOrderIds = getSelectedOrderIds();
      console.log("Selected Order IDs:", selectedOrderIds);
    });
  });
}

// 주문 정보를 서버로부터 가져와서 테이블에 표시하는 코드
function fetchAndDisplayOrderData(orderId) {

 let url = '/TeamProject2/order/search.do';
  if (orderId) {
    // 주문번호가 입력되어 있다면 해당 주문번호에 해당하는 주문 정보만 가져오도록 URL 수정
    url += '?orderId=' + encodeURIComponent(orderId);
  }
  fetch(url)
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
	
	const dataArray = Array.isArray(orderList) ? orderList : [orderList];

  dataArray.forEach((order) => {
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
  addRemoveButtonEventListeners();
  
/*  // x 버튼을 클릭하면 해당 주문 정보 삭제 이벤트 등록
  const removeButtons = document.querySelectorAll(".table-remove");
  removeButtons.forEach((button) => {
    button.addEventListener("click", function() {
      const orderRow = this.parentElement.parentElement;
      orderRow.remove(); // 주문 정보 행 삭제
    });
  });*/
}

// 주문 정보를 JSON 형태로 변환하여 서버로 전송하는 코드
/*function sendData() {
  // ... (기존 코드를 그대로 유지)
}*/

// 초기화 함수 - 이벤트 리스너 등록 및 주문 정보 가져오기
function initialize() {
  fetchAndDisplayOrderData();

}

// DOMContentLoaded 이벤트 발생 시 초기화 함수 호출
document.addEventListener('DOMContentLoaded', function () {
  /*initialize();*/
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

          // Perform the next action after successful response from the server
          // For example, update the UI or fetch new data from the server
          fetchAndDisplayOrderData();
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
const edit_btn = document.getElementById("edit_button").addEventListener("click", sendData);


// ------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------
// 검색 버튼 클릭 이벤트 처리
document.querySelector(".search_btn").addEventListener("click", function() {
  const categorySelect = document.getElementById("c_select");
  const odrtypeInput = document.getElementById("odrtype");
  const selectedCategory = categorySelect.value;
  const orderId = odrtypeInput.value.trim(); // 주문번호 입력값

  // 주문번호가 입력되어 있다면 해당 주문 정보만 조회, 없다면 전체 주문 정보 조회
  fetchAndDisplayOrderData(selectedCategory === '주문 ID' ? orderId : null);
});

// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
// 주문 정보를 삭제하는 함수
function deleteOrders(selectedOrderIds) {
  if (!selectedOrderIds || selectedOrderIds.length === 0) {
    console.error("No valid order IDs to delete.");
    return;
  }

  const xhr1 = new XMLHttpRequest();
  xhr1.open("POST", "/TeamProject2/order/delete.do", true);
  xhr1.setRequestHeader("Content-Type", "application/json");
  xhr1.onreadystatechange = function() {
    if (xhr1.readyState === XMLHttpRequest.DONE) {
      if (xhr1.status === 200) {
        // Check if the response is valid JSON data
        try {
          const response1 = JSON.parse(xhr1.responseText);
          console.log(response1);
          // 삭제 성공시 주문 정보 다시 가져와서 테이블 갱신
          fetchAndDisplayOrderData();
        } catch (error) {
          console.error("Invalid JSON data received from the server.");
        }
      } else {
        // Handle error response from the server
        console.error("Request failed with status:", xhr1.status);
      }
    }
  };

  const data = { "order_id": selectedOrderIds };
  xhr1.send(JSON.stringify(data));
}


// 삭제 버튼에 대한 이벤트 리스너
function addRemoveButtonEventListeners() {
  const removeButtons = document.querySelectorAll(".table-remove");
  removeButtons.forEach((button) => {
    button.addEventListener("click", function() {
      const orderRow = this.closest("tr");
      const orderId = orderRow.querySelector("[name='order_id']").textContent;
      deleteOrders([orderId]); // 주문 정보 삭제 요청 보내기
    });
  });
}

// 선택한 항목에 대한 처리
function getSelectedOrderIds() {
  const selectedOrders1 = document.querySelectorAll("tbody tr.selected");
  const orderIds = Array.from(selectedOrders1).map((order) => {
    return order.querySelector("[name='order_id']").textContent;
  });
  return orderIds;
}



//-----------------------------------------------------

const search_btn_el = document.querySelector(".search_btn");

search_btn_el.addEventListener("click", function () {
  const projectPath = '/TeamProject2';
  const ServerPort = '8080';
  console.log("search_btn_el click..", projectPath);

  const tableBody = document.querySelector('.table-body'); // 테이블의 tbody 요소를 가져옵니다.
  console.log(tableBody);

  // 입력한 주문 ID 값을 가져옴
  const orderIdInput = document.getElementById("odrtype");
  const orderId = orderIdInput.value.trim(); // 입력값 앞뒤 공백 제거
  console.log(orderId);

  const url = "http://localhost:" + ServerPort + projectPath + "/order/search.do" +
    (orderId !== "" ? "?orderId=" + orderId : ""); // orderId가 비어있지 않으면 쿼리 파라미터에 추가

  axios.get(url)
    .then(response => {
      console.log(response.data);
      const list = response.data;
      console.log("list : ", list);

      // 기존 테이블 데이터를 지우고 조회된 데이터만 출력
      tableBody.innerHTML = '';
      insertDataIntoTable(list); // 테이블에 데이터를 삽입하는 함수 호출
    })
    .catch((error) => {
      console.log('fail..', error);
    });
});

//-----------------------
function insertDataIntoTable(orderData) {
  const tableBody = document.querySelector('.table-body');
  const row = document.createElement('tr');

  // 주문 정보를 각 셀에 추가
  row.innerHTML = `
    <td contenteditable="false"><input type="checkbox" class="order-checkbox" /></td>
    <td contenteditable="true" name="order_id">${orderData.order_id}</td>
    <td contenteditable="true" name="member_id">${orderData.member_id || ''}</td>
    <td contenteditable="true" name="product_code">${orderData.product_code || ''}</td>
    <td contenteditable="true" name="product_name">${orderData.product_name || ''}</td>
    <td contenteditable="true" name="adr_addr">${orderData.adr_addr || ''}</td>
    <td contenteditable="true" name="odr_amount">${orderData.odr_amount || ''}</td>
    <td contenteditable="true" name="odr_date">${orderData.odr_date ? new Date(orderData.odr_date).toLocaleString() : ''}</td>
    <td contenteditable="true" name="price">${orderData.price || ''}</td>
    <td></td>
  `;

  const removeButton = document.createElement('span');
  removeButton.id = "removebt";
  removeButton.className = "table-remove glyphicon glyphicon-remove";

  // 삭제 버튼을 클릭했을 때 처리 로직
  removeButton.addEventListener('click', () => handleRemoveButtonClick(orderData.order_id));

  // 삭제 버튼 셀에 삭제 버튼 추가
  row.querySelector('td:last-child').appendChild(removeButton);

  tableBody.appendChild(row);

  // 새로 삽입된 주문 정보에 대해 이벤트 리스너 추가
  addCheckboxEventListeners();
}




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
      <td></td>
    `;
    const removeButton = document.createElement('span'); // Create a new <span> element to be used as the remove button.
    removeButton.id = "removebt"; // Set the id of the remove button to "removebt".
    removeButton.className = "table-remove glyphicon glyphicon-remove"; // Add CSS classes to the remove button for styling purposes.

    // Add a click event listener to the remove button. When the button is clicked, the handleRemoveButtonClick function is called,
    // and the order.order_id is passed as an argument.
    removeButton.addEventListener('click', () => handleRemoveButtonClick(order.order_id));

    // Append the remove button (span element) to the last cell (td element) of the row.
    row.querySelector('td:last-child').appendChild(removeButton);

    tbody.appendChild(row);
  });

  // Add event listeners to the newly displayed checkboxes
  addCheckboxEventListeners();
}


// 주문 정보를 서버로부터 가져와서 테이블에 표시하는 코드
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
    })
    .catch((error) => {
      console.error(error);
      displayOrderList([]); // 에러 발생 시 빈 배열로 테이블에 표시
    });
}


// 주문 정보를 테이블에 표시하는 코드


// 초기화 함수 - 이벤트 리스너 등록 및 주문 정보 가져오기
function initialize() {
  fetchAndDisplayOrderData();
}

// DOMContentLoaded 이벤트 발생 시 초기화 함수 호출
document.addEventListener('DOMContentLoaded', function () {
  initialize();
  document.getElementById("edit_button").addEventListener("click", sendData);
});

// DOMContentLoaded 이벤트 발생 시 초기화 함수 호출

//---------------------------------------

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


// 주문 삭제 요청 보내는 함수 수정
function deleteOrders(orderIds) {
  // Send the JSON data to the server using AJAX only if there are valid orders
  if (orderIds.length > 0) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/TeamProject2/order/delete.do", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
      if (xhr.readyState === XMLHttpRequest.DONE) {
        if (xhr.status === 200) {
          // Handle successful response from the server
          const response = JSON.parse(xhr.responseText);
          if (response.success) {
            console.log("주문이 성공적으로 삭제되었습니다.");
            // 삭제 성공 후에는 테이블을 다시 불러와서 최신 주문 정보를 표시
            fetchAndDisplayOrderData();
          } else {
            console.error("주문 삭제에 실패했습니다. 서버가 다음과 같은 응답을 보냈습니다:", response);
          }
        } else {
          // Handle error response from the server
          console.error("요청이 실패하였습니다. 서버 응답 상태:", xhr.status);
        }
      }
    };

    const orderData = orderIds.map((orderId) => ({ "order_id": orderId }));
    xhr.send(JSON.stringify(orderData));
  } else {
    // If there are no valid orders, log an error or handle it appropriately
    console.error("삭제할 주문이 없습니다.");
  }
}

// 삭제 버튼 클릭 시 deleteOrders 함수 호출
function handleRemoveButtonClick() {
  const selectedOrders = document.querySelectorAll("tbody tr.selected");
  if (selectedOrders.length === 0) {
    console.log("삭제할 주문을 선택해d주세요.");
    return;
  }

  if (confirm("선택한 주문을 모두 삭제하시ddd겠습니까?")) {
    // 선택된 주문 정보가 있는 경우에만 삭제 요청 보냄
    const orderIdsToDelete = [];
    selectedOrders.forEach((order) => {
      const orderId = order.querySelector("[name='order_id']").textContent;
      orderIdsToDelete.push(orderId);
    });
    deleteOrders(orderIdsToDelete);
  }
}

//-----------------------------------------

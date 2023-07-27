
					
					 const search_btn_el = document.querySelector(".search_btn");
					 
					 function insertDataIntoTable(data) {
					      const tableBody = document.querySelector('.table-body');
					      tableBody.innerHTML = ''; // 기존 테이블 데이터를 지우기 위해 비웁니다.
						  const dataArray = Array.isArray(data) ? data : [data];

					      dataArray.forEach((order) => {
					        const row = tableBody.insertRow(); // 새로운 행 요소 생성

					        // 각 데이터에 해당하는 셀을 생성하고 데이터를 추가합니다.
					        const orderIdCell = row.insertCell();
					        orderIdCell.textContent = order.order_id;

					        const memberIdCell = row.insertCell();
					        memberIdCell.textContent = order.member_id;

					        // 이하 각 셀에 대해 위와 같이 추가해줍니다.
					        const productCodeCell = row.insertCell();
					        productCodeCell.textContent = order.product_code;

					        const productNameCell = row.insertCell();
					        productNameCell.textContent = order.product_name;

					        const addressCell = row.insertCell();
					        addressCell.textContent = order.adr_addr;

					        const orderAmountCell = row.insertCell();
					        orderAmountCell.textContent = order.odr_amount;

					        const orderDateCell = row.insertCell();
					        orderDateCell.textContent = order.odr_date;

					        const priceCell = row.insertCell();
					        priceCell.textContent = order.price;

					        // 삭제 버튼 셀을 생성합니다.
					        const deleteCell = row.insertCell();
					        deleteCell.innerHTML = '<span class="table-remove glyphicon glyphicon-remove" id="removebt"></span>';
					      });
					    }
					 
						search_btn_el.addEventListener("click",function(){
							const projectPath = '/TeamProject2';
							const ServerPort = '8080';
							console.log("search_btn_el click..",projectPath);
							
							const tableBody = document.querySelector('.table-body'); // 테이블의 tbody 요소를 가져옵니다.
							console.log(tableBody);
							tableBody.innerHTML = ' '; // 기존 테이블 데이터를 지우기 위해 비웁니다. 
					    	
					    	  // 입력한 주문 ID 값을 가져옴
  							const orderIdInput = document.getElementById("odrtype");
  							const orderId = orderIdInput.value.trim(); // 입력값 앞뒤 공백 제거
  							console.log(orderId);
					    	
					    	const url = "http://localhost:" + ServerPort + projectPath + "/order/search.do" +
              							(orderId !== "" ? "?orderId=" + orderId : ""); // orderId가 비어있지 않으면 쿼리 파라미터에 추가

							axios.get(url)
							.then(response=>{alert("Success!",response.data);
								console.log(response.data);
								const list = response.data;
								console.log("list : " + list);
	
								insertDataIntoTable(list); // 테이블에 데이터를 삽입하는 함수 호출
					        })
					        .catch((error) => {
					          console.log('fail..', error);
					        });
					    });

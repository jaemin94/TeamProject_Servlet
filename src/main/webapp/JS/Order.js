/**
 * 
 */

 const search_btn_el = document.querySelector(".search_btn");
	
	search_btn_el.addEventListener("click",function(){
		const projectPath='%{pageContext.request.contextPath}';
		const ServerPort = '%{pageContext.request.serverPort}';
		console.log("search_btn_el click..",projectPath);
		
		const tableBody = document.querySelector('#tablebody'); // 테이블의 tbody 요소를 가져옵니다.
    	tableBody.innerHTML = ''; // 기존 테이블 데이터를 지우기 위해 비웁니다.
    	
    	
		axios.get("http://localhost:"+ ServerPort+projectPath+"/order/search.do")
		.then(response=>{alert("Success!",response.data);
			const list = response.data;
			list.forEach((bookdto)=>{ 
          // 주문 정보를 테이블에 추가합니다.
          tableBody.innerHTML += `
            <tr>
              <td>${order.order_id}</td>
              <td>${order.member_id}</td>
              <td>${order.product_code}</td>
              <td>${order.product_name}</td>
              <td>${order.adr_addr}</td>
              <td>${order.odr_amount}</td>
              <td>${order.odr_date}</td>
              <td>${order.price}</td>
              <td>
                <span class="table-remove glyphicon glyphicon-remove" id="removebt"></span>
              </td>
            </tr>
          `;
        })
      })
      .catch(error => {
        console.log("fail..");
      })});  // 실패시 처리 로직
 
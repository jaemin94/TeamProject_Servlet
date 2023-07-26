package Order;



import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import Controller.SubController;
import Domain.Common.Dto.OrderDto;
import Domain.Common.Service.OrderService;
import Domain.Common.Service.OrderServiceImpl;


public class Order_Select_Admin implements SubController {
	
	private OrderService service = OrderServiceImpl.getInstance();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
	     
		// 1 파라미터 추출
		
		// 2입력값 검증
		
		// 3 서비스 실행
		
		List<OrderDto> orderList = null;
		
		try {
			 orderList = service.getOrder();
			// JAVA -> JSON 변환
				ObjectMapper objectMapper = new ObjectMapper();
		        String jsonConverted = objectMapper.writeValueAsString(orderList);
				System.out.println("jsonConverted: " + jsonConverted);
				// 4 View로 전달			
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("application/json");
				PrintWriter out = resp.getWriter();
				out.print(jsonConverted);
				
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		
		
	} 
	



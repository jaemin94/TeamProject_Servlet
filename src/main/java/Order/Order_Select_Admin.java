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
	     
		List<OrderDto> orderList = null;
		
		
		  try {
			  String orderId = req.getParameter("orderId");
			  
		    if (orderId != null && !orderId.isEmpty()) {
		      // 클라이언트가 주문 ID를 전달한 경우 단건 조회
		      OrderDto order = service.getOrder(orderId);
		      ObjectMapper objectMapper = new ObjectMapper();
		      String jsonConverted = objectMapper.writeValueAsString(order);
		      System.out.println("jsonConverted: " + jsonConverted);
		      resp.setCharacterEncoding("UTF-8");
		      resp.setContentType("application/json");
		      PrintWriter out = resp.getWriter();
		      out.print(jsonConverted);
		    } else {
		      // 클라이언트가 주문 ID를 전달하지 않은 경우 전체 목록 조회
		      orderList = service.getOrder();
		      ObjectMapper objectMapper = new ObjectMapper();
		      String jsonConverted = objectMapper.writeValueAsString(orderList);
		      System.out.println("jsonConverted: " + jsonConverted);
		      resp.setCharacterEncoding("UTF-8");
		      resp.setContentType("application/json");
		      PrintWriter out = resp.getWriter();
		      out.print(jsonConverted);
		    }
		  } catch (Exception e) {
		    e.printStackTrace();
		  }
		}
	
		
		
	} 
	



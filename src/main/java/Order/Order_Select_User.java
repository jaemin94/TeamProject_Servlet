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


public class Order_Select_User implements SubController {
    private static final long serialVersionUID = 1L;
    private OrderService service = OrderServiceImpl.getInstance();

    @Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
        
        List<OrderDto> orderList = null;
        try {
            // 주문 정보 조회
            orderList = service.getOrder(request);
            System.out.println("orderList : " + orderList);
       
         // JAVA -> JSON 변환
        	ObjectMapper objectMapper = new ObjectMapper();
        	String jsonConverted = objectMapper.writeValueAsString(orderList);
         	        
        // JSP로 주문 정보 리스트 전달
        if (orderList != null && !orderList.isEmpty()) {
        	response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(jsonConverted);
        } else {
            // 주문 정보가 없을 경우 빈 리스트를 JSP로 전달
        	request.setAttribute("orderList", new ArrayList<OrderDto>());
        }
        System.out.println("Order List Size: " + orderList.size());
        // JSP로 포워딩
//        req.getRequestDispatcher("/order/ShoppingBasket_user.jsp").forward(req, resp);
    } catch (Exception e) {
        e.printStackTrace();
        // 에러 처리 로직 작성 (예: 오류 페이지로 리다이렉트)
    }
		
	}
}
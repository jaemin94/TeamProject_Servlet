package Order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Domain.Common.Dao.OrderDao;
import Domain.Common.Dao.OrderDaoimpl;
import Domain.Common.Dto.OrderDto;
import Domain.Common.Service.OrderService;
import Domain.Common.Service.OrderServiceImpl;

@WebServlet("/Shopping_Admin2")
public class Order_Select_Admin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderService service;
    private OrderDao dao;

    public Order_Select_Admin() {
    	service = OrderServiceImpl.getInstance();
    	dao = OrderDaoimpl.getInstance();
    }
    
   

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // OrderController 생성
    	
    	
//    	List<OrderDto> list = new ArrayList();
//		OrderDto dto = null;

    	List<OrderDto> orderList =null;
        try {
            // 주문 정보 조회
            orderList = service.getOrder();
        } catch (Exception e) {
            e.printStackTrace();
            // 에러 처리 로직 작성 (예: 오류 페이지로 리다이렉트)
        }
        System.out.println(orderList);
            // JSP로 주문 정보 리스트 전달
            request.setAttribute("orderList", orderList);

            // JSP로 포워딩
            request.getRequestDispatcher("/JSP/ShoppingBasket_Admin2.jsp").forward(request, response);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
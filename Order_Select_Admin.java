package Order;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Domain.Common.Dto.OrderDto;
import Domain.Common.Service.OrderService;
import Domain.Common.Service.OrderServiceImpl;

@WebServlet("/JSP/Shopping_Basket_Admin")
public class Order_Select_Admin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderService service;

    public Order_Select_Admin() {
        // 생성자에서는 필드 초기화만 하도록 변경
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service = OrderServiceImpl.getInstance(); // doGet 메소드에서 필드 초기화

        List<OrderDto> orderList = null;
        try {
            // 주문 정보 조회
            orderList = service.getOrder();
        } catch (Exception e) {
            e.printStackTrace();
            // 에러 처리 로직 작성 (예: 오류 페이지로 리다이렉트)
        }
        // JSP로 주문 정보 리스트 전달
        if (orderList != null && !orderList.isEmpty()) {
            request.setAttribute("orderList", orderList);
        } else {
            // 주문 정보가 없을 경우 빈 리스트를 JSP로 전달
            request.setAttribute("orderList", new ArrayList<OrderDto>());
        }
        System.out.println("Order List Size: " + orderList.size());
        // JSP로 포워딩
        request.getRequestDispatcher("/JSP/ShoppingBasket_Admin2.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

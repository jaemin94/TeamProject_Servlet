package ServerTest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Domain.Common.Dto.MemberDto;
import Domain.Common.Service.MemberService;
import Domain.Common.Service.MemberServiceImpl;
import Domain.Common.Service.OrderService;
import Domain.Common.Service.OrderServiceImpl;

/**
 * Servlet implementation class Test2
 */
@WebServlet("/Test4")
public class Test4 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderService service;
    private MemberService service2;

    public Test4() {
        service = OrderServiceImpl.getInstance();
        service2 =  MemberServiceImpl.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	MemberDto orderc = new MemberDto();
    	
        String memberId = request.getParameter("member_id");
        String productCodeStr = request.getParameter("product_code");
        String orderAmountStr = request.getParameter("odr_amount");

        if (memberId == null || memberId.isEmpty() || productCodeStr == null || productCodeStr.isEmpty() || orderAmountStr == null || orderAmountStr.isEmpty()) {
            request.setAttribute("message", "모든 필드를 입력해 주세요.");
            request.getRequestDispatcher("/JSP/failure.jsp").forward(request, response);
            return;
        }
        
        

        try {
            int productCode = Integer.parseInt(productCodeStr);
            int orderAmount = Integer.parseInt(orderAmountStr);

            OrderServiceImpl orderService = new OrderServiceImpl();
            boolean result = false;
            try {
                result = service.reqOrder("Role_user", memberId, productCode, orderAmount);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (result) {
                request.setAttribute("message", "주문이 성공적으로 완료되었습니다.");
                request.getRequestDispatcher("/JSP/success.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "주문에 실패하였습니다.");
                request.getRequestDispatcher("/JSP/failure.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "올바른 숫자 값을 입력해 주세요.");
            request.getRequestDispatcher("/JSP/failure.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
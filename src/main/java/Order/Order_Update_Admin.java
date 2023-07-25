package Order;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.OrderController;

@WebServlet("/JSP/Shopping_Basket_Admin/update")
public class Order_Update_Admin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // GET 요청에 대해 특별한 처리가 필요 없으므로 아무 작업도 하지 않음.
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderController controller = new OrderController();

        // Get the array of selectedOrderIds from the request
        String[] selectedOrderIds = request.getParameterValues("selectedOrderIds");
        String member_id = request.getParameter("member_id");
        String odrAmountParam = request.getParameter("odr_amount");
        int odr_amount = 0;
        String role = "Role_Member"; // Set the role here as needed

        // Parse odr_amount parameter if it is not null or empty
        if (odrAmountParam != null && !odrAmountParam.isEmpty()) {
            try {
                odr_amount = Integer.parseInt(odrAmountParam);
            } catch (NumberFormatException e) {
                // 숫자로 변환할 수 없는 경우에 대한 예외 처리
                e.printStackTrace();
            }
        }
        
     // Check the received parameters
        System.out.println("Selected Order Ids:");
        for (String orderId : selectedOrderIds) {
            System.out.println(orderId);
        }

        System.out.println("Member ID: " + member_id);
        System.out.println("Order Amount: " + odr_amount);
        System.out.println("Role: " + role);


        // 여러 개의 주문을 수정할 수 있으므로 반복문을 통해 모든 주문을 처리
        for (String orderId : selectedOrderIds) {
            // Populate the param map
            Map<String, Object> param = new HashMap<>();
            param.put("order_id", orderId);
            param.put("member_id", member_id);
            param.put("odr_amount", odr_amount);
            param.put("price", 0);
            param.put("role", role);

            // Call the execute method
            Map<String, Object> result = controller.execute(4, param);
            // 처리 결과를 확인하거나 필요한 로직을 추가하여 사용
            if (result != null) {
                boolean isUpdated = (boolean) result.get("result");
                if (isUpdated) {
                    System.out.println("[INFO] 주문 수정 완료!");
                }
                System.out.println("role : " + role);
            } else {
            	System.out.println("[INFO] 주문 수정 실패!");
            }

        }

        // 뷰 페이지로 이동
        request.getRequestDispatcher("/JSP/ShoppingBasket_Admin2.jsp").forward(request, response);
    }
}

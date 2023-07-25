package Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.OrderController;
import Domain.Common.Dao.OrderDao;
import Domain.Common.Dao.OrderDaoimpl;
import Domain.Common.Dto.OrderDto;
import Domain.Common.Service.OrderService;
import Domain.Common.Service.OrderServiceImpl;

@WebServlet("/JSP/Shopping_Basket_Admin/*")
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
        List<OrderDto> orderList = null;
        try {
            // 주문 정보 조회
            orderList = service.getOrder();
        } catch (Exception e) {
            e.printStackTrace();
            // 에러 처리 로직 작성 (예: 오류 페이지로 리다이렉트)
        }
        // JSP로 주문 정보 리스트 전달
        request.setAttribute("orderList", orderList);
        System.out.println("Order List Size: " + orderList.size());
        // JSP로 포워딩
        request.getRequestDispatcher("/JSP/ShoppingBasket_Admin2.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 요청의 인코딩을 UTF-8로 설정
        response.setCharacterEncoding("UTF-8"); // 응답의 인코딩을 UTF-8로 설정
        String role = "Role_Member";
        // Get the requested URL to determine the action
        String requestedURL = request.getRequestURI().substring(request.getContextPath().length());

        if (requestedURL.equals("/JSP/Shopping_Basket_Admin/update")) {
            // Update order data
            List<OrderDto> orderUpdateRequest = new ArrayList<>();

            // Read JSON data from the request
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder jsonStr = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonStr.append(line);
            }
            br.close();

            // Parse JSON data into a list of OrderDto objects
            try {
                JsonReader jsonReader = Json.createReader(new StringReader(jsonStr.toString()));
                javax.json.JsonArray jsonArray = jsonReader.readArray();
                for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
                    OrderDto orderDto = new OrderDto();
                    orderDto.setOrder_id(jsonObject.getString("order_id"));
                    orderDto.setMember_id(jsonObject.getString("member_id"));
                    orderDto.setOdr_amount(jsonObject.getInt("odr_amount"));
                    orderDto.setPrice(jsonObject.getInt("price"));
                    orderUpdateRequest.add(orderDto);
                }
            } catch (javax.json.JsonException | NullPointerException e) {
                // Handle JSON parsing error
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            // Create a Map to pass the data to the OrderController
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("order_id", orderUpdateRequest.get(0).getOrder_id());
            paramMap.put("member_id", orderUpdateRequest.get(0).getMember_id());
            paramMap.put("odr_amount", orderUpdateRequest.get(0).getOdr_amount());
            paramMap.put("price", orderUpdateRequest.get(0).getPrice());
            paramMap.put("role",role);
            // orderUpdateRequest 객체를 이용하여 주문 정보를 처리하는 로직 추가
            OrderController controller = new OrderController();
            Map<String, Object> result = controller.execute(4, paramMap);

            // Convert the result map to JSON format manually
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            for (Map.Entry<String, Object> entry : result.entrySet()) {
                jsonObjectBuilder.add(entry.getKey(), entry.getValue().toString());
            }
            JsonObject jsonResult = jsonObjectBuilder.build();

            // 처리 결과를 JSON 형태로 응답
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonResult.toString());
            out.flush();
        }
    }
}

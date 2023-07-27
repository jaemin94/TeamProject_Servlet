package Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.OrderController;
import Controller.SubController;
import Domain.Common.Dto.OrderDto;
import Domain.Common.Service.OrderService;
import Domain.Common.Service.OrderServiceImpl;

public class Order_Update_Admin implements SubController {

    private OrderService service = OrderServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 요청의 인코딩을 UTF-8로 설정
        response.setCharacterEncoding("UTF-8"); // 응답의 인코딩을 UTF-8로 설정
        HttpSession session = request.getSession();
        String role = (String)session.getAttribute("ROLE");
        String requestedURL = request.getRequestURI().substring(request.getContextPath().length());

        if (requestedURL.equals("/order/updateadmin.do")) {
            // Update order data
            List<OrderDto> orderUpdateRequest = new ArrayList<>();

            // Read JSON data from the request
            BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            StringBuilder jsonStr = new StringBuilder();
            String line;
            try {
				while ((line = br.readLine()) != null) {
				    jsonStr.append(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
            PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            out.print(jsonResult.toString());
            out.flush();
        }
    }
}

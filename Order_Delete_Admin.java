package Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Service.OrderService;
import Domain.Common.Service.OrderServiceImpl;

public class Order_Delete_Admin implements SubController {
    private OrderService service = OrderServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            String requestedURL = request.getRequestURI().substring(request.getContextPath().length());

         // ... (이전 코드와 동일)

            if (requestedURL.equals("/order/delete.do")) {
                // 클라이언트에서 보낸 JSON 데이터를 문자열로 파싱하는 부분 수정
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
                    StringBuilder jsonStr = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        jsonStr.append(line);
                    }
                    br.close();

                    // 클라이언트에서 전달한 JSON 데이터를 JSON 배열로 읽어옴
                    JsonReader jsonReader = Json.createReader(new StringReader(jsonStr.toString()));
                    javax.json.JsonArray jsonArray = jsonReader.readArray();

                    List<String> orderIds = new ArrayList<>();
                    for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
                        String orderId = jsonObject.getString("order_id");
                        orderIds.add(orderId);
                    }

                    // 리스트를 service의 removeOrder 메서드로 전달
                    boolean success = service.removeOrder(orderIds);

                    // 처리 결과를 JSON 형태로 응답
                    JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
                    jsonObjectBuilder.add("success", success);
                    JsonObject jsonResult = jsonObjectBuilder.build();

                    // 응답 전송
                    response.setContentType("application/json");
                    PrintWriter out = response.getWriter();
                    out.print(jsonResult.toString());
                    out.flush();
                    out.close();
                } catch (IOException | javax.json.JsonException e) {
                    // Handle JSON parsing error
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    e.printStackTrace();
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
}

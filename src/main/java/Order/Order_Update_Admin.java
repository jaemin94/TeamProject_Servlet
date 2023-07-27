package Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Controller.SubController;
import Domain.Common.Dto.OrderDto;
import Domain.Common.Service.OrderService;
import Domain.Common.Service.OrderServiceImpl;

public class Order_Update_Admin implements SubController {
 
	 private OrderService service = OrderServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // ...
    	   String requestedURL = request.getRequestURI().substring(request.getContextPath().length());
           
           if (requestedURL.equals("/order/updateadmin.do")) {
            // Read JSON data from the request
            StringBuilder jsonStr = new StringBuilder();
            try {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
                while ((line = br.readLine()) != null) {
                    jsonStr.append(line);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Parse JSON data into a list of OrderDto objects
            List<OrderDto> orderUpdateRequest = new ArrayList<>();
            try {
                JSONArray jsonArray = new JSONArray(jsonStr.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    OrderDto orderDto = new OrderDto();
                    orderDto.setOrder_id(jsonObject.getString("order_id"));
                    orderDto.setMember_id(jsonObject.getString("member_id"));
                    orderDto.setOdr_amount(jsonObject.getInt("odr_amount"));
                    orderDto.setPrice(jsonObject.getInt("price"));
                    orderUpdateRequest.add(orderDto);
                }
            } catch (JSONException e) {
                // Handle JSON parsing error
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            // Update orders using orderUpdateRequest list
            for (OrderDto order : orderUpdateRequest) {
            	
            	HttpServletRequest req = (HttpServletRequest)request;
        		HttpSession session = req.getSession();
        		String role = (String)session.getAttribute("ROLE");
            	System.out.println(role);
                boolean updated = service.updateOrder(order, role);
                // Handle update error
                if (!updated) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    return;
                }
            }

            // Respond with success status
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
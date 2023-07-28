package Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import Controller.SubController;
import Domain.Common.Service.OrderService;
import Domain.Common.Service.OrderServiceImpl;

/**
 * Servlet implementation class Order_Delete_Admin
 */

public class Order_Delete_Admin implements SubController {
	private OrderService service= OrderServiceImpl.getInstance();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("Order_Delete_Admin Execute");
		
		// JSON 데이터 파싱
        try {
            BufferedReader reader = req.getReader();
            StringBuilder jsonData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
            System.out.println("Received JSON data: " + jsonData); // JSON 데이터 출력

            // JSON 객체로 파싱
            JSONObject jsonObject = new JSONObject(jsonData.toString());

            // JSON 객체에서 원하는 값을 추출
            String orderId = jsonObject.optString("order_id");
            
            // 만약 key가 "order_id"가 아니라 다른 이름으로 저장되어 있다면 해당 key를 사용

            // 여기서부터 원하는 로직 수행
            if (orderId == null || orderId.isEmpty()) {
                System.out.println("[오류] 데이터 유효성 검사 오류");
                req.setAttribute("msg", "[오류] 삭제할 수 없는 정보입니다.");
                return;
            }
            
            req.setAttribute("order_id", orderId);

            // 2 입력값 검증 및 서비스 실행
            boolean isDeleted = service.removeOrder(req);

            // 3 view로 전달
            resp.setContentType("application/json");
            try (PrintWriter out = resp.getWriter()) {
                if (isDeleted) {
                    // 삭제 성공
                    out.println("{\"success\": true, \"message\": \"주문 정보가 삭제되었습니다.\"}");
                } else {
                    // 삭제 실패
                    out.println("{\"success\": false, \"message\": \"주문 정보 삭제에 실패했습니다.\"}");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
    }
		
	
       




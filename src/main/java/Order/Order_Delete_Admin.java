package Order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		// 1 파라미터 추출
		String order_id = (String)req.getParameter("order_id");
		
		// 2 입력값 검증
		if(order_id==null ) {
			System.out.println("[ERROR]Data Validation Check Error");
			req.setAttribute("msg", "[ERROR] 삭제할수 없는 정보입니다.");
			return ;
		}
		// 3 서비스 실행
		boolean isDeleted = false;
		
		isDeleted = service.removeOrder(req);
		
		
		// 4 view로 전달
		if (isDeleted) {
			// 삭제 성공
			req.setAttribute("msg", "주문 정보가 삭제되었습니다.");
		} else {
			// 삭제 실패
			req.setAttribute("msg", "주문 정보 삭제에 실패했습니다.");
		}
		
	}
       


}

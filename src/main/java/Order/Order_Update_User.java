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
 * Servlet implementation class Order_Update_User
 */

public class Order_Update_User implements SubController {
	private OrderService service= OrderServiceImpl.getInstance();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("Order_Update_User Execute");
		
	}
       


}

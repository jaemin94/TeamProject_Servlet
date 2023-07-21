package ServerTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.OrderController;
import Domain.Common.Service.OrderService;
import Domain.Common.Service.OrderServiceImpl;

/**
 * Servlet implementation class Test2
 */
@WebServlet("/Test2")
public class Order_select extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrderService service;
	
	public Order_select() {
		service = OrderServiceImpl.getInstance();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html; charset=UTF-8");
		OrderController a = new OrderController();
	
		System.out.println("0");
		
		
		
		
		
		Map<String,Object>result = new HashMap();
		result = a.execute(1, result);
		System.out.println(result);
		
		request.setAttribute("result", result);
	    RequestDispatcher rd = request.getRequestDispatcher("/JSP/ShoppingBasket_user.jsp");
	    rd.forward(request, response);
	       
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

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
@WebServlet("/Test3")
public class Test3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

    
	private OrderService service;
	
	public Test3() {
		service = OrderServiceImpl.getInstance();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html; charset=UTF-8");
		OrderController a = new OrderController();
	
		System.out.println("0");
		
		
		
		
		
		Map<String,Object>result = new HashMap();
		result.put("order_id", "201");
		result.put("sid", "123");
		result = a.execute(2, result);
		
		System.out.println(result);
		
		request.setAttribute("result", result);
	    RequestDispatcher rd = request.getRequestDispatcher("/JSP/ShoppingBasket.jsp");
	    rd.forward(request, response);
	       
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

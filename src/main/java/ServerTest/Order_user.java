package ServerTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.FrontController;
import Controller.OrderController;

/**
 * Servlet implementation class Order
 */
@WebServlet("/Order")
public class Order_user extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderController controller;
	
	public void init() throws ServletException {
        // 컨트롤러 인스턴스 생성
        controller = new OrderController();
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Order_user() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int serviceNo= Integer.parseInt(request.getParameter("serviceNo"));
		Map<String,Object> param = new HashMap<>();
		
		
		controller.execute(serviceNo, param);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

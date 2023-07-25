package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Member.Member_Delete;
import Member.Member_Select_Admin;
import Member.Member_Select_User;
import Member.Member_Update_Admin;
import Member.Member_Update_User;
import Member.auth.LoginController;
import Member.auth.LogoutController;
import Order.Order_Delete_Admin;
import Order.Order_Insert_User;
import Order.Order_Select_Admin2;
import Order.Order_Update_User;
import Product.Prod_Delete_Admin;
import Product.Prod_Insert_Admin;
import Product.Prod_Select_Admin;
import Product.Prod_Select_User;
import Product.Prod_Update_Admin;

public class FrontController extends HttpServlet{

	
	private Map<String,SubController> map = new HashMap();
	
	

	@Override
	public void init(ServletConfig config) throws ServletException {

		String projectPath = config.getServletContext().getContextPath();
		
		// order
		map.put(projectPath+"/order/search.do", new Order_Select_Admin2() );
		map.put(projectPath+"/order/delete.do", new Order_Delete_Admin() );
		map.put(projectPath+"/order/insert.do", new Order_Insert_User() );
		map.put(projectPath+"/order/update.do", new Order_Update_User() );
		
		// member
		map.put(projectPath+"/Member/search.do", new Member_Select_Admin() );
		map.put(projectPath+"/Member/search1.do", new Member_Select_User() );
		map.put(projectPath+"/Member/update.do", new Member_Update_Admin() );		
		map.put(projectPath+"/Member/update.do", new Member_Update_User() );
		map.put(projectPath+"/Member/delete.do", new Member_Delete() );
		
		// auth
		map.put(projectPath+"/login.do", new LoginController() );					// 완료
		map.put(projectPath+"/logout.do", new LogoutController() );
		
		// product
		map.put(projectPath+"/Product/search.do", new Prod_Select_Admin() );
		map.put(projectPath+"/Product/search.do", new Prod_Select_User() );
		map.put(projectPath+"/Product/search.do", new Prod_Insert_Admin() );
		map.put(projectPath+"/Product/search.do", new Prod_Update_Admin() );
		map.put(projectPath+"/Product/search.do", new Prod_Delete_Admin() );
		
		map.put(projectPath+"/Main.do", new MainController() );						// 완료
		
	}
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("FrontContoller's Service uri : " + req.getRequestURI());
		
		
		SubController controller = map.get(req.getRequestURI());
		controller.execute(req,resp);

	}	
		
}

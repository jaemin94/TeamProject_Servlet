package Product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Service.ProductService;
import Domain.Common.Service.ProductServiceImpl;

/**
 * Servlet implementation class Prod_Select_Admin
 */

public class Prod_Select_Admin implements SubController {
	private ProductService service  = ProductServiceImpl.getInstance();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("Prod_Select_Admin Execute");
		
	}
       


}

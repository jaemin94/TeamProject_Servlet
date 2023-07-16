package ServerTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Test
 */
@WebServlet("/main")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // MySQL 데이터베이스에 연결
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/shoppingdb?useUnicode=true&characterEncoding=utf8"; // db_name은 실제 데이터베이스 이름으로 변경해야 함
            String username = "root"; // 실제 사용자 이름으로 변경해야 함
            String password = "1234"; // 실제 비밀번호로 변경해야 함
            conn = DriverManager.getConnection(url, username, password);

            // SQL 쿼리 실행
            stmt = conn.createStatement();
            String sql = "SELECT * FROM tbl_product"; // table_name은 실제 테이블 이름으로 변경해야 함
            rs = stmt.executeQuery(sql);

            // 결과 출력
            while (rs.next()) {
                int product_code = rs.getInt("product_code"); // column1은 테이블의 열 이름으로 변경해야 함
                String product_name = rs.getString("product_name"); // column2는 테이블의 열 이름으로 변경해야 함
                int amount = rs.getInt("amount");
                int prod_price = rs.getInt("prod_price");
                
                out.println("product_code: " + product_code + "<br>");
                out.println("product_name: " + product_name + "<br>");
                out.println("amount: " + amount + "<br>");
                out.println("prod_price: " + prod_price + "<br>");
                out.println("<br>");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 연결, 문장, 결과셋을 닫음
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

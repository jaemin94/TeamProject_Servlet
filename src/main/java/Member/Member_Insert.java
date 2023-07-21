package Member;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class Member_Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   String jdbcURL = "jdbc:mysql://localhost:3306/shoppingdb?useUnicode=true&characterEncoding=UTF-8";
       String dbUser = "root";
       String dbPassword = "1234";
       protected static Connection conn;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Member_Insert() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

     	response.setContentType("text/html; charset=UTF-8");
     	request.setCharacterEncoding("UTF-8");
     	response.setCharacterEncoding("UTF-8");
			
    	// 입력한 회원 정보 받기
        String name = request.getParameter("name");
        System.out.println(name);
        String adr_addr = request.getParameter("adr_addr");
        System.out.println(adr_addr);
        String member_id = request.getParameter("member_id");
        System.out.println(member_id);
        String pw = request.getParameter("pw");
        System.out.println(pw);
        
        // DB 연결 정보 설정
      
        try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			if(conn==null) {
				
				conn = DriverManager.getConnection(jdbcURL,dbUser,dbPassword); 
			}
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
        
        
        // 중복 체크
        if (!isUsernameDuplicate(member_id)) {
            // DB에 값 저장
            if (saveUser(name, adr_addr,member_id,pw)) {
            	String message = "회원가입이 완료되었습니다!";
                String script = "<script>alert('" + message + "'); window.close();window.location.href='./JSP/Login.jsp';</script>";
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().write(script);
                
            } else {
            	String message = "회원가입에 실패하였습니다!";
                String script = "<script>alert('" + message + "');window.close();window.location.href='./JSP/Register.jsp'</script>";
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().write(script);

            }
        } else {
        	String message = "중복된 사용자입니다!";
            String script = "<script>alert('" + message + "');window.close();window.location.href='./JSP/Register.jsp'</script>";
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write(script);
        }
    }
    
    // 사용자명 중복 체크
    private boolean isUsernameDuplicate(String member_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            // DB 연결
        	
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            
            // 사용자명 중복 체크 쿼리
            String query = "SELECT COUNT(*) FROM tbl_member WHERE member_id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, member_id);
            resultSet = statement.executeQuery();
            
            // 결과 확인
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // 중복되는 사용자명인 경우 true 반환
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 리소스 해제
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return false; // 예외 발생 시 false 반환
    }
    
    // 회원 정보 저장
    private boolean saveUser(String name, String adr_addr, String member_id, String pw) throws UnsupportedEncodingException {
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            // DB 연결
        	
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            
            // 회원 정보 저장 쿼리
            String query = "INSERT INTO tbl_member (member_id,pw,name,adr_addr) VALUES (?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, new String(member_id.getBytes("UTF-8"), "UTF-8"));
            statement.setString(2, new String(pw.getBytes("UTF-8"), "UTF-8"));
            statement.setString(3, new String(name.getBytes("UTF-8"), "UTF-8"));
            statement.setString(4, new String(adr_addr.getBytes("UTF-8"), "UTF-8"));

            
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0; // 저장 성공 시 true 반환
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("asdasd");
        } finally {
            // 리소스 해제
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return false; // 예외 발생 시 false 반환
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


}

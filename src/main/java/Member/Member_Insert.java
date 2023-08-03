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
import javax.servlet.http.HttpSession;

import Controller.SubController;
import Domain.Common.Service.MemberService;
import Domain.Common.Service.MemberServiceImpl;

/**
 * Servlet implementation class RegisterServlet
 */

public class Member_Insert implements SubController {
	private static final long serialVersionUID = 1L;

	private MemberService service = MemberServiceImpl.getInstance();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println(" Member_Insert Excute");
		//GET 요청 처리
				if(req.getMethod().equals("GET"))
				{
					try {
						req.getRequestDispatcher("/member/Register.jsp").forward(req, resp);
					
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return ;
				}
				
				//POST 요청 처리
				//1 파라미터 추출
		        String name = req.getParameter("name");
		        System.out.println(name);
		        String adr_addr = req.getParameter("adr_addr");
		        System.out.println(adr_addr);
		        String member_id = req.getParameter("member_id");
		        System.out.println(member_id);
		        String pw = req.getParameter("pw");
		        System.out.println(pw);
		
				try {
					//2 입력값 검증
					if (name.isEmpty()|| adr_addr.isEmpty() || member_id.isEmpty() || pw.isEmpty()) {
						System.out.println("[ERROR] Data Validation Check Error!");
						req.setAttribute("msg", "[ERROR] 공백이 있습니다.");
						req.getRequestDispatcher("/member/Register.jsp").forward(req, resp);
						return ;
					}
					//3 서비스 실행
					boolean isJoin=false;
					req.setAttribute("name", name);
					req.setAttribute("adr_addr", adr_addr);
					req.setAttribute("member_id", member_id);
					req.setAttribute("pw", pw);
				
					isJoin=service.memberJoin(req);

					
					//4 View로 전달 
					if(isJoin)
					{
						//main.do 이동 - Redirect
						resp.sendRedirect(req.getContextPath()+"/login.do");
					}
					else
					{
						//login.do 이동 - Forward
						req.getRequestDispatcher("/member/Register.jsp").forward(req, resp);
					}
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		
		
		/*
		 * if (!isUsernameDuplicate(member_id)) { // DB에 값 저장 if (saveUser(name,
		 * adr_addr,member_id,pw)) { String message = "회원가입이 완료되었습니다!"; String script =
		 * "<script>alert('" + message +
		 * "'); window.close();window.location.href='./JSP/Login.jsp';</script>";
		 * response.setContentType("text/html; charset=UTF-8");
		 * response.getWriter().write(script);
		 * 
		 * } else { String message = "회원가입에 실패하였습니다!"; String script = "<script>alert('"
		 * + message +
		 * "');window.close();window.location.href='./JSP/Register.jsp'</script>";
		 * response.setContentType("text/html; charset=UTF-8");
		 * response.getWriter().write(script);
		 * 
		 * } } else { String message = "중복된 사용자입니다!"; String script = "<script>alert('"
		 * + message +
		 * "');window.close();window.location.href='./JSP/Register.jsp'</script>";
		 * response.setContentType("text/html; charset=UTF-8");
		 * response.getWriter().write(script); }
		 */
		
	}




}

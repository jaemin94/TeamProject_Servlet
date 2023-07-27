package Member;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Domain.Common.Dao.MemberDao;
import Domain.Common.Dao.MemberDaoimpl;
import Domain.Common.Dto.MemberDto;
import Domain.Common.Service.MemberService;
import Domain.Common.Service.MemberServiceImpl;
import Domain.Common.Service.Auth.Session;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService;
	public Map<String,HttpSession> sessionMap;
       
	public void init() throws ServletException {
        memberService = MemberServiceImpl.getInstance();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 입력한 아이디와 비밀번호 추출
        String member_id = request.getParameter("member_id");
        String pw = request.getParameter("pw");
        MemberDao dao = new MemberDaoimpl();
        MemberDto dto =  dao.select(member_id);
        // DB에서 해당 사용자 조회
        
         
//		Map<String, Object> result = new HashMap();
//		try {
//			result = memberService.login(member_id, pw);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(result);
//		String sid = (String)result.get("sid");
//		String role = (String)result.get("role");
		
         String role = null;
		
        if (dao != null) {
            // 사용자가 존재하는 경우
            if (dto.getId().equals(member_id)) {
                // 비밀번호가 일치하는 경우, 로그인 성공 처리
                // 세션에 사용자 정보 및 로그인 상태 저장
                HttpSession session = request.getSession();
                session.setAttribute("member_id", member_id);
                session.setAttribute("role", role);
                session.setAttribute("loggedIn", true);
                response.sendRedirect("./JSP/Main.jsp?role=" + role);
                
                System.out.println("Session ID: " + session);
                System.out.println("Member ID: " + member_id);
                System.out.println("Role: " + role);
  
                
//                // 역할에 따른 권한 설정
//                if (member.getRole().equals("admin")) {
//                    // 관리자 역할인 경우, 관리자 페이지로 이동
//                    response.sendRedirect("./JSP/Main.jsp");
//                } else {
//                    // 일반 사용자 역할인 경우, 일반 사용자 페이지로 이동
//                    response.sendRedirect("./JSP/Main.jsp");
//                }
            } else {
                // 비밀번호가 일치하지 않는 경우, 로그인 실패 처리
                response.sendRedirect("./JSP/Login.jsp?error=invalid_password");
            }
        } else {
            // 사용자가 존재하지 않는 경우, 로그인 실패 처리
            response.sendRedirect("./JSP/Login.jsp?error=invalid_username");
        }

	}
}

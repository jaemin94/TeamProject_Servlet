package Member.auth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.SubController;
import Domain.Common.Service.MemberService;
import Domain.Common.Service.MemberServiceImpl;

public class LogoutController implements SubController{

	private MemberService service = MemberServiceImpl.getInstance();
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
        session.invalidate(); // 세션을 무효화하여 로그아웃 처리

        try {
            // main.do 이동 - Redirect
            resp.sendRedirect(req.getContextPath() + "/Main.do");
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

}

package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebFilter("/*") // 모든 요청에 대해 필터 적용
public class SessionFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {
        // 필터 초기화 코드 (필요하면 사용)
    }

    public void destroy() {
        // 필터 종료 코드 (필요하면 사용)
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // HttpServletRequest와 HttpServletResponse로 형변환
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 세션 가져오기 (세션이 없으면 새로 생성)
        HttpSession session = httpRequest.getSession(true);

        // 세션에서 로그인 여부 확인
        boolean loggedIn = (session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn"));

        // 로그인 상태가 아닌 경우 로그인 페이지로 리다이렉트
//        if (!loggedIn && !httpRequest.getRequestURI().endsWith("/Login")) {
//            httpResponse.sendRedirect(httpRequest.getContextPath() + "/JSP/Login.jsp");
//            return;
//        }

        // 모든 페이지에 세션 값 적용
        request.setAttribute("member_id", session.getAttribute("member_id"));
        request.setAttribute("role", session.getAttribute("role"));

        chain.doFilter(request, response); // 다음 필터 또는 서블릿 호출
    }
}




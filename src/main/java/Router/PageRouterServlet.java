package Router;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PageRouterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 요청 URL을 가져옵니다.
        String requestUrl = request.getRequestURI();
        
        // 고정된 부분을 제거하여 실제 페이지 경로를 가져옵니다.
        String pagePath = requestUrl.replace(request.getContextPath() + "/TeamProject2", "");
        
        // 실제 페이지로 리다이렉트합니다.
        response.sendRedirect(request.getContextPath() + "/TeamProject2" + pagePath);
    }
}

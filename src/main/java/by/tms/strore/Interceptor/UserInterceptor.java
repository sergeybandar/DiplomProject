package by.tms.strore.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri=request.getRequestURI();

        System.out.println(uri);
        if ((boolean) request.getSession().getAttribute("isUser") == true) {
            return true;
        } else {
            response.sendRedirect("/");
            return true;
        }
    }
}

package filter;

import com.codegym.model.SignupAccount;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURI();
        if (url.startsWith("/admin")) {
            SignupAccount model = (SignupAccount) SessionUtil.getInstance().getValue(request, "USERMODEL");
            if (model != null) {
                if (model.getUsername().equals(SystemConstant.ADMIN)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else if (model.getUsername().equals(SystemConstant.USER)) {
                    response.sendRedirect(request.getContextPath()+"/login?action=signin&message=not_permission&alert=danger");
                }
            } else {
                response.sendRedirect(request.getContextPath()+"/login?action=signin&message=not_login&alert=danger");
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}

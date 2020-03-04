package io.music.playlist.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static io.music.playlist.web.util.UrlConstants.DOCUMENTATION_URL;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */

public class UrlFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getRequestURI().equals("") || request.getRequestURI().equals("/")){
            response.sendRedirect(DOCUMENTATION_URL);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}

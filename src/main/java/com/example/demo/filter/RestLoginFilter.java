package com.example.demo.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/rest/*"} )
public class RestLoginFilter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false); // 不強制創建

        boolean isLoggedIn = (session != null && session.getAttribute("userCert") != null);

        if (!isLoggedIn) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().write("{\"error\": \"尚未登入，請重新登入\"}");
            return;
        }

        // 已登入，放行
        chain.doFilter(request, response);
    }
}

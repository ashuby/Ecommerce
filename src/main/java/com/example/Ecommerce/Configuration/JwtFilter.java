package com.example.Ecommerce.Configuration;

import com.example.Ecommerce.Services.TokenService;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Configuration
public class JwtFilter extends GenericFilterBean {
    private TokenService tokenService ;
    public JwtFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req ;
        HttpServletResponse httpServletResponse = (HttpServletResponse) res;

        String token = httpServletRequest.getHeader("Authorization");
        if("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())) {
            httpServletResponse.sendError(HttpServletResponse.SC_OK,"Success");
            return ;
        }

        if(allowRequestWithoutToken(httpServletRequest)) {
            httpServletResponse.sendError(HttpServletResponse.SC_OK,"Success");
        }
        else{
            Integer id = new Integer(tokenService.getUserIdToken(token));
            httpServletRequest.setAttribute("userId", "id");
            filterChain.doFilter(req , res);
        }
    }

    public boolean allowRequestWithoutToken(HttpServletRequest httpServletRequest) {
        System.out.println(httpServletRequest.getRequestURI());
        if(httpServletRequest.getRequestURI().contains("/user")) return true;
        return false;
    }
}

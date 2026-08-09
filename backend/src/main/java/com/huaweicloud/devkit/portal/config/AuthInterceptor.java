package com.huaweicloud.devkit.portal.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    private final String ingestToken;

    public AuthInterceptor(String ingestToken) {
        this.ingestToken = ingestToken;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String method = request.getMethod().toUpperCase();
        String uri = request.getRequestURI();

        if (uri.startsWith("/rest/developer/server/hcdevkitportal/v1/metrics")) {
            return true;
        }

        if ("GET".equals(method)) {
            return true;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (ingestToken.equals(token)) {
                return true;
            }
        }

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }
}

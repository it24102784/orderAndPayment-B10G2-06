package com.orderandpayment.orderandpayment.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            // If the user has the ROLE_ADMIN, redirect to the admin dashboard
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                response.sendRedirect("/admin/orders");
                return;
            }
        }

        // If the user is not an admin, redirect to the products page
        response.sendRedirect("/products");
    }
}
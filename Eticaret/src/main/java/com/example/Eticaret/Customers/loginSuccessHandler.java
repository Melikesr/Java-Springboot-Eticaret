package com.example.Eticaret.Customers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class loginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
       CustomerDetails userDetails= (CustomerDetails) authentication.getPrincipal();
        System.out.println("Username"+userDetails.getUsername());
        super.onAuthenticationSuccess(request, response, authentication);

        Collection<? extends GrantedAuthority> authorities=userDetails.getAuthorities();
        authorities.forEach(auth -> System.out.println(auth. getAuthority()));

    }
}

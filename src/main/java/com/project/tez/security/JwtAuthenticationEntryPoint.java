package com.project.tez.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

	//unathorized bir istek gelirse bu sınıf çalışıcak... 
		// burada bir request geliyor ve eğer bir exception varsa, response olarak unauthorized dönücek
		
		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException authException) throws IOException, ServletException {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
			
		}
}

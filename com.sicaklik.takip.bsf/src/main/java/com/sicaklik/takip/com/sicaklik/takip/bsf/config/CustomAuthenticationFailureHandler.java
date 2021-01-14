package com.sicaklik.takip.com.sicaklik.takip.bsf.config;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Controller;
@Controller
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler  {

	public CustomAuthenticationFailureHandler() {
		super();
	}


	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		Map<String, Object> data = new HashMap<>();
		data.put("timestamp", Calendar.getInstance().getTime());
		data.put("exception", exception.getMessage());
		System.out.println("exception = ");
		System.out.println(exception.getMessage());
		if(exception.getMessage().equals("gecersiz kullanici")) {
			getRedirectStrategy().sendRedirect(request, response, "/login.xhtml?gecersizKulError=true");
		}
		else if(exception.getMessage().equals("alanlari doldurunuz")) {
			getRedirectStrategy().sendRedirect(request, response, "/login.xhmtl?bosAlanError=true");
		}
		else {
			getRedirectStrategy().sendRedirect(request, response, "/login.xhtml?error=true");
		}
	}
	
}

package com.sicaklik.takip.com.sicaklik.takip.bsf.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.sicaklik.takip.com.sicaklik.takip.bsf.serviceImpl.KullaniciServiceImp;
import com.sicaklik.takip.com.sicaklik.takip.bsf.util.Util;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    
   Set<String> whitelist;

   @Autowired
   private PasswordEncoder passwordEncoder;
   
   @Autowired
   private KullaniciServiceImp kullaniciService;
   
   @Autowired
   private HttpServletRequest request;
   
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String userIp = details.getRemoteAddress();
        
        System.out.println("----------------userIp:"+userIp);
        System.out.println("custom ip auth -> authenticate fonksiyonu içerisinde");
        
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        String username = String.valueOf(auth.getPrincipal());
        String password = String.valueOf(auth.getCredentials());
        System.out.println("username = " + username);
        System.out.println("password = " + password);
//        String responseCap2 = request.getParameter("j_bilgi");
        
        
        if(Util.empty(username.trim()) || Util.empty(password.trim())) {
        	throw new BadCredentialsException("alanlari doldurunuz");
        }
        
        System.out.println("url = " + request.getRequestURI());
        
        
//        Optional<Kullanici> user = kullaniciRepository.findByUsernamePassword(username, password);
//        if(user == null) {
//        	throw new BadCredentialsException("gecersiz kullanici");
//        }
        
        List<GrantedAuthority> authorities =new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}


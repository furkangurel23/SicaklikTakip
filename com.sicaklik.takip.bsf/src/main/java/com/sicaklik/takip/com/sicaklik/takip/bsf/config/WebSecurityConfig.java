package com.sicaklik.takip.com.sicaklik.takip.bsf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.header.writers.StaticHeadersWriter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableScheduling
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomAuthenticationProvider authenticationProvider;
	
//	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//	    auth.inMemoryAuthentication()
//	        .withUser("user1").password("user1Pass").roles("USER");
//	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> WebSecurityConfig.configure(AuthenticationManagerBuilder auth) icindeyiz");
    	auth.authenticationProvider(authenticationProvider);
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> WebSecurityConfig.configure(HttpSecurity http) icindeyiz");

        SecurityContextHolder.clearContext();

        http.csrf().disable().headers().disable();
        //http.headers().disable();

        http.authorizeRequests()
        		.antMatchers("/login.xhtml").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.xhtml")//.permitAll()
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .defaultSuccessUrl("/index.xhtml", true)
                .failureHandler(authenticationFailureHandler())
//                .failureHandler(authenticationFailureHandler("/login?error=true"))
//                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login.xhtml")
                .invalidateHttpSession(true)
                .permitAll();
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}
	
	@Override
    public void configure(WebSecurity webSecurity) throws Exception {
    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> WebSecurityConfig.configure(WebSecurity webSecurity) icindeyiz");
    	System.out.println("------------------------------------1111");
        webSecurity.ignoring().antMatchers("/javax.faces.resource/**").antMatchers("/resources/**").antMatchers("/services/**").antMatchers("/applet/**");
    }
    
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> WebSecurityConfig.customAuthenticationManager icindeyiz");
    	System.out.println("------------------------------------2222");
        return authenticationManager();
    }
	
	@Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
    	return new CustomAuthenticationFailureHandler();
    }
}

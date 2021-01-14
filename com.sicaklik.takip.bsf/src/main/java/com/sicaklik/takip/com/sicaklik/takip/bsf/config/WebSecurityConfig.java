package com.sicaklik.takip.com.sicaklik.takip.bsf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomAuthenticationProvider authenticationProvider;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> WebSecurityConfig.configure(AuthenticationManagerBuilder auth) icindeyiz");
    	auth.authenticationProvider(authenticationProvider);
    }

	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**").anyRequest();
    }
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		
		http
	      .csrf().disable()
	      .authorizeRequests()
	      .antMatchers("/login.xhtml").permitAll()
  		  .antMatchers("/yetkisiz.xhmtl").permitAll()
  		  .antMatchers(
  				  "/js/**",
  				  "/css/**",
  				  "/webjars/**"
  				  ).permitAll()
  		  .antMatchers("/resources/**").permitAll()
          .anyRequest().authenticated()
          .and()
          .formLogin()
          .loginPage("/login.xhtml")//.permitAll()
          .usernameParameter("j_username")
          .passwordParameter("j_password")
          .defaultSuccessUrl("/cihazListesi.xhtml", true)
	      .failureUrl("/login.xhtml?error=true")
	      .failureHandler(authenticationFailureHandler())
	      .and()
	      .logout()
	      .invalidateHttpSession(true)
          .deleteCookies("JSESSIONID")
          .permitAll();
//	      .logoutUrl("/perform_logout")
//	      .deleteCookies("JSESSIONID")
//	      .logoutSuccessHandler(logoutSuccessHandler());
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}
	
	@Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
    	return new CustomAuthenticationFailureHandler();
    }
}

package com.sicaklik.takip.com.sicaklik.takip.bsf;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import com.sun.faces.config.ConfigureListener;

@Configuration
public class JsfConfig extends SpringBootServletInitializer implements ServletContextAware {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		
		System.out.println("girdi mi;");
		
		servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", "true");
		servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
		
		servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
		// primefaces icon set için
		servletContext.setInitParameter("primefaces.FONT_AWESOME", Boolean.TRUE.toString());
		
		//JSF ortamının sayfalardaki hata ayıklama bilgilerini yazdırmasını sağlar.
		servletContext.setInitParameter("primefaces.PROJECT_STAGE", "Development");
		
		//Javax.faces.STATE_SAVING_METHOD parametresini "server" (varsayılan değer) olarak ayarlamak, bu parametreyi "client" olarak 
		//ayarlamaktan daha iyi performans sağlar. Bunun nedeni, sunucu durumunun kaydedilmesi durumun serileştirilmesini gerektirmemesidir.
		servletContext.setInitParameter("primefaces.STATE_SAVING_METHOD", "server");
		
		//Facelets derleyicisinin sayfalardaki değişiklikleri kontrol edene kadar beklemesi gereken süreyi belirtir.
		servletContext.setInitParameter("primefaces.FACELETS_REFRESH_PERIOD", "1");
		
		servletContext.setInitParameter("primefaces.FACELETS_BUFFER_SIZE", "500000");
		servletContext.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");
		
		servletContext.setInitParameter("javax.faces.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE", Boolean.TRUE.toString());
		
		servletContext.setInitParameter("primefaces.CSP", "true");
		servletContext.setInitParameter("primefaces.CSP_POLICY", "script-src 'self' https: *.googleapis.com");
		
	}

	@Bean
	public ServletRegistrationBean facesServlet() {
		FacesServlet servlet = new FacesServlet();
		ServletRegistrationBean registration = new ServletRegistrationBean(servlet, "*.xhtml");
		registration.setName("FacesServlet");
		registration.setLoadOnStartup(1);
		return registration;
	}

	@Bean
	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
		return new ServletListenerRegistrationBean<ConfigureListener>(new ConfigureListener());
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
	}
}

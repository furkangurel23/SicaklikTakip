package com.sicaklik.takip.com.sicaklik.takip.bsf;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;

import com.sicaklik.takip.com.sicaklik.takip.bsf.scpoe.ScopeName;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@Scope(ScopeName.VIEW)
//@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public @interface SicaklikJsfController {

	@AliasFor(annotation = Controller.class)
	String value() default "";
}

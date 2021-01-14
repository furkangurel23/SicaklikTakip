package com.sicaklik.takip.com.sicaklik.takip.bsf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.sicaklik.takip.com.sicaklik.takip.bsf.scpoe.SessionReplicationAwareScopeMetadataResolver;

@SpringBootApplication
@ComponentScan(scopeResolver = SessionReplicationAwareScopeMetadataResolver.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

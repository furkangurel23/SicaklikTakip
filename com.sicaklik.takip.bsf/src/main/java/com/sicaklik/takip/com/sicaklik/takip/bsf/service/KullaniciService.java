package com.sicaklik.takip.com.sicaklik.takip.bsf.service;


import com.sicaklik.takip.com.sicaklik.takip.bsf.model.Kullanici;

public interface KullaniciService {
	
	Kullanici findByUsername(String username);
	Kullanici findByUsernamePassword(String username, String password);
}

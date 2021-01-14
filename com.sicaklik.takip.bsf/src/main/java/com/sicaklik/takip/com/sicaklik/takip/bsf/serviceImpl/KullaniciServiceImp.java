package com.sicaklik.takip.com.sicaklik.takip.bsf.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicaklik.takip.com.sicaklik.takip.bsf.model.Kullanici;
import com.sicaklik.takip.com.sicaklik.takip.bsf.repository.KullaniciRepository;
import com.sicaklik.takip.com.sicaklik.takip.bsf.service.KullaniciService;

@Service
public class KullaniciServiceImp implements KullaniciService {
	
	private KullaniciRepository kullaniciRepository;

	@Autowired
	public KullaniciServiceImp(KullaniciRepository kullaniciRepository) {
		this.kullaniciRepository = kullaniciRepository;
	}

	@Override
	public Kullanici findByUsername(String username) {
		return kullaniciRepository.findByUsername(username);
	}

	@Override
	public Kullanici findByUsernamePassword(String username, String password) {
		return kullaniciRepository.findByUsernamePassword(username, password);
	}

}

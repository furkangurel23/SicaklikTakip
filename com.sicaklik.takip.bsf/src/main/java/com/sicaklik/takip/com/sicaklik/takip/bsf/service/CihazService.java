package com.sicaklik.takip.com.sicaklik.takip.bsf.service;


import java.util.Date;
import java.util.List;

import com.sicaklik.takip.com.sicaklik.takip.bsf.model.Cihaz;

public interface CihazService {
	
	Cihaz getir(Long id);
	
	List<Cihaz> findByCompany(String bagli_sirket);
	
	List<Cihaz> findByDateRegistration(Date kayit_tarihi);
	
	List<Cihaz> findByCompanyDateRegistration(String bagli_sirket, Date kayit_tarihi);
	
	void kaydet(Cihaz cihaz);
	
	void saveAll(List<Cihaz> cihazList);
	
	void sil(Cihaz cihaz);
	
	void deleteAll(List<Cihaz> cihaz);
	
	List<Cihaz> getAll();
}

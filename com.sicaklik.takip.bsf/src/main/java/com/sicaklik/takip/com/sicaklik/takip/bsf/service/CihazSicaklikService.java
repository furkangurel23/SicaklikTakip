package com.sicaklik.takip.com.sicaklik.takip.bsf.service;


import java.util.List;

import com.sicaklik.takip.com.sicaklik.takip.bsf.model.CihazSicaklik;

public interface CihazSicaklikService {
	
	CihazSicaklik getir(Long id);
	
	List<CihazSicaklik> findByCihazId(long cihaz_id);
	
	void kaydet(CihazSicaklik cihazSicaklik);
	
	void saveAll(List<CihazSicaklik> cihazSicaklik);
	
	void sil(CihazSicaklik cihazSicaklik);
	
	void deleteAll(List<CihazSicaklik> cihazSicaklik);
	
	CihazSicaklik sonSicaklikGetir(long cihaz_id);
}

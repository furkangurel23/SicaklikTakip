package com.sicaklik.takip.com.sicaklik.takip.bsf.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicaklik.takip.com.sicaklik.takip.bsf.model.CihazSicaklik;
import com.sicaklik.takip.com.sicaklik.takip.bsf.repository.CihazSicaklikRepository;
import com.sicaklik.takip.com.sicaklik.takip.bsf.service.CihazSicaklikService;

@Service
public class CihazSicaklikServiceImp implements CihazSicaklikService {
	
	private CihazSicaklikRepository cihazSicaklikRepository;

	@Autowired
	public CihazSicaklikServiceImp(CihazSicaklikRepository cihazSicaklikRepository) {
		this.cihazSicaklikRepository = cihazSicaklikRepository;
	}

	@Override
	public CihazSicaklik getir(Long id) {
		return cihazSicaklikRepository.getir(id);
	}


	@Override
	public void kaydet(CihazSicaklik cihazSicaklik) {
		cihazSicaklikRepository.save(cihazSicaklik);
	}

	@Override
	public void saveAll(List<CihazSicaklik> cihazSicaklikList) {
		cihazSicaklikRepository.saveAll(cihazSicaklikList);
	}

	@Override
	public void sil(CihazSicaklik cihazSicaklik) {
		cihazSicaklikRepository.delete(cihazSicaklik);
	}

	@Override
	public void deleteAll(List<CihazSicaklik> cihazSicaklik) {
		cihazSicaklikRepository.deleteAll(cihazSicaklik);
	}

	@Override
	public List<CihazSicaklik> findByCihazId(long cihaz_id) {
		return cihazSicaklikRepository.findByCihazId(cihaz_id);
	}

	@Override
	public CihazSicaklik sonSicaklikGetir(long cihaz_id) {
		return cihazSicaklikRepository.sonSicaklikGetir(cihaz_id);
	}
	

}

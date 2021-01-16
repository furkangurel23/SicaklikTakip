package com.sicaklik.takip.com.sicaklik.takip.bsf.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicaklik.takip.com.sicaklik.takip.bsf.model.Cihaz;
import com.sicaklik.takip.com.sicaklik.takip.bsf.repository.CihazRepository;
import com.sicaklik.takip.com.sicaklik.takip.bsf.service.CihazService;

@Service
public class CihazServiceImp implements CihazService {
	
	private CihazRepository cihazRepository;

	@Autowired
	public CihazServiceImp(CihazRepository cihazRepository) {
		this.cihazRepository = cihazRepository;
	}

	@Override
	public Cihaz getir(Long id) {
		return cihazRepository.getir(id);
	}

	@Override
	public List<Cihaz> findByCompany(String bagli_sirket) {
		return cihazRepository.findByCompany(bagli_sirket);
	}

	@Override
	public List<Cihaz> findByDateRegistration(Date kayit_tarihi) {
		return cihazRepository.findByDateRegistration(kayit_tarihi);
	}

	@Override
	public List<Cihaz> findByCompanyDateRegistration(String bagli_sirket, Date kayit_tarihi) {
		return cihazRepository.findByCompanyDateRegistration(bagli_sirket, kayit_tarihi);
	}

	@Override
	public void kaydet(Cihaz cihaz) {
		cihazRepository.save(cihaz);
	}

	@Override
	public void saveAll(List<Cihaz> cihazList) {
		cihazRepository.saveAll(cihazList);
	}

	@Override
	public void sil(Cihaz cihaz) {
		cihazRepository.delete(cihaz);
	}

	@Override
	public void deleteAll(List<Cihaz> cihaz) {
		cihazRepository.deleteAll(cihaz);
	}

	@Override
	public List<Cihaz> getAll() {
		return cihazRepository.findAll();
	}
	
	

}

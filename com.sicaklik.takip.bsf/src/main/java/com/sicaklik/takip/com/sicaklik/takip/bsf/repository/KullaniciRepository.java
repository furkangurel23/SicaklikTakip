package com.sicaklik.takip.com.sicaklik.takip.bsf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sicaklik.takip.com.sicaklik.takip.bsf.model.Kullanici;

@Repository
public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {

	@Query(value = "SELECT * FROM user_table u WHERE u.id = ?1", nativeQuery = true)
	Kullanici getir(Long id);
	
	@Query(value = "SELECT * FROM user_table u WHERE u.kullanici_adi = ?1 and u.parola = ?2", nativeQuery = true)
	Kullanici findByUsernamePassword(String kullanici_adi, String parola);
	
	@Query(value = "SELECT * FROM user_table u WHERE u.kullanici_adi = ?1", nativeQuery = true)
	Kullanici findByUsername(String kullanici_adi);
}

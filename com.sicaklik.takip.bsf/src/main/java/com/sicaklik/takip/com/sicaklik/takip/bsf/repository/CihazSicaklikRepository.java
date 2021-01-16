package com.sicaklik.takip.com.sicaklik.takip.bsf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sicaklik.takip.com.sicaklik.takip.bsf.model.CihazSicaklik;

@Repository
public interface CihazSicaklikRepository extends JpaRepository<CihazSicaklik, Long>{

	@Query(value = "SELECT * FROM cihaz_sicaklik c WHERE c.id = ?1", nativeQuery = true)
	CihazSicaklik getir(Long id);
	
	@Query(value = "SELECT * FROM cihaz_sicaklik c WHERE c.cihaz_id=:cihaz_id order by c.olusturma_tarih_saati", nativeQuery = true)
	List<CihazSicaklik> findByCihazId(@Param("cihaz_id") long cihaz_id);
	
	@Query(value = "SELECT * FROM cihaz_sicaklik c WHERE c.cihaz_id=:cihaz_id order by c.id desc limit 1", nativeQuery = true)
	CihazSicaklik sonSicaklikGetir(@Param("cihaz_id") long cihaz_id);
	
//	@Query(value = "SELECT * FROM cihaz_sicaklik c WHERE c.kayit_tarihi = :kayit_tarihi", nativeQuery = true)
//	List<Cihaz> findByDateRegistration(@Param("kayit_tarihi") Date kayit_tarihi);
//	
//	@Query(value = "SELECT * FROM cihaz_sicaklik c WHERE c.bagli_sirket LIKE '%:bagli_sirket%' and c.kayit_tarihi = :kayit_tarihi order by c.id", nativeQuery = true)
//	List<Cihaz> findByCompanyDateRegistration(@Param("bagli_sirket") String bagli_sirket, @Param("kayit_tarihi") Date kayit_tarihi);
	
	
}

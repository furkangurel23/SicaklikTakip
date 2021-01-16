package com.sicaklik.takip.com.sicaklik.takip.bsf.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sicaklik.takip.com.sicaklik.takip.bsf.model.Cihaz;

@Repository
public interface CihazRepository extends JpaRepository<Cihaz, Long>{

	@Query(value = "SELECT * FROM cihaz c WHERE c.id = ?1", nativeQuery = true)
	Cihaz getir(Long id);
	
	@Query(value = "SELECT * FROM cihaz c WHERE c.bagli_sirket= :bagli_sirket order by c.id", nativeQuery = true)
	List<Cihaz> findByCompany(@Param("bagli_sirket") String bagli_sirket);
	
	@Query(value = "SELECT * FROM cihaz c WHERE c.kayit_tarihi = :kayit_tarihi", nativeQuery = true)
	List<Cihaz> findByDateRegistration(@Param("kayit_tarihi") Date kayit_tarihi);
	
	@Query(value = "SELECT * FROM cihaz c WHERE c.bagli_sirket LIKE '%:bagli_sirket%' and c.kayit_tarihi = :kayit_tarihi order by c.id", nativeQuery = true)
	List<Cihaz> findByCompanyDateRegistration(@Param("bagli_sirket") String bagli_sirket, @Param("kayit_tarihi") Date kayit_tarihi);
	
//	@Override
//	default <S extends Cihaz> S save(S entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	@Override
//	default <S extends Cihaz> List<S> saveAll(Iterable<S> entities) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
}

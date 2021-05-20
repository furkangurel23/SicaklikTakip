package com.sicaklik.takip.com.sicaklik.takip.bsf.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="CihazSicaklik")
@Table(name="cihaz_sicaklik")
public class CihazSicaklik implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1588352320712953162L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private Long id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cihaz_id", nullable = false)
	private Cihaz cihaz_id;
	@Column(name="olusturma_tarih_saati", nullable = false)
	private Date olusturma_tarih_saati;
	@Column(name="sicaklik_hava", nullable = false)
	private double sicaklik_hava;
	@Column(name="sicaklik_beton", nullable = false)
	private double sicaklik_beton;
	@Column(name="bagil_nem", nullable = false)
	private double bagil_nem;
	
	public CihazSicaklik() {
		
	}
	
	public CihazSicaklik(Cihaz cihaz_id, double sicaklik_hava, double sicaklik_beton, double bagil_nem) {
		super();
		this.cihaz_id = cihaz_id;
		this.sicaklik_hava = sicaklik_hava;
		this.sicaklik_beton = sicaklik_beton;
		this.bagil_nem = bagil_nem;
	}
	
}
